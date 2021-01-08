/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.notificationuser;

import ao.adnlogico.nuntius.multitenant.tenant.notification.Notification;
import ao.adnlogico.nuntius.multitenant.tenant.user.Users;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "notification_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificationUsers.findAll", query = "SELECT n FROM NotificationUsers n"),
    @NamedQuery(name = "NotificationUsers.findByFkNotification", query = "SELECT n FROM NotificationUsers n WHERE n.notificationUsersPK.fkNotification = :fkNotification"),
    @NamedQuery(name = "NotificationUsers.findByFkUser", query = "SELECT n FROM NotificationUsers n WHERE n.notificationUsersPK.fkUser = :fkUser"),
    @NamedQuery(name = "NotificationUsers.findByIsDismiss", query = "SELECT n FROM NotificationUsers n WHERE n.isDismiss = :isDismiss"),
    @NamedQuery(name = "NotificationUsers.findByDismissedAt", query = "SELECT n FROM NotificationUsers n WHERE n.dismissedAt = :dismissedAt")})
public class NotificationUser implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotificationUsersPK notificationUsersPK;
    @Basic(optional = false)
    @Column(name = "is_dismiss")
    private boolean isDismiss;
    @Column(name = "dismissed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dismissedAt;
    @JoinColumn(name = "fk_notification", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Notification notifications;
    @JoinColumn(name = "fk_user", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public NotificationUser()
    {
    }

    public NotificationUser(NotificationUsersPK notificationUsersPK)
    {
        this.notificationUsersPK = notificationUsersPK;
    }

    public NotificationUser(NotificationUsersPK notificationUsersPK, boolean isDismiss)
    {
        this.notificationUsersPK = notificationUsersPK;
        this.isDismiss = isDismiss;
    }

    public NotificationUser(int fkNotification, int fkUser)
    {
        this.notificationUsersPK = new NotificationUsersPK(fkNotification, fkUser);
    }

    public NotificationUsersPK getNotificationUsersPK()
    {
        return notificationUsersPK;
    }

    public void setNotificationUsersPK(NotificationUsersPK notificationUsersPK)
    {
        this.notificationUsersPK = notificationUsersPK;
    }

    public boolean getIsDismiss()
    {
        return isDismiss;
    }

    public void setIsDismiss(boolean isDismiss)
    {
        this.isDismiss = isDismiss;
    }

    public Date getDismissedAt()
    {
        return dismissedAt;
    }

    public void setDismissedAt(Date dismissedAt)
    {
        this.dismissedAt = dismissedAt;
    }

    public Notification getNotifications()
    {
        return notifications;
    }

    public void setNotifications(Notification notifications)
    {
        this.notifications = notifications;
    }

    public Users getUsers()
    {
        return users;
    }

    public void setUsers(Users users)
    {
        this.users = users;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (notificationUsersPK != null ? notificationUsersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationUser)) {
            return false;
        }
        NotificationUser other = (NotificationUser) object;
        if ((this.notificationUsersPK == null && other.notificationUsersPK != null) || (this.notificationUsersPK != null && !this.notificationUsersPK.equals(other.notificationUsersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.NotificationUsers[ notificationUsersPK=" + notificationUsersPK + " ]";
    }

}
