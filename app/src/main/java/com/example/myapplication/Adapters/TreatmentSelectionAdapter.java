package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.DataController;
import com.example.myapplication.Interfaces.CallBack_TreatmentSelected;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.R;

import java.util.List;

public class TreatmentSelectionAdapter extends RecyclerView.Adapter<TreatmentSelectionAdapter.TreatmentViewHolder> {
    private List<Treatment> treatmentList;
    private CallBack_TreatmentSelected listener;

    private DataController dataController;

    public TreatmentSelectionAdapter(List<Treatment> treatmentList, CallBack_TreatmentSelected listener, DataController dataController) {
        this.treatmentList = treatmentList;
        this.listener = listener;
        this.dataController = dataController;
    }

    @NonNull
    @Override
    public TreatmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treatment, parent, false);
        return new TreatmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreatmentViewHolder holder, int position) {
        Treatment treatment = treatmentList.get(position);
        holder.bind(treatment);
    }

    @Override
    public int getItemCount() {
        return treatmentList.size();
    }

    class TreatmentViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        private TextView nameTextView;

        TreatmentViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }

        void bind(Treatment treatment) {
            nameTextView.setText(treatment.getTreatmentName());
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(treatment.getIsChecked());

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                treatment.setIsChecked(isChecked);

                // Update the database using the passed DataController instance
                if (dataController != null) {
                    dataController.updateTreatmentSelection(treatment);
                }

                // Notify listener about the total selected time
                if (listener != null) {
                    int totalSelectedTime = calculateTotalSelectedTime();
                    listener.onTreatmentSelected(totalSelectedTime);
                }
            });
        }
    }

    private int calculateTotalSelectedTime() {
        int totalTime = 0;
        for (Treatment treatment : treatmentList) {
            if (treatment.getIsChecked()) {
                totalTime += treatment.getTreatmentTimeInMinuets();
            }
        }
        return totalTime;
    }

    private void updateDatabase(Treatment treatment) {
        // Code to update the database with the treatment's selected state
        dataController.updateTreatmentSelection(treatment);
    }
}