package com.payment.config.security;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.payment.model.User;
import com.payment.model.Wallet;
import com.payment.repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MyUserDetailsService.class})
@ExtendWith(SpringExtension.class)
public class MyUserDetailsServiceTest {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLoadUserByUsername() throws UsernameNotFoundException {
        Wallet wallet = new Wallet();
        wallet.setValue(10.0);
        wallet.setCpfCnpjUser("419542658");
        wallet.setId(123L);
        wallet.setUser(new User());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("password");
        user.setCpfCnpj("419542658");
        user.setRoles(null);
        user.setId(123L);
        user.setName("Name");
        user.setWallet(wallet);

        Wallet wallet1 = new Wallet();
        wallet1.setValue(10.0);
        wallet1.setCpfCnpjUser("419542650");
        wallet1.setId(123L);
        wallet1.setUser(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("password");
        user1.setCpfCnpj("419542650");
        user1.setRoles(null);
        user1.setId(123L);
        user1.setName("Name");
        user1.setWallet(wallet1);
        Optional<User> ofResult = Optional.<User>of(user1);
        when(this.userRepository.findByEmail(anyString())).thenReturn(ofResult);
        assertSame(user1, this.myUserDetailsService.loadUserByUsername("janedoe"));
        verify(this.userRepository).findByEmail(anyString());
    }

    @Test
    public void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.<User>empty());
        assertThrows(UsernameNotFoundException.class, () -> this.myUserDetailsService.loadUserByUsername("janedoe"));
        verify(this.userRepository).findByEmail(anyString());
    }
}

