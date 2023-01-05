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
import android.widget.TextView;

import java.io.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_money_record#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_money_record extends Fragment {
    private Context context;
    DBHelper myDB;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_money_record() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_money_record.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_money_record newInstance(String param1, String param2) {
        fragment_money_record fragment = new fragment_money_record();
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
            /*File income = new File(getContext().getFilesDir(), "income.txt");

            // Create the file if it does not exist
            try {
                if (!income.exists()) {
                    income.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //Double number = 0.0;
            /*try {
                BufferedReader reader = new BufferedReader(new FileReader(income));
                String line = reader.readLine();
                number = Integer.parseInt(line);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/



        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money_record, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        myDB = new DBHelper(context);

        Double income_latest = Double.valueOf(myDB.ShowLast());
        TextView income_show = view.findViewById(R.id.income_value);
        income_show.setText("RM" + Double.toString(income_latest));

        Button addmoney = view.findViewById(R.id.add_money_button);
        View.OnClickListener add_money_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.to_add_money);
            }
        };
        addmoney.setOnClickListener(add_money_button);

        Button budget1 = view.findViewById(R.id.budget_rule_1_button);
        View.OnClickListener budget_rule_1_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.to1stbudget);
            }
        };
        budget1.setOnClickListener(budget_rule_1_button);

        Button budget2 = view.findViewById(R.id.budget_rule_2_button);
        View.OnClickListener budget_rule_2_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.to2ndbudget);
            }
        };
        budget2.setOnClickListener(budget_rule_2_button);

        Button budget3 = view.findViewById(R.id.budget_rule_3_button);
        View.OnClickListener budget_rule_3_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.to3rdbudget);
            }
        };
        budget3.setOnClickListener(budget_rule_3_button);

        Button income = view.findViewById(R.id.income_button);
        View.OnClickListener income_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.toincome);
            }
        };
        income.setOnClickListener(income_button);

        Button record = view.findViewById(R.id.record_purchase_button);
        View.OnClickListener record_purchase_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.torecordpurchase);
            }
        };
        record.setOnClickListener(record_purchase_button);
    }

}