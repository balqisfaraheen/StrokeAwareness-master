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

public class bloodsugar extends AppCompatActivity {

    private TextView result;
    private EditText  blood;
    Button calc;
    int bloodstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodsugar);

        result = (TextView) findViewById(R.id.result);
        blood = (EditText) findViewById(R.id.bloodET);
        calc = (Button) findViewById(R.id.calc);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String bloodstr = blood.getText().toString();

                bloodstr =Integer.parseInt(blood.getText().toString());

                if(blood.getText().toString()!=null) {

                    if (bloodstr < 100) {
                        result.setText("normal");
                    } else if (bloodstr > 100 && bloodstr < 125) {
                        result.setText("prediabetes");
                    } else if (bloodstr > 126) {
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
