package com.example.myapplication.Models;

import java.util.HashMap;

public class Week {

    private String name = "";
    private HashMap<String, WorkDay> allWorkDays = new HashMap<>();

    public Week() {
    }

    public String getName() {
        return name;
    }

    public Week setName(String name) {
        this.name = name;
        return this;
    }

    public HashMap<String, WorkDay> getAllWorkDays() {
        return allWorkDays;
    }

    public Week setAllWorkDays(HashMap<String, WorkDay> allWorkDays) {
        this.allWorkDays = allWorkDays;
        return this;
    }
}
