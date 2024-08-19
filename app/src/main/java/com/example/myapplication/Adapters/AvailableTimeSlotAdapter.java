package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interfaces.CallBack_AppointmentSelected;
import com.example.myapplication.Models.Appointment;
import com.example.myapplication.R;

import java.util.List;

public class AvailableTimeSlotAdapter extends RecyclerView.Adapter<AvailableTimeSlotAdapter.ViewHolder> {

    private List<String> availableAppointments;
    private CallBack_AppointmentSelected listener;

    public AvailableTimeSlotAdapter(List<String> availableAppointments, CallBack_AppointmentSelected listener) {
        this.availableAppointments = availableAppointments;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String appointment = availableAppointments.get(position);
        holder.startTimeBtn.setText(appointment);
        holder.startTimeBtn.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAppointmentSelected(appointment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return availableAppointments == null ? 0 : availableAppointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button startTimeBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startTimeBtn = itemView.findViewById(R.id.startTimeBtn);
        }
    }
}