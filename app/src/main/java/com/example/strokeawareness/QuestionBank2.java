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

public class QuestionBank2 extends AppCompatActivity {

    int messageFromBank1;
    RadioButton renal, heart, arterial, blood, ischemic;
    RadioGroup renalRG , diabetesRG, bloodRG , heartRG, arterialRG, ischemicRG ;
    RadioButton radioButton4 , radioButton5, radioButton6, radioButton7, radioButton8, radioButton9;
    Button next;
    ImageView renalapa, diabetesapa, bloodapa, heartapa, arterialapa, ischemicapa ;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank2);

//        Bundle extras = getIntent().getExtras();
//        messageFromBank1 = extras.getInt("points",points);

        Bundle bundle = getIntent().getExtras();
       points = bundle.getInt("points", 0);

        renal = findViewById(R.id.renalRB);
        heart = findViewById(R.id.heartRB);
        arterial = findViewById(R.id.arterialRB);
        blood = findViewById(R.id.bloodRB);
        ischemic = findViewById(R.id.ischemicRB);
        next = findViewById(R.id.nextBT);
        renalRG = findViewById(R.id.renalRG);
        diabetesRG = findViewById(R.id.diabetesRG);
        heartRG = findViewById(R.id.heartRG);
        arterialRG = findViewById(R.id.arterialRG);
        bloodRG = findViewById(R.id.bloodRG);
        ischemicRG = findViewById(R.id.ischemicRG);
        renalapa = findViewById(R.id.renalapa);
        diabetesapa = findViewById(R.id.diabetesapa);
        heartapa = findViewById(R.id.heartapa);
        arterialapa = findViewById(R.id.arterialapa);
        bloodapa = findViewById(R.id.bloodapa);
        ischemicapa = findViewById(R.id.ischemicapa);

        renalapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank2.this, "Kidney disease means your kidneys are damaged and can't filter blood the way they should", Toast.LENGTH_LONG).show();
            }
        });

        diabetesapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank2.this, "Diabetes is a disease in which your blood glucose, or blood sugar, levels are too high", Toast.LENGTH_LONG).show();
            }
        });

        heartapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank2.this, "Congestive heart failure (CHF) is a chronic progressive condition that affects the pumping power of your heart muscles.", Toast.LENGTH_LONG).show();
            }
        });

        arterialapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank2.this, "Peripheral artery disease (also called peripheral arterial disease) is a common circulatory problem in which narrowed arteries reduce blood flow to your limbs.", Toast.LENGTH_LONG).show();
            }
        });

        bloodapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank2.this, "High blood pressure is a common condition in which the long-term force of the blood against your artery walls is high enough that it may eventually cause health problems, ", Toast.LENGTH_LONG).show();
            }
        });

        ischemicapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionBank2.this, "heart problems caused by narrowed heart arteries. When arteries are narrowed, less blood and oxygen reaches the heart muscle.", Toast.LENGTH_LONG).show();
            }
        });


       // Toast.makeText(this, "abc "+points, Toast.LENGTH_SHORT).show();
        setmNext();
    }

    public void setmNext() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int question4point = renalRG.getCheckedRadioButtonId();
                radioButton4  = findViewById(question4point);
                String renal = radioButton4.getText().toString();


                int question5point = diabetesRG.getCheckedRadioButtonId();
                radioButton5  = findViewById(question5point);
                String diabetes = radioButton5.getText().toString();


                int question6point = heartRG.getCheckedRadioButtonId();
                radioButton6 = findViewById(question6point);
                String heart =  radioButton6.getText().toString();


                int question7point =  arterialRG.getCheckedRadioButtonId();
                radioButton7 = findViewById(question7point);
                String arterial = radioButton7.getText().toString();

                int question8point =  bloodRG.getCheckedRadioButtonId();
                radioButton8 = findViewById(question8point);
                String blood = radioButton8.getText().toString();


                int question9point = ischemicRG.getCheckedRadioButtonId();
                radioButton9 = findViewById(question9point);
                String ischemic = radioButton9.getText().toString();


                if (renal.equals("Yes")) {
                    points = points +8;
                } else if (renal.equals("No")) {
                    points = points +0;
                }

                if (diabetes.equals("Yes")) {
                    points = points +7;
                } else if (diabetes.equals("No")) {
                    points = points +0;
                }

                if (heart.equals("Yes")) {
                    points = points +5;
                } else if (heart.equals("No")) {
                    points = points +0;
                }

                if (arterial.equals("Yes")) {
                    points = points +2;
                } else if (arterial.equals("No")) {
                    points = points +0;
                }

                if (blood.equals("Yes")) {
                    points = points +2;
                } else if (blood.equals("No")) {
                    points = points +0;
                }

                if (ischemic.equals("Yes")) {
                    points = points +1;
                } else if (ischemic.equals("No")) {
                    points = points +0;
                }


               /* if(renal.isSelected()){
                    points=points+3;
                }
                if(heart.isSelected()){
                    points=points+4;
                }
                if(arterial.isSelected()){
                    points=points+4;
                }
                if(blood.isSelected()){
                    points=points+4;
                }
                else   if(ischemic.isSelected()){
                    points=points+3;
                }
                */


               // Toast.makeText(getApplicationContext(), ""+points, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuestionBank2.this, QuestionBank3.class);
                intent.putExtra("points", points);
                startActivity(intent);

            }
        });
    }

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
