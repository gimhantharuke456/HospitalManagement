package com.controllers;

import com.controllers.Doctor;
import com.controllers.DoctorDao;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DoctorServlet extends HttpServlet {
    private DoctorDao doctorDao;

    @Override
    public void init() throws ServletException {
        super.init();
        doctorDao = new DoctorDao();  // Assuming DoctorDao is properly initialized
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equalsIgnoreCase(action)) {
            // Handle the request to edit a doctor
            int doctorId = Integer.parseInt(request.getParameter("id"));
            Doctor doctor = doctorDao.getDoctorById(doctorId);
            request.setAttribute("doctor", doctor);
            request.getRequestDispatcher("/editDoctor.jsp").forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            // Handle the request to delete a doctor
            int doctorId = Integer.parseInt(request.getParameter("id"));
            doctorDao.deleteDoctor(doctorId);
            response.sendRedirect(request.getContextPath() + "/doctors");
        } else {
            // Default action: display the list of doctors
            List<Doctor> doctors = doctorDao.getAllDoctors();
            request.setAttribute("doctors", doctors);
            request.getRequestDispatcher("/doctors.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
           
            Doctor doctor = createDoctorFromRequest(request);
            doctorDao.addDoctor(doctor);
        } else if ("update".equalsIgnoreCase(action)) {
            // Handle the update of an existing doctor
            Doctor doctor = createDoctorFromRequest(request);
            doctorDao.updateDoctor(doctor);
        }

        response.sendRedirect(request.getContextPath() + "/DoctorServlet");
    }

    private Doctor createDoctorFromRequest(HttpServletRequest request) {
        // Extract parameters from the request and create a Doctor object
        Doctor doctor = new Doctor();
        if(request.getParameter("doctorId")!=null) {
        	doctor.setDoctorId(Integer.parseInt(request.getParameter("doctorId")));
        }
        doctor.setName(request.getParameter("name"));
        doctor.setEmail(request.getParameter("email"));
        doctor.setContactNumber(request.getParameter("contactNumber"));
        return doctor;
    }

    @Override
    public void destroy() {
      
        super.destroy();
    }
}
