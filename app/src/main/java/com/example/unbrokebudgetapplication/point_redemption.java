package com.example.unbrokebudgetapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class point_redemption extends Fragment {

    private DatabaseReference reference;
    private FirebaseAuth auth;
    int pointscollected;
    Dialog popup;

    public static point_redemption newInstance() {
        return new point_redemption();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_point_redemption, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                pointscollected = dataSnapshot.child("points").getValue(Integer.class);
                TextView tv = getView().findViewById(R.id.TVpoints); //
                tv.setText(Integer.toString(pointscollected)); //display point balance
                ImageView IV1 = view.findViewById(R.id.IVpoints1);
                IV1.setOnClickListener(view1 -> {
                    TextView tv1 = view.findViewById(R.id.TVpts1);
                    int num1 = Integer.parseInt(tv1.getText().toString());
                    if (pointscollected>num1){
                        auth = FirebaseAuth.getInstance();
                        FirebaseUser user1 = auth.getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference();

                        Map<String, Object> updates = new HashMap<>();

                        updates.put("/Users/" +user1.getUid()+ "/points/", ServerValue.increment(-15));
                        reference.updateChildren(updates);
                        popup.setContentView(R.layout.fragment_voucherlv1_popup);
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        popup.show();
                        tv.setText(Integer.toString(pointscollected)); //display point balance

                    }else {
                        String message = "Not enough points";
                        toast(message);
                    }

                });
                ImageView IV2 = view.findViewById(R.id.IVpoints2);
                IV2.setOnClickListener(view1 -> {
                    TextView tv2 = view.findViewById(R.id.TVpts2);
                    int num2 = Integer.parseInt(tv2.getText().toString());
                    if (pointscollected>num2){
                        auth = FirebaseAuth.getInstance();
                        FirebaseUser user1 = auth.getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference();

                        Map<String, Object> updates = new HashMap<>();

                        updates.put("/Users/" +user1.getUid()+ "/points/", ServerValue.increment(-42));
                        reference.updateChildren(updates);
                        popup.setContentView(R.layout.fragment_voucherlv2_popup);
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        popup.show();
                        tv.setText(Integer.toString(pointscollected));
                    }else {
                        String message = "Not enough points";
                        toast(message);
                    }
                });

                ImageView IV3 = view.findViewById(R.id.IVpoints3);
                IV3.setOnClickListener(view1 -> {
                    TextView tv3 = view.findViewById(R.id.TVpts3);
                    int num3 = Integer.parseInt(tv3.getText().toString());
                    if (pointscollected>num3){
                        auth = FirebaseAuth.getInstance();
                        FirebaseUser user1 = auth.getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference();

                        Map<String, Object> updates = new HashMap<>();

                        updates.put("/Users/" +user1.getUid()+ "/points/", ServerValue.increment(-50));
                        reference.updateChildren(updates);
                        popup.setContentView(R.layout.fragment_voucherlv3_popup);
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        popup.show();
                        tv.setText(Integer.toString(pointscollected));
                    }else {
                        String message = "Not enough points";
                        toast(message);
                    }
                });

                ImageView IV4 = view.findViewById(R.id.IVpoints4);
                IV4.setOnClickListener(view1 -> {
                    TextView tv4 = view.findViewById(R.id.TVpts4);
                    int num4 = Integer.parseInt(tv4.getText().toString());
                    if (pointscollected>num4){
                        auth = FirebaseAuth.getInstance();
                        FirebaseUser user1 = auth.getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference();

                        Map<String, Object> updates = new HashMap<>();

                        updates.put("/Users/" +user1.getUid()+ "/points/", ServerValue.increment(-35));
                        reference.updateChildren(updates);
                        popup.setContentView(R.layout.fragment_voucherlv1_popup);
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        popup.show();
                        tv.setText(Integer.toString(pointscollected));
                    }else {
                        String message = "Not enough points";
                        toast(message);
                    }
                });

                ImageView IV5 = view.findViewById(R.id.IVpoints5);
                IV5.setOnClickListener(view1 -> {
                    TextView tv5 = view.findViewById(R.id.TVpts5);
                    int num5 = Integer.parseInt(tv5.getText().toString());
                    if (pointscollected>num5){
                        auth = FirebaseAuth.getInstance();
                        FirebaseUser user1 = auth.getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference();

                        Map<String, Object> updates = new HashMap<>();

                        updates.put("/Users/" +user1.getUid()+ "/points/", ServerValue.increment(-45));
                        reference.updateChildren(updates);
                        popup.setContentView(R.layout.fragment_voucherlv2_popup);
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        popup.show();
                        tv.setText(Integer.toString(pointscollected));
                    }else {
                        String message = "Not enough points";
                        toast(message);
                    }
                });

                ImageView IV6 = view.findViewById(R.id.IVpoints6);
                IV6.setOnClickListener(view1 -> {
                    TextView tv6 = view.findViewById(R.id.TVpts6);
                    int num6 = Integer.parseInt(tv6.getText().toString());
                    if (pointscollected>num6){
                        auth = FirebaseAuth.getInstance();
                        FirebaseUser user1 = auth.getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference();

                        Map<String, Object> updates = new HashMap<>();

                        updates.put("/Users/" +user1.getUid()+ "/points/", ServerValue.increment(-56));
                        reference.updateChildren(updates);
                        popup.setContentView(R.layout.fragment_voucherlv3_popup);
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        popup.show();
                        tv.setText(Integer.toString(pointscollected));
                    }else {
                        String message = "Not enough points";
                        toast(message);
                    }
                });

            }
        });

        popup = new Dialog(getActivity());

        ImageButton BtnIBGift = view.findViewById(R.id.IBGift);
        View.OnClickListener OCLIVGift = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(video_page.newInstance());
                auth = FirebaseAuth.getInstance();
                FirebaseUser user1 = auth.getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference();

                Map<String, Object> updates = new HashMap<>();

                updates.put("/Users/" +user1.getUid()+ "/points/", ServerValue.increment(10));
                reference.updateChildren(updates);
            }
        };
        BtnIBGift.setOnClickListener(OCLIVGift);

        ImageView BtnIBBack = view.findViewById(R.id.IVbackicon);
        View.OnClickListener OCLIBBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(entertainment_page.newInstance());
            }
        };
        BtnIBBack.setOnClickListener(OCLIBBack);

    }

    private void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}