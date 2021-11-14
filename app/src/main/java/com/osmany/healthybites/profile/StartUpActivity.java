package com.osmany.healthybites.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.osmany.healthybites.MainActivity;
import com.osmany.healthybites.R;
import com.parse.ParseUser;

public class StartUpActivity extends AppCompatActivity {

    private Button btnSignUp;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        if(ParseUser.getCurrentUser() != null){
            openMainActivity();
        }

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileCreationActivity();
            }
        });
    }

    private void openProfileCreationActivity() {
        Intent intent = new Intent(this, ProfileCreationActivity.class);
        startActivity(intent);

    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}