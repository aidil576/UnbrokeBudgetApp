package com.example.unbrokebudgetapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class milestone_achievement extends Fragment {

    public milestone_achievement() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_milestone_achievement, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ImageButton Btnlv1 = view.findViewById(R.id.IBrtgl1);
        View.OnClickListener OCL1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.NextToAchievment_Lv1);
            }
        };
        Btnlv1.setOnClickListener(OCL1);

        ImageButton Btnlv2 = view.findViewById(R.id.IBrtgl2);
        View.OnClickListener OCL2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.NextToAchievment_Lv2);
            }
        };
        Btnlv2.setOnClickListener(OCL2);

        ImageButton Btnlv3 = view.findViewById(R.id.IBrtgl3);
        View.OnClickListener OCL3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.NextToAchievment_Lv3);
            }
        };
        Btnlv3.setOnClickListener(OCL3);

    }


}