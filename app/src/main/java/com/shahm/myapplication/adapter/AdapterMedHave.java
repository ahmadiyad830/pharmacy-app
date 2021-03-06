package com.shahm.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ItemMedHaveBinding;
import com.shahm.myapplication.listeners.OnMedHaveClick;
import com.shahm.myapplication.model.Medicines;


import java.util.List;
//https://www.blacktools.io/sapi/?mydrug=1
public class AdapterMedHave extends RecyclerView.Adapter<AdapterMedHave.ViewHolder> {
    private LayoutInflater inflater;
    private List<Medicines> listMed;
    private OnMedHaveClick onMedHaveClick;

    public AdapterMedHave(List<Medicines> listMed, OnMedHaveClick onMedHaveClick) {
        this.listMed = listMed;
        this.onMedHaveClick = onMedHaveClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater==null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemMedHaveBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_med_have, parent,false);
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
        return listMed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ItemMedHaveBinding binding;

        public ViewHolder(@NonNull ItemMedHaveBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        private void bind(Medicines model){
            binding.setMedicines(model);
            binding.setPosition(String.valueOf(getAdapterPosition()));
            binding.container.setOnClickListener(v -> {
                listMed.get(getAdapterPosition()).setExpanded(!model.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
            binding.button.setOnClickListener(v -> {
                onMedHaveClick.onMedHavClick(model);
            });

        }
    }
}
