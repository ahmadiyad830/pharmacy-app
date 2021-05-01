package com.shahm.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ItemSalesBinding;
import com.shahm.myapplication.listeners.OnSalesClick;
import com.shahm.myapplication.model.SalesMed;

import java.util.ArrayList;
import java.util.List;

public class ARFilterSales extends RecyclerView.Adapter<ARFilterSales.ViewHolde> implements Filterable {
    private LayoutInflater inflater;
    private List<SalesMed> filterList;
    private List<SalesMed> medListAll;
    public ARFilterSales(List<SalesMed> filterList, OnSalesClick model) {
        this.filterList = filterList;
        this.medListAll = new ArrayList<>(filterList);
        this.model = model;
    }

    public void setAllListMed(List<SalesMed> allListMed) {
        filterList = allListMed;
    }

    private OnSalesClick model;

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        //        run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SalesMed> filterList = new ArrayList<>();
            if (constraint==null|| constraint.length()==0){
                filterList.addAll(medListAll);
//                notifyDataSetChanged();
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SalesMed model: medListAll) {
                    if (model.getName().toLowerCase().contains(filterPattern)){
                        filterList.add(model);
                    }
                }
            }
//            if (constraint.toString().isEmpty()) {
//                filterList.addAll(medListAll);
//            } else {
//                for (SalesMed model : medListAll) {
//                    if (model.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
//                        filterList.add(model);
//                    }
//                }
//            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        //            run on ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterList.clear();
            filterList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemSalesBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_sales, parent, false);
        return new ViewHolde(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        holder.bind(filterList.get(position));
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        private ItemSalesBinding binding;

        public ViewHolde(ItemSalesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(SalesMed model) {
            binding.setModel(model);
        }

    }
}
