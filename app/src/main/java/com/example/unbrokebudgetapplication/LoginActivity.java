package com.example.unbrokebudgetapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private static final String FILE_EMAIL = "rememberMe" ;
    private static EditText ETmail,ETPassword;
    private Button BtnLogin;
    private TextView TVCreateAcc,TVForgotPass;
    private ImageView IVGoogle;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ProgressDialog progressDialog;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ETmail = findViewById(R.id.ETmail);
        ETPassword = findViewById(R.id.ETPassword);
        BtnLogin = findViewById(R.id.BtnLogin);

        mAuth =FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);

        //show hide password by clicking eye icon
        ImageView IVShowHidePass = findViewById(R.id.IVShowHidePass);
        IVShowHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ETPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // if password visible hide it first
                    ETPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //change icon to closed eye
                    IVShowHidePass.setImageResource(R.drawable.hide_password);
                } else {
                    ETPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    IVShowHidePass.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                }
            }
        });

        //save email and password
        CheckBox checkBox = (CheckBox) findViewById(R.id.CBRemember);
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_EMAIL, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String email = sharedPreferences.getString("svEmail", "");
        String password = sharedPreferences.getString("svPassword", "");
        if (sharedPreferences.contains("checked") && sharedPreferences.getBoolean("checked", false) == true){
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        ETmail.setText(email);
        ETPassword.setText(password);

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
                String emailString = ETmail.getText().toString().trim();
                String passwordString = ETPassword.getText().toString().trim();

                if (checkBox.isChecked()) {
                    editor.putBoolean("checked", true);
                    editor.apply();
                    StoreDataUsingSharedPref(emailString, passwordString);
                }
                if(TextUtils.isEmpty(emailString)){
                    ETmail.setError("Email is required");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
                    ETmail.setError("Please enter a valid email");
                }
                    if(TextUtils.isEmpty(passwordString)){
                    ETPassword.setError("Password is needed");
                }

                else{
                    progressDialog.setMessage("Login in progress");
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

        IVGoogle = findViewById(R.id.IVGoogle);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        IVGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
    }

    private void StoreDataUsingSharedPref(String emailString, String passwordString) {
        SharedPreferences.Editor editor = getSharedPreferences(FILE_EMAIL, MODE_PRIVATE).edit();
        editor.putString("svEmail", emailString);
        editor.putString("svPassword", passwordString);
        editor.apply();
    }

    public static String getUserEmail()
    {
        String email = ETmail.getText().toString();
        return email;
    }

    private void SignIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultcode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultcode, data);

        if(requestCode==100){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void HomeActivity() {
        finish();
        Intent intent = new Intent(getApplicationContext(), savingTips.class);
        startActivity(intent);

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
            Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
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