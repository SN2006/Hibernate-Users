package com.example.controller;

import com.example.service.UserService;
import com.example.view.*;

public class UserController {

    private final UserService userService = new UserService();

    public void createUser(){
        UserCreateView view = new UserCreateView();
        view.getOutput(userService.create(view.getData()));
    }

    public void readAllUsers(){
        UserReadView view = new UserReadView();
        view.getOutput(userService.read());
    }

    public void updateUser(){
        UserUpdateView view = new UserUpdateView();
        view.getOutput(userService.update(view.getData()));
    }

    public void deleteUser(){
        UserDeleteView view = new UserDeleteView();
        view.getOutput(userService.delete(view.getData()));
    }

    public void readUserById(){
        UserReadByIdView view = new UserReadByIdView();
        view.getOutput(userService.readUserById(view.getData()));
    }

    public void readUserByName(){
        UserReadByNameView view = new UserReadByNameView();
        view.getOutput(userService.readUserByName(view.getData()));
    }

    public void deleteAllUsers(){
        DeleteAllUsersView view = new DeleteAllUsersView();
        view.getOutput(userService.deleteAllUsers(view.getAgreement()));
    }

}
