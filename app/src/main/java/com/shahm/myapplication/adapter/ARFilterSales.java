package com.shahm.myapplication.adapter;

import android.content.Context;
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
    private List<SalesMed> listMed;
    private List<SalesMed> filterList;


    public ARFilterSales(List<SalesMed> listMed, OnSalesClick salesClick) {
        this.listMed = listMed;
        filterList = this.listMed;
        this.salesClick = salesClick;
    }

    private OnSalesClick salesClick;
    private Context context;

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        context = parent.getContext();
        ItemSalesBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_sales, parent, false);
        return new ViewHolde(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        holder.bind(listMed.get(position));
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }
    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()){
                    filterList = listMed;
                }else {
                    List<SalesMed> list = new ArrayList<>();
                    for(SalesMed model:listMed){
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
                filterList = (ArrayList<SalesMed>)results.values;
                notifyDataSetChanged();
            }
        };
    }

//    private Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<SalesMed> list = new ArrayList<>();
//
//
//            if (constraint == null || constraint.toString().length() == 0) {
//
//                list.addAll(listMed);
//            } else {
//                for (SalesMed model : list) {
//                    if (model.getName().contains(constraint.toString())) {
//                        list.add(model);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//
//            results.values = list;
//            results.count = list.size();
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            moveList.clear();
//            int oldSize = moveList.size();
//
//            moveList.addAll((Collection<? extends SalesMed>) results.values);
//            notifyDataSetChanged();
//            Toast.makeText(context, "" + getItemCount() + "", Toast.LENGTH_SHORT).show();
//        }
//    };

    public class ViewHolde extends RecyclerView.ViewHolder {
        private ItemSalesBinding binding;

        public ViewHolde(ItemSalesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(SalesMed model) {
            binding.setModel(model);
            binding.getRoot().setOnClickListener(v -> {
                salesClick.onItemClick(model);
            });
        }
    }
}
