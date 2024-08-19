package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.Appointment;
import com.example.myapplication.R;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ManagerViewHolder> {
    private List<Appointment> appointmentList;

    public static class ManagerViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;
        public TextView timeTextView;
        public TextView clientTextView;

        public ManagerViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.text_view_appointment_date);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            clientTextView = itemView.findViewById(R.id.text_view_appointment_client);
        }
    }

    public ManagerAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public ManagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager, parent, false);
        return new ManagerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ManagerViewHolder holder, int position) {
        Appointment currentAppointment = appointmentList.get(position);
        holder.dateTextView.setText(currentAppointment.getDay().name());
        holder.timeTextView.setText(currentAppointment.getStartHour() + " - " + currentAppointment.getEndHour());
        holder.clientTextView.setText(currentAppointment.getClientName());

        // Bind more data as needed
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }
}
