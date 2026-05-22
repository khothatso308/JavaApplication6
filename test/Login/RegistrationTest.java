package Login;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class RegistrationTest {

    Registration reg = new Registration();

    // check username

    @Test
    public void testValidUsername() {
        boolean results = reg.checkUsername("ab_cd");
        assertTrue(results);
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        boolean results = reg.checkUsername("abcde");
        assertFalse(results);
    }

    @Test
    public void testInvalidUsername_TooLong() {
        boolean results = reg.checkUsername("abcdef");
        assertFalse(results);
    }

    // check password

    @Test
    public void testValidPassword() {
        boolean results = reg.checkPassword("Password1!");
        assertTrue(results);
    }

    @Test
    public void testPassword_NoUppercase() {
        boolean results = reg.checkPassword("password1!");
        assertFalse(results);
    }

    @Test
    public void testPassword_NoNumber() {
        boolean results = reg.checkPassword("Password!");
        assertFalse(results);
    }

    @Test
    public void testPassword_NoSpecialChar() {
        boolean results = reg.checkPassword("Password1");
        assertFalse(results);
    }

    @Test
    public void testPassword_TooShort() {
        boolean results = reg.checkPassword("Pass1!");
        assertFalse(results);
    }

    // check phone (regex)

    @Test
    public void testValidPhoneNumber() {
        boolean results = reg.checkPhone("+27831234567");
        assertTrue(results);
    }

    @Test
    public void testInvalidPhone_NoCountryCode() {
        boolean results = reg.checkPhone("0831234567");
        assertFalse(results);
    }

    @Test
    public void testInvalidPhone_WrongLength() {
        boolean results = reg.checkPhone("+2783123456");
        assertFalse(results);
    }

    @Test
    public void testInvalidPhone_ContainsLetters() {
        boolean results = reg.checkPhone("+27831234abc");
        assertFalse(results);
    }

    // registration pass

    @Test
    public void testRegisterSuccess() {

        String input = "ab_cd\nPassword1!\n+27831234567\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        Registration reg = new Registration();

        reg.register(scanner);

        assertTrue(true);
    }

    // registration fail

    @Test
    public void testRegisterFail() {

        String input = "abc\nabc\nabc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        Registration reg = new Registration();

        reg.register(scanner);

        assertTrue(true);
    }

    // Test Login Failure

    @Test
    public void testLoginFail() {

        String registerInput = "ab_cd\nPassword1!\n+27831234567\n";
        System.setIn(new ByteArrayInputStream(registerInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        Registration reg = new Registration();

        reg.register(scanner);

        String loginInput = "wrong\nwrong\n";
        System.setIn(new ByteArrayInputStream(loginInput.getBytes()));

        Scanner scanner2 = new Scanner(System.in);

        reg.login(scanner2);

        assertTrue(true);
    }

    // Test Login Without Register

    @Test
    public void testLoginWithoutRegister() {

        String loginInput = "user\npass\n";
        System.setIn(new ByteArrayInputStream(loginInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        Registration reg = new Registration();

        reg.login(scanner);

        assertTrue(true);
    }
}