package com.example.myapplication.pages;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.AvailableTimeSlotAdapter;
import com.example.myapplication.Data.DataController;
import com.example.myapplication.Enums.Day;
import com.example.myapplication.Models.Appointment;
import com.example.myapplication.Models.WorkDay;
import com.example.myapplication.R;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentsActivity extends AppCompatActivity {

    public static final String KEY_TIME = "KEY_TIME";
    private int totalTimeTreatment;
    private Map<Day, Button> dayButtons;
    private RecyclerView recyclerView;
    private AvailableTimeSlotAdapter adapter;
    private DataController dataController;
    private LocalTime opening;
    private LocalTime closing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oppointments);
        findViews();
        initViews();
    }

    private void initViews() {
        dataController = new DataController();
        Intent previousIntent = getIntent();
        totalTimeTreatment = (int) previousIntent.getSerializableExtra(KEY_TIME);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (Map.Entry<Day, Button> entry : dayButtons.entrySet()) {
            final Day day = entry.getKey();
            entry.getValue().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAvailableTimeSlots(day);
                }
            });
        }
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        dayButtons = new HashMap<>();
        dayButtons.put(Day.SUNDAY, findViewById(R.id.btnSunday));
        dayButtons.put(Day.MONDAY, findViewById(R.id.btnMonday));
//        dayButtons.put(Day.TUESDAY, findViewById(R.id.btnTuesday));
//        dayButtons.put(Day.WEDNESDAY, findViewById(R.id.btnWednesday));
//        dayButtons.put(Day.THURSDAY, findViewById(R.id.btnThursday));
//        dayButtons.put(Day.FRIDAY, findViewById(R.id.btnFriday));
//        dayButtons.put(Day.SATURDAY, findViewById(R.id.btnSaturday));
    }

    private void showAvailableTimeSlots(Day selectedDay) {
        getOpeningAndClosingHours(selectedDay);
        List<String> availableStartHours = new ArrayList<>();
        dataController.getAppointmentList(new DataController.DataCallback<List<Appointment>>() {
            @Override
            public void onDataReceived(List<Appointment> appointmentList) {
                List<Appointment> dayAppointments = getAppointmentsForDay(selectedDay, appointmentList);
                dayAppointments.sort(Comparator.comparing(Appointment::getStartHour));

                LocalTime currentTime = opening;
                for (Appointment appointment : dayAppointments) {
                    LocalTime appointmentStart = LocalTime.parse(appointment.getStartHour());

                    // Check every possible time slot until the start of the next appointment
                    while (currentTime.plusMinutes(totalTimeTreatment).isBefore(appointmentStart)) {
                        availableStartHours.add(currentTime.toString());
                        currentTime = currentTime.plusMinutes(totalTimeTreatment);
                    }

                    // Move the current time to the end of this appointment
                    currentTime = LocalTime.parse(appointment.getEndHour());
                }
                // Check if there's enough time after the last appointment until closing time
                while (currentTime.plusMinutes(totalTimeTreatment).isBefore(closing)) {
                    availableStartHours.add(currentTime.toString());
                    currentTime = currentTime.plusMinutes(totalTimeTreatment);
                }
                adapter = new AvailableTimeSlotAdapter(availableStartHours);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Exception e) {
                // Handle the error
            }
        });


    }

    private List<Appointment> getAppointmentsForDay(Day day, List<Appointment> appointments) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDay() == day) {
                result.add(appointment);
            }
        }
        return result;
    }

    private void getOpeningAndClosingHours(Day selectedDay) {
        dataController.getWorkDayList(new DataController.DataCallback<List<WorkDay>>() {
            @Override
            public void onDataReceived(List<WorkDay> workDayList) {
                for (WorkDay workDay : workDayList) {
                    if (workDay.getDayEnum() == selectedDay && workDay.getIsOpen()) {
                        opening = LocalTime.parse(workDay.getStartHour());
                        closing = LocalTime.parse(workDay.getEndHour());
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                // Handle the error
            }
        });
    }
}
