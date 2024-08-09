package com.example.myapplication.Data;

import androidx.annotation.NonNull;

import com.example.myapplication.Enums.Day;
import com.example.myapplication.Models.Clinic;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.Models.Week;
import com.example.myapplication.Models.WorkDay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataController {

    private List<Treatment> treatmentList = null;
    private List<WorkDay> workDayList = null;


    public interface DataCallback<T> {
        void onDataReceived(T data);

        void onError(Exception e);
    }

    public void getTreatmentList(final DataCallback<List<Treatment>> callback) {
        if (treatmentList != null) {
            // Return cached data if already fetched
            callback.onDataReceived(treatmentList);
        } else {
            DatabaseReference clinicRef = FirebaseDatabase.getInstance().getReference("clinic");
            clinicRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Clinic clinic = dataSnapshot.getValue(Clinic.class);
                    List<Treatment> treatmentList = new ArrayList<>(clinic.getAllTreatments().values());
                    callback.onDataReceived(treatmentList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    callback.onError(error.toException());
                }
            });
        }
    }

    public void getWorkDayList(final DataCallback<List<WorkDay>> callback) {
        if (workDayList != null) {
            // Return cached data if already fetched
            callback.onDataReceived(workDayList);
        } else {
            DatabaseReference weekRef = FirebaseDatabase.getInstance().getReference("week");
            weekRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Week week = dataSnapshot.getValue(Week.class);
                    List<WorkDay> workDayList = new ArrayList<>(week.getAllWorkDays().values());
                    callback.onDataReceived(workDayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    callback.onError(error.toException());
                }
            });
        }
    }

    public void updateTreatmentSelection(Treatment treatment) {
        // Update the treatment's selection state in the Firebase Realtime Database
        DatabaseReference treatmentRef = FirebaseDatabase.getInstance().getReference("clinic").child("allTreatments").child(treatment.getKeyName());
        treatmentRef.child("isChecked").setValue(treatment.getTreatmentPrice());
    }
}
