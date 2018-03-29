import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class delfest extends HttpServlet
{
  	PreparedStatement ps,ps1,ps2;
  	Connection c;
  	public void init(ServletConfig config)
	{
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		
		 try
		 { 
			
			try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		 //ps=c.prepareStatement("select posts from festrequest where postid=?");
		//ps1=c.prepareStatement("insert into fests values(?,?)");
		ps2=c.prepareStatement("delete from festrequest where postid=?");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			String cate=req.getParameter("delval");
			int n=Integer.parseInt(cate);
			ps2.setInt(1,n);
			ps2.executeUpdate();
			res.sendRedirect("/network/displayfest");

		}
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}