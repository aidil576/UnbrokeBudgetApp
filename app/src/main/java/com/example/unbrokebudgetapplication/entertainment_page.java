package com.example.unbrokebudgetapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class entertainment_page extends Fragment {

    public entertainment_page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment_page, container, false);

    }

    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button BtnPR = view.findViewById(R.id.BtnPointsRedemption);
        View.OnClickListener OCLbtnPR = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.NextToPointRedemption);
            }
        };
        BtnPR.setOnClickListener(OCLbtnPR);

        Button BtnMA = view.findViewById(R.id.BtnMilestoneAchievement);
        View.OnClickListener OCLbtnMA = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.NextToMilestone);
            }
        };
        BtnMA.setOnClickListener(OCLbtnMA);

        Button BtnLD = view.findViewById(R.id.BtnLuckyDraw);
        View.OnClickListener OCLbtnLD = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.NextToLuckyWheel);
            }
        };
        BtnLD.setOnClickListener(OCLbtnLD);
    }
}