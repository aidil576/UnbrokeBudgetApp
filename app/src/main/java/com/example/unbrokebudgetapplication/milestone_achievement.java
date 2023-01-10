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