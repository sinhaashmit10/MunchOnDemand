package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        EditText nameEditText = findViewById(R.id.editTextName);
        EditText emailEditText = findViewById(R.id.editTextTextEmail);
        EditText passwordEditText = findViewById(R.id.editTextTextPassword2);
        Button createAccountButton = findViewById(R.id.create_account_button);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    // Handle empty fields if needed
                    return;
                }

                // Call the registerNewUser function
                registerNewUser(name, email, password);
            }
        });
    }

    private void registerNewUser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-up user's information
                            // You can now login the user after successful registration
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                updateUserName(user, name);
                                loginUser(email, password);
                            }
                        } else {
                            // If sign up fails, display a message to the user.
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthException e) {
                                // Handle specific authentication errors if needed.
                                e.printStackTrace();
                            } catch (Exception e) {
                                // Handle other exceptions.
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void updateUserName(FirebaseUser user, String name) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // User's name is updated successfully
                        } else {
                            // Handle name update failure if needed
                        }
                    }
                });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, move to the next activity
                            moveToNextActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthException e) {
                                // Handle specific authentication errors if needed.
                                e.printStackTrace();
                            } catch (Exception e) {
                                // Handle other exceptions.
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(SignActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Optional: You can finish the current activity if needed
    }
}
