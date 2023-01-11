package com.example.unbrokebudgetapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class lucky_wheel extends Fragment {

    //wheel values
    final int [] sectors = {5, 10, 0, 100, 0, 0, 0, 200};
    final int [] sectorDegrees = new int[sectors.length];

    //randomindex
    int SectorRandomIndex = 0;

    //what to spin
    ImageView wheel;
    boolean spinning = false;
    int earningsRecord = 0;

    //random to help generate index
    Random random = new Random();

    public static lucky_wheel newInstance() {
        return new lucky_wheel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lucky_wheel, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ImageButton BtnBack = view.findViewById(R.id.IBBacket);
        View.OnClickListener OCLbtnBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen) getActivity()).switchContent(entertainment_page.newInstance());
            }
        };
        BtnBack.setOnClickListener(OCLbtnBack);

        //to spin
        wheel = view.findViewById(R.id.wheel);
        //tap detector
        ImageView belt = view.findViewById(R.id.belt);

        generateSectorDegrees();

        //click wheel to spin the wheel
        wheel.setOnClickListener(view1 -> {
            //only if it is not spinning
            if(!spinning){
                spin();
                spinning = true; //now spinning
            }
        });

        //withdraw
        Button withdrawbtn = view.findViewById(R.id.Btnspin);
        withdrawbtn.setOnClickListener(view1 -> {
            //String text = "ready to spin "+ earningsRecord + " points??";
            //toast(text);
            //only if it is not spinning
            if(!spinning){
                spin();
                spinning = true; //now spinning

            }

        });
    }

    private void spin() {
        //get any random sectors
        SectorRandomIndex = random.nextInt(sectors.length); // the bound is exclusive

        //generate a random degree to spin the wheel
        int randomDegree = generateRandomDegreeToSpin();

        //do actual spinning using rotation animation
        RotateAnimation rotateAnimation = new RotateAnimation(0, randomDegree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        //time for spinning
        rotateAnimation.setDuration(3600); // = 3.6 seconds
        rotateAnimation.setFillAfter(true); // filter

        //interpolator
        rotateAnimation.setInterpolator(new DecelerateInterpolator()); // starts with high speed end with slow

        //spinning listener
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //interested when spinning ends
                //get earns
                int earnedPoints = sectors[sectors.length - (SectorRandomIndex + 1)];

                //saved the earning
                savedEarnings(earnedPoints);

                //toast current earning
                String sms = "you have earned "+ earnedPoints + " points";
                toast(sms);

                //spinning ended
                spinning = false;
                //wheel.startAnimation(rotateAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheel.startAnimation(rotateAnimation);

    }

    private void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    private void savedEarnings(int earnedPoints) {
        //saved it in earning records
        earningsRecord = earningsRecord + earnedPoints;
        //set the value to tv so the user can see how much points they get
        TextView tv = getView().findViewById(R.id.earnings); //
        tv.setText(String.valueOf(earningsRecord));

    }

    private int generateRandomDegreeToSpin() {
        // Make it higher as much as you can
        return (360 * sectors.length) + sectorDegrees[SectorRandomIndex];
    }

    private void generateSectorDegrees() {
        //for 1 sector
        int sectorDegree = 360/ sectors.length;

        for (int i=0; i < sectors.length; i++){
            //make it greater as much as you can
            sectorDegrees[i] = (i+1) * sectorDegree;
        }
    }

}