package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private EditText fullNameEditText, addressEditText, mobileEditText, emailEditText, birthdayEditText;
    private Button submitButton, savedProfileButton; // Added savedProfileButton

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Database reference with your custom URL
        String customDatabaseUrl = "https://munch-on-demand-default-rtdb.firebaseio.com/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(customDatabaseUrl);
        usersRef = database.getReference("users");

        fullNameEditText = findViewById(R.id.fullNameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        birthdayEditText = findViewById(R.id.editTextDate);
        submitButton = findViewById(R.id.submitButton);
        savedProfileButton = findViewById(R.id.button6); // Initialize the button

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });

        savedProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSavedProfileActivity(); // Handle button click to open SavedProfileActivity
            }
        });

        ImageView homeImage = findViewById(R.id.imageView6);
        ImageView cartImage = findViewById(R.id.imageView7);
        ImageView searchImage = findViewById(R.id.imageView8);
        ImageView listImage = findViewById(R.id.imageView9);
        ImageView profileImage = findViewById(R.id.imageView10);

        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });

        listImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListActivity();
            }
        });

        // profileImage does nothing as required
    }

    private void saveUserProfile() {
        String fullName = fullNameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String birthday = birthdayEditText.getText().toString().trim();

        if (!fullName.isEmpty() && !address.isEmpty() && !mobile.isEmpty() && !email.isEmpty() && !birthday.isEmpty()) {
            // Get the current user's UID
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            User userProfile = new User(fullName, address, mobile, email, birthday);

            // Push the user profile to the "users" node using the user UID as the key
            usersRef.child(uid).setValue(userProfile);

            Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show();

            // Clear the input fields
            fullNameEditText.getText().clear();
            addressEditText.getText().clear();
            mobileEditText.getText().clear();
            emailEditText.getText().clear();
            birthdayEditText.getText().clear();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMainActivity() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void openCartActivity() {
        Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private void openSearchActivity() {
        Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void openListActivity() {
        Intent intent = new Intent(ProfileActivity.this, ListActivity.class);
        startActivity(intent);
    }

    private void openSavedProfileActivity() {
        Intent intent = new Intent(ProfileActivity.this, SavedProfileActivity.class);
        startActivity(intent);
    }
}
