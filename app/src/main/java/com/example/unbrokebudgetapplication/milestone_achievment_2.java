package com.example.unbrokebudgetapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;


public class milestone_achievment_2 extends Fragment {

    private FirebaseAuth auth;
    private DatabaseReference reference;
    int pointscollected;

    public static milestone_achievment_2 newInstance() {
        return new milestone_achievment_2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_milestone_achievment_2, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Button BtnClaim1 = view.findViewById(R.id.button1);
        View.OnClickListener OCLbtn1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(milestone_achievement.newInstance());
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference();
//
                Map<String, Object> updates = new HashMap<>();

                updates.put("/Users/" +user.getUid()+ "/points/",ServerValue.increment(-100));
                reference.updateChildren(updates);
            }
        };
        BtnClaim1.setOnClickListener(OCLbtn1);


    }
}