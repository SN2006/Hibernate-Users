package com.example.service;

import com.example.controller.UserController;
import com.example.exceptions.OptionException;
import com.example.utils.AppStarter;
import com.example.utils.Constants;
import com.example.view.AppView;

public class AppService {

    private final UserController userController = new UserController();

    public void handleOption(String option) {
        switch (option) {
            case "0" -> new AppView().getOutput("0");
            case "1" -> userController.createUser();
            case "2" -> userController.readAllUsers();
            case "3" -> userController.updateUser();
            case "4" -> userController.deleteUser();
            case "5" -> userController.deleteAllUsers();
            case "6" -> userController.readUserById();
            case "7" -> userController.readUserByName();
            default -> {
                try {
                    throw new OptionException(Constants.INCORRECT_OPTION_MSG);
                }catch (OptionException e) {
                    new AppView().getOutput(e.getMessage());
                }
            }
        }
        AppStarter.startApp();
    }

}
