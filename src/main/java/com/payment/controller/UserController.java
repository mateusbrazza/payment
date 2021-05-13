package com.payment.controller;

import com.payment.dto.AuthTransferDTO;
import com.payment.dto.RegisterDTO;
import com.payment.model.Transfer;
import com.payment.model.User;
import com.payment.service.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "user registration with wallet and type of user")
    public ResponseEntity<RegisterDTO> save(@RequestBody RegisterDTO registerDTO){
        userService.save(registerDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @PatchMapping("/transfer")
    @ApiOperation(value = "Performs balance transfer between accounts")
    public ResponseEntity update(@RequestBody Transfer transfer) throws NotFoundException {
        if(userService.update(transfer)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
