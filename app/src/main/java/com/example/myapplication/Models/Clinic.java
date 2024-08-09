package com.example.myapplication.Models;

import com.example.myapplication.Enums.TreatmentEnum;

import java.util.HashMap;

public class Clinic {
    private String name = "";
    private HashMap<String, Treatment> allTreatments = new HashMap<>();

    public Clinic() {
    }

    public String getName() {
        return name;
    }

    public Clinic setName(String name) {
        this.name = name;
        return this;
    }

    public HashMap<String, Treatment> getAllTreatments() {
        return allTreatments;
    }

    public Clinic setAllTreatments(HashMap<String, Treatment> allTreatments) {
        this.allTreatments = allTreatments;
        return this;
    }
}
