package Controller;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import Model.*;
import DAO.*;



public class HighScoreController extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
	   PrintWriter out=response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>High Score Controller</title>");
    out.println("</head>");
    out.println("<body><br><br>");
	
	QuestionDao qd=new QuestionDao();
	out.println("\nThe top 5 high scores<br>");
	out.println("<table border=\"1\">");
	
	out.println("<tr><th>Name</th><th>Score</th></tr>");
	int i=0;
	for(i=0;i<5;i++)
	{
	ScoreBean sb=new ScoreBean();
	sb=qd.ret_score(i);
	out.println("<tr><td>"+sb.getsname()+"</td><td>"+sb.getscore()+"</td></tr>");
	}
	out.println("</table></body></html>");
	}
}
	