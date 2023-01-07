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

   // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public entertainment_page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment entertainment_page.
     */
    // TODO: Rename and change types and number of parameters
    public static entertainment_page newInstance(String param1, String param2) {
        entertainment_page fragment = new entertainment_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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