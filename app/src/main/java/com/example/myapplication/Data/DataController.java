package com.example.myapplication.Data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.Models.Appointment;
import com.example.myapplication.Models.Appointments;
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
import java.util.List;

public class DataController {

    private List<Treatment> treatmentList = null;
    private List<WorkDay> workDayList = null;
    private List<Appointment> appointmentList = null;


    public interface DataCallback<T> {
        void onDataReceived(T data);
        void onError(Exception e);
    }

    public void getTreatmentList(final DataCallback<List<Treatment>> callback) {
        if (treatmentList != null) {
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

    public void getAppointmentList(final DataCallback<List<Appointment>> callback) {
        if (appointmentList != null) {

            callback.onDataReceived(appointmentList);
        } else {
            DatabaseReference appointmentRef = FirebaseDatabase.getInstance().getReference("appointment");
            appointmentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Appointments appointments = dataSnapshot.getValue(Appointments.class);
                    List<Appointment> appointmentList = new ArrayList<>(appointments.getAllAppointments().values());
                    callback.onDataReceived(appointmentList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    callback.onError(error.toException());
                }
            });
        }
    }

    public void addAppointmentToDatabase(Appointment appointment) {
        // Generate a unique key for the new appointment
        String appointmentId = FirebaseDatabase.getInstance().getReference("appointment").push().getKey();

        if (appointmentId != null) {
            // Save the new appointment under the generated unique key
            FirebaseDatabase.getInstance().getReference("appointment")
                    .child("allAppointments")
                    .child(appointmentId)
                    .setValue(appointment)
                    .addOnSuccessListener(aVoid -> {
                        // Handle success
                        Log.d("DataController", "Appointment added successfully");
                    })
                    .addOnFailureListener(e -> {
                        // Handle failure
                        Log.e("DataController", "Failed to add appointment", e);
                    });
        }
    }


    public void updateTreatmentSelection(Treatment treatment) {
        DatabaseReference treatmentRef = FirebaseDatabase.getInstance().getReference("clinic").child("allTreatments").child(treatment.getKeyName());
        treatmentRef.child("isChecked").setValue(treatment.getTreatmentPrice());
    }
}
