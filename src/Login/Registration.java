package Login;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;

public class Registration {

    private String storedUsername;
    private String storedPassword;
    private String storedPhone;
    private boolean registrationSuccess = false;
    
    //check username
    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

     //Check password
    public boolean checkPassword(String password) {

        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUppercase && hasNumber && hasSpecial;
    }

    //check phone number
    public boolean checkPhone(String phone) {
        return phone.matches("^\\+27\\d{9}$");
    }

    //register method
    public void register(Scanner scanner) {

        System.out.println("----- REGISTER -----");

        int userAttempts = 0;

        while (userAttempts < 3) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            if (checkUsername(username)) {
                storedUsername = username;
                System.out.println("username added successfully");
                break;
            } else {
                userAttempts++;
                System.out.println("Invalid username (" + userAttempts + "/3)");
            }
        }

        if (userAttempts == 3) {
            System.out.println("Registration failed");
            return;
        }

        int passAttempts = 0;

        while (passAttempts < 3) {
            System.out.print("input password: ");
            String password = scanner.nextLine();

            if (checkPassword(password)){ 
                storedPassword = password;
           
                System.out.println("password entered successfully!");
                break;
            } else {
                passAttempts++;
                System.out.println("Invalid password (" + passAttempts + "/3)");
            }
        }

        if (passAttempts == 3) {
            System.out.println("Registration failed");
            registrationSuccess= false;
            return;
        }

        int phoneAttempts = 0;

        while (phoneAttempts < 3) {
            System.out.print("Enter phone number. Incude (+27): ");
            String phone = scanner.nextLine();

            if (checkPhone(phone)) {
                System.out.println("Phone number was added successfully");
                storedPhone = phone;
                break;
            } else {
                phoneAttempts++;
                System.out.println("Incorrect format (" + phoneAttempts + "/3)");
            }
        }

        if (phoneAttempts == 3) {
            System.out.println("Registration failed");
            return;
        }

        registrationSuccess = true;
        System.out.println("Registration successful!");
    }

    //Login method
     public boolean login(Scanner khuthi) {

    System.out.println("\n--- LOGIN ---");

    if (!registrationSuccess) {
        System.out.println("You cannot log in because registration was not complete.");
        return false;
    }

    System.out.print("Enter username: ");
    String loginUsername = khuthi.nextLine();

    System.out.print("Enter password: ");
    String loginPassword = khuthi.nextLine();

    if (storedUsername.equals(loginUsername) && storedPassword.equals(loginPassword)) {

        System.out.println("Welcome Khothatso Motheogane, it is great to see you again.");

        return true;

    } else {

        System.out.println("Username or password incorrect, please try again");

        return false;
    }
     }
}
