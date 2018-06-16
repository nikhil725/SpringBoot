<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

<body bgcolor="Cornsilk">
	<form name="myform" action="EmpUpdateDelete" method="post">
	<center><h1>Employee Data</h1></center>
		<table border="1">
			<tr>
				<td>empID</td>
				<td>emp Name</td>
				<td>emp Address</td>
				<td>emp Salary</td>
				<td>Working day</td>
			</tr>

			<%
				ResultSet resultSet = (ResultSet) request.getAttribute("rs");
				int count=0;
				while (resultSet.next()) {
					
			%>
			<tr>
				
				<td><input type="text" name="empid" value="<%=resultSet.getInt(1)%>"></td>
				<td><input type="text" name="empname"  value="<%=resultSet.getString(2)%>"></td>
				<td><input type="text" name="empaddress" value="<%=resultSet.getString(3)%>"></td>
				<td><input type="text" name="empsalary" value="<%=resultSet.getInt(4)%>"></td>
				<td><input type="text" name="workingday" value="<%=resultSet.getInt(5)%>"></td>
			</tr>
					
			<%
				}
			%>
		</table>
		</center>
		<br>
		<br>
	</form>
</body>
</html>