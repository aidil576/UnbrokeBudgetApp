package com.example.unbrokebudgetapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class milestone_achievment_2_2 extends Fragment {

    int earningsRecord = 0;

    public static milestone_achievment_2_2 newInstance() {
        return new milestone_achievment_2_2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_milestone_achievment_2_2, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Button BtnClaim2 = view.findViewById(R.id.button2);
        View.OnClickListener OCLbtn2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(milestone_achievement.newInstance());
            }
        };
        BtnClaim2.setOnClickListener(OCLbtn2);

        earningsRecord = earningsRecord + 500;

    }

}