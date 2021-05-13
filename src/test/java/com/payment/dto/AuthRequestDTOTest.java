package com.payment.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthRequestDTOTest {
    @Test
    public void testConverter() {
        UsernamePasswordAuthenticationToken actualConverterResult = (new AuthRequestDTO("jane.doe@example.org", "password"))
                .converter();
        assertFalse(actualConverterResult.isAuthenticated());
        assertEquals("jane.doe@example.org", actualConverterResult.getName());
    }
}

