package com.example.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Models.Price;
import com.example.myapplication.R;
import com.example.pricelist.PriceAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class PriceListFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price_list_tab, container, false);
        findViews(v);
        initViews();
        return v;
    }

    private void initViews() {
        List<Price> priceList = Arrays.asList(
                new Price("לק ג'ל", "130"),
                new Price("תיקון מבנה אנטומי", "150"),
                new Price("ציור פשוט", "5"),
                new Price("ציור רגיל", "10"),
                new Price("ציור מורכב", "20"),
                new Price("בניה בפוליג'ל", "280"),
                new Price("מילוי בפוליג'ל", "180"),
                new Price("השלמת ציפורן", "15")
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new PriceAdapter(priceList));
    }


    private void findViews(View v) {
        recyclerView = v.findViewById(R.id.recyclerView);
    }
}