package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.strokeawareness.QuestionBank2;
import com.example.strokeawareness.R;
import com.google.firebase.auth.FirebaseAuth;

public class QuestionBank1 extends AppCompatActivity {
   // public static final String EXTRA_NUMBER = "com.example.strokeawareness.EXTRA_NUMBER";

    RadioButton male, female, school, diploma, university;
    EditText age;
    Button next;
    RadioGroup question3, question2;
    RadioButton radioButton2 , radioButton3;
    int points = 0;
    int ageInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank1);

        next = findViewById(R.id.nextBT);
        male = findViewById(R.id.maleRB);
        female = findViewById(R.id.femaleRB);
        school = findViewById(R.id.schoolRB);
        diploma = findViewById(R.id.diplomaRB);
        university = findViewById(R.id.universityRB);
        question3 = findViewById(R.id.question3);
        question2 = findViewById(R.id.question2);


        setmNext();
    }

    public void setmNext() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // age = findViewById(R.id.ageET);
               // String age1 = new age.getText();
               // String age1 = age.getText().toString();
               // int umur = Integer.parseInt(age1);

                int question2point = question2.getCheckedRadioButtonId();
                radioButton2 = findViewById(question2point);
                String gender = radioButton2.getText().toString();

                int question3point = question3.getCheckedRadioButtonId();
                radioButton3 = findViewById(question3point);
                String school = radioButton3.getText().toString();

                EditText age = findViewById(R.id.ageET);
//                ageInt=  age.getText().toString();


                try {
                    ageInt =Integer.parseInt(age.getText().toString());


                    if(age.getText().toString()!=null) {


                        if(ageInt>20){
                            points=(ageInt-20); }

                        if (gender.equals("Male")) {
                            points = points +3;
                        } else if (gender.equals("Female")) {
                            points = points +0;
                        }

                        if (school.equals("Secondary school or below")) {
                            points = points +4;
                        } else if (school.equals("Diploma or Post Secondary School")) {
                            points = points +3;
                        }else if (school.equals("Post Diploma or University")) {
                            points = points +0;
                        }

                     //   if(school.isSelected()){
                       /*     points=points+4;
                        }
                        else   if(diploma.isSelected()){
                            points=points+3;
                        }*/


                        //Toast.makeText(getApplicationContext(), ""+points, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), QuestionBank2.class);

                        intent.putExtra("points", points);

                        startActivity(intent);

                    }





                }
                catch (NumberFormatException nfe)
                {
                    nfe.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Please enter the age",Toast.LENGTH_SHORT).show();
                }
//



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

            Intent intent = new Intent(getApplicationContext(),HomeFragment.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
