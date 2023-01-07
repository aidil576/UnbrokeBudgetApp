package com.example.unbrokebudgetapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH = 3000;
    Animation animation;
    private ImageView imageView;
    private TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        imageView = findViewById(R.id.logo);
        appName = findViewById(R.id.appName);

        imageView.setAnimation(animation);
        appName.setAnimation(animation);
    }
}