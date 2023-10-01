package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        ImageView homeImage = findViewById(R.id.imageView6);
        ImageView cartImage = findViewById(R.id.imageView7);
        ImageView searchImage = findViewById(R.id.imageView8);
        ImageView offersImage = findViewById(R.id.imageView9);
        ImageView profileImage = findViewById(R.id.imageView10);
        ImageView offer1Image = findViewById(R.id.imageView18);
        ImageView offer2Image = findViewById(R.id.imageView19);
        ImageView offer3Image = findViewById(R.id.imageView20);
        ImageView offer4Image = findViewById(R.id.imageView21);
        ImageView offer5Image = findViewById(R.id.imageView24);
        ImageView offer6Image = findViewById(R.id.imageView25);

        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });

        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, CartActivity.class));
            }
        });

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, MenuActivity.class));
            }
        });

        offersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do nothing for now
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, ProfileActivity.class));
            }
        });

        offer1Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Offer 1 applied");
            }
        });

        offer2Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Offer 2 applied");
            }
        });

        offer3Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Offer 3 applied");
            }
        });

        offer4Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Offer 4 applied");
            }
        });

        offer5Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Offer 5 applied");
            }
        });

        offer6Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Offer 6 applied");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
