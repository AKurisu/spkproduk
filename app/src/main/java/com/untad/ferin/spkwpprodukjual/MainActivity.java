package com.untad.ferin.spkwpprodukjual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.untad.ferin.spkwpprodukjual.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        View viewer = bind.getRoot();
        setContentView(viewer);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange_ff9800)));

        mAuth = FirebaseAuth.getInstance();
        bind.btnLogin.setOnClickListener(view -> {
            setLogin();
        });
        bind.btnSignup.setOnClickListener(view -> {
            openSignup();
        });
    }

    private void openSignup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void setLogin() {
        String email = bind.edtLoginNama.getEditText().getText().toString();
        String password = bind.edtLoginPassword.getEditText().getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SIGNIN", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        toMenu();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SIGNIN", "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            toMenu();
        }
    }

    private void toMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}