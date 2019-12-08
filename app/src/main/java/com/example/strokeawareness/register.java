package com.example.strokeawareness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class register extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRegister;
    private TextView textViewSignIn, mPasswordTV, readTV;
    private EditText  editTextEmail, mPasswordET, fullName, note;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    CheckBox checkBox;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        buttonRegister =  findViewById(R.id.button3);
        textViewSignIn = findViewById(R.id.login);

        //editTextPassword = findViewById(R.id.password);
        fullName = findViewById(R.id.fullname);
        editTextEmail = findViewById(R.id.email);
        mPasswordET = findViewById(R.id.PasswordET);
        mPasswordTV = findViewById(R.id.ToggleTV);
        note = findViewById(R.id.NoteToMyself);
        readTV = findViewById(R.id.readTV);
        checkBox = findViewById(R.id.checkBoxCB);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

        readTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intToHome = new Intent(register.this, privacy1.class);
                startActivity(intToHome);
            }
        });




        mPasswordTV.setVisibility(View.GONE);
        mPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //password show/hide
        mPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mPasswordET.getText().length() > 0){
                    mPasswordTV.setVisibility(View.VISIBLE);
                }
                else{
                    mPasswordTV.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });



        mPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPasswordTV.getText() == "SHOW"){
                    mPasswordTV.setText("HIDE");
                    mPasswordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mPasswordET.setSelection(mPasswordET.length());
                }
                else {
                    mPasswordTV.setText("SHOW");
                    mPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPasswordET.setSelection(mPasswordET.length());
                }
            }
        });

    }


    private boolean validateEmail() {
        String emailInput = editTextEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            editTextEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            editTextEmail.setError("Please enter a valid email address");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }


    private boolean validateUsername() {
        String usernameInput = fullName.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            fullName.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            fullName.setError("Username too long");
            return false;
        } else {
            fullName.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = mPasswordET.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            mPasswordET.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            mPasswordET.setError("Password too weak");
            return false;
        } else {
            mPasswordET.setError(null);
            return true;
        }
    }


    private boolean validateread() {

            if(!checkBox.isChecked()){
                checkBox.setError("Tick this box");
                Toast.makeText(register.this, "Have you read the agreement?", Toast.LENGTH_SHORT).show();
                return false;

            }
        return true;
    }



    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = mPasswordET.getText().toString().trim();
        final String name = fullName.getText().toString().trim();

        if (!validateEmail() | !validateUsername() | !validatePassword() | !validateread()){
            return;
        }

        /*if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
         */


        progressDialog.setMessage("Registering User..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final String noteToSelf = note.getText().toString();
                            String name = fullName.getText().toString();


                            final Users information = new Users(
                                    name,
                                    email,
                                    noteToSelf

                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(register.this, "Done", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intToHome = new Intent(register.this, Login.class);
                                    startActivity(intToHome);
                                    progressDialog.dismiss();
                                }
                            });

                            String userID = firebaseAuth.getCurrentUser().getUid();
                            Users newUser = new Users(userID, name, email, noteToSelf);
                            //information.setNoteToSelf(noteToSelf.toString().trim());
                           // information.setUserEmail(email.toString().trim());
                            //information.setUserName(name.toString().trim());

                            mDatabase.child("Users").child(userID).setValue(newUser, new DatabaseReference.CompletionListener() {

                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    Toast.makeText(register.this, "Done", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intToHome = new Intent(register.this, Login.class);
                                    startActivity(intToHome);
                                    progressDialog.dismiss();
                                }
                            });

                        } else {
                            Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();
        }
        if (view == textViewSignIn){
            Intent intToHome = new Intent(register.this, Login.class);
            startActivity(intToHome);

        }

    }
}

