package com.example.myapplication.Models;


import com.example.myapplication.Enums.TreatmentEnum;

public class Treatment {

    private String treatmentName = "";
    private Long treatmentPrice = 0L;
    private Long treatmentTimeInMinuets = 0L;
    private Boolean isChecked = Boolean.FALSE;
    private TreatmentEnum type = TreatmentEnum.GEL;

    public Treatment() {
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public Treatment setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
        return this;
    }

    public Long getTreatmentPrice() {
        return treatmentPrice;
    }

    public Treatment setTreatmentPrice(Long treatmentPrice) {
        this.treatmentPrice = treatmentPrice;
        return this;
    }

    public Long getTreatmentTimeInMinuets() {
        return treatmentTimeInMinuets;
    }

    public Treatment setTreatmentTimeInMinuets(Long treatmentTimeInMinuets) {
        this.treatmentTimeInMinuets = treatmentTimeInMinuets;
        return this;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public Treatment setIsChecked(Boolean checked) {
        isChecked = checked;
        return this;
    }

    public TreatmentEnum getType() {
        return type;
    }

    public Treatment setType(TreatmentEnum type) {
        this.type = type;
        return this;
    }
}
