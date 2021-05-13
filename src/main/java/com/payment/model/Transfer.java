package com.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "value")
    private double value;

    @Column(name = "user_sender")
    @JsonIgnore
    private String userSender;

    @Column(name = "user_recipient")
    @JsonIgnore
    private String userRecipient;

    @Column(name = "id_sender")
    private Long idSender;

    @Column(name = "id_recipient")
    private Long idRecipient;

    @Column(name = "dateTransfer")
    @JsonIgnore
    private LocalDateTime dateTransfer = LocalDateTime.now();

}
