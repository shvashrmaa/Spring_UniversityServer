package com.universitymanagementserver.server.models;

import com.universitymanagementserver.server.enums.GenderEnum;

public class UserModel {
    Integer userId;
    String name;
    String password;
    String email;
    Integer age;
    String gender;
    String profilePicUrl;

    public UserModel(Integer userId, String name, String password, String email, Integer age, String gender, String profilePicUrl) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender =  gender;
        this.profilePicUrl = profilePicUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}

