package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_account_setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_account_setting extends Fragment {

   private Context context;

    public fragment_account_setting() {
        // Required empty public constructor
    }
    public static fragment_account_setting newInstance(){
        return new fragment_account_setting();
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_account_setting newInstance(String param1, String param2) {
        fragment_account_setting fragment = new fragment_account_setting();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_account_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        ImageButton BtnNext = view.findViewById(R.id.BtnNextFeedback);
        View.OnClickListener BtnNextFeedback = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen)getActivity()).switchContent(FeedbackFragment.newInstance());
            }
        };
        BtnNext.setOnClickListener(BtnNextFeedback);
    }
}