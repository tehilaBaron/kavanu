package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.TreatmentAdapter;
import com.example.myapplication.Data.DataController;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.R;

import java.util.List;

public class PriceListFragment extends Fragment {
    private RecyclerView recyclerView;
    private DataController dataController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(com.example.myapplication.R.layout.fragment_price_list_tab, container, false);
        findViews(v);
        initViews();
        return v;
    }

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataController = new DataController();
        dataController.getTreatmentList(new DataController.DataCallback<List<Treatment>>() {
            @Override
            public void onDataReceived(List<Treatment> treatmentList) {
                recyclerView.setAdapter(new TreatmentAdapter(treatmentList));
            }

            @Override
            public void onError(Exception e) {
                // Handle the error
            }
        });
    }

    private void findViews(View v) {
        recyclerView = v.findViewById(R.id.recyclerView);
    }
}
