package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Set a 3-second timeout using a Handler
        int splashTimeout = 3000; // 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity after the timeout
                Intent mainIntent = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, splashTimeout);
    }
}
