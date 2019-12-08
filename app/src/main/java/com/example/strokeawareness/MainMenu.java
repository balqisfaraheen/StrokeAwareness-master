package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.strokeawareness.CalculatorFragment;
import com.example.strokeawareness.InformationFragment;
import com.example.strokeawareness.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        firebaseAuth = FirebaseAuth.getInstance();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
        android.widget.Toast.makeText(MainMenu.this, "Firebase connection Success", android.widget.Toast.LENGTH_LONG).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_history:
                            selectedFragment = new HistoryFragment();
                            break;
                        case R.id.nav_cal:
                            selectedFragment = new CalculatorFragment();
                            break;
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_info:
                            selectedFragment = new InformationFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new profileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                            selectedFragment).commit();

                    return true;
                }
            } ;


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
