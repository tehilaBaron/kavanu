package com.example.myapplication.pages;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.AvailableTimeSlotAdapter;
import com.example.myapplication.Data.DataController;
import com.example.myapplication.Enums.Day;
import com.example.myapplication.Enums.TreatmentEnum;
import com.example.myapplication.Interfaces.CallBack_AppointmentSelected;
import com.example.myapplication.Models.Appointment;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.Models.WorkDay;
import com.example.myapplication.R;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppointmentsActivity extends AppCompatActivity implements CallBack_AppointmentSelected {

    public static final String KEY_TIME = "KEY_TIME";
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_TREATMENT = "KEY_TREATMENT";
    private int totalTimeTreatment;
    private Map<Day, AppCompatButton> dayButtons;
    private RecyclerView recyclerView;
    private AvailableTimeSlotAdapter adapter;
    private DataController dataController;
    private LocalTime opening;
    private LocalTime closing;
    private Boolean isOpen;
    private Day selectedDayPublic;
    private ImageView closeImg;
    private TextView closeText;
    private ImageView fullImg;
    private TextView fullText;
    private String clientName;

    private List<TreatmentEnum> selectedTreatments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        findViews();
        initViews();
    }

    private void initViews() {
        selectedTreatments = new ArrayList<>();
        isOpen = true;
        dataController = new DataController();
        Intent previousIntent = getIntent();
        totalTimeTreatment = (int) previousIntent.getSerializableExtra(KEY_TIME);
        clientName = (String) previousIntent.getSerializableExtra(KEY_NAME);
        ArrayList<String> treatments = previousIntent.getStringArrayListExtra(KEY_TREATMENT);

        if (treatments != null) {
            for (String name : treatments) {
                selectedTreatments.add(TreatmentEnum.valueOf(name));
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showAvailableTimeSlots(Day.SUNDAY);
        for (Map.Entry<Day, AppCompatButton> entry : dayButtons.entrySet()) {
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
        dayButtons.put(Day.TUESDAY, findViewById(R.id.btnTuesday));
        dayButtons.put(Day.WEDNESDAY, findViewById(R.id.btnWednesday));
        dayButtons.put(Day.THURSDAY, findViewById(R.id.btnThursday));
        dayButtons.put(Day.FRIDAY, findViewById(R.id.btnFriday));
        dayButtons.put(Day.SATURDAY, findViewById(R.id.btnSaturday));
        closeImg = findViewById(R.id.closeImg);
        closeText = findViewById(R.id.closeText);
        fullImg = findViewById(R.id.fullImg);
        fullText = findViewById(R.id.fullText);
    }

    private void showAvailableTimeSlots(Day selectedDay) {
        selectedDayPublic = selectedDay;
        getOpeningAndClosingHours(selectedDay, new DataController.DataCallback<Void>() {
            @Override
            public void onDataReceived(Void result) {
                // This is where you can safely use isOpen after it has been set
                if (isOpen) {

                    List<String> availableStartHours = new ArrayList<>();
                    dataController.getAppointmentList(new DataController.DataCallback<List<Appointment>>() {
                        @Override
                        public void onDataReceived(List<Appointment> appointmentList) {
                            List<Appointment> dayAppointments = getAppointmentsForDay(selectedDay, appointmentList);
                            dayAppointments.sort(Comparator.comparing(Appointment::getStartHour));

                            LocalTime currentTime = opening;
                            for (Appointment appointment : dayAppointments) {
                                LocalTime appointmentStart = LocalTime.parse(appointment.getStartHour());

                                while (currentTime.plusMinutes(totalTimeTreatment).isBefore(appointmentStart)) {
                                    availableStartHours.add(currentTime.toString());
                                    currentTime = currentTime.plusMinutes(totalTimeTreatment);
                                }

                                currentTime = LocalTime.parse(appointment.getEndHour());
                            }
                            while (currentTime.plusMinutes(totalTimeTreatment).isBefore(closing)) {
                                availableStartHours.add(currentTime.toString());
                                currentTime = currentTime.plusMinutes(totalTimeTreatment);
                            }
                            if (availableStartHours.isEmpty()) {
                                recyclerView.setVisibility(View.GONE);
                                closeImg.setVisibility(View.GONE);
                                closeText.setVisibility(View.GONE);
                                fullImg.setVisibility(View.VISIBLE);
                                fullText.setVisibility(View.VISIBLE);
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                closeImg.setVisibility(View.INVISIBLE);
                                closeText.setVisibility(View.INVISIBLE);
                                fullImg.setVisibility(View.INVISIBLE);
                                fullText.setVisibility(View.INVISIBLE);
                            }
                            adapter = new AvailableTimeSlotAdapter(availableStartHours, AppointmentsActivity.this);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onError(Exception e) {
                            // Handle the error
                        }
                    });
                } else {
                    recyclerView.setVisibility(View.GONE);
                    closeImg.setVisibility(View.VISIBLE);
                    closeText.setVisibility(View.VISIBLE);
                    fullImg.setVisibility(View.GONE);
                    fullText.setVisibility(View.GONE);
                }
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

    @Override
    public void onAppointmentSelected(String startHour) {
        Appointment newAppointment = new Appointment();
        newAppointment.setStartHour(startHour);
        newAppointment.setDay(selectedDayPublic);
        newAppointment.setClientName(clientName);
        newAppointment.setTreatments(selectedTreatments);
        newAppointment.setEndHour(LocalTime.parse(startHour).plusMinutes(totalTimeTreatment).toString());
        dataController.addAppointmentToDatabase(newAppointment);
        moveToGoodbyeintent(newAppointment);
    }

    private void moveToGoodbyeintent(Appointment newAppointment) {
        Intent intent = new Intent(this, GoodbyeActivity.class);
        intent.putExtra(GoodbyeActivity.KEY_APPOINTMENT, newAppointment);
        startActivity(intent);
        finish();

    }

    private void getOpeningAndClosingHours(Day selectedDay, DataController.DataCallback<Void> callback) {
        dataController.getWorkDayList(new DataController.DataCallback<List<WorkDay>>() {
            @Override
            public void onDataReceived(List<WorkDay> workDayList) {
                for (WorkDay workDay : workDayList) {
                    if (workDay.getDayEnum() == selectedDay) {
                        if (workDay.getIsOpen()) {
                            opening = LocalTime.parse(workDay.getStartHour());
                            closing = LocalTime.parse(workDay.getEndHour());
                        }
                        isOpen = workDay.getIsOpen();
                    }
                }
                if (callback != null) {
                    callback.onDataReceived(null);
                }
            }

            @Override
            public void onError(Exception e) {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }

}
