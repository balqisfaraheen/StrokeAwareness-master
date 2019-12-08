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

public class hdlcholestrol extends AppCompatActivity {


    private TextView result;
    private EditText hdl;
    Button calc;
    int hdlstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdlcholestrol);

        result = (TextView) findViewById(R.id.result);
        hdl = (EditText) findViewById(R.id.hdlET);
        calc = (Button) findViewById(R.id.calc);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String bloodstr = blood.getText().toString();

                hdlstr =Integer.parseInt(hdl.getText().toString());

                if(hdl.getText().toString()!=null) {

                    if (hdlstr < 100) {
                        result.setText("normal");
                    } else if (hdlstr > 100 && hdlstr< 125) {
                        result.setText("prediabetes");
                    } else if (hdlstr > 126) {
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
