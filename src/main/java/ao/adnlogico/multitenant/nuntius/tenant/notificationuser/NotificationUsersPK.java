/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.multitenant.nuntius.tenant.notificationuser;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sebastião Paulo
 */
@Embeddable
public class NotificationUsersPK implements Serializable
{

    @Basic(optional = false)
    @Column(name = "fk_notification")
    private int fkNotification;
    @Basic(optional = false)
    @Column(name = "fk_user")
    private int fkUser;

    public NotificationUsersPK()
    {
    }

    public NotificationUsersPK(int fkNotification, int fkUser)
    {
        this.fkNotification = fkNotification;
        this.fkUser = fkUser;
    }

    public int getFkNotification()
    {
        return fkNotification;
    }

    public void setFkNotification(int fkNotification)
    {
        this.fkNotification = fkNotification;
    }

    public int getFkUser()
    {
        return fkUser;
    }

    public void setFkUser(int fkUser)
    {
        this.fkUser = fkUser;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) fkNotification;
        hash += (int) fkUser;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationUsersPK)) {
            return false;
        }
        NotificationUsersPK other = (NotificationUsersPK) object;
        if (this.fkNotification != other.fkNotification) {
            return false;
        }
        if (this.fkUser != other.fkUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.NotificationUsersPK[ fkNotification=" + fkNotification + ", fkUser=" + fkUser + " ]";
    }

}
