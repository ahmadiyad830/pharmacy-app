package com.shahm.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ItemDepotBinding;
import com.shahm.myapplication.listeners.OnDepotClick;
import com.shahm.myapplication.model.Depot;

import java.util.List;

public class ARDepot extends RecyclerView.Adapter<ARDepot.ViewHolde> {
    private LayoutInflater inflater;
    private List<Depot> listDepot;
    private OnDepotClick onDepotClick;

    public ARDepot(List<Depot> listDepot,OnDepotClick onDepotClick) {
        this.listDepot = listDepot;
        this.onDepotClick = onDepotClick;
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemDepotBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_depot, parent, false);
        return new ViewHolde(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        holder.bind(listDepot.get(position));
        boolean isExpanded = listDepot.get(position).isExpanded();
        holder.binding.cardView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listDepot.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        private ItemDepotBinding binding;

        public ViewHolde(@NonNull ItemDepotBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Depot model) {
            binding.setModel(model);
            binding.container.setOnClickListener(v -> {
                listDepot.get(getAdapterPosition()).setExpanded(!model.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

        }

    }
}
