package com.example.unbrokebudgetapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unbrokebudgetapplication.databinding.FragmentMilestoneAchievementBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.HashMap;


public class milestone_achievement extends Fragment {

    DatabaseReference reference;
    FirebaseAuth auth;
    int pointscollected;

    public static milestone_achievement newInstance() {
        return new milestone_achievement();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_milestone_achievement, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        TextView tv = view.findViewById(R.id.TVpointsbalance);

        reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pointscollected = snapshot.child("points").getValue(Integer.class);
                tv.setText(Integer.toString(pointscollected));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        ImageView Btnlv1 = view.findViewById(R.id.IBrtgl1);
        View.OnClickListener OCL1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(milestone_achievment_2.newInstance());
            }
        };
        Btnlv1.setOnClickListener(OCL1);

        ImageView Btnlv2 = view.findViewById(R.id.IBrtgl2);
        View.OnClickListener OCL2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(milestone_achievment_2_2.newInstance());
            }
        };
        Btnlv2.setOnClickListener(OCL2);

        ImageView Btnlv3 = view.findViewById(R.id.IBrtgl3);
        View.OnClickListener OCL3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(milestone_achievment_2_3.newInstance());
            }
        };
        Btnlv3.setOnClickListener(OCL3);

        ImageButton BtnBacket = view.findViewById(R.id.IBBacket);
        View.OnClickListener OCLBacket = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(entertainment_page.newInstance());
            }
        };
        BtnBacket.setOnClickListener(OCLBacket);

    }


}