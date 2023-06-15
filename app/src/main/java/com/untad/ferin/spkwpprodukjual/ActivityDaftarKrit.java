package com.untad.ferin.spkwpprodukjual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityDaftarKrit extends AppCompatActivity {

    private Button btn_Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_krit);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange_ff9800)));

        btn_Edit = (Button) findViewById(R.id.btn_Edit);
        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityKrit();
            }
        });

    }

    public void openActivityKrit() {
        Intent intent = new Intent(this, ActivityKrit.class);
        startActivity(intent);
    }

}