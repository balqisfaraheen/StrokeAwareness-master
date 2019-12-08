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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class QuestionBank3 extends AppCompatActivity {

    int messageFromBank2;
    RadioButton current, past, former, more, less, not, active;
    RadioGroup smokingRG, drinkingRG, activeRG;
    RadioButton radioButton10, radioButton11, radioButton12;
    Button next;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank3);

        Bundle bundle = getIntent().getExtras();
        points = bundle.getInt("points", 0);



        next = findViewById(R.id.nextBT);
        current = findViewById(R.id.currentRB);
        past = findViewById(R.id.pastRB);
        former = findViewById(R.id.formerRB);
        more = findViewById(R.id.moreRB);
        less = findViewById(R.id.lessRB);
        not = findViewById(R.id.notRB);
        active = findViewById(R.id.activeRB);
        smokingRG = findViewById(R.id.smokingRG);
        drinkingRG = findViewById(R.id.drinkingRG);
        activeRG = findViewById(R.id.activeRG);


       // Toast.makeText(this, "abc "+points, Toast.LENGTH_SHORT).show();
        setmNext();
    }

    public void setmNext() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int question10point =  smokingRG.getCheckedRadioButtonId();
                radioButton10  = findViewById(question10point);
                String smoking = radioButton10.getText().toString();


                int question11point = drinkingRG.getCheckedRadioButtonId();
                radioButton11  = findViewById(question11point);
                String drinking = radioButton11.getText().toString();


                int question12point = activeRG.getCheckedRadioButtonId();
                radioButton12 = findViewById(question12point);
                String active =  radioButton12.getText().toString();

                if (smoking.equals("Currently smoke")) {
                    points = points +8;
                } else if (smoking.equals("Smoked in the past")) {
                    points = points +5;
                }else if (smoking.equals("Have never smoke")) {
                    points = points +0;
                }

                if (drinking.equals("Former drinker")) {
                    points = points +5;
                } else if (drinking.equals("Never had any alcohol")) {
                    points = points +0;
                }else if (drinking.equals("1-6 drinks per week")) {
                    points = points +2;
                }else if (drinking.equals("7 or more drinks per week")) {
                    points = points +3;
                }

                if (active.equals("If you have not been active")) {
                    points = points +2;
                } else if (active.equals("If you only been active 1/2 times per week")) {
                    points = points +1;
                }else if (active.equals("if you only been active 3 times or more per week?")) {
                    points = points +0;
                }

                /* if( current.isSelected()){
                    points=points+3;
                }
                if(past.isSelected()){
                    points=points+4;
                }
                if(more.isSelected()){
                    points=points+4;
                }
                if(not.isSelected()){
                    points=points+4;
                }
                else   if(active.isSelected()){
                    points=points+3;
                }

                 */


                //Toast.makeText(getApplicationContext(), ""+points, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuestionBank3.this, QuestionBank4.class);
                intent.putExtra("points", points);
                startActivity(intent);

            }
        });
    }




    //bar home sign out
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
