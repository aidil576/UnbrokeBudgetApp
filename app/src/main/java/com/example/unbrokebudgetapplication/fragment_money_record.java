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
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class fragment_money_record extends Fragment {
    private Context context;
    DBHelper myDB;


    public fragment_money_record() {
        // Required empty public constructor
    }

    public static fragment_money_record newInstance() {
        return new fragment_money_record();
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


        TextView monthTextView = view.findViewById(R.id.month_top);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

        monthTextView.setText(month_name);

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
        if(!myDB.isIncomeTableEmpty())
        {
            if(!budget.isEmpty()) {
                if (budget.equals("532RULE")) {
                    total_income = Double.parseDouble(myDB.ShowLast());
                    need = (0.5) * total_income;
                    want = Double.parseDouble(df.format((0.3) * total_income));
                    savings = (0.2) * total_income;

                    bills = Double.parseDouble(df.format((0.4) * need));
                    groceries = Double.parseDouble(df.format((0.3) * need));
                    food = Double.parseDouble(df.format((0.2) * need));
                    transportation = Double.parseDouble(df.format((0.1) * need));
                } else if (budget.equals("60%RULE")) {
                    total_income = Double.parseDouble(myDB.ShowLast());
                    need = Double.parseDouble(df.format((0.6) * total_income));
                    want = (0.4) * total_income;
                    savings = (0.0) * total_income;

                    bills = Double.parseDouble(df.format((0.4) * need));
                    groceries = Double.parseDouble(df.format((0.3) * need));
                    food = Double.parseDouble(df.format((0.2) * need));
                    transportation = Double.parseDouble(df.format((0.1) * need));
                }
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

        Button viewDetail = view.findViewById(R.id.more_expenses_button);
        View.OnClickListener more_expenses_button = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(Fragment_Daily_Expenses.newInstance());
            }
        };
        viewDetail.setOnClickListener(more_expenses_button);




    }

}