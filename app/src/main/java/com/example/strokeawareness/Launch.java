package com.example.strokeawareness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.strokeawareness.R;

public class Launch extends AppCompatActivity {
    android.widget.TextView textView;
    android.widget.Button btnClick1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_apps);


        textView = findViewById(R.id.textView13);
        btnClick1 = findViewById(R.id.button3);

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(com.example.strokeawareness.Launch.this,register.class);
                startActivity(intent);

            }
        });

        btnClick1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.strokeawareness.Launch.this, MainMenu.class);
                startActivity(intent);

            }
        });
    }
}