package Login;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testSentMessagesArray() {

        Message.sentMessages.clear();

        Message msg = new Message(
                "+27834557896",
                "Did you get the cake?"
        );

        msg.sentMessage();

        assertEquals(
                "Did you get the cake?",
                Message.sentMessages.get(0)
        );
    }

    @Test
    public void testStoredMessagesArray() {

        Message.storedMessages.clear();

        Message msg = new Message(
                "+27838888456",
                "Where are you? You are late. I have asked you to be on time."
        );

        msg.storeMessage();

        assertEquals(
                "Where are you? You are late. I have asked you to be on time.",
                Message.storedMessages.get(0)
        );
    }

    @Test
    public void testDisregardedMessagesArray() {

        Message.disregardedMessages.clear();

        Message msg = new Message(
                "+27834484845",
                "Yohooo, I am at the gate."
        );

        msg.disregardMessage();

        assertEquals(
                "Yohooo, I am at the gate.",
                Message.disregardedMessages.get(0)
        );
    }

    @Test
    public void testMessageIDCreated() {

        Message msg = new Message(
                "+27834557896",
                "Did you get the cake?"
        );

        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testRecipientValidation() {

        Message msg = new Message(
                "+27834557896",
                "Did you get the cake?"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testLongestMessage() {

        String longest =
                "Where are you? You are late. I have asked you to be on time.";

        String shortMsg =
                "Did you get the cake?";

        assertTrue(longest.length() > shortMsg.length());
    }

    @Test
    public void testSearchRecipientData() {

        Message.recipients.clear();

        Message.recipients.add("+27838888456");

        assertTrue(
                Message.recipients.contains("+27838888456")
        );
    }

    @Test
    public void testDeleteHash() {

        Message.messageHashes.clear();

        Message.messageHashes.add("12:1:CAKE");

        Message.deleteByHash("12:1:CAKE");

        assertEquals(
                0,
                Message.messageHashes.size()
        );
    }

    @Test
    public void testStoredMessageData() {

        Message.storedMessages.clear();

        Message.storedMessages.add(
                "Okay, I am leaving without you."
        );

        assertEquals(
                "Okay, I am leaving without you.",
                Message.storedMessages.get(0)
        );
    }

    @Test
    public void testReportData() {

        Message.messageHashes.clear();

        Message.messageHashes.add("11:1:CAKE");

        assertEquals(
                "11:1:CAKE",
                Message.messageHashes.get(0)
        );
    }
}