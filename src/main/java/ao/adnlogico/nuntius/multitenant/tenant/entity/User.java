package ao.adnlogico.nuntius.multitenant.tenant.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Md. Amran Hossain
 */
@Entity
@Table(name = "usuario")
public class User implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true)
    private String userName;

    @Size(max = 20)
    @Column(name = "telefone ", nullable = false, unique = true)
    private String phone;

    @Size(max = 20)
    @Column(name = "telefone_alt", nullable = false, unique = true)
    private String phoneAlt;

    @Size(max = 255)
    @Column(name = "num_mecanografico", nullable = false)
    private String mecanograficoNum;

    @Column(name = "descricao", nullable = false)
    private String desc;

    @Column(name = "fk_funcao ", nullable = false)
    private Integer function;

    @Column(name = "fk_pessoa  ", nullable = false)
    private Integer person;

    @Column(name = "fk_perfil_acesso  ", nullable = false)
    private Integer profileAccess;

    @Column(name = "criado_em ", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "atualizado_em ", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Size(max = 255)
    @Column(name = "palavra_passe", nullable = false)
    private String password;
    @Size(max = 10)
    @Column(name = "status", nullable = false)
    private String status;

    public User()
    {
    }

    public User(String userName, String phone, String phoneAlt, String mecanograficoNum, String desc, Integer function, Integer person, Integer profileAccess, Date createdAt, Date updatedAt, String password, String status)
    {
        this.userName = userName;
        this.phone = phone;
        this.phoneAlt = phoneAlt;
        this.mecanograficoNum = mecanograficoNum;
        this.desc = desc;
        this.function = function;
        this.person = person;
        this.profileAccess = profileAccess;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password = password;
        this.status = status;
    }

    public Integer getId()
    {
        return id;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhoneAlt()
    {
        return phoneAlt;
    }

    public void setPhoneAlt(String phoneAlt)
    {
        this.phoneAlt = phoneAlt;
    }

    public String getMecanograficoNum()
    {
        return mecanograficoNum;
    }

    public void setMecanograficoNum(String mecanograficoNum)
    {
        this.mecanograficoNum = mecanograficoNum;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Integer getFunction()
    {
        return function;
    }

    public void setFunction(Integer function)
    {
        this.function = function;
    }

    public Integer getPerson()
    {
        return person;
    }

    public void setPerson(Integer person)
    {
        this.person = person;
    }

    public Integer getProfileAccess()
    {
        return profileAccess;
    }

    public void setProfileAccess(Integer profileAccess)
    {
        this.profileAccess = profileAccess;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public User setUserId(Integer userId)
    {
        this.id = userId;
        return this;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public User setUserName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public String getPassword()
    {
        return password;
    }

    public User setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public String getStatus()
    {
        return status;
    }

    public User setStatus(String status)
    {
        this.status = status;
        return this;
    }
}
