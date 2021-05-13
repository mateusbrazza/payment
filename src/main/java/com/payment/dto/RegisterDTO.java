package com.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payment.model.Role;
import com.payment.model.Wallet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class RegisterDTO {
    @JsonIgnore
    private Long id;
    private String cpfCnpj;
    private String email;
    private String name;
    private String password;
    private List<Role> roles;
    private Wallet wallet;
}
