package ao.adnlogico.nuntius.multitenant.master.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Domingos M. Fernando
 */
@Entity
@Table(name = "master_tenant")
public class MasterTenant implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 150)
    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Size(max = 50)
    @Column(name = "db_name", nullable = false)
    private String dbName;

    @Size(max = 255)
    @Column(name = "db_url", nullable = false)
    private String dbUrl;

    @Size(max = 50)
    @Column(name = "db_user_name", nullable = false)
    private String dbUserName;

    @Size(max = 100)
    @Column(name = "db_password", nullable = false)
    private String dbPassword;

    @Size(max = 100)
    @Column(name = "driver_class", nullable = false)
    private String driverClass;

    @Size(max = 10)
    @Column(name = "status", nullable = false)
    private String status;

    @Size(max = 255)
    @Column(name = "settings")
    private String settings;

    public MasterTenant()
    {
    }

    public MasterTenant(Long id, String clientId, String dbName, String dbUrl, String dbUserName, String dbPassword, String driverClass, String status)
    {
        this.id = id;
        this.clientId = clientId;
        this.dbName = dbName;
        this.dbUrl = dbUrl;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
        this.driverClass = driverClass;
        this.status = status;
    }

    public MasterTenant(@Size(max = 50) String dbName, @Size(max = 100) String url, @Size(max = 50) String userName, @Size(max = 100) String password, @Size(max = 100) String driverClass, @Size(max = 10) String status)
    {
        this.dbName = dbName;
        this.dbUrl = url;
        this.dbUserName = userName;
        this.dbPassword = password;
        this.driverClass = driverClass;
        this.status = status;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getClientId()
    {
        return clientId;
    }

    public MasterTenant setClientId(String clientId)
    {
        this.clientId = clientId;
        return this;
    }

    public String getDbName()
    {
        return dbName;
    }

    public MasterTenant setDbName(String dbName)
    {
        this.dbName = dbName;
        return this;
    }

    public String getDbUrl()
    {
        return dbUrl;
    }

    public MasterTenant setUrl(String url)
    {
        this.dbUrl = url;
        return this;
    }

    public String getDbUserName()
    {
        return dbUserName;
    }

    public MasterTenant setUserName(String userName)
    {
        this.dbUserName = userName;
        return this;
    }

    public String getDbPassword()
    {
        return dbPassword;
    }

    public MasterTenant setPassword(String password)
    {
        this.dbPassword = password;
        return this;
    }

    public String getDriverClass()
    {
        return driverClass;
    }

    public MasterTenant setDriverClass(String driverClass)
    {
        this.driverClass = driverClass;
        return this;
    }

    public String getStatus()
    {
        return status;
    }

    public MasterTenant setStatus(String status)
    {
        this.status = status;
        return this;
    }

    public String getSettings()
    {
        return settings;
    }

    public void setSettings(String settings)
    {
        this.settings = settings;
    }

}
