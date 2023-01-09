package com.example.unbrokebudgetapplication;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_record_purchase#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_record_purchase extends Fragment {
    private Context context;
    DBHelper myDB;
    RadioGroup radioGroup;
    TextView textView;
    EditText cost;
    String expenses_type;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_record_purchase() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_record_purchase newInstance() {
        return new Fragment_record_purchase();
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

        return inflater.inflate(R.layout.fragment_record_purchase, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        myDB = new DBHelper(context);

        radioGroup = view.findViewById(R.id.type_RG);
        cost = view.findViewById(R.id.cost_ET);





        Button confirm_spend = view.findViewById(R.id.spend_confirm_button);
        View.OnClickListener spend_confirm_button = new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v){

                RadioButton bills= view.findViewById(R.id.select_bills);//---------------------------
                RadioButton groceries= view.findViewById(R.id.select_groceries);
                RadioButton food= view.findViewById(R.id.select_food);
                RadioButton transportation= view.findViewById(R.id.select_transportation);
                RadioButton entertainment= view.findViewById(R.id.select_entertainment);

                if(bills.isChecked())
                    expenses_type = "Bills";
                else if(groceries.isChecked())
                    expenses_type = "Groceries";
                else if(food.isChecked())
                    expenses_type = "Food";
                else if(transportation.isChecked())
                    expenses_type = "Transportation";
                else if(entertainment.isChecked())
                    expenses_type = "Entertainment";

                LocalDateTime now = LocalDateTime.now();
                Date date = Date.valueOf(String.valueOf(now.toLocalDate()));
                Time time = Time.valueOf(now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

                double amount = (Double.parseDouble(cost.getText().toString()))*(-1);

                boolean isInserted = myDB.addMoney(getContext(), expenses_type, amount, date, time);
                if(isInserted =true)
                {
                    System.out.println("added");
                }
                else
                    System.out.println("not added");

                ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());
            }
        };
        confirm_spend.setOnClickListener(spend_confirm_button);


        Button cancel_spend = view.findViewById(R.id.spend_cancel_button);
        View.OnClickListener spend_cancel_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());
            }
        };
        cancel_spend.setOnClickListener(spend_cancel_button);

    }


}