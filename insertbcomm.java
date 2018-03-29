/*insertbcomm*/
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class insertbcomm extends HttpServlet
{
  	PreparedStatement ps;
  	Connection c;
  	public void init(ServletConfig config)
	{
 		
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		HttpSession session=req.getSession();
		String rollnof,comment,sname,cate,query;
		int n=0;
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		 try
		 { 
			try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		  
		   ps=c.prepareStatement("insert into commentbpost values(?,?,TO_CHAR(SYSDATE, 'MM-DD-YYYY HH24:MI:SS'),?,?)");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			rollnof=(String)session.getAttribute("rollno");
			sname=(String)session.getAttribute("name");
			comment=req.getParameter("comment");
			n=Integer.parseInt(req.getParameter("val"));
			ps.setInt(1,n);
			ps.setString(2,comment);
			ps.setString(3,sname);
			ps.setString(4,rollnof);
			ps.executeUpdate();
			//res.sendRedirect("http://localhost:8081/network/comments.html");
			res.sendRedirect("/network/viewbooks");
		        c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}

























