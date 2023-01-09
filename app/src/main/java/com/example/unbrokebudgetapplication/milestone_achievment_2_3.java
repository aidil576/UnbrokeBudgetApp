package com.example.unbrokebudgetapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class milestone_achievment_2_3 extends Fragment {

    public static milestone_achievment_2_3 newInstance() {
        return new milestone_achievment_2_3();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_milestone_achievment_2_3, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Button BtnClaim3 = view.findViewById(R.id.button3);
        View.OnClickListener OCLbtn3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(milestone_achievement.newInstance());
            }
        };
        BtnClaim3.setOnClickListener(OCLbtn3);
    }
}