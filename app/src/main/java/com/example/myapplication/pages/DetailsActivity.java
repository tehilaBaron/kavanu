package com.example.myapplication.pages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Adapters.ViewPagerAdapter;
import com.example.myapplication.Data.DataManager;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Button BTN_oppointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        findViews();
        initViews();
        saveDataToFireBase();
    }

    private void initViews() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("מחירון");
                            break;
                        case 1:
                            tab.setText("שעות עבודה");
                            break;
                        case 2:
                            tab.setText("מפה");
                            break;
                        case 3:
                            tab.setText("יצירת קשר");
                            break;
                    }
                }).attach();
        BTN_oppointment.setOnClickListener(v -> changeToTreatmentsActivity());

    }

    private void findViews() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        BTN_oppointment = findViewById(R.id.BTN_oppointment);

    }

    private void saveDataToFireBase() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference clinicRef = db.getReference("clinic");
        DatabaseReference weekRef = db.getReference("week");
        clinicRef.setValue(DataManager.createClinicWithTreatments());
        weekRef.setValue(DataManager.createWeekWithWorkDays());
    }

    private void changeToTreatmentsActivity() {
        Intent intent = new Intent(this, TreatmentActivity.class);
        startActivity(intent);
        finish();
    }
}
