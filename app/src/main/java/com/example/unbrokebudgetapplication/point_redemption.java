package com.example.unbrokebudgetapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class point_redemption extends Fragment {

    int earningsRecord = 0;

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

        ImageButton BtnIBGift = view.findViewById(R.id.IBGift);
        View.OnClickListener OCLIVGift = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(video_page.newInstance());
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

        ImageView IV1 = view.findViewById(R.id.IVpoints1);
        IV1.setOnClickListener(view1 -> {
            TextView tv1 = view.findViewById(R.id.TVpts1);
            int num1 = Integer.parseInt(tv1.getText().toString());
            if (earningsRecord>num1){
                earningsRecord = earningsRecord - 10000;
            }else {
                String message = "Not enough points";
                toast(message);
            }
        });

        ImageView IV2 = view.findViewById(R.id.IVpoints2);
        IV2.setOnClickListener(view1 -> {
            TextView tv2 = view.findViewById(R.id.TVpts2);
            int num2 = Integer.parseInt(tv2.getText().toString());
            if (earningsRecord>num2){
                earningsRecord = earningsRecord - 42000;
            }else {
                String message = "Not enough points";
                toast(message);
            }
        });

        ImageView IV3 = view.findViewById(R.id.IVpoints3);
        IV3.setOnClickListener(view1 -> {
            TextView tv3 = view.findViewById(R.id.TVpts3);
            int num3 = Integer.parseInt(tv3.getText().toString());
            if (earningsRecord>num3){
                earningsRecord = earningsRecord - 50000;
            }else {
                String message = "Not enough points";
                toast(message);
            }
        });

        ImageView IV4 = view.findViewById(R.id.IVpoints4);
        IV4.setOnClickListener(view1 -> {
            TextView tv4 = view.findViewById(R.id.TVpts4);
            int num4 = Integer.parseInt(tv4.getText().toString());
            if (earningsRecord>num4){
                earningsRecord = earningsRecord - 35000;
            }else {
                String message = "Not enough points";
                toast(message);
            }
        });

        ImageView IV5 = view.findViewById(R.id.IVpoints5);
        IV5.setOnClickListener(view1 -> {
            TextView tv5 = view.findViewById(R.id.TVpts5);
            int num5 = Integer.parseInt(tv5.getText().toString());
            if (earningsRecord>num5){
                earningsRecord = earningsRecord - 45000;
            }else {
                String message = "Not enough points";
                toast(message);
            }
        });

        ImageView IV6 = view.findViewById(R.id.IVpoints6);
        IV6.setOnClickListener(view1 -> {
            TextView tv6 = view.findViewById(R.id.TVpts6);
            int num6 = Integer.parseInt(tv6.getText().toString());
            if (earningsRecord>num6){
                earningsRecord = earningsRecord - 56000;
            }else {
                String message = "Not enough coin";
                toast(message);
            }
        });
    }

    private void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}