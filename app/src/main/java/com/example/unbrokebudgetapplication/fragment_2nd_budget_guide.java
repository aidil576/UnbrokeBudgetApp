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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_2nd_budget_guide#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_2nd_budget_guide extends Fragment {
    private Context context;
    DBHelper myDB = new DBHelper(context);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static fragment_2nd_budget_guide newInstance() {
        return new fragment_2nd_budget_guide();
    }
    public fragment_2nd_budget_guide() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_2nd_budget_guide.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_2nd_budget_guide newInstance(String param1, String param2) {
        fragment_2nd_budget_guide fragment = new fragment_2nd_budget_guide();
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
        return inflater.inflate(R.layout.fragment_2nd_budget_guide, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button back = view.findViewById(R.id.back_button_2ndBudget);
        View.OnClickListener back_button_2ndBudget = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());
            }
        };
        back.setOnClickListener(back_button_2ndBudget);

        Button use = view.findViewById(R.id.use_button_budget2);
        View.OnClickListener use_button_budget2 = new View.OnClickListener(){
            @Override
            public void onClick(View v){

                boolean isInserted = myDB.addGuide(getContext(),"60%RULE");
                ((MainScreen) getActivity()).switchContent(fragment_money_record.newInstance());
            }
        };
        use.setOnClickListener(use_button_budget2);

    }
}