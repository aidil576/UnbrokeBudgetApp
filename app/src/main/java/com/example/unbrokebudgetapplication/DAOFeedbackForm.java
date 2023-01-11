package com.example.unbrokebudgetapplication;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOFeedbackForm {

    private DatabaseReference databaseReference;
    public DAOFeedbackForm(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(FeedbackForm.class.getSimpleName());
    }
    public Task<Void> add(FeedbackForm emp){
//        if(emp == null) //throw exception
        return databaseReference.push().setValue(emp);
    }

}
