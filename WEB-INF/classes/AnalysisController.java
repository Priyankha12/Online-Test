package Controller;
import Model.*;
import DAO.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class AnalysisController extends HttpServlet
{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
    
	   // JDBC driver name and database URL
       final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
       response.setContentType("text/html");
	   PrintWriter out=response.getWriter();
	   out.println("<html>");
	   out.println("<head>");
	   out.println("<title>Analysis Controller</title>");
	   out.println("</head>");
	   out.println("<body><h2>Result Analysis</h2><br>");
       try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
		 
          stmt = conn.createStatement();
         String sql;     
         sql = "SELECT qno,noa,noc FROM analysis";
         ResultSet rs = stmt.executeQuery(sql);
		 
         // Extract data from result set
         while(rs.next())
         {
            //Retrieve by column name
            int qno1 = rs.getInt("qno");
			int noa1 = rs.getInt("noa");
			int noc1 = rs.getInt("noc");
			out.println("<br><br>Question no "+qno1);//+". "+ques1);
			out.println("<br>Number of Attempts : "+noa1);
			out.println("<br>Number of Correct answers : "+noc1);
			float f1=noc1;
			float f2=noa1;
			float x=(f1/f2)*100;
			out.println("<br>Total Percentage : "+x+"%");
		 }
		 out.println("</body></html>");
		 }
       catch(SQLException se)
       {
         //Handle errors for JDBC
		 out.println("Exception1");
          se.printStackTrace();
       }
       catch(Exception e)
       {
         //Handle errors for Class.forName
		 out.println("Exception2");
         e.printStackTrace();
       }         
    }
}


