package com.example.demo.model;

public class Log {
    private String date;
    
    // Default constructor is required for JSON deserialization
    public Log() {}

    public Log(String date) {
        this.date = date;
    }

    // Getters and Setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}