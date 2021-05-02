package com.shahm.myapplication.view_frgament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.FragmentSalesPharmacyBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSalesPharmacy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSalesPharmacy extends Fragment {
    private FragmentSalesPharmacyBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sales_pharmacy,container,false);

        return binding.getRoot();
    }
}