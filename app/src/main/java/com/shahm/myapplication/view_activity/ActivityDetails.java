package com.shahm.myapplication.view_activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityDetailsBinding;
import com.shahm.myapplication.model.Medicines;

public class ActivityDetails extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Medicines model = (Medicines) getIntent().getSerializableExtra("medicines");
        if (model != null)
            binding.setModel(model);

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        convertNumToBarcode();




    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.collapseActionView.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        binding.collapseActionView.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
    }

    private void convertNumToBarcode() {
        String barCode = "6253500622058".trim();
        MultiFormatWriter writer = new MultiFormatWriter();
        if (barCode != null && barCode.isEmpty()){
            try {
                BitMatrix matrix = writer.encode(barCode, BarcodeFormat.CODABAR, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.createBitmap(matrix);
                binding.imageTVShow.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else Toast.makeText(this, "error try again", Toast.LENGTH_SHORT).show();
    }

}