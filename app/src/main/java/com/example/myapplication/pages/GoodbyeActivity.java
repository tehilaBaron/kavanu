package com.example.myapplication.pages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Models.Appointment;
import com.example.myapplication.R;

public class GoodbyeActivity extends AppCompatActivity {
    public static final String KEY_APPOINTMENT = "KEY_APPOINTMENT";
    private TextView details;
    private Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodbye);
        findViews();
        initViews();
    }

    private void findViews() {
        details = findViewById(R.id.details);
    }

    private void initViews() {
        Intent previousIntent = getIntent();
        appointment = (Appointment) previousIntent.getSerializableExtra(KEY_APPOINTMENT);
        if (appointment != null) {
            details.setText(appointment.getClientName() + ", איזה כיף שנפגש ביום " + appointment.getDay().name() + " בשעה " + appointment.getStartHour());
        }
    }
}