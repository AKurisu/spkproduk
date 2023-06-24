package com.untad.ferin.spkwpprodukjual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.untad.ferin.spkwpprodukjual.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMenuBinding.inflate(getLayoutInflater());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda Yakin Ingin Logout?")
                .setPositiveButton("Ya", (dialogInterface, i) -> {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}