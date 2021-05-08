package com.payment.controller;

import com.payment.dto.AuthTransferDTO;
import com.payment.model.Transfer;
import com.payment.model.User;
import com.payment.service.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/mateus")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    @ApiOperation(value = "Performs balance transfer between accounts")
    public ResponseEntity update(@RequestBody Transfer transfer) throws NotFoundException {
        userService.update(transfer);
            return new ResponseEntity<>(HttpStatus.OK);

    }

}
