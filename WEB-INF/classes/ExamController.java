package Controller;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import Model.*;
import DAO.*;

 
public class ExamController extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
	  
    response.setContentType("text/html");
	PrintWriter out=response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Exam Controller</title>");
	out.println("</head>");
      String name=request.getParameter("sname");
      String pass=request.getParameter("password");
	  out.println("<body>");
	  out.println("Hello " + name);
	  
	  
	  QuestionBean q=new QuestionBean();
	  QuestionBean[] aq=new QuestionBean[5];
      QuestionDao qd=new QuestionDao();
	  String[] qns={"q1","q2","q3","q4","q5"};
	  String[] qnss={"qno1","qno2","qno3","qno4","qno5"};
	  String[] qnsss={"ques1","ques2","ques3","ques4","ques5"};
	  int i;
	  for(i=0;i<5;i++)
	  {
      q=qd.getQuestions(i+1);    
	  aq[i]=q;  
	  }
	  out.println("<body>");
	  out.println("<form action=\"servlet3\" method=\"GET\" >");
	  out.println("<input type='hidden' name='sname' value='"+name+"'>");
	  out.println("<input type='hidden' name='password' value='"+pass+"'>");
	  out.println("<br>");
	  for(i=0;i<5;i++)
	  {
	  out.println(aq[i].getqno() +".  " + aq[i].getques());
	  out.println("<input type='hidden' name='"+qnss[i]+"' value='"+aq[i].getqno()+"'>");
	  out.println("<input type='hidden' name='"+qnsss[i]+"' value='"+aq[i].getques()+"'>");
	  out.println("<br>");
	  out.println("<input type=\"radio\" name='"+qns[i]+"' value=\"A\">");
	  out.println("   "+aq[i].getcha());
	  out.println("<br>");
	  out.println("<input type=\"radio\" name='"+qns[i]+"' value=\"B\">");
	  out.println("   "+aq[i].getchb());
	  out.println("<br>");
	  out.println("<input type=\"radio\" name='"+qns[i]+"' value=\"C\">");
	  out.println("   "+aq[i].getchc());
	  out.println("<br>");
	  out.println("<input type=\"radio\" name='"+qns[i]+"' value=\"D\">");
	  out.println("   "+aq[i].getchd());
	  out.println("<br>");
	  out.println("<br><br>");
  }
	  out.println("<input type=\"submit\" value=\"Result\">");
	  out.println("</form>");
	  out.println("</body>");
	  out.println("</html>");
	
      
   }
} 
