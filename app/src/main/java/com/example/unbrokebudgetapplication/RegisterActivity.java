package com.example.unbrokebudgetapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText ETUsername,ETEmail,ETMobileNum,ETPass;
    private Button Btnregister;
    private TextView TVLogin;
    private ImageView IVBack;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ETUsername = findViewById(R.id.ETUsername);
        ETEmail = findViewById(R.id.ETEmail);
        ETMobileNum = findViewById(R.id.ETMobileNum);
        ETPass = findViewById(R.id.ETPass);
        Btnregister = findViewById(R.id.Btnregister);
        TVLogin = findViewById(R.id.TVLogin);
        IVBack = findViewById(R.id.IVBack);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        TVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        IVBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Btnregister.setOnClickListener((view) ->{
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        } );

        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = ETEmail.getText().toString();
                String passwordString = ETPass.getText().toString();
                String userName = ETUsername.getText().toString();
                String phoneNum = ETMobileNum.getText().toString();

                if (TextUtils.isEmpty(userName)){
                    ETUsername.setError("Username is required");
                    ETUsername.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(emailString)){
                    ETEmail.setError("Email is required");
                    ETEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
                    ETEmail.setError("Please provide a valid email");
                    ETEmail.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(phoneNum)){
                    ETMobileNum.setError("Phone Number Is required");
                    ETMobileNum.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(passwordString)){
                    ETPass.setError("Password Is required");
                    ETPass.requestFocus();
                    return;
                }
                if (passwordString.length() < 6){
                    ETPass.setError("Min password length should be 6 characters");
                    ETPass.requestFocus();
                }

                else{
                    progressDialog.setMessage("Register in progress");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(userName, emailString, phoneNum);
                                FirebaseDatabase.getInstance().getReference("Users") //send data to realtime database
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(RegisterActivity.this, "Registration is success", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegisterActivity.this, "Account created successfully!", Toast.LENGTH_LONG).show();
                                finish();
                                progressDialog.dismiss();
                            }else{
                                Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }
        });


    }
}