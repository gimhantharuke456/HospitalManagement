package com.controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospital_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final String INSERT_DOCTOR = "INSERT INTO doctors (name, email, contact_number) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctors";
    private static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctors WHERE doctor_id = ?";
    private static final String UPDATE_DOCTOR = "UPDATE doctors SET name = ?, email = ?, contact_number = ? WHERE doctor_id = ?";
    private static final String DELETE_DOCTOR = "DELETE FROM doctors WHERE doctor_id = ?";

    public void addDoctor(Doctor doctor) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getEmail());
            statement.setString(3, doctor.getContactNumber());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating doctor failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    doctor.setDoctorId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating doctor failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_DOCTORS)) {

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setContactNumber(resultSet.getString("contact_number"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public Doctor getDoctorById(int doctorId) {
        Doctor doctor = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_DOCTOR_BY_ID)) {

            statement.setInt(1, doctorId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    doctor = new Doctor();
                   
                    doctor.setName(resultSet.getString("name"));
                    doctor.setEmail(resultSet.getString("email"));
                    doctor.setContactNumber(resultSet.getString("contact_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    public void updateDoctor(Doctor doctor) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE_DOCTOR)) {

            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getEmail());
            statement.setString(3, doctor.getContactNumber());
            statement.setInt(4, doctor.getDoctorId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int doctorId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DELETE_DOCTOR)) {

            statement.setInt(1, doctorId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
