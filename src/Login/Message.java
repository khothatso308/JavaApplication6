package Login;

import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    public static int totalMessages = 0;

    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();

    public Message(String recipient, String message) {

        this.recipient = recipient;
        this.message = message;

        totalMessages++;
        this.messageNumber = totalMessages;

        generateMessageID();
        createMessageHash();
    }

    // Generate random 10-digit ID
    public void generateMessageID() {

        Random random = new Random();

        String id = "";

        for (int i = 0; i < 10; i++) {
            id += random.nextInt(10);
        }

        messageID = id;
    }

    // Check message ID
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // Validate recipient number
    public String checkRecipientCell() {

        if (recipient.startsWith("+") && recipient.length() <= 13) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Create message hash
    public void createMessageHash() {

        String firstTwo = messageID.substring(0, 2);

        String[] words = message.split(" ");

        String lastWord = "";

        if (words.length > 0) {
            lastWord = words[words.length - 1];
        }

        messageHash = (firstTwo + ":" + messageNumber + ":" + lastWord).toUpperCase();
    }

    // Send message
    public void sentMessage() {

        sentMessages.add(message);

        saveMessageToJson("Sent");
    }

    // Store message
    public void storeMessage() {

        storedMessages.add(message);

        saveMessageToJson("Stored");
    }

    // Delete message
    public void disregardMessage() {

        System.out.println("Message deleted.");
    }

    // Print message details
    public void printMessages() {

        System.out.println("\nMESSAGE DETAILS");
        System.out.println("--------------------------");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
    }

    // Save to JSON file
    public void saveMessageToJson(String status) {

        try {

            FileWriter writer = new FileWriter("messages.json", true);

            writer.write("{\n");
            writer.write("\"messageID\":\"" + messageID + "\",\n");
            writer.write("\"messageHash\":\"" + messageHash + "\",\n");
            writer.write("\"recipient\":\"" + recipient + "\",\n");
            writer.write("\"message\":\"" + message + "\",\n");
            writer.write("\"status\":\"" + status + "\"\n");
            writer.write("}\n");

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving message.");
        }
    }
}