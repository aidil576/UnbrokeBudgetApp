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

public class point_redemption extends Fragment {

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
    }
}