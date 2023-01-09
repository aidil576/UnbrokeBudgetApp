package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_1st_budget_guide extends Fragment {
    private Context context;
    DBHelper myDB = new DBHelper(context);
    public static Fragment_1st_budget_guide newInstance() {
        return new Fragment_1st_budget_guide();
    }
    public Fragment_1st_budget_guide() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1st_budget_guide, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button back = view.findViewById(R.id.back_button_1stBudget);
        View.OnClickListener back_button_1stBudget = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());
            }
        };
        back.setOnClickListener(back_button_1stBudget);

        Button use = view.findViewById(R.id.use_button_budget1);
        View.OnClickListener use_button_budget1 = new View.OnClickListener(){
            @Override
            public void onClick(View v){

                boolean isInserted = myDB.addGuide(getContext(),"532RULE");

                ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());
            }
        };
        use.setOnClickListener(use_button_budget1);

    }
}