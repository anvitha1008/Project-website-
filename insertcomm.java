import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class insertcomm extends HttpServlet
{
  	PreparedStatement ps;
  	Connection c;
  	public void init(ServletConfig config)
	{
 		/*try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		  ps=c.prepareStatement("select rollno,sname,comm from dfmusic");
		 // ps1=c.prepareStatement("select rollno,sname,comm from dfmusic");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}*/
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		HttpSession session=req.getSession();
		String rollnof,comment,sname,cate,query;
		cate=(String)session.getAttribute("category");
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		 try
		 { 
			try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		  query="INSERT INTO ";
		  query=query.concat(cate);
		  query=query.concat("(ROLLNO,COMM,SNAME,DATETIME) VALUES(?,?,?,TO_CHAR(SYSDATE, 'MM-DD-YYYY HH24:MI:SS'))");
		  //ps=c.prepareStatement("INSERT INTO DFMUSIC(ROLLNO,COMM,SNAME,DATETIME) VALUES(?,?,?,TO_CHAR(SYSDATE, 'MM-DD-YYYY HH24:MI:SS'))");
		 // ps1=c.prepareStatement("select rollno,sname,comm from dfmusic");
		   ps=c.prepareStatement(query);
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			rollnof=(String)session.getAttribute("rollno");
			sname=(String)session.getAttribute("name");
			comment=req.getParameter("comment");
			ps.setString(1,rollnof);
			ps.setString(2,comment);
			ps.setString(3,sname);
			ps.executeUpdate();
			//res.sendRedirect("http://localhost:8081/network/comments.html");
			res.sendRedirect("/network/displaycomm");
		        c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}

























