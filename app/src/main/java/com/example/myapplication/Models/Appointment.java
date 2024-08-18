package com.example.myapplication.Models;

import com.example.myapplication.Enums.Day;
import com.example.myapplication.Enums.TreatmentEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Appointment {
    private String clientName = "";
    private String startHour = "";
    private String endHour = "";
    private Day day = Day.SUNDAY;
    private List<TreatmentEnum> treatments = Collections.emptyList();
    ;

    public Appointment() {
    }

    public String getClientName() {
        return clientName;
    }

    public Appointment setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getStartHour() {
        return startHour;
    }

    public Appointment setStartHour(String startHour) {
        this.startHour = startHour;
        return this;
    }

    public String getEndHour() {
        return endHour;
    }

    public Appointment setEndHour(String endHour) {
        this.endHour = endHour;
        return this;
    }


    public Day getDay() {
        return day;
    }

    public Appointment setDay(Day day) {
        this.day = day;
        return this;
    }

    public List<TreatmentEnum> getTreatments() {
        return treatments;
    }

    public Appointment setTreatments(List<TreatmentEnum> treatments) {
        this.treatments = treatments;
        return this;
    }
}
