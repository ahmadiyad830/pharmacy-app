package com.shahm.myapplication.view_frgament;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ARPHSales;
import com.shahm.myapplication.databinding.BottomSheetDetailsSalesBinding;
import com.shahm.myapplication.databinding.FragmentPharmacySalesBinding;
import com.shahm.myapplication.listeners.OnSalesClick;
import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.viewmodel.VMPHSales;
import com.shahm.myapplication.viewmodel.ui.VMShareText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPharmacySales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPharmacySales extends Fragment implements OnSalesClick {

    private FragmentPharmacySalesBinding binding;
    private VMPHSales viewModel;
    private int increment = 1;
    private int currentDrug = 10000;
    private ARPHSales adapter;
    private List<SalesPharmacy> listSales = new ArrayList<>();
    private BottomSheetDialog bottomAppBar;
    private BottomSheetDetailsSalesBinding bindingBottomSheet;
    private ViewGroup parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        doInitialization(inflater, container);
        return binding.getRoot();
    }

    private void doInitialization(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pharmacy_sales, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMPHSales.class);

        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new ARPHSales(listSales, this);
        binding.recycler.setAdapter(adapter);

        listenerRecycler();
        getListSales();
    }


    private void listenerRecycler() {
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)) {
                    if (increment <= currentDrug) {
                        increment++;
                        getListSales();
                    }

                }
            }
        });
    }


    private void getListSales() {
        viewModel.getSales(increment).observe(getViewLifecycleOwner(), salesMed -> {
            if (listSales != null) {
                int oldSize = listSales.size();
                listSales.addAll(salesMed);
                adapter.notifyItemRangeInserted(oldSize, listSales.size());

            }
        });
    }


    @Override
    public void onItemClick(SalesPharmacy model) {
//        if (bottomAppBar == null){
//            bottomAppBar = new BottomSheetDialog(requireActivity());
//            bindingBottomSheet = DataBindingUtil.inflate(LayoutInflater.from(requireActivity()), R.layout.bottom_sheet_details_sales, parent, false);
////            FrameLayout frameLayout = bottomAppBar.findViewById(com.google.android.material.R.id.design_bottom_sheet);
//            if (frameLayout != null) {
//                BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
//                bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        }



    }

    @Override
    public void deleteItem(String salesId,int position) {
        viewModel.postDelete("7247").observe(getViewLifecycleOwner(),aVoid -> {
            adapter.notifyItemRemoved(position);
            Toast.makeText(requireActivity(), "delete", Toast.LENGTH_SHORT).show();
        });
    }
}
