package Login;

import Login.Message;
import java.util.Scanner;

public class QuickChat {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages would you like to send? ");
        int maxMessages = input.nextInt();
        input.nextLine();

        int sentCount = 0;

        int option = 0;

        while (option != 3) {

            System.out.println("\nMENU");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            System.out.print("Choose option: ");
            option = input.nextInt();
            input.nextLine();

            if (option == 1) {

                if (sentCount >= maxMessages) {

                    System.out.println("Message limit reached.");

                } else {

                    System.out.print("Enter recipient number: ");
                    String recipient = input.nextLine();

                    System.out.print("Enter message: ");
                    String messageText = input.nextLine();

                    if (messageText.length() > 250) {

                        System.out.println("Please enter a message of less than 250 characters.");

                    } else {

                        Message msg = new Message(recipient, messageText);

                        System.out.println(msg.checkRecipientCell());

                        msg.sentMessage();

                        msg.printMessages();

                        sentCount++;
                    }
                }

            } else if (option == 2) {

                System.out.println("Coming Soon.");

            } else if (option == 3) {

                System.out.println("Total messages sent: " + Message.totalMessages);
                System.out.println("Goodbye.");

            } else {

                System.out.println("Invalid option.");
            }
        }
    }
}