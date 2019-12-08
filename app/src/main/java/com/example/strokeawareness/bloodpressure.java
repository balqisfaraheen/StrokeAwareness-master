package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class bloodpressure extends AppCompatActivity {

    RadioButton opressure1 , opressure2, opressure3;
    TextView oresult;
    RadioGroup question1;
    Button ocalc;
    RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodpressure);

        opressure1 = findViewById(R.id.pressure1RB);
        opressure2 = findViewById(R.id.pressure2RB);
        opressure3 = findViewById(R.id.pressure3RB);
        oresult = findViewById(R.id.result);
        question1 = findViewById(R.id.rg);
        ocalc = findViewById(R.id.calc);

        check();

    }

    public void check(){
        ocalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int question3point = question1.getCheckedRadioButtonId();
                radioButton3 = findViewById(question3point);
                String blood = radioButton3.getText().toString();

                if (blood.equals("120/80 most of the time"))
                {
                    oresult.setText("normal");
                }
                else if (blood.equals("more than 120/80 but less than 140/90 most of the time"))
                {
                    oresult.setText("prehypertension");
                }
                else if (blood.equals("usually 140/90 or higher")) {
                    oresult.setText("hypertension, or high blood pressure");
                }

            }
        });

    }



    //button for bar sign out and home
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.signOutMenuItd) {

            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.homeee) {

            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }




}