package com.payment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoleTest {
    @Test
    public void testConstructor() {
        Role actualRole = new Role();
        actualRole.setId(123L);
        actualRole.setName("Name");
        assertEquals("Name", actualRole.getAuthority());
        assertEquals(123L, actualRole.getId().longValue());
        assertEquals("Name", actualRole.getName());
    }
}

