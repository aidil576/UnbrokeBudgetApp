package com.example.unbrokebudgetapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText ETUsername,ETEmail,ETMobileNum,ETPass;
    private Button Btnregister;
    private TextView TVLogin;
    private ImageView IVBack;

    public FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    //FirebaseFirestore firestore;

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

        Btnregister.setOnClickListener((view) -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = ETEmail.getText().toString();
                String passwordString = ETPass.getText().toString();
                String userName = ETUsername.getText().toString();
                String phoneNum = ETMobileNum.getText().toString();

                if (TextUtils.isEmpty(userName)) {
                    ETUsername.setError("Username is required");
                }
                if (TextUtils.isEmpty(emailString)) {
                    ETEmail.setError("Email is required");
                }
                if (TextUtils.isEmpty(phoneNum)) {
                    ETPass.setError("Phone Number Is required");
                }
                if (TextUtils.isEmpty(passwordString)) {
                    ETPass.setError("Password Is required");
                } else {
                    progressDialog.setMessage("Register in progress");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegisterActivity.this, "Account created successfully!", Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user, emailString);//nak masuk coins
                                finish();
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
            }


        });

    }

    /*//nak masuk coins
    private void updateUI(FirebaseUser user , String email) {

        firestore = FirebaseFirestore.getInstance();

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("points", 0);
        map.put("uid", user.getUid());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");

        reference.child(user.getUid())
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });


        firestore.collection("points").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(RegisterActivity.this, "Account created successfully!", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
            }
        });

    }*/

}