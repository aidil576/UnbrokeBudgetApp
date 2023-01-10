package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


public class FeedbackFragment extends Fragment {

    private Button BtnFeedback;
    private Context context;
    private RatingBar ratingBar;
    private TextView ratedPoint;




    public FeedbackFragment() {
        // Required empty public constructor
    }
    public static FeedbackFragment newInstance(){
        return new FeedbackFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_feedback, container, false);

       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        ratingBar = view.findViewById(R.id.ratingBar);
        ratedPoint = view.findViewById(R.id.ratedPoint);
        //to set rating
//        float rating = 3.5f;
//        ratingBar.setRating(rating);
//        to enable change
//        float rating2 = ratingBar.getRating();
//        ratingBar.setIsIndicator(false);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratedPoint.setText("You have rated " + rating);
            }
        });
    }


}