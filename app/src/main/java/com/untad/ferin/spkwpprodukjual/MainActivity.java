package com.untad.ferin.spkwpprodukjual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.untad.ferin.spkwpprodukjual.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;
    private Button btn_kriteria;
    private Button btn_alternatif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        View viewer = bind.getRoot();
        setContentView(viewer);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange_ff9800)));

        bind.btnKriteria.setOnClickListener(view -> openActivityDaftarKrit());

        bind.btnAlternatif.setOnClickListener(view -> openActivityAlternatif());

        bind.btnPerangkingan.setOnClickListener(view -> openActivityPerangkingan());
    }

    private void openActivityPerangkingan() {
        Intent intent = new Intent(this, ActivityPerangkingan.class);
        startActivity(intent);
    }

    public void openActivityDaftarKrit() {
        Intent intent = new Intent(this, ActivityDaftarKrit.class);
        startActivity(intent);
    }

    public void openActivityAlternatif() {
        Intent intent = new Intent(this, ActivityAlternatif.class);
        startActivity(intent);
    }
}