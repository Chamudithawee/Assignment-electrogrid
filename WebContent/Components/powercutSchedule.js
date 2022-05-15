$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});


//save--------------------
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();
 	
 	
 	// Form validation-------------------
	var status = validatePowercutForm();
	if (status != true)
 	{
 		$("#alertError").text(status);
 		$("#alertError").show();
 		return;
 	}
 	
 	// If valid------------------------
 	$("#formItem").submit();

});

//update----------------------------
$(document).on("click", ".btnUpdate", function(event)
{
	
	$("#hidIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());
 	$("#powercutCode").val($(this).closest("tr").find('td:eq(0)').text());
 	$("#name").val($(this).closest("tr").find('td:eq(1)').text());
 	$("#group").val($(this).closest("tr").find('td:eq(2)').text());
 	$("#dayStartTime").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#dayEndTime").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#nightStartTime").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#nightEndTime").val($(this).closest("tr").find('td:eq(6)').text()); 
	
});














//---------------------------------Client-Model------------------------------------

function validatePowercutForm()
{
	if ($("#powercutCode").val().trim() == "")
 	{
 		return "Insert Code.";
 	}
 	
	// NAME
	if ($("#name").val().trim() == "")
 	{
 		return "Insert Name.";
 	} 
 	
 	if ($("#group").val().trim() == "")
 	{
 		return "Insert group.";
 	} 
 	
 	return true;
 	
}



