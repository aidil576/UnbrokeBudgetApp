package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

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
    public static fragment_money_record newInstance() {
        return new fragment_money_record();
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
        View view = inflater.inflate(R.layout.fragment_money_record, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        myDB = new DBHelper(context);


        DecimalFormat df = new DecimalFormat("#.##");
//        Double income_latest = Double.valueOf(myDB.ShowLast());
//        TextView income_show = view.findViewById(R.id.income_value);
//        income_show.setText("RM" + Double.toString(income_latest));

        String str = myDB.ShowLast();
        Double income_latest;

        if (str.isEmpty()) {
            // Set income_latest to a default value or display an error message
            income_latest = 0.0;
        } else {
            income_latest = Double.valueOf(str);
        }
        TextView income_show = view.findViewById(R.id.income_value);
        income_show.setText("RM" + df.format(income_latest));

        Double total_available = myDB.calc_Sum();
        TextView balance = view.findViewById(R.id.balance_value);
        balance.setText("RM"+df.format(total_available));

        Double expenses_total = (myDB.calcNegative())*(-1);

        TextView expenses = view.findViewById(R.id.expenses_value);
        expenses.setText("RM"+df.format(expenses_total));

        String data;
        double total_income = 0;
        double need;
        double want = 0;
        double savings;
        double bills = 0;
        double groceries = 0;
        double food = 0;
        double transportation = 0;
        String budget = myDB.getBudget();
        //budget:
        if(!budget.isEmpty()) {
            if (budget.equals("532RULE")) {
                total_income = Double.parseDouble(myDB.ShowLast());
                need = (0.5) * total_income;
                want = (0.3) * total_income;
                savings = (0.2) * total_income;

                bills = (0.4) * need;
                groceries = (0.3) * need;
                food = (0.2) * need;
                transportation = (0.1) * need;
            }
            else if(budget.equals("60%RULE"))
            {
                total_income = Double.parseDouble(myDB.ShowLast());
                need = (0.6) * total_income;
                want = (0.4) * total_income;
                savings = (0.0) * total_income;

                bills = (0.4) * need;
                groceries = (0.3) * need;
                food = (0.2) * need;
                transportation = (0.1) * need;
            }
        }

        TextView Bills = view.findViewById(R.id.bills_value);
        Bills.setText("RM"+Double.toString(bills));

        TextView Groceries = view.findViewById(R.id.groceries_value);
        Groceries.setText("RM"+Double.toString(groceries));

        TextView Food = view.findViewById(R.id.food_value);
        Food.setText("RM"+Double.toString(food));

        TextView Transportation = view.findViewById(R.id.transportation_value);
        Transportation.setText("RM"+Double.toString(transportation));

        TextView Entertainment = view.findViewById(R.id.entertainment_value);
        Entertainment.setText("RM"+Double.toString(want));




        Button addmoney = view.findViewById(R.id.add_money_button);
        View.OnClickListener add_money_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_add_money.newInstance());
            }
        };
        addmoney.setOnClickListener(add_money_button);

        Button budget1 = view.findViewById(R.id.budget_rule_1_button);
        View.OnClickListener budget_rule_1_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(Fragment_1st_budget_guide.newInstance());
            }
        };
        budget1.setOnClickListener(budget_rule_1_button);

        Button budget2 = view.findViewById(R.id.budget_rule_2_button);
        View.OnClickListener budget_rule_2_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_2nd_budget_guide.newInstance());
            }
        };
        budget2.setOnClickListener(budget_rule_2_button);

        Button budget3 = view.findViewById(R.id.budget_rule_3_button);
        View.OnClickListener budget_rule_3_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_3rd_budget_guide.newInstance());
            }
        };
        budget3.setOnClickListener(budget_rule_3_button);

        Button income = view.findViewById(R.id.income_button);
        View.OnClickListener income_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_change_income.newInstance());
            }
        };
        income.setOnClickListener(income_button);

        Button record = view.findViewById(R.id.record_purchase_button);
        View.OnClickListener record_purchase_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(Fragment_record_purchase.newInstance());
            }
        };
        record.setOnClickListener(record_purchase_button);




    }

}