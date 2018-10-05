package Controller;
import Model.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import DAO.*;
 
public class LoginController extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
	   PrintWriter out=response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Login Controller</title>");
    out.println("</head>");
    out.println("<body>");
      String name=request.getParameter("sname");
      String pass=request.getParameter("password");
      StudentBean s=new StudentBean();      
      s.setsname(name);
      s.setpassword(pass);
      int flag=0;
      LoginDao ld=new LoginDao();
      flag=ld.studentAuthentication(s);    
      if(flag==1) 
	  {out.println("\nSuccess<br>");
	  out.println("Rules:");
	  out.println("<br>"+"1. 5 Questions"+"<br>"+"2. each question carries one mark.");
	  out.println("<br>");
	  out.println("<form action=\"servlet2\" method=\"GET\">");
	  out.println("<input type='hidden' name='sname' value='"+name+"'>");
	  out.println("<input type='hidden' name='password' value='"+pass+"'>");
	  
	  out.println("<br><br>");
	  out.println("<input type=\"submit\" value=\"taketest\" >");//onclick=\"location.href='time.html';\">");
	  out.println("</form>");
	  out.println("<br><br><br>");
	  }
      else 
	  out.println("Failure");
	  
	  out.println("</body>");
    out.println("</html>");
   }
} 
