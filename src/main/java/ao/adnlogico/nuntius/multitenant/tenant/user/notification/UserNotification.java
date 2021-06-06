/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.adnlogico.nuntius.multitenant.tenant.user.notification;

import ao.adnlogico.nuntius.multitenant.tenant.notification.Notification;
import ao.adnlogico.nuntius.multitenant.tenant.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author Sebastião Paulo
 */
@Entity
@Table(name = "user_notifications")
@Data
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(value = {"user"}, allowSetters = true)
public class UserNotification implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_notification")
    @JsonIgnoreProperties({"notifications"})
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    private LocalDateTime readedAt;

    @Column(columnDefinition = "BOOLEAN default false")
    private Boolean isReaded;
}
