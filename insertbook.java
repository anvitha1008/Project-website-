import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class insertbook extends HttpServlet
{
  	PreparedStatement ps,ps1;
  	Connection c;
  	public void init(ServletConfig config)
	{
	}

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		HttpSession session=req.getSession();
		String rollnof,sname,bookname,available,author,subject;
		int edition;
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		 try
		 { 
		try
   		{
    			Class.forName("oracle.jdbc.driver.OracleDriver");
    			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			ps=c.prepareStatement("INSERT INTO BOOKSAVAIL(BOOKNAME,AUTHOR,EDITION,SUBJECT,AVAILABLE_TILL,DATEBREQ,ROLLNO,SNAME,BID) VALUES(?,?,?,?,?,TO_CHAR(SYSDATE, 'MM-DD-YYYY HH24:MI:SS'),?,?,?)");
			ps1=c.prepareStatement("select bid from booksavail");
		}
		  catch(Exception e)
  		{
      			   e.printStackTrace();
  		}
			int n=0,x=0;
			boolean chk=false;
		 	Random randomGenerator = new Random();
   			 while(chk==false)
			{	
			  ps1.executeUpdate();
			  ResultSet rs1=ps1.getResultSet();
    			  int randomInt = randomGenerator.nextInt(1000);
			  while(rs1.next())
			   {
				x=1;
				if(rs1.getInt(1)==randomInt)
				  chk=false;
				else
				{
				   chk=true;
				   n=randomInt;
				}
			    }
			   if(x==0)
			   {
				n=randomInt;
				chk=true;
			  }
			}
			//out.println("abc");
			rollnof=(String)session.getAttribute("rollno");
			sname=(String)session.getAttribute("name");
			out.println(""+rollnof+" "+sname+"");
			bookname=req.getParameter("bookname").toUpperCase();
			author=req.getParameter("author").toUpperCase();
			edition=Integer.parseInt(req.getParameter("edition"));
			subject=req.getParameter("subject").toUpperCase();
			available=req.getParameter("available").toUpperCase();
			//out.println("gfgf");
			out.println(bookname+author+edition+subject+available);
			ps.setString(1,bookname);
			ps.setString(2,author);
			ps.setInt(3,edition);
			ps.setString(4,subject);
			ps.setString(5,available);
			ps.setString(6,rollnof);
			ps.setString(7,sname);
			ps.setInt(8,n);
			//out.println(""+n+"");
			out.println("abc1");
			ps.executeUpdate();
			//out.println("xyz");
			res.sendRedirect("http://localhost:8081/network/post2.html");
		        c.close();
	   	 }
		
	 	 catch(Exception e)
	  	 {
	  	      	e.printStackTrace();
	   	 }	
	    
	}
}
