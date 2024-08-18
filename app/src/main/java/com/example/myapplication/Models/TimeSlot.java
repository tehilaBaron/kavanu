package com.example.myapplication.Models;

public class TimeSlot {
    private String startTime;  // Start time of the slot (e.g., "09:00")
    private String endTime;    // End time of the slot (e.g., "10:00")
    private boolean isAvailable;  // Indicates if the slot is available or not

    public TimeSlot(String startTime, String endTime, boolean isAvailable) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime + (isAvailable ? " (Available)" : " (Unavailable)");
    }
}
