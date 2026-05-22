package Login;

/*
 * Program: Login,Registration and chatting System
 * Author: [khothatso]
 * Date: [ 21 may 2026]
 *
 * DESCRIPTION:
 * This program allows a user to register (username, password, phone number)
 * and login only if registration is successful.after login,the program enters quickChat where the user can send messags.
 *
 * REFERENCES:
 * 1. Oracle Java Documentation - String Class
 *    https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 *
 * 2. Oracle Java Documentation - Character Class
 *    https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html
 *
 * 3. Java Scanner Class
 *    https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
 *
 * 4. South African Phone Number Format (+27)
 *    https://en.wikipedia.org/wiki/Telephone_numbers_in_South_Africa
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */








import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {

        Scanner khuthi = new Scanner(System.in);

        Registration reg = new Registration();

        int choice;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            choice = khuthi.nextInt();
            khuthi.nextLine();

            switch (choice) {

                case 1:

                    reg.register(khuthi);
                    break;

                case 2:

                    boolean loggedIn = reg.login(khuthi);

                    // Open QuickChat after successful login
                    if (loggedIn) {

                        System.out.println("\nWelcome to QuickChat.");

                        System.out.print("How many messages would you like to send? ");
                        int maxMessages = khuthi.nextInt();
                        khuthi.nextLine();

                        int sentCount = 0;

                        int option = 0;

                        while (option != 3) {

                            System.out.println("\nQUICKCHAT MENU");
                            System.out.println("1. Send Messages");
                            System.out.println("2. Show recently sent messages");
                            System.out.println("3. Quit");

                            System.out.print("Choose option: ");

                            option = khuthi.nextInt();
                            khuthi.nextLine();

                            // SEND MESSAGE
                            if (option == 1) {

                                if (sentCount >= maxMessages) {

                                    System.out.println("Message limit reached.");

                                } else {

                                    while (true) {

                                        // Ask recipient number
                                        System.out.print("Enter recipient number: ");
                                        String recipient = khuthi.nextLine();

                                        // Validate recipient number
                                        Message tempMessage = new Message(recipient, "");

                                        String result = tempMessage.checkRecipientCell();

                                        // If recipient number is wrong
                                        if (!result.equals("Cell phone number successfully captured.")) {

                                            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");

                                            System.out.println("Please re-enter the recipient number in the correct format.");

                                            continue;
                                        }

                                        // If number is correct
                                        System.out.println("Cell phone number successfully captured.");

                                        // Ask message
                                        System.out.print("Enter your message: ");
                                        String messageText = khuthi.nextLine();

                                        // Validate message length
                                        if (messageText.length() > 250) {

                                            System.out.println("Please enter a message of less than 250 characters.");

                                            continue;
                                        }

                                        // Create final message
                                        Message msg = new Message(recipient, messageText);

                                        // Ask what to do with message
                                        System.out.println("\nChoose what to do:");
                                        System.out.println("1. Send Message");
                                        System.out.println("2. Disregard Message");
                                        System.out.println("3. Store Message to send later");

                                        int messageChoice = khuthi.nextInt();
                                        khuthi.nextLine();

                                        switch (messageChoice) {

                                            case 1:

                                                msg.sentMessage();

                                                System.out.println("Message successfully sent.");

                                                msg.printMessages();

                                                sentCount++;

                                                break;

                                            case 2:

                                                msg.disregardMessage();

                                                break;

                                            case 3:

                                                msg.storeMessage();

                                                System.out.println("Message successfully stored.");

                                                break;

                                            default:

                                                System.out.println("Invalid option.");
                                        }

                                        break;
                                    }
                                }

                            }
                            // SHOW RECENT MESSAGES
                            else if (option == 2) {

                                System.out.println("Coming Soon.");

                            }
                            // QUIT
                            else if (option == 3) {

                                System.out.println("Total messages sent: " + Message.totalMessages);

                                System.out.println("Exiting QuickChat.");

                            } else {

                                System.out.println("Invalid option.");
                            }
                        }
                    }

                    break;

                case 3:

                    System.out.println("Goodbye!");
                    break;

                default:

                    System.out.println("Invalid option");
            }

        } while (choice != 3);

        khuthi.close();
    }
}