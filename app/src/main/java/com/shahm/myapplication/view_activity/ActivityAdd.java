package com.shahm.myapplication.view_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityAddBinding;
import com.shahm.myapplication.model.Medicines;


public class ActivityAdd extends AppCompatActivity {

    private ActivityAddBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        binding.btnCamBarCode.setOnClickListener(v -> {
            onScan();
        });
    }


    private void saveInstance(Bundle outState) {
        Medicines model = new Medicines(binding.edtBarCode.getText().toString(),
                binding.name.getText().toString(),
                binding.scientific.getText().toString(),
                binding.concentration.getText().toString(),
                binding.dosageform.getText().toString(),
                binding.note.getText().toString(),
                binding.store.getText().toString(),
                binding.sachet.getText().toString(),
                binding.location.getText().toString(), "");
        if (model != null) {
            try {
                outState.putSerializable("save_medicines", model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onScan() {
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setPrompt("for flags");
        intent.setBeepEnabled(true);
        intent.setOrientationLocked(true);
        intent.setCaptureActivity(Capture.class);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setCameraId(0);  // Use a specific camera of the device
        intent.setBarcodeImageEnabled(true);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
            if (result.getContents() != null) {
                binding.edtBarCode.setText(result.getContents());
                String barCode = binding.edtBarCode.getText().toString();
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
//                    TODO warning maybe the not bar code maybe qr use dialog when user chiois if qr of barcode
                    BitMatrix matrix = writer.encode(barCode, BarcodeFormat.CODABAR,
                            (int) getResources().getDimension(R.dimen._350sdp),
                            (int) getResources().getDimension(R.dimen._200sdp));
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);

                    binding.imgBarcode.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "error\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            } else Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }


    public static class Capture extends CaptureActivity {

    }
}