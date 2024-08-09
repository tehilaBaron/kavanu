package com.example.myapplication.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.WorkDay;
import com.example.myapplication.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class WorkDayAdapter extends RecyclerView.Adapter<WorkDayAdapter.WorkDayViewHolder> {

    private final List<WorkDay> workDays;

    public WorkDayAdapter(List<WorkDay> workDays) {
        this.workDays = workDays;
    }

    @NonNull
    @Override
    public WorkDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_day, parent, false);
        return new WorkDayViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull WorkDayViewHolder holder, int position) {
        WorkDay workDay = getItem(position);
        holder.dayTitle.setText(workDay.getDayName());
        holder.status.setText(!workDay.getIsOpen() ? "סגור" : "");

        if (workDay.getIsOpen()) {
            holder.startHour.setVisibility(View.VISIBLE);
            holder.endHour.setVisibility(View.VISIBLE);
            holder.startHour.setText(workDay.getStartHour());
            holder.endHour.setText(workDay.getEndHour() + " - ");
        } else {
            holder.startHour.setVisibility(View.GONE);
            holder.endHour.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return workDays == null ? 0 : workDays.size();
    }


    private WorkDay getItem(int position) {
        return workDays.get(position);
    }

    static class WorkDayViewHolder extends RecyclerView.ViewHolder {
        private final TextView dayTitle;
        private final TextView startHour;

        private final TextView endHour;
        private final TextView status;

        WorkDayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTitle = itemView.findViewById(R.id.dayTitle);
            startHour = itemView.findViewById(R.id.startHour);
            status = itemView.findViewById(R.id.status);
            endHour = itemView.findViewById(R.id.endHour);
        }
    }
}