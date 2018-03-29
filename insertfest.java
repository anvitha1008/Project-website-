import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class insertfest extends HttpServlet
{
  	PreparedStatement ps,ps1;
  	Connection c;
  	public void init(ServletConfig config)
	{
 		
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		HttpSession session=req.getSession();
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		boolean chk=false;
		int n=0;
		 try
		 { 
			try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		ps1=c.prepareStatement("select postid from festrequest");
		   ps=c.prepareStatement("insert into festrequest values(?,?,TO_CHAR(SYSDATE, 'MM-DD-YYYY HH24:MI:SS'))");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			String comment=req.getParameter("festdetails");
			int x=0;
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
				
			ps.setInt(1,n);
			ps.setString(2,comment);
			ps.executeUpdate();
			res.sendRedirect("/network/festpost1.html");
		        c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}

























