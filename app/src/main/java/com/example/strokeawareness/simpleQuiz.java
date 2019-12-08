package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strokeawareness.MainActivity;
import com.example.strokeawareness.R;
import com.google.firebase.auth.FirebaseAuth;

public class simpleQuiz extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button quit;

    private String mAnswer;
    private int mScore=0;
    private int mQuestionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_quiz);

        mScoreView = findViewById(R.id.scoreTV);
        mQuestionView = findViewById(R.id.questionTV);
        mButtonChoice1 = findViewById(R.id.choice1TV);
        mButtonChoice2 = findViewById(R.id.choice2TV);
        mButtonChoice3 = findViewById(R.id.choice3TV);
        quit = findViewById(R.id.quitBT);

        updateQuestion();

        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mButtonChoice1.getText()== mAnswer){
                    mScore = mScore+1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(simpleQuiz.this, "correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(simpleQuiz.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mButtonChoice2.getText()== mAnswer){
                    mScore = mScore+1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(simpleQuiz.this, "correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(simpleQuiz.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mButtonChoice3.getText()== mAnswer){
                    mScore = mScore+1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(simpleQuiz.this, "correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(simpleQuiz.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
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

    private void updateQuestion(){
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
       // lastpage();
    }

    private void lastpage() {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
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
