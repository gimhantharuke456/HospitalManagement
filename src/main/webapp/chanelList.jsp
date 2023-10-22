<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.controllers.*" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chanel List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
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
    </style>
</head>
<body>

<div class="container">
    <h1 class="mt-4 mb-4">Chanel List</h1>

    <!-- Display Chanels -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Chanel ID</th>
            <th>Doctor ID</th>
            <th>Date</th>
            <th>Reason</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% List<Chanel> chanels = (List<Chanel>) request.getAttribute("chanelList"); %>
        <% if(chanels != null) {
            for (Chanel chanel : chanels) {
        %>
        <tr>
            <td><%= chanel.getId() %></td>
            <td><%= chanel.getDoctorId() %></td>
            <td><%= chanel.getDate() %></td>
            <td><%= chanel.getReason() %></td>
            <td>
                <!-- Edit Button -->
                <button type="button" class="btn btn-primary" onclick="editChanel(<%= chanel.getId() %>)" data-toggle="modal" data-target="#editModal">
                    Edit
                </button>
                <!-- Delete Button -->
                <a class="btn btn-danger" href="<%request.getContextPath(); %>?action=delete&id=<%= chanel.getId() %>" data-toggle="modal" data-target="#deleteModal">
                    Delete
                </a>
            </td>
        </tr>
        <% }} %>
        </tbody>
    </table>
</div>

<!-- Edit Chanel Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit Chanel</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Your form for editing -->
                <form id="editForm" action="<%= request.getContextPath() %>/ChanelServlet?action=update" method="post">
                	<input type="text" class="form-control" style="display:hidden" id="editChanelId" name="id" required>
                    <input type="hidden" id="editChanelId" name="chanelId">
                    <div class="form-group">
                        <label for="editDoctorId">Doctor ID:</label>
                        <!-- Replace this with a drop-down for selecting a doctor -->
                        <input type="text" class="form-control" id="editDoctorId" name="doctorId" required>
                    </div>
                    <div class="form-group">
                        <label for="editDate">Date:</label>
                        <input type="date" class="form-control" id="editDate" name="date" required>
                    </div>
                    <div class="form-group">
                        <label for="editReason">Reason:</label>
                        <textarea class="form-control" id="editReason" name="reason" rows="3" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Chanel</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Delete Chanel Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Confirm Delete Chanel</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this Chanel?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" onclick="deleteChanel()">Delete</button>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
    integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script>
    // Edit Chanel Function
    function editChanel(chanelId) {
    	console.log(chanelId)
    	 $.ajax({
    	        type: 'GET',
    	        url: '<%= request.getContextPath() %>/ChanelServlet?action=getChanel&chanelId=' + chanelId,
    	        success: function (chanel) {
    				console.log("chanel " + chanel)
    	            $('#editChanelId').val(chanel.chanelId);
    	            $('#editDoctorId').val(chanel.doctorId);
    	            $('#editDate').val(chanel.date);
    	            $('#editReason').val(chanel.reason);

    	            // Show the edit modal
    	            $('#editModal').modal('show');
    	        },
    	        error: function (error) {
    	        	console.log(error)
    	            console.error('Error fetching Chanel details:', error);
    	        }
    	    });
    }

    // Delete Chanel Function
    function confirmDelete(chanelId) {
        // Implement logic to open the delete modal and set the chanelId for deletion
    }

    function deleteChanel() {
        // Implement logic to delete the Chanel using AJAX
        // After success, refresh the page or update the table
    }
</script>

</body>
</html>
