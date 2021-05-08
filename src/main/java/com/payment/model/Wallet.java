package com.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "card")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idUSer")
    @OneToMany(mappedBy = "usuario")
    private int idUser;

    @Column(name = "value")
    private String value;

}
