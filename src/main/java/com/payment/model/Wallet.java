package com.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", insertable=false, updatable=false)
    @JsonIgnore
    private User user;

    @Column(name = "cpf_cnpj_User")
    @JsonIgnore
    private String cpfCnpjUser;

    @Column(name = "value")
    private Double value;

}
