package com.payment.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TransferTest {
    @Test
    public void testConstructor() {
        Transfer actualTransfer = new Transfer();
        actualTransfer.setDateTransfer(null);
        actualTransfer.setId(123L);
        actualTransfer.setIdRecipient(1L);
        actualTransfer.setIdSender(1L);
        actualTransfer.setUserRecipient("User Recipient");
        actualTransfer.setUserSender("User Sender");
        actualTransfer.setValue(10.0);
        assertNull(actualTransfer.getDateTransfer());
        assertEquals(123L, actualTransfer.getId().longValue());
        assertEquals(1L, actualTransfer.getIdRecipient().longValue());
        assertEquals(1L, actualTransfer.getIdSender().longValue());
        assertEquals("User Recipient", actualTransfer.getUserRecipient());
        assertEquals("User Sender", actualTransfer.getUserSender());
        assertEquals(10.0, actualTransfer.getValue());
    }
}

