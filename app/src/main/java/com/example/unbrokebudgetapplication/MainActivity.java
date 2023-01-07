package com.example.unbrokebudgetapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    //DBHelper myDB;
    //BottomNavigationView navigationView;

    Animation animation;

    private static int SPLASH = 3000;
    private ImageView imageView;
    private TextView appName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myDB = new DBHelper(this);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        imageView = findViewById(R.id.appLogo);
        appName = findViewById(R.id.appName);

        imageView.setAnimation(animation);
        appName.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginFragment.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, LoginFragment.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH);


        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, new fragment_money_record()).commit();
        //navigationView.setSelectedItemId(R.id.dailyExpenses);

//        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                switch (item.getItemId()) {
//                    case R.id.redeem:
//                        fragment = new point_redemption();
//                        break;
//
//                    case R.id.savingTip:
//                        fragment = new savingTips();
//                        break;
//                    case R.id.moneyRecord:
//                        fragment = new fragment_money_record();
//                        break;
//                    case R.id.dailyExpenses:
//                        fragment = new Fragment_Daily_Expenses();
//                        break;
//                    case R.id.profile:
//                        fragment = new LoginFragment();
//                        break;
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();
//                return true;
//            }
//        });

    }
}