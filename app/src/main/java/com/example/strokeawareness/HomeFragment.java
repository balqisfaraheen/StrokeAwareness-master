package com.example.strokeawareness;

import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.strokeawareness.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    ImageView imageLocation;
    TextView riskCentre;
    FirebaseUser firebaseUser;
    private DatabaseReference RiskRef;
    private FirebaseAuth mAuth;
    private String currentUserID;

    Button check;

    // public HomeFragment() {
    //}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageLocation = view.findViewById(R.id.imagelocation);
        riskCentre = view.findViewById(R.id.centreRiskTV);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //check = view.findViewById(R.id.checkBT);
        //user = FirebaseAuth.getInstance().getCurrentUser();
        //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        //uid = user.getUid();

        userRisk();

        imageLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Location.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void userRisk() {
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        //RiskRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);
        //RiskRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("History");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseReference.child("Users").child(currentUserID).child("History").orderByKey().limitToLast(1);
        lastQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("risk")){
                    String risk1 = dataSnapshot.child("risk").getValue().toString();
                     riskCentre.setText(risk1);

                }
                else
                {
                    String risk1 = dataSnapshot.child("risk").getValue().toString();
                    riskCentre.setText(risk1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }







    //String risk = dataSnapshot.child("risk").getValue().toString();
//                    riskCentre.setText(risk);
    // History users = dataSnapshot.getValue(History.class);
    // riskCentre.setText(users.getRisk());
    // return;

    // History users = dataSnapshot.getValue(History.class);
    // String risk1 = dataSnapshot.child("risk").getValue().toString();
    // riskCentre.setText(risk1);





       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String risk = (String) dataSnapshot.child("Users").child(uid).child("History").getValue();
                riskCentre.setText(risk);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        */


}



/*
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseReference.child("Users").child(uid).child("History").orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(DataSnapshot dataSnapshot) {
                                                         String risk = dataSnapshot.child("risk").getValue().toString();
                                                         riskCentre.setText(risk);

                                                     }

                                                     @Override
                                                     public void onCancelled(@NonNull DatabaseError databaseError) {

                                                     }

                                                 });


 */




       /* DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference idRef = rootRef.child("Users").child(uid).child("History");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    double lat = ds.child("risk").getValue(Double.class);
                    riskCentre.setText((int) lat);
                    //Log.d("TAG", lat + " / ");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        idRef.addListenerForSingleValueEvent(eventListener);

        */


































        /*user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseReference.child(uid).child("History").orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String risk = dataSnapshot.child("risk").getValue().toString();
                riskCentre.setText(risk);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


/*  check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Query lastQuery = databaseReference.child("History").orderByKey().limitToLast(1);
                lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String risk = dataSnapshot.child("risk").getValue().toString();
                        riskCentre.setText(risk);
                      // History history = dataSnapshot.getValue(History.class);
                       // String risk1 = dataSnapshot.child("risk").getValue().toString();
                        //String mname = history.getRisk();
                        //riskCentre.setText(mname);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle possible errors.
                    }
                });
            }
        }); */





