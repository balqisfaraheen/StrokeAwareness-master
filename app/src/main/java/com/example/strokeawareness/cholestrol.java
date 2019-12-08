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

public class cholestrol extends AppCompatActivity {

    private TextView result;
    private EditText cholestrol;
    Button calc;
    int cholestrolstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cholestrol);


        result = (TextView) findViewById(R.id.result);
        cholestrol = (EditText) findViewById(R.id.bloodET);
        calc = (Button) findViewById(R.id.calc);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String bloodstr = blood.getText().toString();

                cholestrolstr =Integer.parseInt(cholestrol.getText().toString());

                if(cholestrol.getText().toString()!=null) {

                    if (cholestrolstr < 100) {
                        result.setText("normal");
                    } else if (cholestrolstr > 100 && cholestrolstr < 125) {
                        result.setText("prediabetes");
                    } else if (cholestrolstr > 126) {
                        result.setText("diabetes");
                    }

                }

            }
        });
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
