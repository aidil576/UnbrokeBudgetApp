package com.example.unbrokebudgetapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private EditText ETmail,ETPassword;
    private Button BtnLogin;
    private TextView TVCreateAcc,TVForgotPass;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ETmail = findViewById(R.id.ETmail);
        ETPassword = findViewById(R.id.ETPassword);
        BtnLogin = findViewById(R.id.BtnLogin);

        mAuth =FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        loginDetails();

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,fragment_money_record.class);
                startActivity(intent);
            }
        });
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = ETmail.getText().toString();
                String passwordString = ETPassword.getText().toString();

                if(TextUtils.isEmpty(emailString)){
                    ETmail.setError("Email is required");
                }
                if(TextUtils.isEmpty(passwordString)){
                    ETPassword.setError("Password is needed");
                }

                else{
                    progressDialog.setMessage("login in progress");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this,MainScreen.class);
                                startActivity(intent);
                                finish();
                                progressDialog.dismiss();
                            }else{
                                Toast.makeText(LoginActivity.this, "The email or password you entered is wrong. Please try again" , Toast.LENGTH_LONG).show();
                                //task.getException().toString()
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }

    private void loginDetails() {
        TVCreateAcc = findViewById(R.id.TVCreateAcc);
        TVForgotPass = findViewById(R.id.TVForgotPass);

        TVCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        TVForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, EmptyActivity.class);
            startActivity(intent);
//                Navigation.findNavController(view).navigate(R.id.ForgotPass);
            }
        });

    }
//                Intent intent = new Intent(LoginFragment.this, ForgotPassActivity.class);
//                startActivity(intent);

    public LoginActivity() {
        // Required empty public constructor
    }




}