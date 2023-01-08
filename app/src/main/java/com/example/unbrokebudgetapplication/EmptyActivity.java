package com.example.unbrokebudgetapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        loadFragment(new ForgotPass());
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //frame_container is your layout name in xml file
        transaction.replace(R.id.NHFEmptyActivity, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void backFunction(View view){
        Intent intent = new Intent(EmptyActivity.this, LoginActivity.class);
        startActivity(intent);
    }


}