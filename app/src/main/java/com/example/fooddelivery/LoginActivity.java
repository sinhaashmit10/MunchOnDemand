package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        TextView dontHaveAccountTextView = findViewById(R.id.textView7);

        dontHaveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignActivity when the TextView is clicked
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
        Log.d("LoginActivity", "Login button clicked");


        Button loginButton = findViewById(R.id.button3);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email and password from the EditText fields
                EditText emailEditText = findViewById(R.id.editTextTextEmailAddress);
                EditText passwordEditText = findViewById(R.id.editTextTextPassword);
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Perform Firebase authentication
                signInWithEmailAndPassword(email, password);
            }
        });
    }


    private void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Authentication successful, move to LocationActivity2
                            Intent intent = new Intent(LoginActivity.this, LocationActivity2.class);
                            startActivity(intent);
                            finish(); // Close the LoginActivity
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed. Wrong credentials.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
