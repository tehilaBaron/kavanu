package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    }

    @Override
    public int getItemCount() {
        return treatmentList == null ? 0 : treatmentList.size();
    }

    private Treatment getItem(int position) {
        return treatmentList.get(position);
    }

    public static class TreatmentViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemPrice;


        public TreatmentViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);

        }
    }
}
