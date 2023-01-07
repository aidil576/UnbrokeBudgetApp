package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class savingTips extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saving_tips, container, false);
        TextView tf1 = (TextView) view.findViewById(R.id.linkInvest);
        TextView tf2 = (TextView) view.findViewById(R.id.linkEmergency);
        TextView tf3 = (TextView) view.findViewById(R.id.linkDonation);

        tf1.setMovementMethod(LinkMovementMethod.getInstance());
        tf2.setMovementMethod(LinkMovementMethod.getInstance());
        tf3.setMovementMethod(LinkMovementMethod.getInstance());

//        if (tf1 != null) {
//            String text = "<a href='http://www.youtube.com/watch?v=Fd_AtH0yVqU'>here is the video</a>";
//            tf1.setText(Html.fromHtml(text));
//        }
        return view;
    }

}