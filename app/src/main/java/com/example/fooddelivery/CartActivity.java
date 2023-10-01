package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private DatabaseReference cartRef;
    private FirebaseAuth mAuth;
    private TextView emptyCartText; // Add this variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Handle not signed in
            return;
        }

        // Initialize Firebase Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        cartRef = firebaseDatabase.getReference("cartItems");

        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emptyCartText = findViewById(R.id.emptyCartText); // Initialize the TextView

        cartItems = new ArrayList<>();

        // Set up a ValueEventListener to fetch cart items from the database
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cartItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CartItem cartItem = snapshot.getValue(CartItem.class);
                    if (cartItem != null) {
                        cartItem.setDatabaseKey(snapshot.getKey());
                        cartItems.add(cartItem);
                    }
                }
                cartAdapter.notifyDataSetChanged();

                // Update the visibility of the empty cart text based on the cartItems list
                if (cartItems.isEmpty()) {
                    emptyCartText.setVisibility(View.VISIBLE);
                } else {
                    emptyCartText.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });

        cartAdapter = new CartAdapter(this, cartItems);
        recyclerView.setAdapter(cartAdapter);

        // Initialize navigation image views
        ImageView homeImage = findViewById(R.id.imageView6);
        ImageView cartImage = findViewById(R.id.imageView7);
        ImageView listImage = findViewById(R.id.imageView9);
        ImageView profileImage = findViewById(R.id.imageView10);
        ImageView offerImage = findViewById(R.id.imageView9);  // Assuming this is the offer image

        // Set click listeners for navigation images
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });

        // Cart image is already on the cart page

        listImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MenuActivity.class));
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ProfileActivity.class));
            }
        });

        // Set click listener for search image button
        ImageView searchImage = findViewById(R.id.imageView8);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MenuActivity.class));
            }
        });

        // Set click listener for the "Next" button to open SelectAddressActivity and save the order
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user's UID
                String uid = currentUser.getUid();

                // Save cart items to separate database under user's UID
                DatabaseReference userCartRef = firebaseDatabase.getReference("userCarts").child(uid);
                for (CartItem item : cartItems) {
                    userCartRef.push().setValue(item);
                }

                // Show toast message
                Toast.makeText(CartActivity.this, "Order Saved", Toast.LENGTH_SHORT).show();

                // Open SelectAddressActivity
                startActivity(new Intent(CartActivity.this, SelectAddressActivity.class));
            }
        });

        // Set click listener for the offer image to open ListActivity
        offerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ListActivity.class));
            }
        });
    }
}
