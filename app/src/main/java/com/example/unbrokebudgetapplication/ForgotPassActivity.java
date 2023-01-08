package com.example.unbrokebudgetapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgotPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
    }
    public void backFunction(View view){
        Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}