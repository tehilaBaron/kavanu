package com.example.myapplication.Interfaces;

import com.example.myapplication.Enums.TreatmentEnum;

import java.util.List;

public interface CallBack_TreatmentSelected {
    void onTreatmentSelected(int totalSelectedTime, List<TreatmentEnum> treatmentList);
}
