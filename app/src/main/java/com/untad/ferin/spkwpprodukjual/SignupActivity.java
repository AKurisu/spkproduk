package com.untad.ferin.spkwpprodukjual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.untad.ferin.spkwpprodukjual.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding bind;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySignupBinding.inflate(getLayoutInflater());
        View v = bind.getRoot();
        setContentView(v);

        mAuth = FirebaseAuth.getInstance();
        bind.btnLogin.setOnClickListener(view -> {
            setCreate();
        });
    }

    private void setCreate() {

        String email = bind.edtLoginNama.getEditText().getText().toString();
        String password = bind.edtLoginPassword.getEditText().getText().toString();

        if (password.length() < 6){
            bind.edtLoginPassword.setError("Password tidak boleh kurang dari 6 karakter");
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SIGNUP", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(this, MenuActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}