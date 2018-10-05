package Controller;
import Model.*;
import DAO.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class ResultController extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
	   PrintWriter out=response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Result Controller</title>");
    out.println("</head>");
    out.println("<body>");
      String[]  a=new String[5];
	  int i;
	   String name=request.getParameter("sname");
      String pass=request.getParameter("password");
  
      QuestionDao qd=new QuestionDao();
      a=qd.getAnswers();    
	  
	  String[] qns={"q1","q2","q3","q4","q5"};
	  String[] qnss={"qno1","qno2","qno3","qno4","qno5"};
	  String[] qnsss={"ques1","ques2","ques3","ques4","ques5"};
      int score=0;
	  for(i=0;i<5;i++)
	  {
		  
	  String a0=request.getParameter(qns[i]);
	  String a1=request.getParameter(qnss[i]);
	  String a2=request.getParameter(qnsss[i]);
	  out.println(a1+ ".  "+a2);
	  out.println("<br>");
	  out.println("Your Answer: " + a0);
	  out.println("<br>Correct Answer: " + a[i]);
	  out.println("<br>");
	  int x;
	  
	  if(a0.equals(a[i]))
	  {
		 qd.update_analysis(i+1,true);
		
		
		score++;
	  }
	  else
	  {qd.update_analysis(i+1,false);
	  
	  }
	  
      
	  }
	 
	  
	  out.println("<br>");
	  out.println("your score: " + score);
	  qd.updatescore(name,pass,score);
	  int rank=qd.getRank(name);
	  out.println("<br><br>");
	  out.println("Your net rank: " + rank);
	  out.println("<br><br>");
	  out.println("<form action=\"servlet4\" method=\"GET\">");
	  out.println("<br>");
	  out.println("<input type=\"submit\" value=\"View High Scores\">");
	  out.println("</form>");
	  out.println("<br><br>");
	  out.println("<form action=\"servlet5\" method=\"GET\">");
	  out.println("<br>");
	  out.println("<input type=\"submit\" value=\"Get question wise analysis\">");
	  out.println("</form>");
	  out.println("</body>");
	  out.println("</html");
	  
	  
      
   }
} 
