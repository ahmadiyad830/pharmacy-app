package com.shahm.myapplication.view_frgament.analyze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayoutMediator;
import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ViewPager2Adapter;
import com.shahm.myapplication.databinding.FragmentAnalyzeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAnalyze#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAnalyze extends Fragment {
    private FragmentAnalyzeBinding binding;
    private ViewPager2Adapter viewPager2Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_analyze, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadTabLayout();
    }

    private void loadTabLayout() {
        viewPager2Adapter = new ViewPager2Adapter(requireActivity());
        viewPager2Adapter.addFragment(new FragmentDepotAnalyze());
        viewPager2Adapter.addFragment(new FragmentDrugAnalyze());
        binding.viewPager2.setAdapter(viewPager2Adapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tableLayout,
                binding.viewPager2, (tab, position) -> {
            switch (position) {
                case 1:
                    tab.setText("sales drug");
                    break;
                case 0:
                default:
                    tab.setText("sales pharmacy");
                    break;

            }
        });
        tabLayoutMediator.attach();
    }

}