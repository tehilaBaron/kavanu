package com.example.myapplication.Models;

import java.util.HashMap;

public class Appointments {
    private String name = "";
    private HashMap<String, Appointment> allAppointments = new HashMap<>();

    public Appointments() {
    }

    public String getName() {
        return name;
    }

    public Appointments setName(String name) {
        this.name = name;
        return this;
    }

    public HashMap<String, Appointment> getAllAppointments() {
        return allAppointments;
    }

    public Appointments setAllAppointments(HashMap<String, Appointment> allAppointments) {
        this.allAppointments = allAppointments;
        return this;
    }
}
