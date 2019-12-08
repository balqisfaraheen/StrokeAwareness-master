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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class check extends AppCompatActivity {


    private EditText height;
    private EditText weight;
    private TextView result;
    private TextView totalresult;
    Float result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        totalresult = (TextView) findViewById(R.id.totalresult);

        Button check = (Button) findViewById(R.id.calc);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weightStr = weight.getText().toString();
                String heightStr = height.getText().toString();

                if (heightStr != null && !"".equals(heightStr)
                        && weightStr != null  &&  !"".equals(weightStr)) {
                    float heightValue = Float.parseFloat(heightStr) / 100;
                    float weightValue = Float.parseFloat(weightStr);

                    float bmi = weightValue / (heightValue * heightValue);

                    displayBMI(bmi);
                }

            }
        });

    }

        private void displayBMI(float bmi) {

            if(bmi<18.5)
            {
                result.setText("underweight");
            }
            else if(bmi>18.5&&bmi<24.9)
            {
                result.setText("normal weight");
            }
            else if(bmi>25&&bmi<29.9)
            {
                result.setText("overweight");
            }
            else if(bmi>30&&bmi<39.9)
            {
                result.setText("obesity");
            }
            else if(bmi>40)
            {
                result.setText("severe obesity");
            }




/*
            if (Float.compare(bmi, 15f) <= 0) {
                result.setText("very_severely_underweight");
            } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
                result.setText("severely_underweight");
            } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
                result.setText("underweight");
            } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
                result.setText("normal");
            } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
                result.setText("overweight");
            } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
                result.setText("obese_class_i");
            } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
                result.setText("obese_class_ii");
            } else {
                result.setText("obese_class_iii");
            }
 */

            result1 = bmi ;
            totalresult.setText(String.format("BMI : %.3f", result1));

        }



    //button for bar sign out and home
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==R.id.signOutMenuItd){

            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }

        if(item.getItemId() ==R.id.homeee){

            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
