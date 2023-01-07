package com.example.unbrokebudgetapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class milestone_achievment_2_2 extends Fragment {


    public milestone_achievment_2_2() {
        // Required empty public constructor
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
                Navigation.findNavController(view).navigate(R.id.BackToMilestone_Lv2);
            }
        };
        BtnClaim2.setOnClickListener(OCLbtn2);
    }

}