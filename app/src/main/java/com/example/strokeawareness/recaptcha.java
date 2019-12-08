  package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

  public class recaptcha extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    CheckBox checkbox;
    GoogleApiClient googleApiClient;

    String SiteKey = "6LdRcMUUAAAAAAnEz9RJf6Pjh_eLYZx1f8wBCwNL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recaptcha);

        checkbox = findViewById(R.id.checkBoxCB);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(recaptcha.this)
                .build();
        googleApiClient.connect();

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox.isChecked()) {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if ((status != null) && status.isSuccess()) {
                                        //Toast.makeText(getApplicationContext()
                                          //      , "Successfully verified..."
                                          //     , Toast.LENGTH_SHORT).show();
                                        Toast.makeText(recaptcha.this, "Successfully verified...", Toast.LENGTH_SHORT).show();
                                        checkbox.setTextColor(Color.GREEN);
                                        Intent i = new Intent(recaptcha.this, MainMenu.class);
                                        startActivity(i);
                                    }
                                }
                            });
                } else {
                    checkbox.setTextColor(Color.BLACK);

                }
            }
        });
    }

    @Override
      public void onConnected(@Nullable Bundle bundle){

    }

    @Override
      public void onConnectionSuspended(int i){

    }
}
