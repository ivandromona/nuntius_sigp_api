/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.controller;

import ao.adnlogico.nuntius.multitenant.dto.AuthResponse;
import ao.adnlogico.nuntius.multitenant.security.RequestAuthorization;
import ao.adnlogico.nuntius.multitenant.tenant.entity.User;
import ao.adnlogico.nuntius.multitenant.tenant.model_assembler.UserModelAssembler;
import ao.adnlogico.nuntius.multitenant.tenant.repository.UserRepository;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author domingos.fernando
 */
@RestController
@RequestMapping("/nuntius/v1/api")
public class UserController implements Serializable
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

//    @Autowired
//    roleTypeService service;
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    @Autowired
    ApplicationContext applicationContext;

    public UserController(UserRepository repository, UserModelAssembler assembler)
    {

        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestAuthorization
    @RequestMapping(value = "/current/{userName}", method = RequestMethod.POST)
    public ResponseEntity<?> userInfo(@PathVariable String userName) throws AuthenticationException
    {
        User current = repository.findByEmail(userName);
        AuthResponse response = new AuthResponse(current.getId(), current.getEmail(), "");
        return ResponseEntity.ok(response);
    }

}
