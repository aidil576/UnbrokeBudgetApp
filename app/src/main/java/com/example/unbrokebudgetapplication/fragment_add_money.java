package com.example.unbrokebudgetapplication;


import static java.sql.Time.valueOf;

import android.content.Context;
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
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_add_money#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_add_money extends Fragment {
    private Context context;
    DBHelper myDB = new DBHelper(context);
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_add_money() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_add_money newInstance() {
        return new fragment_add_money();
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
        return inflater.inflate(R.layout.fragment_add_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        DecimalFormat df = new DecimalFormat("#.##");
//        Double total_available = myDB.calc_Sum();
//        TextView balance = view.findViewById(R.id.total_money_value);
//        balance.setText("RM"+df.format(total_available));


        Button cancel = view.findViewById(R.id.cancel_button_addmoney);
        View.OnClickListener cancel_button_addmoney = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());
            }
        };
        cancel.setOnClickListener(cancel_button_addmoney);

        EditText amounttoadd = (EditText) view.findViewById(R.id.amount_add_value);


        Button confirm = view.findViewById(R.id.confirm_button_addmoney);
        View.OnClickListener confirm_button_addmoney = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(R.id.amount_add_value != 0) {
                    LocalDateTime now = LocalDateTime.now();
                    Date date = Date.valueOf(String.valueOf(now.toLocalDate()));
                    Time time = Time.valueOf(now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    double amount = Double.parseDouble(amounttoadd.getText().toString());

                    boolean isInserted = myDB.addMoney(getContext(), "top-up", amount, date, time);
                    if(isInserted =true)
                    {
                        System.out.println("added");
                    }
                    else
                        System.out.println("not added");
                    ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());

                }
                else
                    ((MainScreen) getActivity()).switchContent(fragment_add_money.newInstance());

            }
        };
        confirm.setOnClickListener(confirm_button_addmoney);

    }
}