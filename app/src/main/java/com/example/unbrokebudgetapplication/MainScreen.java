package com.example.unbrokebudgetapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainScreen extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.main_frame2);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame2,new fragment_money_record()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch(item.getItemId()){
                    case R.id.moneyRecord:
                        selectedFragment = new fragment_money_record();
                        break;
                    case R.id.savingTip:
                        selectedFragment = new savingTips();
                        break;
                    case R.id.redeem:
                        selectedFragment = new entertainment_page();
                        break;
                    case R.id.dailyExpenses:
                        selectedFragment = new Fragment_Daily_Expenses();
                    case R.id.profile:
                        selectedFragment = new lucky_wheel();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame2, selectedFragment).commit();
                return true;
            }
        });
    }
}