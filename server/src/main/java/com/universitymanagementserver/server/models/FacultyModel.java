package com.universitymanagementserver.server.models;

public class FacultyModel {
    private Integer userId;
    private String name;
    private String email;
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FacultyModel(Integer userId ,String name, String email, String password) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.password = password;

    }
}
