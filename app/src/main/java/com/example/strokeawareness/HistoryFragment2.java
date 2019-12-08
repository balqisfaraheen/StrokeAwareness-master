package com.example.strokeawareness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryFragment2 extends AppCompatActivity {



    DatabaseReference reference;
    // RecyclerView recyclerView;
    ArrayList<History> list;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_fragment2);


                final RecyclerView recyclerView  = (RecyclerView ) findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager( new LinearLayoutManager(this));
                /* final FragmentActivity c = getActivity();
                LinearLayoutManager layoutManager = new LinearLayoutManager(c);
                recyclerView.setLayoutManager(layoutManager); */
                list = new ArrayList <History>();


                reference = FirebaseDatabase.getInstance().getReference().child("History").child("-LrqV7KCIxK7c1tDjBpD");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list = new ArrayList<History>();
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            History p = dataSnapshot1.getValue(History.class);
                            list.add(p);
                        }
                        adapter = new MyAdapter(HistoryFragment2.this,list);
                        recyclerView .setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Toast.makeText(HistoryFragment.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                    }

                });



            }

}


