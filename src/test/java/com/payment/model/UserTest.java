package com.payment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void testHashCode() {
        assertEquals(31, (new User()).hashCode());
    }

    @Test
    public void testHashCode2() {
        User user = new User();
        user.setId(123L);
        assertEquals(154, user.hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse((new User()).equals("Obj"));
        assertFalse((new User()).equals(null));
    }

    @Test
    public void testConstructor() {
        User actualUser = new User();
        assertNull(actualUser.getAuthorities());
        assertNull(actualUser.getPassword());
        assertNull(actualUser.getUsername());
        assertTrue(actualUser.isAccountNonExpired());
        assertTrue(actualUser.isAccountNonLocked());
        assertTrue(actualUser.isCredentialsNonExpired());
        assertTrue(actualUser.isEnabled());
    }
}

