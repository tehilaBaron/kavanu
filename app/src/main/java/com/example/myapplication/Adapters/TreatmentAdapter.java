package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interfaces.CallBack_TreatmentSelected;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.R;

import java.util.List;

public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder> {

    private List<Treatment> treatmentList;

    public TreatmentAdapter(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;

    }

    @NonNull
    @Override
    public TreatmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_price, parent, false);
        return new TreatmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreatmentViewHolder holder, int position) {
        Treatment treatment = getItem(position);
        holder.itemName.setText(treatment.getTreatmentName());
        holder.itemPrice.setText(String.valueOf(treatment.getTreatmentPrice()));
//        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                treatmentCallback.onTreatmentSelected(getTotalSelectedTime());
//            } else {
//                treatmentCallback.onTreatmentSelected(getTotalSelectedTime());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return treatmentList == null ? 0 : treatmentList.size();
    }

    public int getTotalSelectedTime() {
        int total = 0;
//        for (int i = 0; i < getItemCount(); i++) {
//            if (treatmentList.get(i).isChecked()) {
//                total += treatmentList.get(i).getTreatmentTimeInMinuets();
//            }
//        }
        return total;
    }

    private Treatment getItem(int position) {
        return treatmentList.get(position);
    }

    public static class TreatmentViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemPrice;
        //CheckBox checkBox;

        public TreatmentViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            //  checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
