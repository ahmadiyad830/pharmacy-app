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
import com.shahm.myapplication.databinding.ItemPharmacySalesBinding;
import com.shahm.myapplication.listeners.OnSalesClick;
import com.shahm.myapplication.model.SalesPharmacy;

import java.util.ArrayList;
import java.util.List;

public class ARPHSales extends RecyclerView.Adapter<ARPHSales.ViewHolder> implements Filterable {
    private LayoutInflater inflater;
    private List<SalesPharmacy> listMed;
    private List<SalesPharmacy> filterList;


    public ARPHSales(List<SalesPharmacy> listMed, OnSalesClick salesClick) {
        this.listMed = listMed;
        filterList = this.listMed;
        this.salesClick = salesClick;
    }

    private OnSalesClick salesClick;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        context = parent.getContext();
        ItemPharmacySalesBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_pharmacy_sales, parent, false);
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listMed.get(position));
    }

    @Override
    public int getItemCount() {
        if (filterList!=null&&listMed.size()>0){
            return filterList.size();
        }
        return 0;
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
                    List<SalesPharmacy> list = new ArrayList<>();
                    for(SalesPharmacy model:listMed){
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
                filterList = (ArrayList<SalesPharmacy>)results.values;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPharmacySalesBinding binding;

        public ViewHolder(ItemPharmacySalesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(SalesPharmacy model) {
            binding.setModel(model);
            binding.getRoot().setOnClickListener(v -> {
                salesClick.onItemClick(model);
            });
        }
    }
}
