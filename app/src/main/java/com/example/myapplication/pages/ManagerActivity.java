package com.example.myapplication.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.ManagerAdapter;
import com.example.myapplication.Data.DataController;
import com.example.myapplication.Models.Appointment;
import com.example.myapplication.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ManagerAdapter adapter;
    private List<Appointment> appointmentList;
    private DataController dataController;
    private AppCompatImageButton back_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        findViews();
        initViews();
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler_view_appointments);
        back_BTN = findViewById(R.id.back_BTN);
    }

    private void initViews() {
        dataController = new DataController();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        back_BTN.setOnClickListener(v -> changeToMainActivity());
        fetchAppointmentsFromDatabase();
    }

    private void changeToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void fetchAppointmentsFromDatabase() {
        dataController.getAppointmentList(new DataController.DataCallback<List<Appointment>>() {
            @Override
            public void onDataReceived(List<Appointment> appointments) {
                appointmentList = appointments;
                List<String> daysOrder = Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
                Collections.sort(appointments, new Comparator<Appointment>() {
                    @Override
                    public int compare(Appointment a1, Appointment a2) {
                        int dayCompare = Integer.compare(
                                daysOrder.indexOf(a1.getDay().name()),
                                daysOrder.indexOf(a2.getDay().name())
                        );
                        if (dayCompare == 0) {
                            return a1.getStartHour().compareTo(a2.getStartHour());
                        }
                        return dayCompare;
                    }
                });
                adapter = new ManagerAdapter(appointmentList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }
}