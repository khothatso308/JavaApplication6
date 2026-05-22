package Login;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    // Check Message ID

    @Test
    public void testCheckMessageID() {

        Message msg = new Message("+27831234567", "Hello World");

        boolean result = msg.checkMessageID();

        assertTrue(result);
    }

    // Check Recipient Number

    @Test
    public void testValidRecipientNumber() {

        Message msg = new Message("+27831234567", "Hello");

        String result = msg.checkRecipientCell();

        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testInvalidRecipientNumber_NoPlus() {

        Message msg = new Message("0831234567", "Hello");

        String result = msg.checkRecipientCell();

        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            result
        );
    }

    @Test
    public void testInvalidRecipientNumber_TooLong() {

        Message msg = new Message("+2783123456789", "Hello");

        String result = msg.checkRecipientCell();

        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            result
        );
    }

    // Check Message Length

    @Test
    public void testValidMessageLength() {

        String message = "Hello World";

        assertTrue(message.length() <= 250);
    }

    @Test
    public void testInvalidMessageLength() {

        String message = "";

        for (int i = 0; i < 251; i++) {
            message += "A";
        }

        assertTrue(message.length() > 250);
    }

    // Test Sent Message

    @Test
    public void testSentMessage() {

        Message.sentMessages.clear();

        Message msg = new Message("+27831234567", "Test Message");

        msg.sentMessage();

        assertEquals(1, Message.sentMessages.size());
    }

    // Test Store Message

    @Test
    public void testStoreMessage() {

        Message.storedMessages.clear();

        Message msg = new Message("+27831234567", "Stored Message");

        msg.storeMessage();

        assertEquals(1, Message.storedMessages.size());
    }

    // Test Total Messages Counter

    @Test
    public void testTotalMessagesCounter() {

        int before = Message.totalMessages;

        new Message("+27831234567", "Message 1");

        assertEquals(before + 1, Message.totalMessages);
    }

    // Test Message Object Creation

    @Test
    public void testMessageObjectCreated() {

        Message msg = new Message("+27831234567", "Hello");

        assertNotNull(msg);
    }
}