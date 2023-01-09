package com.example.unbrokebudgetapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class milestone_achievment_2 extends Fragment {

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
            }
        };
        BtnClaim1.setOnClickListener(OCLbtn1);
    }
}