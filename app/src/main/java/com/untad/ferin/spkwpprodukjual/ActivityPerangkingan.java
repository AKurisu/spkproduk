package com.untad.ferin.spkwpprodukjual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.untad.ferin.spkwpprodukjual.data.Alternatif;
import com.untad.ferin.spkwpprodukjual.data.Alternative;
import com.untad.ferin.spkwpprodukjual.data.Result;
import com.untad.ferin.spkwpprodukjual.databinding.ActivityPerangkinganBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivityPerangkingan extends AppCompatActivity {

    ActivityPerangkinganBinding bind;
    List<Alternative> alternatives_value;
    List<Alternatif> list_alternatif;
    ArrayList<String> keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityPerangkinganBinding.inflate(getLayoutInflater());
        View view = bind.getRoot();
        setContentView(view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Perankingan");
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange_ff9800)));
        }

        Query query = FirebaseDatabase.getInstance().getReference().child("alternatif");
        bind.rvPerangkingan.setLayoutManager(new LinearLayoutManager(this));
        keys = new ArrayList<>();
        alternatives_value = new ArrayList<>();
        list_alternatif = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()) {
                    if (snap.child("nama").getValue() != null){
                        setCalculation(snap);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void setCalculation(DataSnapshot snap) {
        String nama = String.valueOf(snap.child("nama").getValue());
        String modal = String.valueOf(snap.child("modal").getValue());
        String umur = String.valueOf(snap.child("umur_simpan").getValue());
        int etalase = Integer.parseInt(String.valueOf(snap.child("etalase").getValue()));
        int laba = Integer.parseInt(String.valueOf(snap.child("laba").getValue()));
        int tren = Integer.parseInt(String.valueOf(snap.child("tren").getValue()));

        keys.add(snap.getKey());
        alternatives_value.add(new Alternative(nama, modal, etalase, umur, laba, tren));
        list_alternatif.add(new Alternatif(nama, snap.getKey()));
        Log.d("DATA", snap.getKey());

        Kalkulasi kalkulasi = new Kalkulasi(list_alternatif, alternatives_value);
        List<Result> results = kalkulasi.result;

        setRank(results);
    }

    private void setRank(List<Result> results) {
        Comparator<Result> sort = (result, t1) -> Double.compare(result.value, t1.value);
        Collections.sort(results, sort.reversed());

        setView(results);
    }

    private void setView(List<Result> results) {
        DaftarRankingAdapter adapter = new DaftarRankingAdapter(results);
        bind.rvPerangkingan.setHasFixedSize(true);
        bind.rvPerangkingan.setLayoutManager(new LinearLayoutManager(this));
        bind.rvPerangkingan.setAdapter(adapter);
    }
}