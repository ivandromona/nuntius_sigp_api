/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.util;

import java.time.Year;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author domingos.fernando
 */
public class Constants
{

    public static final String SECRET_KEY = "sDt+gt45Cta/oEsrDS/KWyOhIL680kbRRQLfmKdFY4EpCbYp1kierUa8kgYQMcXz6rqFtavv9kKMqMtBFsmoBg==";
    public static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile("^[a-z0-9!#$%&'*+\\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$", Pattern.CASE_INSENSITIVE);

    public static final String PREFIX_CANDIDATE_CODE = "P" + Year.now().getValue() % 1000;
    public static final String MIDDLE_CANDIDATE_SELF_REGISTER_CODE = "C";
    public static final String MIDDLE_CANDIDATE_ADMIN_REGISTER_CODE = "A";
    public static final Integer SUFIX_CANDIDATE_CODE = 1000000000;

    public static final String MIDDLE_EXTERN_CANDIDATE_CODE = "E";

    public static final String SMS_API_KEY = "INS1395983150";
    public static final String SMS_DEFAULT_FROM = "INEFOP";
    public static final String URL_SEND_IND_SMS = "https://cupplus.co.ao/smsapp/apis/smscontact/";

    public static final String SMS_PAPE_GROUP = "PAPE";
    public static final String URL_ADD_CONTACT_TO_SMS = "https://cupplus.co.ao/smsapp/apis/addcontact/";

    public static final String DEFAULT_CANDIDATE_WELCOME_SMS = "A sua candidatura foi submetida com sucesso. \n"
                                                               + "Esperamos que tenha êxito no seu negócio!. Seu número de candidatura é o seguinte: ";
    public static final String DEFAULT_REGISTER_WELCOME_SMS = "Bem-vindo ao PAPE. O seu registo foi efetuado com sucesso. Pode proceder com a sua candidatura.";

    public static final String CANDIDATE_ERROR_TITLE = "Erro ao efetuar candidatura, duplicação de candidatura";
    public static final String CANDIDATE_ERROR_MSG = "A sua candidatura não pode ser processada, pois já foi submetida uma candidatura com o seu perfil. "
                                                     + "Caso queira alterar os dados da sua candidatura inicie sessão e edite a sua candidatura.";

    public static final String USER_NOT_FOUND_ERROR_TITLE = "Erro ao efetuar candidatura. O Usuário não encontrado";
    public static final String USER_NOT_FOUND_ERROR_MSG = "A sua candidatura não pode ser processada, pois o Usuário que pretende associar a esta"
                                                          + " candidatura não foi encontrado, certifique-se de associar a candidatura a um usuário registrado";

    public static final String USER_STORE_ERROR_TITLE = "Erro ao inseriri usuário";
    public static final String USER_STORE_ERROR_MSG = "A sua candidatura não pode ser processada, pois o Usuário que pretende associar a esta"
                                                      + " candidatura não foi gravado com sucesso, certifique-se de associar a candidatura a um usuário válido";

//    public static final String DEFAULT_EMAIL_FROM = "contacto@seedours.com";
    public static final String DEFAULT_EMAIL_FROM = "pape.reporte@maptss.gov.ao";

    public static final String CANDIDATE_BI_ERROR_TITLE = "Erro ao efetuar candidatura, duplicação de BI";
    public static final String CANDIDATE_BI_ERROR_MSG = "A sua candidatura não pode ser processada, pois já foi submetida uma candidatura com o seu BI. "
                                                        + "Caso queira alterar os dados da sua candidatura inicie sessão e edite a sua candidatura.";
    public static final String CANDIDATE_PHONE_ERROR_TITLE = "Erro ao efetuar candidatura, duplicação de Telefone";
    public static final String CANDIDATE_PHONE_ERROR_MSG = "A sua candidatura não pode ser processada, pois já foi submetida uma candidatura com o seu Telefone. "
                                                           + "Caso queira alterar os dados da sua candidatura inicie sessão e edite a sua candidatura.";
    public static final String CANDIDATE_EMAIL_ERROR_TITLE = "Erro ao efetuar candidatura, duplicação de Email";
    public static final String CANDIDATE_EMAIL_ERROR_MSG = "A sua candidatura não pode ser processada, pois já foi submetida uma candidatura com o seu Email. "
                                                           + "Caso queira alterar os dados da sua candidatura inicie sessão e edite a sua candidatura.";

    @Value("${enviroment.staging}")
    private static String enviromentValue;
    public static final String DEFAULT_APP_URL_BASE = "/nuntius";
    public static final String DEFAULT_APP_API_VERSION = "/v1";
    public static final String APP_NAME = "/api";

//    /nuntius/v1/api
}
