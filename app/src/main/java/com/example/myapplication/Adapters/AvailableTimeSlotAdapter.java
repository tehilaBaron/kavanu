package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.TimeSlot;
import com.example.myapplication.R;

import java.util.List;

public class AvailableTimeSlotAdapter extends RecyclerView.Adapter<AvailableTimeSlotAdapter.ViewHolder> {

    private List<String> availableAppointments;

    public AvailableTimeSlotAdapter(List<String> availableAppointments) {
        this.availableAppointments = availableAppointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String appointment = availableAppointments.get(position);
        holder.startTimeTextView.setText(appointment);
    }

    @Override
    public int getItemCount() {
        return availableAppointments == null ? 0 : availableAppointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView startTimeTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.startTimeTextView);
        }
    }
}