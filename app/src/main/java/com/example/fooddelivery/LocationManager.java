package com.example.fooddelivery;

public class LocationManager {
    private static LocationManager instance;
    private String currentCity;

    private LocationManager() {
        // Private constructor to prevent instantiation from other classes.
    }

    public static LocationManager getInstance() {
        if (instance == null) {
            instance = new LocationManager();
        }
        return instance;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String city) {
        this.currentCity = city;
    }
}
