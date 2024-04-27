package com.example.view;

import com.example.utils.Constants;

import java.util.Scanner;

public class AppView {

    private final Scanner INPUT = new Scanner(System.in);

    public String getOption() {
        getMenu();
        return INPUT.next();
    }

    private void getMenu() {
        System.out.print("""

                OPTIONS:
                1 - Create user.
                2 - Read users.
                3 - Update user.
                4 - Delete user.
                5 - Delete all users.
                6 - Read user by id.
                7 - Read users by name.
                0 - Close the App.
                """);
        System.out.print("Input your option: ");
    }

    public void getOutput(String output) {
        if (output.equals("0")) {
            System.out.println(Constants.APP_CLOSE_MSG);
            System.exit(0);
        } else System.out.println(output);
    }

}
