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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class QuestionBank4 extends AppCompatActivity {

    int messageFromBank3;
    RadioButton critical, cry, fearful;
    RadioGroup criticalRG, cryRG, fearfulRG;
    RadioButton radioButton13, radioButton14, radioButton15;
    Button calculate;
    ImageView criticalapa, cryapa, fearfulapa;
    int points;
    int risk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank4);

        Bundle bundle = getIntent().getExtras();
        points = bundle.getInt("points",0);

        critical = findViewById(R.id.criticalRB);
        cry = findViewById(R.id.cryRB);
        fearful = findViewById(R.id.fearfulRB);
        calculate = findViewById(R.id.calculateBT);
        criticalRG = findViewById(R.id.criticalRG);
        cryRG = findViewById(R.id.cryRG);
        fearfulRG = findViewById(R.id.fearfulRG);
        criticalapa = findViewById(R.id.criticalapa);
        cryapa = findViewById(R.id.cryapa);
        fearfulapa = findViewById(R.id.fearfulapa);


        criticalapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank4.this, "Critical people make rude comments, judge our decisions, talk at length about what we're doing wrong or rarely have anything nice to say", Toast.LENGTH_LONG).show();
            }
        });


       cryapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank4.this, "Your emotions get stirred up easily even over small thing or small issue that created by others or even yourself ", Toast.LENGTH_SHORT).show();
            }
        });


        fearfulapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank4.this, " Feeling afraid, frightened", Toast.LENGTH_SHORT).show();
            }
        });


        Toast.makeText(this, "abc "+points, Toast.LENGTH_SHORT).show();
        setmNext();
    }

    public void setmNext() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int question13point = criticalRG.getCheckedRadioButtonId();
                radioButton13  = findViewById(question13point);
                String critical = radioButton13.getText().toString();


                int question14point = cryRG.getCheckedRadioButtonId();
                radioButton14  = findViewById(question14point);
                String cry = radioButton14.getText().toString();


                int question15point = fearfulRG.getCheckedRadioButtonId();
                radioButton15 = findViewById(question15point);
                String fearful =  radioButton15.getText().toString();



                if (critical.equals("Often / Always")) {
                    points = points +4;
                } else if (critical.equals("Sometimes / Rarely")) {
                    points = points +0;
                }

                if (cry.equals("Often / Always")) {
                    points = points +4;
                } else if (cry.equals("Sometimes / Rarely")) {
                    points = points +0;
                }

                if (fearful.equals("Often / Always")) {
                    points = points +3;
                } else if (fearful.equals("Sometimes / Rarely")) {
                    points = points +0;
                }


                /*
                if( critical.isSelected()){
                    points=points+4;
                }
                if(cry.isSelected()){
                    points=points+4;
                }
                else if(fearful.isSelected()){
                    points=points+3;
                }
                 */
                //Toast.makeText(getApplicationContext(), ""+points, Toast.LENGTH_SHORT).show();

                calculateRisk();

            }

            private void calculateRisk() {

                //Risk=1-[0.99982]exp(30/10)
                // To make it into percentage *100
                double newpoint = Double.valueOf(points);
                double count1 = newpoint/10 ;
                double count2 = Math.exp(count1);
                double count3 = Math.pow(0.99982, count2);
                double count4 = 1-count3 ;
                double risk = count4*100;

                Intent intent = new Intent(QuestionBank4.this, ResultRisk.class);
                intent.putExtra("risk", risk);
                startActivity(intent);
            }
        });
    }













    // button bar home and sign out
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
