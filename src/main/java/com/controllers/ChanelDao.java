package com.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChanelDao {
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospital_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    Connection connection;

    public ChanelDao() {
    	try {
			this.connection=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void addChanel(Chanel chanel) throws SQLException {
    	try {
			this.connection=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "INSERT INTO Chanel (doctorId, date, reason) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chanel.getDoctorId());
            statement.setString(2, chanel.getDate());
            statement.setString(3, chanel.getReason());
            statement.executeUpdate();
        }
    }

    public void updateChanel(Chanel chanel) throws SQLException {
    	try {
			this.connection=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "UPDATE Chanel SET doctorId=?, date=?, reason=? WHERE chanelId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chanel.getDoctorId());
            statement.setString(2, chanel.getDate());
            statement.setString(3, chanel.getReason());
            statement.setInt(4, chanel.getId());
            statement.executeUpdate();
        }
    }

    public void deleteChanel(int chanelId) throws SQLException {
    	try {
			this.connection=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "DELETE FROM Chanel WHERE chanelId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chanelId);
            statement.executeUpdate();
        }
    }

    public Chanel getChanelById(int chanelId) throws SQLException {
    	try {
			this.connection=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	System.out.println("chanel id "+chanelId);
        String sql = "SELECT * FROM Chanel WHERE chanelId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chanelId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToChanel(resultSet);
                }
            }
        }
        return null;
    }

    public List<Chanel> getAllChanel() throws SQLException {
    	try {
			this.connection=DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Chanel> chanelList = new ArrayList<>();
        String sql = "SELECT * FROM Chanel";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Chanel chanel = mapResultSetToChanel(resultSet);
                chanelList.add(chanel);
            }
        }
        return chanelList;
    }

    private Chanel mapResultSetToChanel(ResultSet resultSet) throws SQLException {
        Chanel chanel = new Chanel();
 
       
        chanel.setDoctorId(resultSet.getInt("doctorId"));
        chanel.setDate(resultSet.getString("date"));
        chanel.setReason(resultSet.getString("reason"));
 
        	try {
        		chanel.setId(resultSet.getInt("chanelId"));
        	}catch (Exception e) {
        		
        	}
    
        return chanel;
    }
}
