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
 	var type = ($("#hidIDsave").val() == "") ? "POST" : "PUT";
 	
 	$.ajax(
	{
		url : "powercutAPI",
		type : type,
		data : $("#formPowercut").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onPowercutComplete(response.responseText, status);
		}
	});

});

//update----------------------------
$(document).on("click", ".btnUpdate", function(event)
{
	
	$("#hidIDSave").val($(this).data("id"));
 	$("#powercutCode").val($(this).closest("tr").find('td:eq(0)').text());
 	$("#name").val($(this).closest("tr").find('td:eq(1)').text());
 	$("#group").val($(this).closest("tr").find('td:eq(2)').text());
 	$("#dayStartTime").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#dayEndTime").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#nightStartTime").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#nightEndTime").val($(this).closest("tr").find('td:eq(6)').text()); 
	
});

//delete--------------------------------
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
			url : "powercutAPI",
			type : "DELETE",
			data : "id=" + $(this).data("id"),
			dataType : "text",
			complete : function(response, status)
			{
					onPowercutDeleteComplete(response.responseText, status);
			}
	}); 

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

function onPowercutComplete(response, status)
{
	if (status == "success")
	 {
	 		var resultSet = JSON.parse(response);
	 		if (resultSet.status.trim() == "success")
	 		{
	 				$("#alertSuccess").text("Successfully saved.");
	 				$("#alertSuccess").show();
	 				$("#divItemsGrid").html(resultSet.data);
	 		} else if (resultSet.status.trim() == "error")
	 		{
	 				$("#alertError").text(resultSet.data);
	 				$("#alertError").show();
	 		}
	 		} else if (status == "error")
	 		{
	 				$("#alertError").text("Error while saving.");
	 				$("#alertError").show();
	 		} else
	 		{
	 				$("#alertError").text("Unknown error while saving..");
	 				$("#alertError").show();
	 		}
			 		$("#hidItemIDSave").val("");
		 			$("#formItem")[0].reset(); 
}


function onPowercutDeleteComplete(response, status)
{
		if (status == "success")
		{
				var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
				$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
		}
		} else if (status == "error")
		{
				$("#alertError").text("Error while deleting.");
				$("#alertError").show();
		} else
		{
				$("#alertError").text("Unknown error while deleting..");
				$("#alertError").show();
		}
}



