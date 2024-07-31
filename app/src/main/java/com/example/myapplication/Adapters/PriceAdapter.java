package com.example.pricelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.Price;
import com.example.myapplication.R;

import java.util.List;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder> {

    private List<Price> priceList;

    public PriceAdapter(List<Price> priceList) {
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_price, parent, false);
        return new PriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        Price price = priceList.get(position);
        holder.itemName.setText(price.getItemName());
        holder.itemPrice.setText(price.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    public static class PriceViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemPrice;

        public PriceViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }
    }
}
