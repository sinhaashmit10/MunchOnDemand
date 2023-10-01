package com.example.fooddelivery;

public class User {
    private String fullName;
    private String address;
    private String mobile;
    private String email;
    private String birthday;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String fullName, String address, String mobile, String email, String birthday) {
        this.fullName = fullName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
