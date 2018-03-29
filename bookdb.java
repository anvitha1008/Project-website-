import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class bookdb extends HttpServlet
{
  	PreparedStatement ps,ps1;
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
			String rollnof="",passwordf="",rollnodb="", passworddb="",test="DFMUSIC",tabl="DFMUSIC",query;
		query="select datetime from ";
		query=query.concat(tabl);
		out.println(query);
			try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		  ps=c.prepareStatement(query);
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}						
			int n=0,chkfill=0,cont=0;
			String abc;
  			abc=req.getParameter("button"); 	
			out.println(abc);	
			//passwordf=req.getParameter("password"); 		    
			/* RequestDispatcher rd = req.getRequestDispatcher("/refresh.html");
			RequestDispatcher rd1= req.getRequestDispatcher("/regdbvalid.html");
			RequestDispatcher rd2= req.getRequestDispatcher("/main1.html");
			RequestDispatcher rd4= req.getRequestDispatcher("/newreg.html");
			out.println("servlet");
			
			ps.executeUpdate();
			ResultSet rs=ps.getResultSet();
			while(rs.next())
			{
			 test=rs.getString(1);
		 	 out.println(test);
	 		} */
			c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}
