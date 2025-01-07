package view.interfaces;

import entities.users.User;

import java.util.Scanner;

public abstract class UserInterface {
    private User user;

    public void showWelcome() {
        if (this.user != null) {
            System.out.println("Have a nice day, " + user.getName() + "!\n");
        }
    }

    public abstract void showMenu();

    public void enter() {
        showWelcome();
        showMenu();
    }

    private boolean readYesOrNo() {
        String input;
        Scanner scanner = new Scanner(System.in);
        boolean result = false;

        System.out.println("Please enter Y/N: ");

        while (true) {
            input = scanner.nextLine().trim().toUpperCase(); // Convert input to uppercase to handle both cases
            if (input.equals("Y")) {
                result = true;
                break;
            } else if (input.equals("N")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Y/N\n");
            }
        }

        return result;
    }
}