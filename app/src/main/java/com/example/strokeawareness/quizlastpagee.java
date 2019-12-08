package com.example.strokeawareness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class quizlastpagee extends AppCompatActivity {

    Button next, quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlastpagee);

        next = findViewById(R.id.nextBT);
        quit = findViewById(R.id.nextBT);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),simpleQuiz.class);
                startActivity(intent);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
