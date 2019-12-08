package com.example.strokeawareness;

import android.provider.ContactsContract;

public class Users {
    String UserName;
    String UserEmail;
    String noteToSelf;



    public Users (){}

    public Users(String userID, String name, String email, String noteToSelf) {

    }

    public Users(String userName, String userEmail, String noteToSelf) {
        UserName = userName;
        UserEmail = userEmail;
        this.noteToSelf = noteToSelf;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getNoteToSelf() {
        return noteToSelf;
    }

    public void setNoteToSelf(String noteToSelf) {
        this.noteToSelf = noteToSelf;
    }
}