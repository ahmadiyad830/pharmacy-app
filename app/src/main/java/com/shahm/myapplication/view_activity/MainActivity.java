package com.shahm.myapplication.view_activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController controller;
    private BottomSheetDialog bottomSheetDialog;
    private ActivityAdd bindingBS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        controller = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(binding.bottomNav, controller);
        binding.bottomNav.setBackground(null);

//        bottomSheetCreate();
        binding.fabScrollRecycler.setOnClickListener(v -> {
            Intent intent = new Intent(this,ActivityAdd.class);
            startActivity(intent);
        });


    }

//    private void bottomSheetCreate() {
//        if (bottomSheetDialog == null) {
//            bottomSheetDialog = new BottomSheetDialog(this);
//            bindingBS = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_add, findViewById(R.id.container_add),
//                    false);
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            bottomSheetDialog.setContentView(bindingBS.getRoot(), params);
//            FrameLayout frameLayout = bottomSheetDialog.findViewById(
//                    com.google.android.material.R.id.design_bottom_sheet);
//            if (frameLayout != null) {
//                BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
//                bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        }
//    }


}