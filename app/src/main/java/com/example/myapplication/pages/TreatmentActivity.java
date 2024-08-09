package com.example.myapplication.pages;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.TreatmentAdapter;
import com.example.myapplication.Data.DataController;
import com.example.myapplication.Fragments.PriceListFragment;
import com.example.myapplication.Interfaces.CallBack_TreatmentSelected;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TreatmentActivity extends AppCompatActivity implements CallBack_TreatmentSelected {

    private TextView totalTimeTextView;
    private RecyclerView recyclerView;

    private DataController dataController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        findViews();

    }

    private void initViews() {
        dataController = new DataController();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataController.getTreatmentList(new DataController.DataCallback<List<Treatment>>() {
            @Override
            public void onDataReceived(List<Treatment> treatmentList) {
                recyclerView.setAdapter(new TreatmentAdapter(treatmentList));
            }

            @Override
            public void onError(Exception e) {
                // Handle error
            }
        });

    }

    private void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        totalTimeTextView = findViewById(R.id.totalTimeTextView);
    }


    @Override
    public void onTreatmentSelected(int totalSelectedTime) {
        totalTimeTextView.setText("Total Time: " + totalSelectedTime + " minutes");
    }


}
















