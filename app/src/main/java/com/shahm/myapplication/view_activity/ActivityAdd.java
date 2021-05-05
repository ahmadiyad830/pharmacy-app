package com.shahm.myapplication.view_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

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
import com.shahm.myapplication.viewmodel.VMMedicines;

import java.util.HashMap;


public class ActivityAdd extends AppCompatActivity {

    private ActivityAddBinding binding;
    private Medicines model;
    private VMMedicines viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(VMMedicines.class);

        binding.btnCamBarCode.setOnClickListener(v -> {
            onScan();
        });
        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
        String type = getIntent().getStringExtra("type");
        binding.btnAdd.setOnClickListener(v -> {
            String barcode = binding.getBarcode();
            String name = binding.getName();
            String concentration = binding.getConcentration();
            String scientific = binding.getScientific();
            String dosageform = binding.getDosageform();
            String notes = binding.getNotes();
            String location = binding.getLocation();
            String store = binding.getStore();
            String sachet = binding.getSachet();
            HashMap<String,String> map = new HashMap<>();
            map.put("name","adsfasdfasdf");
            map.put("scientific","ASdfasdfa");
            map.put("concentration","ASdfasdfasdfasdfasdf");
            map.put("dosageform","Asdfasdfasdf");
            map.put("notes","Asdfasdfasdf");
            map.put("store","Asdfasdfasdf");
            map.put("sachet","Asdfasdfasdf");
            map.put("slocation","Asdfasdfasdf");
            map.put("squantity","asdf");
//            viewModel.postMap(map).observe(this, aVoid -> {
//                Toast.makeText(this, "success upload data", Toast.LENGTH_SHORT).show();
//            });
            viewModel.postField(name,scientific,concentration,dosageform,notes,store,sachet,location,"5")
                    .observe(this,aVoid -> {
                        Toast.makeText(this, "success upload data", Toast.LENGTH_SHORT).show();
                    });
            uploadData();
        });

        switch (type) {
            case "sale":
                model = (Medicines) getIntent().getSerializableExtra("model");
                if (model != null) {
                    setValue();
                }
                break;
            case "add":
                Toast.makeText(this, "add custom sale", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void uploadData() {
//        Medicines medicines1 = new Medicines("test model","test model","test model","test model","test model","test model","test model","test model","test model");
//        viewModel.postData(medicines1).observe(this, medicines -> {
//            Toast.makeText(this, "success upload data", Toast.LENGTH_SHORT).show();
//        });

    }


    private void setValue() {
        binding.setBarcode(model.getBarcode());
        binding.setName(model.getName());
        binding.setConcentration(model.getConcentration());
        binding.setScientific(model.getScientific());
        binding.setDosageform(model.getDosageform());
        binding.setNotes(model.getNotes());
        binding.setLocation(model.getLocation());
        binding.setStore(model.getStore());
        binding.setSachet(model.getSachet());
    }


//    private void saveInstance(Bundle outState) {
//        Medicines model = new Medicines(binding.edtBarCode.getText().toString(),
//                binding.name.getText().toString(),
//                binding.scientific.getText().toString(),
//                binding.concentration.getText().toString(),
//                binding.dosageform.getText().toString(),
//                binding.note.getText().toString(),
//                binding.store.getText().toString(),
//                binding.sachet.getText().toString(),
//                binding.location.getText().toString(), "");
//        if (model != null) {
//            try {
//                outState.putSerializable("save_medicines", model);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

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