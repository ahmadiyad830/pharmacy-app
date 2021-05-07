package com.shahm.myapplication.view_frgament;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.FragmentDetailsBinding;
import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.viewmodel.ui.VMShareText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetails extends Fragment {
    private FragmentDetailsBinding binding;
    private SalesPharmacy model;
    private String TAG = "FragmentDetails";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            FragmentDetailsArgs args = FragmentDetailsArgs.fromBundle(getArguments());
            model = args.getArgDetailsSales();
            binding.setModel(model);
        }

    }
}