package com.example.unbrokebudgetapplication;

import static java.lang.Double.valueOf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_change_income#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_change_income extends Fragment {
    private Context context;
    DBHelper myDB;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_change_income() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_change_income.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_change_income newInstance(String param1, String param2) {
        fragment_change_income fragment = new fragment_change_income();
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
        return inflater.inflate(R.layout.fragment_change_income, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        myDB = new DBHelper(context);

        String str = myDB.ShowLast();
        Double income_latest;

        if (str.isEmpty()) {
            // Set income_latest to a default value or display an error message
            income_latest = 0.0;
        } else {
            income_latest = Double.valueOf(str);
        }

        TextView income_show = view.findViewById(R.id.initial_income_value);
        income_show.setText("RM" + Double.toString(income_latest));



        Button update = view.findViewById(R.id.confirm_salary);
        View.OnClickListener confirm_salary = new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v){

                LocalDateTime now = LocalDateTime.now();
                Date date = Date.valueOf(String.valueOf(now.toLocalDate()));
                Time time = Time.valueOf(now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                double amount = Double.parseDouble(income_latest.toString());


                boolean isInserted = myDB.addMoney(getContext(), "Salary", amount, date, time);
                if(isInserted =true)
                {
                    System.out.println("added");
                }
                else
                    System.out.println("not added");


                Navigation.findNavController(view).navigate(R.id.income_moneyrecord);
            }
        };
        update.setOnClickListener(confirm_salary);

        Button cancel = view.findViewById(R.id.income_cancel);
        View.OnClickListener income_cancel = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.income_moneyrecord);
            }
        };
        cancel.setOnClickListener(income_cancel);

        EditText editIncome = (EditText) view.findViewById(R.id.edit_income);

        Button confirm = view.findViewById(R.id.income_confirm);
        View.OnClickListener income_confirm = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double income_amount = Double.parseDouble(editIncome.getText().toString());
                boolean isInserted = myDB.changeIncome(getContext(), income_amount);
                if(isInserted =true)
                {
                    System.out.println("added");
                }
                else
                    System.out.println("not added");

                Navigation.findNavController(view).navigate(R.id.income_moneyrecord);
            }
        };
        confirm.setOnClickListener(income_confirm);

    }
}