package com.shahm.myapplication.view_activity;

import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityMainBinding;
import com.shahm.myapplication.view_frgament.FragmentPharmacySales;
import com.shahm.myapplication.view_frgament.analyze.FragmentAnalyze;
import com.shahm.myapplication.view_frgament.home_ui.FragmentHome;

public class MainActivity extends AppCompatActivity  {
    private ActivityMainBinding binding;
    private NavController controller;
    private BottomSheetDialog bottomSheetDialog;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    FloatingActionButton btnFloat, btnStore, btnAdd;
    private boolean clicked = false;
    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        controller = Navigation.findNavController(this, R.id.fragment);
        toolbar = findViewById(R.id.toolbar);

        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.animation_open_float);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.animation_close_float);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.animation_from_bottom);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.animation_to_bottom);

        binding.navigationView.setItemIconTintList(null);
        NavController controller = Navigation.findNavController(this,R.id.nav_host);
        NavigationUI.setupWithNavController(binding.navigationView,controller);

        binding.icSearch.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_fragmentStore_to_fragmentSetting);
        });
        binding.imgMenu.setOnClickListener(v -> {
            binding.drawer.openDrawer(GravityCompat.START);
        });
        btnFloat = findViewById(R.id.floatArrow);
        btnAdd = findViewById(R.id.floatAdd);
        btnStore = findViewById(R.id.floatStore);
        btnFloat.setOnClickListener(v -> {
            clickFloat();
        });
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityAdd.class);
            startActivity(intent);
        });
        btnStore.setOnClickListener(v -> {
            Toast.makeText(this, "add sales", Toast.LENGTH_SHORT).show();
        });



    }

    private void clickFloat() {
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
//        if (!clicked){
//            clicked = true
//        }else clicked = false;
    }

    private void setVisibility(boolean clicked) {
        if (!clicked) {
            btnStore.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
        } else {
            btnStore.setVisibility(View.INVISIBLE);
            btnAdd.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(boolean clicked) {
        if (!clicked) {
            btnAdd.startAnimation(fromBottom);
            btnStore.startAnimation(fromBottom);
            btnFloat.startAnimation(rotateOpen);
        } else {
            btnAdd.startAnimation(toBottom);
            btnStore.startAnimation(toBottom);
            btnFloat.startAnimation(rotateClose);
        }
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