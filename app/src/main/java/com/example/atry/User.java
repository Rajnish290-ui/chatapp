package com.example.atry;
public class User {
String email;
    String username;
    String password;
    String profile;
    String phoneNo;
    String userId;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(String email, String username, String password, String userId) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.userId=userId;

    }

    public User(){}

    public User(String email, String username, String photoUrl, String phoneNo,String userId ) {
        this.email = email;
        this.username = username;
        this.profile = photoUrl;
        this.phoneNo = phoneNo;
        this.userId =userId;
        this.password=password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



}


