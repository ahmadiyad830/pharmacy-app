package com.shahm.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ItemAnalyzEdepotBinding;
import com.shahm.myapplication.listeners.OnDepotClick;
import com.shahm.myapplication.model.AnalyzeDepot;

import java.util.List;
//https://www.blacktools.io/sapi/?store=%D8%B7%D9%86%D9%88%D8%B3&month=3
public class ARAnalyzeDepot extends RecyclerView.Adapter<ARAnalyzeDepot.ViewHolde> {
    private LayoutInflater inflater;
    private List<AnalyzeDepot> listAnalyzeDepot;
    private OnDepotClick<AnalyzeDepot> onDepotClick;

    public ARAnalyzeDepot(List<AnalyzeDepot> listAnalyzeDepot, OnDepotClick<AnalyzeDepot> onDepotClick) {
        this.listAnalyzeDepot = listAnalyzeDepot;
        this.onDepotClick = onDepotClick;
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemAnalyzEdepotBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_analyz_edepot, parent, false);
        return new ViewHolde(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        holder.bind(listAnalyzeDepot.get(position));
        boolean isExpanded = listAnalyzeDepot.get(position).isExpanded();
        holder.binding.cardView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listAnalyzeDepot.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        private ItemAnalyzEdepotBinding binding;

        public ViewHolde(@NonNull ItemAnalyzEdepotBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(AnalyzeDepot model) {
            binding.setModel(model);
            binding.container.setOnClickListener(v -> {
                listAnalyzeDepot.get(getAdapterPosition()).setExpanded(!model.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
            binding.goDetails.setOnClickListener(v -> {
                onDepotClick.onItemClick(model);
            });

        }

    }
}
