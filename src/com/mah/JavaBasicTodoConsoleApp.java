package com.mah;

import com.mah.controller.UserController;
import com.mah.entity.User;
import com.mah.shared.MyResponseEntity;

import java.util.Scanner;
import java.util.UUID;

import static com.mah.shared.AnsiColorUtils.*;
import static com.mah.shared.ConsoleColors.*;

public class JavaBasicTodoConsoleApp {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static int showWelcomePage() {
        int selectedOption;
        System.out.println(GREEN_BOLD_BRIGHT + "\nBASIC TODO CONSOLE APP" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "SELECT OPTION" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "--------------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "1. LOGIN" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "2. REGISTER" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "3. EXIT" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "--------------" + ANSI_RESET);
        System.out.print(ANSI_CYAN + "ENTER YOUR OPTION: " + ANSI_RESET);
        selectedOption = SCANNER.nextInt();
        return selectedOption;
    }

    private static void registerUser() {
        String firstName = "";
        String lastName = "";
        String email = "";
        String username = "";
        String password = "";
        String confirmPassword = "";

        System.out.print(ANSI_YELLOW + "Please enter your First Name: " + ANSI_RESET);
        firstName = SCANNER.next();
        System.out.print(ANSI_YELLOW + "Please enter your Last Name: " + ANSI_RESET);
        lastName = SCANNER.next();
        System.out.print(ANSI_YELLOW + "Please enter your email: " + ANSI_RESET);
        email = SCANNER.next();
        System.out.print(ANSI_YELLOW + "Please enter your Username: " + ANSI_RESET);
        username = SCANNER.next();
        System.out.print(ANSI_YELLOW + "Please enter your password: " + ANSI_RESET);
        password = SCANNER.next();
        System.out.print(ANSI_YELLOW + "Please confirm your password: " + ANSI_RESET);
        confirmPassword = SCANNER.next();

        if (!password.equals(confirmPassword)) {
            do {
                System.out.print(ANSI_RED + "Password didn't match please re-confirm your password: " + ANSI_RESET);
                confirmPassword = SCANNER.next();
            } while (!password.equals(confirmPassword));
        }
        if (UserController.getUserController().registerUser(new User(UUID.randomUUID().toString(), firstName, lastName, email, username, password))) {
            System.out.println(new MyResponseEntity(CYAN_BOLD_BRIGHT + "Registered Successfully, Please log in to continue" + ANSI_RESET));
        } else {
            System.out.println(new MyResponseEntity(RED_BOLD_BRIGHT + "Registration Failed" + ANSI_RESET));
        }

    }

    private static void loginUser(int numberOfLoginAttempts) {
        String emailOrUserName = "";
        String password = "";
        System.out.print(ANSI_YELLOW + "\nPlease enter your Email or Username: " + ANSI_RESET);
        emailOrUserName = SCANNER.next();

        System.out.print(ANSI_YELLOW + "Please enter your password: " + ANSI_RESET);
        password = SCANNER.next();
        User user = UserController.getUserController().loginUser(emailOrUserName, password);
        if (user == null) {
            System.out.print(ANSI_RED + "You have entered incorrect credentials, Please provide correct credentials\n" + ANSI_RESET);
            if (numberOfLoginAttempts > 0) {
                System.out.print(RED_BOLD_BRIGHT + numberOfLoginAttempts + " ATTEMPT(S) LEFT\n" + ANSI_RESET);
                loginUser(numberOfLoginAttempts - 1);
            }
        } else {
            System.out.print(ANSI_CYAN + "Welcome: " + ANSI_RESET + CYAN_BOLD_BRIGHT + String.format("%s %s\n", user.getFirstName(), user.getLastName()) + ANSI_RESET);
            showMainPage();
        }
    }

    public static void showMainPage() {
        System.out.println(GREEN_BOLD_BRIGHT + "\nBASIC TODO CONSOLE APP" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "SELECT OPTION" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "--------------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "1. ADD TASK" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "2. SHOW TASK" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "3. UPDATE TASK" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "4. DELETE TASK" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "5. LOG OUT" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "--------------" + ANSI_RESET);
        System.out.print(ANSI_CYAN + "ENTER YOUR OPTION: " + ANSI_RESET);
        int choice = SCANNER.nextInt();
        switch (choice) {
            case 1:
                System.out.println("ADD TASK");
                break;
            case 2:
                System.out.println("SHOW TASK");
                break;
            case 3:
                System.out.println("UPDATE TASK");
                break;
            case 4:
                System.out.println("DELETE TASK");
                break;
            case 5:
                System.exit(1);
                 break;
            default:
                System.out.print("\nYou have entered wrong input");
                break;
        }
    }

    public static void startBasicTodoConsoleApp() {
        final char doYouWantToContinue = 'y';
        char yesIWantToContinue = 'y';
        do {
            switch (showWelcomePage()) {
                case 1:
                    loginUser(2);
                    break;

                case 2:
                    registerUser();
                    break;

                case 3:
                    System.exit(1);
                    break;

                default:
                    System.out.print("\nYou have entered wrong input");
                    System.out.print("\nDo you want to continue press [Y/N]: ");
                    yesIWantToContinue = SCANNER.next().charAt(0);
                    if (!(yesIWantToContinue == 'y' || yesIWantToContinue == 'n' || yesIWantToContinue == 'Y' || yesIWantToContinue == 'N')) {
                        do {
                            System.out.print("\nYou have entered wrong input");
                            System.out.print("\nDo you want to continue press [Y/N]: ");
                            yesIWantToContinue = SCANNER.next().charAt(0);
                        } while (!(yesIWantToContinue == 'y' || yesIWantToContinue == 'n' || yesIWantToContinue == 'Y' || yesIWantToContinue == 'N'));
                    }
                    break;
            }

        } while (doYouWantToContinue == yesIWantToContinue);
    }

    public static void main(String[] args) {
        startBasicTodoConsoleApp();
    }
}
