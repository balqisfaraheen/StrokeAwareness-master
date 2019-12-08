package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.strokeawareness.R;
import com.google.firebase.auth.FirebaseAuth;

public class FAST extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast);

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            if(bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
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



