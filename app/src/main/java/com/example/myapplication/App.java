package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.myapplication.Data.DataManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        saveDataToFireBase();
    }

    private void saveDataToFireBase() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference clinicRef = db.getReference("clinic");
        DatabaseReference weekRef = db.getReference("week");
        DatabaseReference appointmentRef = db.getReference("appointment");
        clinicRef.setValue(DataManager.createClinicWithTreatments());
        weekRef.setValue(DataManager.createWeekWithWorkDays());
        //appointmentRef.setValue(DataManager.createAppointments());
    }
}
