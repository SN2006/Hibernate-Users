package com.example.view;

import java.util.Scanner;

public class DeleteAllUsersView {

    private final Scanner INPUT = new Scanner(System.in);

    public String getAgreement(){
        System.out.println("Are you sure? (Y/N): ");
        return INPUT.nextLine();
    }

    public void getOutput(String output){
        System.out.println(output);
    }

}
