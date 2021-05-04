package com.shahm.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ItemAnalyzeDrugBinding;
import com.shahm.myapplication.model.AnalyzeDrug;

import java.util.List;

public class ARAnalyzeDrug extends RecyclerView.Adapter<ARAnalyzeDrug.ViewHolder> {
    private List<AnalyzeDrug> listDrug;
    private LayoutInflater inflater;

    public ARAnalyzeDrug(List<AnalyzeDrug> listDrug) {
        this.listDrug = listDrug;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemAnalyzeDrugBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_analyze_drug, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listDrug.get(position));
    }

    @Override
    public int getItemCount() {
        return listDrug.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemAnalyzeDrugBinding binding;

        public ViewHolder(@NonNull ItemAnalyzeDrugBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(AnalyzeDrug model) {
            binding.setModel(model);
        }

    }
}
