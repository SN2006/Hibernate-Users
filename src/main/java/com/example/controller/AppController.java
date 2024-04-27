package com.example.controller;

import com.example.service.AppService;
import com.example.view.AppView;

public class AppController {

    private final AppView view;
    private final AppService service;

    public AppController(AppView view, AppService service) {
        this.view = view;
        this.service = service;
    }

    public void runApp(){
        service.handleOption(view.getOption());
    }
}
