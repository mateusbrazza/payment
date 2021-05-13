package com.payment.controller;




import com.payment.config.security.JwtProvider;
import com.payment.dto.AuthRequestDTO;
import com.payment.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping
public class AuthenticateController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authManager;


    @PostMapping("/authenticate")
    @ApiOperation(value = "performs user authentication to use system endpoints")
    public ResponseEntity<AuthRequestDTO> authentication(@RequestBody @Valid AuthRequestDTO form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = jwtProvider.generateToken(authentication);
            return ResponseEntity.ok(new AuthRequestDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
