package com.payment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.payment.dto.RegisterDTO;
import com.payment.model.Transfer;
import com.payment.model.User;
import com.payment.model.Wallet;
import com.payment.repository.TransferRepository;
import com.payment.repository.UserRepository;

import java.util.Optional;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TransferRepository transferRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
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
        user1.setPassword("iloveyou");
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

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setCpfCnpj("Cpf Cnpj");
        user2.setRoles(null);
        user2.setId(123L);
        user2.setName("Name");
        user2.setWallet(wallet1);
        when(this.userRepository.save((User) any())).thenReturn(user2);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("foo");

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setCpfCnpj("Cpf Cnpj");
        user3.setRoles(null);
        user3.setId(123L);
        user3.setName("Name");
        user3.setWallet(new Wallet());

        Wallet wallet2 = new Wallet();
        wallet2.setValue(10.0);
        wallet2.setCpfCnpjUser("Cpf Cnpj User");
        wallet2.setId(123L);
        wallet2.setUser(user3);

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setCpfCnpj("Cpf Cnpj");
        user4.setRoles(null);
        user4.setId(123L);
        user4.setName("Name");
        user4.setWallet(wallet2);

        Wallet wallet3 = new Wallet();
        wallet3.setValue(10.0);
        wallet3.setCpfCnpjUser("Cpf Cnpj User");
        wallet3.setId(123L);
        wallet3.setUser(user4);

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setWallet(wallet3);
        registerDTO.setCpfCnpj("42");
        registerDTO.setPassword("Password");
        this.userService.save(registerDTO);
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.userRepository).save((User) any());
        assertEquals("foo", registerDTO.getPassword());
        assertEquals("42", registerDTO.getWallet().getCpfCnpjUser());
    }

    @Test
    public void testSave2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
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
        user1.setPassword("iloveyou");
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

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setCpfCnpj("Cpf Cnpj");
        user2.setRoles(null);
        user2.setId(123L);
        user2.setName("Name");
        user2.setWallet(wallet1);
        when(this.userRepository.save((User) any())).thenReturn(user2);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("foo");

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setCpfCnpj("Cpf Cnpj");
        user3.setRoles(null);
        user3.setId(123L);
        user3.setName("Name");
        user3.setWallet(new Wallet());

        Wallet wallet2 = new Wallet();
        wallet2.setValue(10.0);
        wallet2.setCpfCnpjUser("Cpf Cnpj User");
        wallet2.setId(123L);
        wallet2.setUser(user3);

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setCpfCnpj("Cpf Cnpj");
        user4.setRoles(null);
        user4.setId(123L);
        user4.setName("Name");
        user4.setWallet(wallet2);

        Wallet wallet3 = new Wallet();
        wallet3.setValue(10.0);
        wallet3.setCpfCnpjUser("Cpf Cnpj User");
        wallet3.setId(123L);
        wallet3.setUser(user4);

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setWallet(wallet3);
        registerDTO.setCpfCnpj("42");
        registerDTO.setPassword("Password");
        this.userService.save(registerDTO);
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.userRepository).save((User) any());
        assertEquals("foo", registerDTO.getPassword());
        assertEquals("42", registerDTO.getWallet().getCpfCnpjUser());
    }

    @Test
    public void testGetById() throws NotFoundException {
        Wallet wallet = new Wallet();
        wallet.setValue(10.0);
        wallet.setCpfCnpjUser("Cpf Cnpj User");
        wallet.setId(123L);
        wallet.setUser(new User());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setCpfCnpj("Cpf Cnpj");
        user.setRoles(null);
        user.setId(123L);
        user.setName("Name");
        user.setWallet(wallet);

        Wallet wallet1 = new Wallet();
        wallet1.setValue(10.0);
        wallet1.setCpfCnpjUser("Cpf Cnpj User");
        wallet1.setId(123L);
        wallet1.setUser(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setCpfCnpj("Cpf Cnpj");
        user1.setRoles(null);
        user1.setId(123L);
        user1.setName("Name");
        user1.setWallet(wallet1);
        Optional<User> ofResult = Optional.<User>of(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user1, this.userService.getById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    public void testGetById2() throws NotFoundException {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(NotFoundException.class, () -> this.userService.getById(123L));
        verify(this.userRepository).findById((Long) any());
    }

//    @Test
//    public void testUpdate() throws NotFoundException {
//        Wallet wallet = new Wallet();
//        wallet.setValue(10.0);
//        wallet.setCpfCnpjUser("Cpf Cnpj User");
//        wallet.setId(123L);
//        wallet.setUser(new User());
//
//        User user = new User();
//        user.setEmail("jane.doe@example.org");
//        user.setPassword("iloveyou");
//        user.setCpfCnpj("Cpf Cnpj");
//        user.setRoles(null);
//        user.setId(123L);
//        user.setName("Name");
//        user.setWallet(wallet);
//
//        Wallet wallet1 = new Wallet();
//        wallet1.setValue(10.0);
//        wallet1.setCpfCnpjUser("Cpf Cnpj User");
//        wallet1.setId(123L);
//        wallet1.setUser(user);
//
//        User user1 = new User();
//        user1.setEmail("jane.doe@example.org");
//        user1.setPassword("iloveyou");
//        user1.setCpfCnpj("Cpf Cnpj");
//        user1.setRoles(null);
//        user1.setId(123L);
//        user1.setName("Name");
//        user1.setWallet(wallet1);
//        Optional<User> ofResult = Optional.<User>of(user1);
//        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
//        this.userService.update(new Transfer());
//        verify(this.userRepository, times(2)).findById((Long) any());
//    }

    @Test
    public void testUpdate2() throws NotFoundException {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(NotFoundException.class, () -> this.userService.update(new Transfer()));
        verify(this.userRepository).findById((Long) any());
    }

//    @Test
//    public void testRequestAuth() {
//
//        this.userService.requestAuth();
//    }


}

