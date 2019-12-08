package com.example.strokeawareness;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.strokeawareness.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultRisk extends AppCompatActivity {


    TextView totalRisk, category, description;
    Double risk;
    Button saveBT;
    History history;
    ImageView bloodIV;
    private DatabaseReference reff;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_risk);

        totalRisk = findViewById(R.id.riskTV);
        category = findViewById(R.id.categoryTV);
        description = findViewById(R.id.descriptionTV);
        saveBT = findViewById(R.id.saveBT);
        bloodIV = findViewById(R.id.bloodIV);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
       // String userID = firebaseAuth.getCurrentUser().getUid();
        history = new History();


        //Database
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
       // reff.child("Users").child(uid).child("username").setValue(risk);
        //reff = database.getInstance().getReference("History");

        reff = database.getInstance().getReference("Users").child(uid).child("History");


       // Bundle bundle = getIntent().getExtras();
       // messageFromBank4 = bundle.getInt("risk",risk);
       // totalRisk.setText(messageFromBank4);


        //import from previous activity
        Bundle bundle = getIntent().getExtras();
        risk = bundle.getDouble("risk",0);
        //String finalRisk= new Double(risk).toString();
        totalRisk.setText(String.format("Risk : %.2f/100", risk));
       // totalRisk.setText("your have  " + risk + "% chance of having stroke over 100");
        //yourTextView.setText(String.format("Value of a: %.2f", a));


        //Calendar
        Calendar calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
       /* final Date currentTime = Calendar.getInstance().getTime(); */
        final TextView textViewDate = findViewById(R.id.calendarTV);
        textViewDate.setText(currentDate);


        //category
        category();

        // On Click save button
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ResultRisk.this);

                builder.setMessage("Do you want to save for further look?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //String userID = firebaseAuth.getCurrentUser().getUid();
//                                Double risk1 = Double.parseDouble(totalRisk.getText().toString().trim());
                               // Integer calendar = Integer.parseInt(textViewDate.getText().toString().trim());

                                //reff.child(String.valueOf(user)).child("History").setValue(history);
                                history.setRisk(risk.toString());
                               // history.setUserId(userID);
                                 //history.setCalendar(currentDate);
                                history.setCategory(category.getText().toString().trim());
//
                               // history.setCalendar(textViewDate.getText().toString().trim());
                                reff.push().setValue(history);
                                Toast.makeText(ResultRisk.this, "Count saved", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                                startActivity(intent);

                            }
                        })


                        .setNegativeButton("NO", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

     // back press
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultRisk.this);


        builder.setTitle("Want to exit")
                .setMessage("Are you sure dont want to save?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ResultRisk.super.onBackPressed();
                        Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("NO", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }



    //category class description
    private void category() {


        if(risk<=0.13){
            category.setText("Very Low Risk");
            description.setText("Congratulation, you are in lower risk of having stroke category. You are good in maintaing healthy lifestyle.");
            bloodIV.setImageResource(R.drawable.normal1);
        }
        else if(risk<=1.60){
            category.setText("Low Risk");
            description.setText("Keep practicing what you have done. Beware of your sugar intake, keep exercise and maintaining good habit of no smoking and drinking");
            bloodIV.setImageResource(R.drawable.baddarah);
        }
        else if(risk<=11.20){
            category.setText("Average");
            description.setText("You are in the way of having potential being a stroke patient, control your bad habits and change your lifestyle. You need to exercise more, delete all bad habit of drinking nor smoking. Having positive and healthy mind");
            bloodIV.setImageResource(R.drawable.salurandarah);
        }
        else if(risk<=80.00){
            category.setText("High Risk");
            description.setText("Try to change your bad habit of drinking or smoking if any. You have to slowly focus on your health and can start meeting doctor for any futher checking ob your health issue.");
            bloodIV.setImageResource(R.drawable.highrisk);
        }
        else if (risk>=81.00){
            category.setText("Very High Risk");
            description.setText("You have to plan on how to lower your stroke risk. You are asked for meting doctor, check up, do appointment and focusing on your health. Know where and how to change is important. Because you still given chance to improve");
            bloodIV.setImageResource(R.drawable.teruk);
        }


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
