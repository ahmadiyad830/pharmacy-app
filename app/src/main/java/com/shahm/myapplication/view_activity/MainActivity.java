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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityMainBinding;
import com.shahm.myapplication.view_frgament.FragmentPharmacySales;
import com.shahm.myapplication.view_frgament.analyze.FragmentAnalyze;
import com.shahm.myapplication.view_frgament.home_ui.FragmentHome;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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

        toggle = new ActionBarDrawerToggle(this, binding.drawer, toolbar, R.string.open, R.string.close);
        binding.drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        try {
            binding.navigationView.setNavigationItemSelectedListener(this);
        } catch (Exception e) {
            finish();
            e.printStackTrace();
        }

        btnFloat = findViewById(R.id.floatArrow);
        btnAdd = findViewById(R.id.floatAdd);
        btnStore = findViewById(R.id.floatStore);
        btnFloat.setOnClickListener(v -> {
            clickFloat();
        });
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,ActivityAdd.class);
            startActivity(intent);
        });
        btnStore.setOnClickListener(v -> {
            Toast.makeText(this, "add sales", Toast.LENGTH_SHORT).show();
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentHome()).commit();
        }

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


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            loadFragment(new FragmentHome());
        } else if (id == R.id.sales) {
            loadFragment(new FragmentPharmacySales());
        } else if (id == R.id.analyze) {
            loadFragment(new FragmentAnalyze());
        }
        binding.drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START, Animatable2.AnimationCallback.class.isAnnotation());
        } else {
            super.onBackPressed();
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