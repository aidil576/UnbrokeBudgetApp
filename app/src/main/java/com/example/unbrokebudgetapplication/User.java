package com.example.unbrokebudgetapplication;

public class User {

    public String username, email, mobileNum;
    public int points;

    public User(){

    }

    public User(String username, String email, String mobileNum, int points){
        this.username = username;
        this.email = email;
        this.mobileNum = mobileNum;
        this.points = points;
    }
}
