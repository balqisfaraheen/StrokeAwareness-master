package com.example.strokeawareness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class checkreading extends AppCompatActivity {

    Button obmiBT, opressureBT, osugarBT, ocholestrolBT, ohdlBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkreading);


        obmiBT= findViewById(R.id.bmiBT);
        opressureBT= findViewById(R.id.pressureBT);
        osugarBT= findViewById(R.id.sugarBT);
        ocholestrolBT= findViewById(R.id.cholestrolBT);
        ohdlBT= findViewById(R.id.hdlBT);


        obmiBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkreading.this, check.class);
                startActivity(intent);
            }
        });

        opressureBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkreading.this, bloodpressure.class);
                startActivity(intent);
            }
        });

        osugarBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkreading.this, bloodsugar.class);
                startActivity(intent);
            }
        });

        ocholestrolBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkreading.this, cholestrol.class);
                startActivity(intent);
            }
        });

        ohdlBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkreading.this, hdlcholestrol.class);
                startActivity(intent);
            }
        });


    }
}
