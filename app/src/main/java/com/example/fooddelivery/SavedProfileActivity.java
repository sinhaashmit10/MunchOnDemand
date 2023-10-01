package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SavedProfileActivity extends AppCompatActivity {

    private TextView nameTextView, addressTextView, mobileTextView, birthdayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedprofile);

        nameTextView = findViewById(R.id.textView28);
        addressTextView = findViewById(R.id.textView31);
        mobileTextView = findViewById(R.id.textView32);
        birthdayTextView = findViewById(R.id.textView33);

        // Fetch and display the data from the Firebase database using user UID
        fetchAndDisplayUserData();

        // Set up click listeners for image views
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
                openMenuActivity();
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

    private void fetchAndDisplayUserData() {
        // Get the current user's UID
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Replace "users" with the correct reference to your users node in the database
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user != null) {
                    nameTextView.setText(user.getFullName());
                    addressTextView.setText(user.getAddress());
                    mobileTextView.setText(user.getMobile());
                    birthdayTextView.setText(user.getBirthday());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(SavedProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void openCartActivity() {
        Intent intent = new Intent(SavedProfileActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private void openMenuActivity() {
        Intent intent = new Intent(SavedProfileActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void openListActivity() {
        Intent intent = new Intent(SavedProfileActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
