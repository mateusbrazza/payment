package com.payment.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.dto.RegisterDTO;
import com.payment.model.Transfer;
import com.payment.model.User;
import com.payment.model.Wallet;
import com.payment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    public void testSave() throws Exception {
        doNothing().when(this.userService).save((RegisterDTO) any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("password");
        user.setCpfCnpj("Cpf Cnpj");
        user.setRoles(null);
        user.setId(123L);
        user.setName("Name");
        user.setWallet(new Wallet());

        Wallet wallet = new Wallet();
        wallet.setValue(10.0);
        wallet.setCpfCnpjUser("Cpf Cnpj User");
        wallet.setId(123L);
        wallet.setUser(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("password");
        user1.setCpfCnpj("Cpf Cnpj");
        user1.setRoles(null);
        user1.setId(123L);
        user1.setName("Name");
        user1.setWallet(wallet);

        Wallet wallet1 = new Wallet();
        wallet1.setValue(10.0);
        wallet1.setCpfCnpjUser("Cpf Cnpj User");
        wallet1.setId(123L);
        wallet1.setUser(user1);

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("jane.doe@example.org");
        registerDTO.setPassword("password");
        registerDTO.setCpfCnpj("8456855455");
        registerDTO.setRoles(null);
        registerDTO.setId(123L);
        registerDTO.setName("Name");
        registerDTO.setWallet(wallet1);
        String content = (new ObjectMapper()).writeValueAsString(registerDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testUpdate() throws Exception {
        when(this.userService.update((Transfer) any())).thenReturn(true);

        Transfer transfer = new Transfer();
        transfer.setIdSender(1L);
        transfer.setIdRecipient(1L);
        transfer.setUserRecipient("User Recipient");
        transfer.setId(123L);
        transfer.setUserSender("User Sender");
        transfer.setDateTransfer(null);
        transfer.setValue(10.0);
        String content = (new ObjectMapper()).writeValueAsString(transfer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/user/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdate2() throws Exception {
        when(this.userService.update((Transfer) any())).thenReturn(false);

        Transfer transfer = new Transfer();
        transfer.setIdSender(1L);
        transfer.setIdRecipient(1L);
        transfer.setUserRecipient("User Recipient");
        transfer.setId(123L);
        transfer.setUserSender("User Sender");
        transfer.setDateTransfer(null);
        transfer.setValue(10.0);
        String content = (new ObjectMapper()).writeValueAsString(transfer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/user/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

