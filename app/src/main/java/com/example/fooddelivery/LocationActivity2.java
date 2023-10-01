package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity2 extends AppCompatActivity {

    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location2);

        // Get a reference to the Spinner
        Spinner spinner = findViewById(R.id.spinner);

        // Get the array of cities from the strings.xml resource file
        String[] citiesArray = getResources().getStringArray(R.array.cities_array);

        // Create an ArrayAdapter using the citiesArray and a default layout for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, citiesArray);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set the OnItemSelectedListener for the spinner to get the selected city
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected city from the spinner
                selectedCity = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected in the spinner (if required)
            }
        });

        // Initialize the Next button and set its click listener
        Button nextButton = findViewById(R.id.button4);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    private void openMainActivity() {
        // Pass the selected city to MainActivity using an Intent extra
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedCity", selectedCity);
        startActivity(intent);
        finish(); // Optional: Finish this activity to prevent going back to it when pressing back in MainActivity.
    }
}
