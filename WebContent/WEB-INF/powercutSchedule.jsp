<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PowerCut Schedule</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/powercutSchedule.js"></script>

</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-8">
			
			<h1 class="text-center">Add PowerCut Record</h1>
			<br>
			
			<form id="formPowercut" name="formPowercut" method="post" action="powercutSchedule.jsp">
			
			  <div class="form-group">
			    <label for="powercutCode">Code:</label>
			    <input type="text" class="form-control" name="powercutCode" id="powercutCode">
			  </div>
			  
			  <div class="form-group">
			    <label for="name">Name:</label>
			    <input type="text" class="form-control" name="name" id="name">
			  </div>
			  
			  <div class="form-group">
			    <label for="group">Group:</label>
			    <input type="text" class="form-control" name="group" id="group">
			  </div>
			  
			  <div class="form-group">
			    <label for="dayStartTime">Day Start Time:</label>
			    <input type="text" class="form-control" name="dayStartTime" id="dayStartTime">
			  </div>
			  
			   <div class="form-group">
			    <label for="dayEndTime">Day End Time:</label>
			    <input type="text" class="form-control" name="dayEndTime" id="dayEndTime">
			  </div>
			  
			   <div class="form-group">
			    <label for="nightStartTime">Night Start Time:</label>
			    <input type="text" class="form-control" name="nightStartTime" id="nightStartTime">
			  </div>
			  
			   <div class="form-group">
			    <label for="nightEndTime">Night End Time:</label>
			    <input type="text" class="form-control" name="nightEndTime" id="nightEndTime">
			  </div>
			  
			  <div id="alertSuccess" class="alert alert-success"></div>
 			  <div id="alertError" class="alert alert-danger"></div>
			  
			  <button type="button" id="btnSave" name="btnSave" class="btn btn-default">Add</button>
			  <input type="hidden" id="hidIDSave" name="hidIDSave" value="">
			</form>
			
		</div>
	</div>
</div>

</body>
</html>