package ao.adnlogico.nuntius.multitenant.tenant.auth;

import ao.adnlogico.nuntius.multitenant.constant.UserStatus;
import ao.adnlogico.nuntius.multitenant.master.config.DBContextHolder;
import ao.adnlogico.nuntius.multitenant.master.MasterTenant;
import ao.adnlogico.nuntius.multitenant.master.MasterTenantService;
import ao.adnlogico.nuntius.multitenant.security.UserTenantInformation;
import ao.adnlogico.nuntius.multitenant.dto.AuthResponse;
import ao.adnlogico.nuntius.multitenant.dto.UserLoginDTO;
import ao.adnlogico.nuntius.multitenant.dto.UserPasswordUpdater;
import ao.adnlogico.nuntius.multitenant.exception.EntityNotFoundException;
import ao.adnlogico.nuntius.multitenant.security.CustomPasswordEncoder;
import ao.adnlogico.nuntius.multitenant.tenant.user.User;
import ao.adnlogico.nuntius.multitenant.tenant.user.UserModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.user.UserRepository;
import ao.adnlogico.nuntius.multitenant.util.ApiError;
import ao.adnlogico.nuntius.multitenant.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author domingos.fernando
 */
@RestController
@RequestMapping("/nuntius/v1/api")
public class AuthenticationController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private Map<String, String> mapValue = new HashMap<>();
    private Map<String, String> userDbMap = new HashMap<>();

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserModelAssembler assembler;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    MasterTenantService masterTenantService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(@RequestBody @NotNull UserLoginDTO userLoginDTO) throws AuthenticationException
    {
        LOGGER.info("userLogin() method call...");
        String tenantId = userLoginDTO.getUserName().split("@")[1];
        LOGGER.info("userLogin() method call... Print tenant ID  = " + tenantId);
        userLoginDTO.setTenantOrClientId(tenantId);
        if (null == userLoginDTO.getUserName() || userLoginDTO.getUserName().isEmpty()) {
            return new ResponseEntity<>("User name is required", HttpStatus.BAD_REQUEST);
        }
        //set database parameter
        MasterTenant masterTenant = masterTenantService.findByClientId(userLoginDTO.getTenantOrClientId());
        if (null == masterTenant || masterTenant.getStatus().toUpperCase().equals(UserStatus.INACTIVE)) {
            throw new RuntimeException("Please contact service provider.");
        }
        //Entry Client Wise value dbName store into bean.
        loadCurrentDatabaseInstance(masterTenant.getDbName(), userLoginDTO.getUserName());

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName(), userLoginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername(), String.valueOf(userLoginDTO.getTenantOrClientId()));
        //Map the value into applicationScope bean
        setMetaDataAfterLogin();
        return ResponseEntity.ok(new AuthResponse(userDetails.getUsername(), token));
    }

    private void loadCurrentDatabaseInstance(String databaseName, String userName)
    {
        DBContextHolder.setCurrentDb(databaseName);
        mapValue.put(userName, databaseName);
    }

    @Bean(name = "userTenantInfo")
    @ApplicationScope
    public UserTenantInformation setMetaDataAfterLogin()
    {
        UserTenantInformation tenantInformation = new UserTenantInformation();
        if (mapValue.size() > 0) {
            mapValue.keySet().forEach(key -> {
                if (null == userDbMap.get(key)) {
                    //Here Assign putAll due to all time one come.
                    userDbMap.putAll(mapValue);
                }
                else {
                    userDbMap.put(key, mapValue.get(key));
                }
            });
            mapValue = new HashMap<>();
        }
        tenantInformation.setMap(userDbMap);
        return tenantInformation;
    }

    private User loadCurrentLogedUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails local = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(local.getUsername());
        return user;
    }

    /**
     * Methods to make umpdates on my profile
     *
     * @return
     */
    @GetMapping("/auth/currentProfile")
    public EntityModel<User> loadCurrentUserAuth()
    {
        User user = loadCurrentLogedUser();
        return assembler.toModel(user);
    }

    @PutMapping("/auth/updateProfile")
    public ResponseEntity<?> update(@RequestBody User newUser)
    {
        User updatedUser = userRepository.findById(loadCurrentLogedUser().getId()) //
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setPhone(newUser.getPhone());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }) //
                .orElseGet(() -> {
                    newUser.setId(loadCurrentLogedUser().getId());
                    return userRepository.save(newUser);
                });

        EntityModel<User> entityModel = assembler.toModel(updatedUser);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/auth/updateMyPassword")
    public ResponseEntity<?> updateMyPassword(@RequestBody UserPasswordUpdater newUserPasswd)
    {
        User user = userRepository.findById(loadCurrentLogedUser().getId()) //
                .orElseThrow(() -> new EntityNotFoundException(new User(), loadCurrentLogedUser().getId()));

        ApiError apiError;
        if (new CustomPasswordEncoder().getPasswordEncoder().matches(newUserPasswd.getOldPassword(), user.getPassword())) {
            user.setPassword(new CustomPasswordEncoder().getPasswordEncoder().encode(newUserPasswd.getNewPassword()));
            userRepository.save(user);
            apiError = new ApiError(HttpStatus.OK, "A sua palavra passe foi atualizada com sucesso de hoje em diante passará a usar a nova palavra passe", "Operação efetuada com sucesso");
        }
        else {
            apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Erro ao tentar atualizar a password, confirme sua password anterior.", "Passwords diferentes.");
        }
        return ResponseEntity.ok(apiError);
    }
}
