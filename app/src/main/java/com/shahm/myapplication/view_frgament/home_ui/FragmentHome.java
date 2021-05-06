package com.shahm.myapplication.view_frgament.home_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayoutMediator;
import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ViewPager2Adapter;
import com.shahm.myapplication.databinding.FragmentHomeBinding;


public class FragmentHome extends Fragment {
    private FragmentHomeBinding binding;
    private ViewPager2Adapter viewPager2Adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);


       ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.toolbarHome);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.main_menu_toolbar_, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.toolbar_i_have) {
////            binding.recyclerMedicines.setAdapter(null);
////            adapter.notifyDataSetChanged();
//        } else if (id == R.id.toolbar_all_drug) {
////            adapter.setListMedicines(getMedicines(1));
//        } else if (id == R.id.toolbar_setting) {
//
//            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home_to_fragmentSetting);
//        }
        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        binding.collapseActionView.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
//        binding.collapseActionView.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        loadTabLayout();
    }
    private void loadTabLayout() {
        viewPager2Adapter = new ViewPager2Adapter(requireActivity());
        viewPager2Adapter.addFragment(new FragmentAllMedicines());
        viewPager2Adapter.addFragment(new FragmentMedicinesHave());
        binding.viewPager2.setAdapter(viewPager2Adapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tableLayout,
                binding.viewPager2, (tab, position) -> {
            switch (position) {
                case 1:
                    tab.setText("Medicines i Have");
                    break;
                case 0:
                default:
                    tab.setText("All Medicines");
                    break;

            }
        });
        tabLayoutMediator.attach();
    }


}