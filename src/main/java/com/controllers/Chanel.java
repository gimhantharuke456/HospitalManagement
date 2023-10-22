package com.controllers;

public class Chanel {
	private int id;
    private int doctorId;
    private String date;
    private String reason;

    public Chanel() {
        // Default constructor
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Chanel(int doctorId, String date, String reason) {
        this.doctorId = doctorId;
        this.date = date;
        this.reason = reason;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
