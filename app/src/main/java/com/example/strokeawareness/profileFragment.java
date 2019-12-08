package com.example.strokeawareness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profileFragment extends Fragment {

    TextView fullname, email, note;
    FirebaseUser firebaseUser;
    private DatabaseReference profileRef;
    private FirebaseAuth mAuth;
    private String currentUserID;

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container ,false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        fullname = view.findViewById(R.id.nameDisplay);
        email = view.findViewById(R.id.emailDisplay);
        note = view.findViewById(R.id.noteDisplay);

        Userinfo();

        return view;
    }


    private void Userinfo(){
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        profileRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);
        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(getContext()==null){
                    return;
                }
                else
                {
                   Users users = dataSnapshot.getValue(Users.class);
                   fullname.setText(users.getUserName());
                   email.setText(users.getUserEmail());
                   note.setText(users.getNoteToSelf());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
