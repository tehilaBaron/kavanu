package com.example.myapplication.Models;

import com.example.myapplication.Enums.Day;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class WorkDay {

    private String dayName = "";
    private Day dayEnum = Day.SUNDAY;
    private String startHour = "";
    private String endHour = "";
    private Boolean isOpen = Boolean.TRUE;

    public WorkDay() {
    }

    public String getDayName() {
        return dayName;
    }

    public WorkDay setDayName(String dayName) {
        this.dayName = dayName;
        return this;
    }

    public Day getDayEnum() {
        return dayEnum;
    }

    public WorkDay setDayEnum(Day dayEnum) {
        this.dayEnum = dayEnum;
        return this;
    }

    public String getStartHour() {
        return startHour;
    }

    public WorkDay setStartHour(String startHour) {
        this.startHour = startHour;
        return this;
    }

    public String getEndHour() {
        return endHour;
    }

    public WorkDay setEndHour(String endHour) {
        this.endHour = endHour;
        return this;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public WorkDay setIsOpen(Boolean open) {
        isOpen = open;
        return this;
    }
}
