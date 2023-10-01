package com.example.fooddelivery;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class SelectAddressActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private static final String CHANNEL_ID = "OrderPlacedChannel"; // Channel ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);

        nameEditText = findViewById(R.id.nameText);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);

        findViewById(R.id.placeOrderButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                // Here, you can perform any validation you want before proceeding to the next activity.

                // Send order placed notification
                sendOrderPlacedNotification();

                Intent intent = new Intent(SelectAddressActivity.this, OrderPlacedActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
    }

    private void sendOrderPlacedNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

        Intent intent = new Intent(this, OrderPlacedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Order Placed")
                .setContentText("Your order has been placed and will reach you soon.")
                .setSmallIcon(R.drawable.logo) // Replace with your notification icon
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        CharSequence name = "Order Placed Channel";
        String description = "Channel for order placed notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
