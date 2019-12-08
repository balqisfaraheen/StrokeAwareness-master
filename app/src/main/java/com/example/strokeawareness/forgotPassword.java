package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

    ProgressBar progressBar;
    EditText userEmail;
    Button userPass, signIn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        progressBar = findViewById(R.id.progressBar1);
        userEmail = findViewById(R.id.userEmailET);
        userPass = findViewById(R.id.sendPasswordBT);
        signIn = findViewById(R.id.signInBT);

        firebaseAuth = FirebaseAuth.getInstance();

        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    Toast.makeText(forgotPassword.this, "Password send to your email", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(forgotPassword.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(forgotPassword.this, Login.class);
                startActivity(intent);

            }
        });



    }
}
