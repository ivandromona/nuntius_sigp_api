package ao.adnlogico.nuntius.multitenant.constant;

/**
 * @author Md. Amran Hossain
 */
public class JWTConstants
{

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "domingos_m_nuntius";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
