package com.example.strokeawareness;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strokeawareness.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private View HistoryView;
    private RecyclerView myHistoryList;

    private DatabaseReference HistoryRef, UsersRef;
    private FirebaseAuth mAuth;
    private String currentUserID;


    public HistoryFragment(){

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HistoryView = inflater.inflate(R.layout.fragment_history, container,false);
        myHistoryList = (RecyclerView) HistoryView.findViewById(R.id.history_list);
        myHistoryList.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        HistoryRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("History");
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("History");
        return HistoryView;
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<History>()
                .setQuery(HistoryRef, History.class)
                .build();

        final FirebaseRecyclerAdapter<History,HistoryViewHolder>adapter
                = new FirebaseRecyclerAdapter<History, HistoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final HistoryViewHolder historyViewHolder, int i, @NonNull History history)
            {
                String userIDs = getRef(i).getKey();

                UsersRef.child(userIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("History"))
                        {
                            String risk = dataSnapshot.child("risk").getValue().toString();
                            String category = dataSnapshot.child("category").getValue().toString();

                            historyViewHolder.display_risk.setText(risk);
                            historyViewHolder.display_category.setText(category);
                        }

                        else
                        {
                            String risk = dataSnapshot.child("risk").getValue().toString();
                            String category = dataSnapshot.child("category").getValue().toString();

                            historyViewHolder.display_risk.setText(risk);
                            historyViewHolder.display_category.setText(category);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_display_layout, parent,false);
                HistoryViewHolder viewHolder =new HistoryViewHolder(view);
                return viewHolder;
            }
        };

        myHistoryList.setAdapter(adapter);
        adapter.startListening();

    }


    public static class HistoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView display_risk,display_category;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            display_risk = itemView.findViewById(R.id.riskDisplay);
            display_category = itemView.findViewById(R.id.categoryDisplay);
        }
    }



}
