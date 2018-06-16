package com.empinfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * Servlet implementation class InsertData
 */
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int empid = Integer.parseInt(req.getParameter("empid"));
		String empName = req.getParameter("name");
		String address = req.getParameter("address");
		int wday = Integer.parseInt(req.getParameter("wday"));
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nikhil_DB", "root", "root");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into emp23 values(?,?,?,?)");
			
			preparedStatement.setInt(1, empid);
			preparedStatement.setString(2, empName);
			preparedStatement.setString(3, address);
			preparedStatement.setInt(4, wday);
			preparedStatement.executeUpdate();
			resp.sendRedirect("index.html");
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
