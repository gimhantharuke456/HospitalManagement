<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.controllers.Doctor" %>
<%@ page import="com.controllers.DoctorServlet" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Doctors</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">
    <!-- Custom Styles -->
    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }

        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-top: 20px;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            text-align: left;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #dee2e6;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .btn-group {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="mt-4 mb-4">Doctors</h1>

    <div class="btn-group">
    <button type="button" class="btn btn-success" id="addDoctorButton">Add Doctor</button>
        <a href="<%= request.getContextPath() + "/ChanelServlet?action=view" %>" class="btn btn-success" id="addDoctorButton">My Channels</a>
	</div>

    <!-- Display doctors -->
    <table class="table table-striped" id="doctorsTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Contact Number</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors"); %>
        <% if(doctors != null && !doctors.isEmpty()) {
           for (Doctor doctor : doctors) { %>
            <tr>
                <td><%= doctor.getDoctorId() %></td>
                <td><%= doctor.getName() %></td>
                <td><%= doctor.getEmail() %></td>
                <td><%= doctor.getContactNumber() %></td>
                <td>
                     <a href="addChanel.jsp?doctorId=<%= doctor.getDoctorId() %>" class="btn btn-info btn-sm">Channel</a>
                </td>
            </tr>
            <%-- Edit Modal --%>
            <div class="modal fade" id="editModal<%= doctor.getDoctorId() %>" tabindex="-1" role="dialog"
                 aria-labelledby="editModalLabel<%= doctor.getDoctorId() %>" aria-hidden="true">
                <%-- Modal content is generated dynamically based on the doctor --%>
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editModalLabel<%= doctor.getDoctorId() %>">Edit Doctor</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="editForm<%= doctor.getDoctorId() %>" method="post"
                                  action="<%= request.getContextPath() + "/doctors" %>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="doctorId" value="<%= doctor.getDoctorId() %>">
                                <div class="form-group">
                                    <label for="editName<%= doctor.getDoctorId() %>">Name:</label>
                                    <input type="text" class="form-control" id="editName<%= doctor.getDoctorId() %>"
                                           name="name" value="<%= doctor.getName() %>" required>
                                </div>
                                <div class="form-group">
                                    <label for="editEmail<%= doctor.getDoctorId() %>">Email:</label>
                                    <input type="email" class="form-control" id="editEmail<%= doctor.getDoctorId() %>"
                                           name="email" value="<%= doctor.getEmail() %>" required>
                                </div>
                                <div class="form-group">
                                    <label for="editContactNumber<%= doctor.getDoctorId() %>">Contact Number:</label>
                                    <input type="text" class="form-control"
                                           id="editContactNumber<%= doctor.getDoctorId() %>" name="contactNumber"
                                           value="<%= doctor.getContactNumber() %>" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%-- Delete Modal --%>
            <div class="modal fade" id="deleteModal<%= doctor.getDoctorId() %>" tabindex="-1" role="dialog"
                 aria-labelledby="deleteModalLabel<%= doctor.getDoctorId() %>" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel<%= doctor.getDoctorId() %>">Confirm Deletion</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete this doctor?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <a href="<%= request.getContextPath() + "/doctors?action=delete&id=" + doctor.getDoctorId() %>"
                               class="btn btn-danger">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
        <% }} %>
        </tbody>
    </table>
</div>

<!-- Add Doctor Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Add Doctor</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addForm" method="post" action="<%= request.getContextPath() + "/DoctorServlet" %>">
                    <input type="hidden" name="action" value="add">
                    <div class="form-group">
                        <label for="addName">Name:</label>
                        <input type="text" class="form-control" id="addName" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="addEmail">Email:</label>
                        <input type="email" class="form-control" id="addEmail" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="addContactNumber">Contact Number:</label>
                        <input type="text" class="form-control" id="addContactNumber" name="contactNumber" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-XrEUWmON8ZlVaCz9dILC7NjuEfnN6gngVrj4yzgF3HcqTQp0FqRIpswqLQlmh/jW"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyO2BUbuKRcJNoN9vI82+cO+hXO1NQPD8X"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>

<script>
    // Use jQuery to open the Add Doctor modal when the button is clicked
    $(document).ready(function () {
        $("#addDoctorButton").click(function () {
        	console.log("cel")
            $("#addModal").modal("show");
        });
    });
</script>
</body>
</html>
