package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Fragment_Daily_Expenses extends Fragment {
    private Context context;
    DBHelper myDB;

    public Fragment_Daily_Expenses() {
        // Required empty public constructor
    }

    public static Fragment_Daily_Expenses newInstance() {
        return new Fragment_Daily_Expenses();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__daily__expenses, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        myDB = new DBHelper(context);
        DecimalFormat df = new DecimalFormat("#.##");


        String foodTxt = "Food";
        String billsTxt = "Bills";
        String groceriesTxt = "Groceries";
        String transportTxt = "Transportation";
        String entertainmentTxt = "Entertainment";

        double balance = (myDB.calcSumOfAType(foodTxt) + myDB.calcSumOfAType(groceriesTxt) + myDB.calcSumOfAType(entertainmentTxt) + myDB.calcSumOfAType(transportTxt) + myDB.calcSumOfAType(billsTxt))*-1;

        double food = (myDB.calcSumOfAType(foodTxt)*-1);
        double grocery = (myDB.calcSumOfAType(groceriesTxt)*-1);
        double entertainment = (myDB.calcSumOfAType(entertainmentTxt)*-1);
        double transportation = (myDB.calcSumOfAType(transportTxt)*-1);
        double bill = (myDB.calcSumOfAType(billsTxt)*-1);


        TextView Balance = view.findViewById(R.id.totalBalanceAmount);
        Balance.setText("RM" + df.format(balance));

        TextView Food = view.findViewById(R.id.food_price);
        Food.setText(("RM" + df.format(food)));
        TextView Grocery = view.findViewById(R.id.grocery_price);
        Grocery.setText("RM" + df.format(grocery));
        TextView Entertainment = view.findViewById(R.id.entertainment_price);
        Entertainment.setText("RM" + df.format(entertainment));
        TextView Transportation = view.findViewById(R.id.transportation_price);
        Transportation.setText("RM" + df.format(transportation));
        TextView Bill = view.findViewById(R.id.bill_price);
        Bill.setText("RM" + df.format(bill));


    }
}