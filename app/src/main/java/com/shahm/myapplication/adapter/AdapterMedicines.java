package com.shahm.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.listeners.OnMedClick;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ItemMedicinesBinding;
import com.shahm.myapplication.model.Medicines;

import java.util.List;

public class AdapterMedicines extends RecyclerView.Adapter<AdapterMedicines.ViewHolder> {
    private LayoutInflater inflater;
    private List<Medicines> listMedicines;
    private OnMedClick itemClick;
    public AdapterMedicines(List<Medicines> listMedicines, OnMedClick onMedClick) {
        this.listMedicines = listMedicines;
        itemClick = onMedClick;
    }

    public void setListMedicines(List<Medicines> listMedicines) {
        this.listMedicines = listMedicines;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemMedicinesBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_medicines, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listMedicines.get(position));
        boolean isExpanded = listMedicines.get(position).isExpanded();
        holder.binding.constrainExpanded.setVisibility(isExpanded? View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return listMedicines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMedicinesBinding binding;

        public ViewHolder(ItemMedicinesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Medicines model) {
            binding.setMedicines(model);
            binding.setPosition(String.valueOf(getAdapterPosition()));
            binding.container.setOnClickListener(v -> {
                listMedicines.get(getAdapterPosition()).setExpanded(!model.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
            binding.btnDetails.setOnClickListener(v -> {

                itemClick.onItemClick(model);
            });
        }
    }
}
