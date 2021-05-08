package com.payment.controller;

import com.payment.model.User;
import com.payment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/mateus")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping()
////    @ApiOperation(value = "Realiza o cadastro do usuario")
//    public ResponseEntity<User> save(@RequestBody User user) {
//        try {
//            userService.save(user);
//        }  finally{
//
//        }
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
