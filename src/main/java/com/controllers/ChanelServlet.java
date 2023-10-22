package com.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChanelServlet extends HttpServlet {
	   
    private ChanelDao chanelDao;

    @Override
    public void init() throws ServletException {
        super.init();
 
		

		    chanelDao = new ChanelDao();
		
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("view".equalsIgnoreCase(action)) {
            viewAllChanel(request, response);
        } else if ("edit".equalsIgnoreCase(action)) {
            editChanel(request, response);
        }
        else if("getChanel".equals(action)) {
        	int chanelId = Integer.parseInt(request.getParameter("chanelId"));
        	System.out.println("Chanel chane "+chanelId);
            Chanel chanel;
			try {
				chanel = chanelDao.getChanelById(chanelId);
				 String jsonResponse = convertChanelToJson(chanel);
		            response.setContentType("application/json");
		            response.getWriter().write(jsonResponse);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        
           
        }
        else if ("delete".equalsIgnoreCase(action)) {
            deleteChanel(request, response);
        } else {
            viewAllChanel(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equalsIgnoreCase(action)) {
            addChanel(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            updateChanel(request, response);
        } else {
          
        }
    }

    private void viewAllChanel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Chanel> chanelList = chanelDao.getAllChanel();
            request.setAttribute("chanelList", chanelList);
            request.getRequestDispatcher("/chanelList.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void addChanel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract parameters from the request and create a Chanel object
        Chanel chanel = createChanelFromRequest(request);
        try {
            chanelDao.addChanel(chanel);
            response.sendRedirect(request.getContextPath() + "/ChanelServlet?action=view");
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void editChanel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement logic to open the edit modal and pre-fill the form
        // You can use AJAX to fetch the details from the server based on chanelId
        // and populate the form fields in the modal
        int chanelId = Integer.parseInt(request.getParameter("id"));
        try {
            Chanel chanel = chanelDao.getChanelById(chanelId);
            request.setAttribute("chanel", chanel);
            request.getRequestDispatcher("/editChanel.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void updateChanel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Chanel chanel = createChanelFromRequest(request);
        try {
            chanelDao.updateChanel(chanel);
            response.sendRedirect(request.getContextPath() + "/ChanelServlet?action=view");
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void deleteChanel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int chanelId = Integer.parseInt(request.getParameter("id"));
        try {
            chanelDao.deleteChanel(chanelId);
            response.sendRedirect(request.getContextPath() + "/ChanelServlet?action=view");
        } catch (SQLException e) {
            // Handle database error
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Chanel createChanelFromRequest(HttpServletRequest request) {
        Chanel chanel = new Chanel();
       try {
    	   chanel.setId(Integer.parseInt(request.getParameter("id")));
       }catch(Exception e) {
    	   
       }
        chanel.setDoctorId(Integer.parseInt(request.getParameter("doctorId")));
        chanel.setDate(request.getParameter("date"));
        chanel.setReason(request.getParameter("reason"));
        return chanel;
    }

    @Override
    public void destroy() {
        // Close any resources (e.g., database connections) if needed
        super.destroy();
    }
    private String convertChanelToJson(Chanel chanel) {
   
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"chanelId\":").append(chanel.getId()).append(",");
        jsonBuilder.append("\"doctorId\":").append(chanel.getDoctorId()).append(",");
        jsonBuilder.append("\"date\":\"").append(chanel.getDate()).append("\",");
        jsonBuilder.append("\"reason\":\"").append(chanel.getReason()).append("\"");
        jsonBuilder.append("}");
        
        return jsonBuilder.toString();
    }
}
