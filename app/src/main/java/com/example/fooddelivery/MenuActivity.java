package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItem> cartItems;
    private SearchView searchView;
    private DatabaseReference cartRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize Firebase Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        cartRef = firebaseDatabase.getReference("cartItems");

        // Initialize RecyclerView and its adapter
        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartItems = new ArrayList<>();
        cartItems.add(new MenuItem("Chicken Biryani", 250, R.drawable.chickenbiryani));
        cartItems.add(new MenuItem("Butter Chicken", 190, R.drawable.butterchicken));
        cartItems.add(new MenuItem("Margarita Pizza", 150, R.drawable.margaritapizza));
        cartItems.add(new MenuItem("French Fries", 100, R.drawable.frenchfries));
        cartItems.add(new MenuItem("Butterscotch Shake", 120, R.drawable.butterscotch));

        menuAdapter = new MenuAdapter(cartItems);
        menuRecyclerView.setAdapter(menuAdapter);

        // Set up the SearchView
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                menuAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // Initialize navigation image views
        ImageView homeImage = findViewById(R.id.imageView6);
        ImageView cartImage = findViewById(R.id.imageView7);
        ImageView searchImage = findViewById(R.id.imageView8);
        ImageView profileImage = findViewById(R.id.imageView10);
        ImageView offerImage = findViewById(R.id.imageView9);  // Assuming this is the offer image

        // Set click listeners for navigation images
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });

        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CartActivity.class));
            }
        });

        // List image has no action

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ProfileActivity.class));
            }
        });

        // Set click listener for "Add to Cart" button in MenuAdapter
        menuAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                // Add the selected item to the Firebase Database along with the image
                String itemId = cartRef.push().getKey();
                if (itemId != null) {
                    // Create a new CartItem with the MenuItem data
                    CartItem cartItem = new CartItem(item.getName(), item.getPrice(), item.getImageResource());

                    // Add the cart item to the database
                    cartRef.child(itemId).setValue(cartItem);

                    Toast.makeText(MenuActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for "Go to Cart" button
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CartActivity.class));
            }
        });

        // Set click listener for the offer image to open ListActivity
        offerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ListActivity.class));
            }
        });
    }
}
