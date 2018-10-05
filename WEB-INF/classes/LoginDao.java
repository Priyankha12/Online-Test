
package DAO;
import Model.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class LoginDao 
{
    public int studentAuthentication(StudentBean s)
    {
       // JDBC driver name and database URL
       final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
       String name = s.getsname();
       String pass =s.getpassword();
       try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;     
         sql = "SELECT sname,password FROM Student";
         ResultSet rs = stmt.executeQuery(sql);
         // Extract data from result set
         while(rs.next())
         {
            //Retrieve by column name
            String name1 = rs.getString("sname");
            String pass1 = rs.getString("password");                              
           if(name.equals(name1)&& pass.equals(pass1))            
              return 1;         
        }
		return 0;
       }
       catch(SQLException se)
       {
         //Handle errors for JDBC
          se.printStackTrace();
       }
       catch(Exception e)
       {
         //Handle errors for Class.forName
         e.printStackTrace();
       }         
       return 0;
    }
}


