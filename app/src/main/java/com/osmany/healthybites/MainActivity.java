package com.osmany.healthybites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.osmany.healthybites.fragments.RandomRecipiesFragment;


public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                    default:
                        fragment = new RandomRecipiesFragment();
                        break;
//                    case R.id.action_compose:
//                        fragment = new ComposeFragment();
//                        break;
//                    case R.id.action_compose:
//                        fragment = new ComposeFragment();
//                        break;
//                    case R.id.action_profile:
//                    default:
//                        fragment = new ProfileFragment();
//                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

}