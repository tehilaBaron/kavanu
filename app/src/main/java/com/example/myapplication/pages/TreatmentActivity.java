package com.example.myapplication.pages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.TreatmentSelectionAdapter;
import com.example.myapplication.Data.DataController;
import com.example.myapplication.Interfaces.CallBack_TreatmentSelected;
import com.example.myapplication.Models.Appointment;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.R;

import java.util.List;

public class TreatmentActivity extends AppCompatActivity implements CallBack_TreatmentSelected {

    private TextView totalTimeTextView;
    private RecyclerView recyclerView;
    private TreatmentSelectionAdapter adapter;
    private DataController dataController;
    private Button BTN_oppointment;
    private TextView titleServices;

    private int totalSelectedTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatmant);
        findViews();
        initViews();


    }

    private void initViews() {
        dataController = new DataController();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataController.getTreatmentList(new DataController.DataCallback<List<Treatment>>() {
            @Override
            public void onDataReceived(List<Treatment> treatmentList) {
                adapter = new TreatmentSelectionAdapter(treatmentList, TreatmentActivity.this, dataController);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Exception e) {
                // Handle error
            }
        });
        BTN_oppointment.setOnClickListener(v -> changeToAppointmentActivity());
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        totalTimeTextView = findViewById(R.id.totalTimeTextView);
        titleServices = findViewById(R.id.titleServices);
        BTN_oppointment = findViewById(R.id.BTN_oppointment);
    }


    @Override
    public void onTreatmentSelected(int time) {
        totalSelectedTime = time;
        totalTimeTextView.setText("זמן טיפול: " + time + " דקות");
    }

    private void changeToAppointmentActivity() {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        intent.putExtra(AppointmentsActivity.KEY_TIME, totalSelectedTime);
        startActivity(intent);
        finish();
    }


}
















