package com.untad.ferin.spkwpprodukjual;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.untad.ferin.spkwpprodukjual.data.Alternative;
import com.untad.ferin.spkwpprodukjual.databinding.ActivityEditAlternatifBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityEditAlternatif extends AppCompatActivity {

    public static final String DATA_KEY = "data_key";
    public static final String DATA_NAMA = "data_nama";
    public static final String DATA_ETALASE = "data_etalase";
    public static final String DATA_UMUR = "data_umur";
    public static final String DATA_LABA = "data_laba";
    public static final String DATA_TREN = "data_tren";
    public static final String DATA_MODAL = "data_modal";

    private String key;
    private DatabaseReference db;
    private ActivityEditAlternatifBinding bind;
    AutoCompleteTextView text_umur, text_modal;
    TextInputEditText text_nama, text_etalase, text_laba, text_tren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityEditAlternatifBinding.inflate(getLayoutInflater());
        View view = bind.getRoot();
        setContentView(view);
        String title = "Tambah Alternatif";
        key = "";

        Intent intent = getIntent();
        if (intent.hasExtra("data_nama")){
            setEditValue(intent);
            title = "Ubah Alternatif";
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange_ff9800)));
            actionBar.setTitle(title);
        }

        text_umur = bind.txtUmur;
        text_modal = bind.txtModal;
        text_nama = bind.txtNama;
        text_etalase = bind.txtEtalase;
        text_laba = bind.txtLaba;
        text_tren = bind.txtTren;

        setSpinnerAdapter();
        bind.btnSimpanAlternatif.setOnClickListener(v -> {
            if (!checkError()){
                saveData();
            }
        });
    }

    private void setEditValue(Intent intent) {
        key = intent.getStringExtra("data_key");
        String nama = intent.getStringExtra("data_nama");
        String modal = intent.getStringExtra("data_modal");
        int etalase = intent.getIntExtra("data_etalase", 0);
        String umur = intent.getStringExtra("data_umur");
        int laba = intent.getIntExtra("data_laba", 0);
        int tren = intent.getIntExtra("data_tren", 0);

        bind.txtNama.setText(nama);
        bind.txtModal.setText(modal);
        bind.txtEtalase.setText(String.valueOf(etalase));
        bind.txtUmur.setText(umur);
        bind.txtLaba.setText(String.valueOf(laba));
        bind.txtTren.setText(String.valueOf(tren));

        bind.btnSimpanAlternatif.setText("Ubah");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private boolean checkError() {
        boolean status = false;
        try {
            if (text_nama.getText().toString().isEmpty()){
                status = true;
                text_nama.setError("Masukkan nama");
            }
            if (text_modal.getText().toString().isEmpty()){
                status = true;
                text_modal.setError("Masukkan modal");
            }
            if (text_etalase.getText().toString().isEmpty()){
                status = true;
                text_etalase.setError("Masukkan etalase");
            }
            if (text_umur.getText().toString().isEmpty()){
                status = true;
                text_umur.setError("Masukkan umur");
            }
            if (text_laba.getText().toString().isEmpty()){
                status = true;
                text_laba.setError("Masukkan laba");
            }
            if (text_tren.getText().toString().isEmpty()){
                status = true;
                text_tren.setError("Masukkan tren");
            }
        } catch (NullPointerException e){
            Log.e("Null", e.toString());
        }
        return status;
    }

    private void saveData() {
//        Toast.makeText(this, "HELLO", Toast.LENGTH_SHORT).show();
        String nama = text_nama.getText().toString().trim();
        String modal = text_modal.getText().toString().trim();
        int etalase = Integer.parseInt(text_etalase.getText().toString());
        String umur = text_umur.getText().toString().trim();
        int laba = Integer.parseInt(text_laba.getText().toString().trim());
        int tren = Integer.parseInt(text_tren.getText().toString().trim());

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHmmSS");
        String str = formatter.format(date);

        Alternative alternative = new Alternative(nama, modal, etalase, umur, laba, tren);
        db = FirebaseDatabase.getInstance().getReference();
        if (key.isEmpty()){
            db.child("alternatif").child(nama + str)
                    .setValue(alternative).addOnSuccessListener(unused -> {
                        Toast.makeText(this, String.valueOf(etalase), Toast.LENGTH_SHORT).show();
                        new MaterialAlertDialogBuilder(this)
                                .setTitle("Pesan")
                                .setMessage("Data berhasil dimasukkan")
                                .setPositiveButton("OK", (dialogInterface, i) -> finish())
                                .setNegativeButton("Cancel", null)
                                .show();
                    });
        } else {
            db.child("alternatif").child(key).removeValue();
            db.child("alternatif").child(key)
                    .setValue(alternative).addOnSuccessListener(unused -> {
                        Toast.makeText(this, String.valueOf(etalase), Toast.LENGTH_SHORT).show();
                        new MaterialAlertDialogBuilder(this)
                                .setTitle("Pesan")
                                .setMessage("Data berhasil diubah")
                                .setPositiveButton("OK", (dialogInterface, i) -> finish())
                                .setNegativeButton("Cancel", null)
                                .show();
                    });
        }

    }

    private void setSpinnerAdapter() {
        String[] item_modal = {"<1 Juta", "1 Juta - 3 Juta", ">3 Juta - 5 Juta", ">5 Juta - 7 Juta", ">7 Juta - 10 Juta", ">10 Juta"};
        String[] item_umur = {"<1 Minggu", "1 Minggu - 1 Bulan", ">1 Bulan - 6 Bulan", ">6 Bulan - 12 Bulan", ">12 Bulan - 24 Bulan", ">24 Bulan - 36 Bulan", ">36 Bulan"};

        ArrayAdapter<String> adapterItemsUmur = new ArrayAdapter<>(this, R.layout.list_umur, item_umur);
        ArrayAdapter<String> adapterItemsModal = new ArrayAdapter<>(this, R.layout.list_modal, item_modal);

        text_umur.setAdapter(adapterItemsUmur);
        text_modal.setAdapter(adapterItemsModal);

        text_umur.setOnItemClickListener((adapterView, view, i, l) -> {
            text_umur.setError(null);
            String item_umur1 = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(ActivityEditAlternatif.this, "Item: " + item_umur1, Toast.LENGTH_SHORT).show();
        });
        text_modal.setOnItemClickListener((adapterView, view, i, l) -> {
            text_modal.setError(null);
        });
    }
}