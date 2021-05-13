package com.payment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class WalletTest {
    @Test
    public void testConstructor() {
        Wallet actualWallet = new Wallet();
        actualWallet.setCpfCnpjUser("Cpf Cnpj User");
        actualWallet.setId(123L);
        Wallet wallet = new Wallet();
        wallet.setValue(10.0);
        wallet.setCpfCnpjUser("Cpf Cnpj User");
        wallet.setId(123L);
        User user = new User();
        wallet.setUser(user);
        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("passsword");
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
        user2.setPassword("passsword");
        user2.setCpfCnpj("Cpf Cnpj");
        user2.setRoles(null);
        user2.setId(123L);
        user2.setName("Name");
        user2.setWallet(wallet1);
        actualWallet.setUser(user2);
        actualWallet.setValue(10.0);
        User user3 = actualWallet.getUser();
        Wallet wallet2 = user3.getWallet();
        User user4 = wallet2.getUser();
        Wallet wallet3 = user4.getWallet();
        assertEquals("Cpf Cnpj User", wallet3.getCpfCnpjUser());
        assertEquals("Cpf Cnpj User", actualWallet.getCpfCnpjUser());
        assertEquals("Cpf Cnpj User", wallet2.getCpfCnpjUser());
        assertEquals(123L, wallet2.getId().longValue());
        assertEquals(123L, actualWallet.getId().longValue());
        assertEquals(123L, wallet3.getId().longValue());
        assertSame(user2, user3);
        assertEquals(user3, user4);
        assertSame(user, wallet3.getUser());
        assertSame(user1, user4);
        assertEquals(user1, user3);
        assertEquals(10.0, wallet2.getValue().doubleValue());
        assertEquals(10.0, wallet3.getValue().doubleValue());
        assertEquals(10.0, actualWallet.getValue().doubleValue());
    }
}

