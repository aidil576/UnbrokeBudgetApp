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
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_change_income#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_change_income extends Fragment {
    private static final String FILE_NAME = "income.txt";

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_income, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button cancel = view.findViewById(R.id.income_cancel);
        View.OnClickListener income_cancel = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(view).navigate(R.id.income_moneyrecord);
            }
        };
        cancel.setOnClickListener(income_cancel);


        Button confirm = view.findViewById(R.id.income_confirm);
        View.OnClickListener income_confirm = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File("income.txt");


                EditText edit_income = view.findViewById(R.id.edit_income);
                String income = edit_income.getText().toString();

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(income); // write the contents of the EditText field to the file
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();//here
                }

                Navigation.findNavController(view).navigate(R.id.income_moneyrecord);
            }
        };
        confirm.setOnClickListener(income_confirm);

    }
}