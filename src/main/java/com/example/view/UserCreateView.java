package com.example.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserCreateView {

    private final Scanner INPUT = new Scanner(System.in);

    public Map<String, String> getData() {
        System.out.println("\nCREATE FORM");
        Map<String, String> map = new HashMap<>();
        System.out.print("Input name: ");
        map.put("name", INPUT.nextLine().trim());
        System.out.print("Input email in format example@gmail.com: ");
        map.put("email", INPUT.nextLine().trim());
        return map;
    }

    public void getOutput(String output) {
        System.out.println(output);
    }

}
