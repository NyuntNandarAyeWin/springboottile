<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	
	alert("OK Jquery");
	
		$("#btnSave").click(function(){
			
			var name=$("#name").val();
			var rollNo=$("#rollNo").val();
			var std={name:name,rollNo:rollNo};
			$.ajax({
			      type: 'POST',
			      contentType : "application/json",
			      url: "/ajax_save",
			      data:JSON.stringify(std),
			      dataType: "json",
			      success: function(resultData) { 
			    	  
			    	  alert("save successful");
			    	  console.log(resultData);
			    	  $("#std_table").append(
			    				'<tr>'
			    					+'<td>'+resultData.name+'</td>'
			    					+'<td>'+resultData.rollNo+'</td>'
			    				+'</tr>'
			    		  );
			      }
			});
		});
		call_student();
});
function call_student(){
	$.ajax({
	      type: 'GET',
	      contentType : "application/json",
	      url: "/students",
	     
	    
	      success: function(resultData) { 
	    	  
	    	  resultData.forEach(function(std,i){
	    		  $("#std_table").append(
	    				'<tr>'
	    					+'<td>'+std.name+'</td>'
	    					+'<td>'+std.rollNo+'</td>'
	    					+'<td><a  onclick="deleteStudent('+std.id+')">Del</a></td>'
	    				+'</tr>'
	    		  );
	    		  
	    	  });
	      }
	});
}
function deleteStudent(id){
	$.ajax({
	      type: 'DELETE',
	      url: "/delete/"+id,
	     
	    
	      success: function(resultData) { 
	    	  alert('delete success');
	      }
	});
}
</script>
</head>
<body>
<div>
	<label>Name:</label> <input type="text" id="name" />
	<br>
	<label>Roll NO:</label><input type="text" id="rollNo" />
	<button id="btnSave">Save</button>
</div>
<div>
<table id="std_table">
	<thead>
	<tr>
		<th>Name</th>
		<th>Roll No</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
		<tr>
		
		</tr>
	</tbody>
</table>
</div>
</body>
</html>