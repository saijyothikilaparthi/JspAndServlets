package jsbwebsite1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class login extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	String name=req.getParameter("username");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	try {
		PrintWriter pw=resp.getWriter();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggy","root","root");
		String sql="insert into login(name,email,password) values(?,?,?)";
		PreparedStatement pmst=conn.prepareStatement(sql);
		pmst.setString(1, name);
		pmst.setString(2, email);
		pmst.setString(3, password);
		
		int i=pmst.executeUpdate();
		if(i>0) {
			pw.println("data added");
		}
		else {
			pw.println("error");
		}
		pmst.close();
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	

}
