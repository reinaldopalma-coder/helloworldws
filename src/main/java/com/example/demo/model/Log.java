package com.example.demo.model;

public class Log {
	private Integer id;
	private String date;
	private String geo;
	private Long temperature;
	
	// Default constructor is required for JSON deserialization
	public Log() {
	}

	public Log(Integer id, String date) {
		this.id = id;
		this.date = date;
	}

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}