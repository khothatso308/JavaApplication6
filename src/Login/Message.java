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

    // Part 3 Arrays
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();

    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> recipients = new ArrayList<>();

    public Message(String recipient, String message) {

        this.recipient = recipient;
        this.message = message;

        totalMessages++;
        this.messageNumber = totalMessages;

        generateMessageID();
        createMessageHash();
    }

    // Generate Message ID
    public void generateMessageID() {

        Random random = new Random();

        String id = "";

        for (int i = 0; i < 10; i++) {
            id += random.nextInt(10);
        }

        messageID = id;
    }

    // Check Message ID
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Check Recipient
    public String checkRecipientCell() {

        if (recipient.startsWith("+") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Create Hash
    public void createMessageHash() {

        String firstTwo = messageID.substring(0, 2);

        String[] words = message.split(" ");

        String lastWord = "";

        if (words.length > 0) {
            lastWord = words[words.length - 1];
        }

        messageHash = (firstTwo + ":" + messageNumber + ":" + lastWord).toUpperCase();
    }

    // Send Message
    public void sentMessage() {

        sentMessages.add(message);
        recipients.add(recipient);
        messageIDs.add(messageID);
        messageHashes.add(messageHash);

        saveMessageToJson("Sent");
    }

    // Store Message
    public void storeMessage() {

        storedMessages.add(message);
        recipients.add(recipient);
        messageIDs.add(messageID);
        messageHashes.add(messageHash);

        saveMessageToJson("Stored");
    }

    // Disregard Message
    public void disregardMessage() {

        disregardedMessages.add(message);

        System.out.println("Message deleted.");
    }

    // Print Message Details
    public void printMessages() {

        System.out.println("\nMESSAGE DETAILS");
        System.out.println("----------------------");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
    }

    // Save JSON
    public void saveMessageToJson(String status) {

        try {

            FileWriter writer = new FileWriter("messages.json", true);

            writer.write("{\n");
            writer.write("\"messageID\":\"" + messageID + "\",\n");
            writer.write("\"recipient\":\"" + recipient + "\",\n");
            writer.write("\"message\":\"" + message + "\",\n");
            writer.write("\"messageHash\":\"" + messageHash + "\",\n");
            writer.write("\"status\":\"" + status + "\"\n");
            writer.write("}\n");

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving message.");
        }
    }

    // Part 3 Methods

    public static void displaySentMessages() {

        System.out.println("\nSENT MESSAGES");

        for (int i = 0; i < sentMessages.size(); i++) {

            System.out.println("------------------");
            System.out.println("Recipient: " + recipients.get(i));
            System.out.println("Message: " + sentMessages.get(i));
        }
    }

    public static void displayLongestMessage() {

        String longest = "";

        for (String msg : storedMessages) {

            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }

        System.out.println("\nLongest Message:");
        System.out.println(longest);
    }

    public static void searchByMessageID(String id) {

        for (int i = 0; i < messageIDs.size(); i++) {

            if (messageIDs.get(i).equals(id)) {

                System.out.println("Recipient: " + recipients.get(i));

                if (i < storedMessages.size()) {
                    System.out.println("Message: " + storedMessages.get(i));
                }

                return;
            }
        }

        System.out.println("Message ID not found.");
    }

    public static void searchByRecipient(String number) {

        boolean found = false;

        for (int i = 0; i < recipients.size(); i++) {

            if (recipients.get(i).equals(number)) {

                found = true;

                if (i < storedMessages.size()) {
                    System.out.println(storedMessages.get(i));
                }
            }
        }

        if (!found) {
            System.out.println("No messages found.");
        }
    }

    public static void deleteByHash(String hash) {

        for (int i = 0; i < messageHashes.size(); i++) {

            if (messageHashes.get(i).equals(hash)) {

                messageHashes.remove(i);

                System.out.println("Message successfully deleted.");

                return;
            }
        }

        System.out.println("Hash not found.");
    }

    public static void displayReport() {

        System.out.println("\nMESSAGE REPORT");

        for (int i = 0; i < messageHashes.size(); i++) {

            System.out.println("----------------------");
            System.out.println("Hash: " + messageHashes.get(i));
            System.out.println("Recipient: " + recipients.get(i));

            if (i < storedMessages.size()) {
                System.out.println("Message: " + storedMessages.get(i));
            }
        }
    }
}