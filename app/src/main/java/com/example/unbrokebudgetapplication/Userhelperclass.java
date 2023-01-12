package com.example.unbrokebudgetapplication;

public class Userhelperclass {
    String email;
    int points;

    public Userhelperclass(String email, int points){
        this.email = email;
        this.points = points;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }
}
