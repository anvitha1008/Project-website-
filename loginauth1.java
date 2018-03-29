import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class loginauth1 extends HttpServlet
{
  	PreparedStatement ps,ps1;
  	Connection c;
  	public void init(ServletConfig config)
	{
 		try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		  ps=c.prepareStatement("select rollno from students");
		  ps1=c.prepareStatement("select rollno,password,chkfill from students where rollno=?");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		 try
		 { 
			String rollnof="",passwordf="",rollnodb="",passworddb="",test;						
			int n=0,chkfill=0,cont=0;
  			rollnof=req.getParameter("rollno");		
			passwordf=req.getParameter("password"); 		    
			RequestDispatcher rd = req.getRequestDispatcher("/refresh.html");
			RequestDispatcher rd1= req.getRequestDispatcher("/regdbvalid.html");
			RequestDispatcher rd2= req.getRequestDispatcher("/main1.html");
			RequestDispatcher rd4= req.getRequestDispatcher("/newreg.html");
			ps.executeUpdate();
			ResultSet rs=ps.getResultSet();
			while(rs.next())
			{
			 test=rs.getString(1);
			 out.println(test);
		 	 if(test.equals(rollnof))
			 {
  				n=1;
			 }
	 		}
			if(n==0)
			{
				//out.println("<html><body>Incorrect username or password<br>Please <a href=refresh.html>Try Again</a></body></html>");
				res.sendRedirect("http://localhost:8081/network/refresh.html");
			}
			if(n==1)
			{
				out.println("HBCTH");
				ps1.setString(1,rollnof);
				ps1.executeUpdate();
				ResultSet rs1=ps1.getResultSet();
				while(rs1.next())
				{
					rollnodb=rs1.getString(1);
					passworddb=rs1.getString(2);
					chkfill=rs1.getInt(3);
					out.println("roll"+rollnodb+"pass"+passworddb+"chk"+chkfill);
				}	
				out.println("chk"+chkfill+" rdb "+rollnodb+" pdb "+passworddb);
				if(chkfill==1)
				{
					if((passworddb.equals(passwordf)))
					{
						res.sendRedirect("http://localhost:8081/network/main1.html");
					}
					else
					{
						res.sendRedirect("http://localhost:8081/network/refresh.html");
						//rd.forward(req,res);
					}
				}
				else
				{
					//rd4.forward(req,res);
					res.sendRedirect("http://localhost:8081/network/newreg.html");
				}
			}
			out.println("n value is "+n);
			c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}
