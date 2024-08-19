package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Adapters.WorkDayAdapter;
import com.example.myapplication.Data.DataController;
import com.example.myapplication.Models.Appointment;
import com.example.myapplication.Models.WorkDay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import com.example.myapplication.R;

public class workHoursFragment extends Fragment {

    private DataController dataController;
    private RecyclerView recyclerView;

    public workHoursFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_work_hours_tab, container, false);
        findViews(v);
        initViews();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<WorkDay> workDays = new ArrayList<>();


        WorkDayAdapter adapter = new WorkDayAdapter(workDays);
        recyclerView.setAdapter(adapter);

    }

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataController = new DataController();
        dataController.getWorkDayList(new DataController.DataCallback<List<WorkDay>>() {
            @Override
            public void onDataReceived(List<WorkDay> workDayList) {
                workDayList.sort(Comparator.comparing(WorkDay::getDayEnum));
                recyclerView.setAdapter(new WorkDayAdapter(workDayList));
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

    private void findViews(View v) {
        recyclerView = v.findViewById(R.id.recyclerView);
    }
}
