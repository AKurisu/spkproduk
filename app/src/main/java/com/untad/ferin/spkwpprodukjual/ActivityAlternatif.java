package com.untad.ferin.spkwpprodukjual;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.untad.ferin.spkwpprodukjual.data.Alternative;

public class ActivityAlternatif extends AppCompatActivity {

    private RecyclerView recyclerView;
    DaftarAlternatifAdapter adapter;
    DatabaseReference mbase;

    FloatingActionButton mAddFab;

    Boolean isAllFabVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternatif);

        mbase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Alternative> options
                = new FirebaseRecyclerOptions.Builder<Alternative>()
                .setQuery(mbase.child("alternatif"), Alternative.class)
                .build();

        adapter = new DaftarAlternatifAdapter(options);

        recyclerView.setAdapter(adapter);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange_ff9800)));

        mAddFab = findViewById(R.id.add_fab);
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityEditAlternatif();
            }
        });
    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    private void openActivityEditAlternatif() {
        Intent intent = new Intent(this, ActivityEditAlternatif.class);
        startActivity(intent);
    }
}