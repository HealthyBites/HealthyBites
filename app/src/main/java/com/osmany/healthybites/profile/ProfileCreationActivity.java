package com.osmany.healthybites.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.osmany.healthybites.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class ProfileCreationActivity extends AppCompatActivity {

    public static final String TAG = "ProfileCreationActivity";

    private Button btSignUp;
    private EditText etNewUsername;
    private EditText etNewPassword;
    private EditText etNewEmail;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
        
        btSignUp = findViewById(R.id.btSignUp);
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        etNewEmail = findViewById(R.id.etNewEmail);
        
        
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick Signup Button");
                String username = etNewUsername.getText().toString();
                String email = etNewEmail.getText().toString();
                String password = etNewPassword.getText().toString();
                signupUser(username, email ,password);
                openLoginActivity(); 
            }
        });
    }

    private void signupUser(String username, String email,String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    openLoginActivity();
                    Toast.makeText(ProfileCreationActivity.this, "Successful Sign Up!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Issue with signup", e);
                    return;
                }
            }
        });
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}