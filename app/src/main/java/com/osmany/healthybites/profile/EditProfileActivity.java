package com.osmany.healthybites.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.osmany.healthybites.MainActivity;
import com.osmany.healthybites.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class EditProfileActivity extends AppCompatActivity {

    public static final String TAG = "EditProfileActivity";

    private Button btSubmit;
    private EditText etChangeEmail;
    private EditText etChangeUsername;
    private EditText etAge;
    private EditText etWeight;
    private EditText etHeight;
    private EditText etDiet;

    private String age, weight, height, diet, objectID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btSubmit =  findViewById(R.id.btSubmit);

        etChangeEmail =  findViewById(R.id.etChangeEmail);
        etChangeUsername =  findViewById(R.id.etChangeUsername);
        etAge =  findViewById(R.id.etAge);
        etHeight =  findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        etDiet =  findViewById(R.id.etDiet);

        /*  ParseUser currentUser = new ParseUser();
        etChangeUsername.setText(currentUser.getString("username"));
        etChangeEmail.setText(currentUser.getEmail());
        etAge.setText(currentUser.getString("age"));
        etHeight.setText(currentUser.getString("height"));
        etWeight.setText(currentUser.getString("weight"));
        etDiet.setText(currentUser.getString("diet")); */

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick Submit Button");
                age = etAge.getText().toString();
                height = etHeight.getText().toString();
                weight = etWeight.getText().toString();
                diet = etDiet.getText().toString();

                ParseUser currentUser = ParseUser.getCurrentUser();

                currentUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(EditProfileActivity.this, "Profile Updated..", Toast.LENGTH_SHORT).show();
                            currentUser.put("age", age);
                            currentUser.put("weight", weight);
                            currentUser.put("height" , height);
                            currentUser.put("diet", diet);
                            Log.i(TAG, "Data: " + " " + age + " " + weight + " " +height + " " +diet);
                            openMainActivity();
                        }else{
                            Log.i(TAG,"Save in background " + e.getLocalizedMessage());
                            Toast.makeText(EditProfileActivity.this, "Failed to update profile " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
