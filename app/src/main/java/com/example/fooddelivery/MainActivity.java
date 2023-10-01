package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Find the image views by their IDs
        ImageView imageView7 = findViewById(R.id.imageView7);
        ImageView imageView8 = findViewById(R.id.imageView8);
        ImageView imageView9 = findViewById(R.id.imageView9);
        ImageView imageView10 = findViewById(R.id.imageView10);
        ImageView imageView5 = findViewById(R.id.imageView5); // New ImageView

        // Set click listeners for the image views
        imageView7.setOnClickListener(v -> openCartActivity());
        imageView8.setOnClickListener(v -> openSearchActivity());
        imageView9.setOnClickListener(v -> openListActivity());
        imageView10.setOnClickListener(v -> openProfileActivity());
        imageView5.setOnClickListener(v -> openLoginActivity()); // Set click listener for imageView5

        // Set up the image slider
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_INSIDE));
        imageList.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_INSIDE));
        imageList.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_INSIDE));
        imageSlider.setImageList(imageList);

        // Set click listener for "View Menu" TextView
        TextView viewMenuTextView = findViewById(R.id.textView17);
        viewMenuTextView.setOnClickListener(v -> openMenuActivity());

        // Set user's name to the textView25
        updateUserDisplayName();
    }

    private void updateUserDisplayName() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String displayName = user.getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                TextView welcomeTextView = findViewById(R.id.textView25);
                welcomeTextView.setText("Hey, " + displayName);
            }
        }
    }

    private void openCartActivity() {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private void openSearchActivity() {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void openListActivity() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }

    private void openProfileActivity() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openMenuActivity() {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void openLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Replace LoginActivity with the actual LoginActivity class name
        startActivity(intent);
    }
}
