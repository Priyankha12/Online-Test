package DAO;
import Model.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class QuestionDao 
{
    public QuestionBean getQuestions(int x)
    {
       // JDBC driver name and database URL
       final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
	   QuestionBean q=new QuestionBean();
       try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;     
         sql = "SELECT qno,ques,cha,chb,chc,chd FROM Question";
         ResultSet rs = stmt.executeQuery(sql);
         // Extract data from result set
		 int i=1;
		 while(rs.next())
         {
            //Retrieve by column name
			if(i==x)
			{
             q.qno = rs.getInt("qno");
             q.ques = rs.getString("ques"); 
			 q.cha=rs.getString("cha");
			 q.chb=rs.getString("chb");
			 q.chc=rs.getString("chc");
			 q.chd=rs.getString("chd");
			}
			 i++;
			 
        }
		
		
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
    return q;
    }
	
	public String[] getAnswers()
	{
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
       String[] a=new String[5];
       try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;     
         sql = "SELECT ans FROM Question";
         ResultSet rs = stmt.executeQuery(sql);
         // Extract data from result set
		 
		 int i;
		
		 i=0;
         while(rs.next())
         {
            //Retrieve by column name
             a[i] = rs.getString("ans");
             i++;
        }
		
		return a;
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
     return a;
	}
	public void updatescore(String name,String pass,int score)
    {
       // JDBC driver name and database URL
       final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;     
         sql = "Update Student set score='"+score+"' where sname='"+name+"' and password='"+pass+"'";
         int z = stmt.executeUpdate(sql);
		 String sql2;
		 sql2 = "Insert into Scores values('"+name+"','"+score+"')";
		 z= stmt.executeUpdate(sql2);
         // Extract data from result set
         
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
       
    }
	
	
	public int getRank(String name)
	{
		  final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
	   int rank=0;
	   try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;     
         sql = "Select sname,score from Scores order by score DESC";
		 int prev=-1;
         ResultSet rs = stmt.executeQuery(sql);
		 while(rs.next())
		 {
			 String s=rs.getString("sname");
			 int a=rs.getInt("score");
			 if((s.equals(name)==false&&a!=prev)||rank==0)
			 rank++;
			 if(s.equals(name)==true)
				 break;
			 prev=a;
			 
		 }
         
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
     return rank; 
	}	
	
	public ScoreBean ret_score(int x)
	{
	   final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
	   //int rank=1;
	   ScoreBean sb = new ScoreBean();
	   try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;     
         sql = "Select sname,score from Scores order by score DESC";
         ResultSet rs = stmt.executeQuery(sql);
		 int c=0;
		 
		 while(rs.next())
		 {
			 String s=rs.getString("sname");
			 int a=rs.getInt("score");
			 if(c==x)
			 {
				 sb.setsname(s);
				 sb.setscore(a);
				 break;
			 }
			 
			 c++;
		 }
         
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
     return sb; 
	}	
	public void update_analysis(int qno1,boolean correct){
		
		
	   final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
       final String DB_URL="jdbc:mysql://localhost:3306/TEST";
       //  Database credentials
       final String USER = "root";
       final String PASS = "priyankha12";
       Connection  conn = null;
       Statement stmt = null;
	   int a=0,b=0;
	
	   try{
	   
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
       String sql1,sql2;     
		sql1="SELECT noa,noc FROM analysis where qno='"+qno1+"'";
		ResultSet rs=stmt.executeQuery(sql1);
		rs.next();
	
		 a=rs.getInt("noa");
		 b=rs.getInt("noc");
		a=a+1;
		if(correct)
		b=b+1;
	
	  sql2="update analysis set noa='"+a+"',noc='"+b+"'where qno='"+qno1+"'";
		int dummy=stmt.executeUpdate(sql2);
		  rs.close();
         stmt.close();
         conn.close();
      
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
		
	}
	
	
	
	
}


