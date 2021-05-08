package com.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "value")
    private double value;
    @Column(name = "user_sender")
    private Integer userSender;
    @Column(name = "user_recipient")
    private Integer userRecipient;
    @Column(name = "dateTransfer")
    private LocalDateTime dateTransfer = LocalDateTime.now();

}
