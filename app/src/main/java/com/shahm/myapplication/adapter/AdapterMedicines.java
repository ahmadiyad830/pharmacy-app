package com.shahm.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ItemMedicinesBinding;
import com.shahm.myapplication.listeners.OnMedClick;
import com.shahm.myapplication.model.Medicines;

import java.util.ArrayList;
import java.util.List;
//https://www.blacktools.io/sapi/?alldrug=1
public class AdapterMedicines extends RecyclerView.Adapter<AdapterMedicines.ViewHolder> implements Filterable {
    private LayoutInflater inflater;
    private List<Medicines> listMed;
    private List<Medicines> filterList;
    private OnMedClick itemClick;

    public AdapterMedicines(List<Medicines> listMed, OnMedClick onMedClick) {
        this.listMed = listMed;
        filterList = this.listMed;
        itemClick = onMedClick;

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
        holder.bind(listMed.get(position));
        boolean isExpanded = listMed.get(position).isExpanded();
        holder.binding.constrainExpanded.setVisibility(isExpanded? View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    @Override
    public Filter getFilter() {
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()){
                    filterList = listMed;
                }else {
                    List<Medicines> list = new ArrayList<>();
                    for(Medicines model:listMed){
                        if (model.getName().toLowerCase().contains(key.toLowerCase())){
                            list.add(model);
                        }
                    }
                    filterList= list;
                }
                FilterResults results = new FilterResults();
                results.values = filterList;
                results.count = filterList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (ArrayList<Medicines>)results.values;
                notifyDataSetChanged();
            }
        };
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
                listMed.get(getAdapterPosition()).setExpanded(!model.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
            binding.btnDetails.setOnClickListener(v -> {
                itemClick.clickDetails(model);
            });
        }
    }
}
