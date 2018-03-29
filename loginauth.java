import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public Class loginauth extends HttpServlet
{
  	PreparedStatement ps,ps1;
  	Connection c;
  	public void init(ServletConfig config)
	{
 		/*try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		  
		 // out.println("initt");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}*/
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		
		res.setContentType("text/html");
		
   		PrintWriter out=res.getWriter();
		//out.println("vhjgdyrs");
		HttpSession session=req.getSession();
		
		 try
		 { 
		try
   		{
   		   Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		  //ps=c.prepareStatement("select rollno from students");
		  ps1=c.prepareStatement("select rollno,sname from students where rollno=? AND password=? ");
		 //out.println("initt");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			out.println("vhjgdyrs");
			String rollnof="",passwordf="",rollnodb="",passworddb="",test,sname;						
			int n=0,chkfill=0,cont=0;
			ResultSet rs = null; 
  			rollnof=req.getParameter("rollno").toUpperCase();		
			passwordf=req.getParameter("password");
			out.println("vhjgdyrs"); 		    
			/*RequestDispatcher rd = req.getRequestDispatcher("/refresh.html");
			RequestDispatcher rd1= req.getRequestDispatcher("/regdbvalid.html");
			RequestDispatcher rd2= req.getRequestDispatcher("/main1.html");
			RequestDispatcher rd4= req.getRequestDispatcher("/newreg.html");*/
			//out.println("vhjgdyrs1");
	
			
				//out.println("HBCTH");
				ps1.setString(1,rollnof);
				ps1.setString(2,passwordf);
				ps1.executeUpdate();
				ResultSet rs1=ps1.getResultSet();
				if(rs1.next()==true)
				{
					sname=rs1.getString(2);
					session.setAttribute("rollno",rollnof);
					session.setAttribute("name",sname);
					res.sendRedirect("http://localhost:8081/network/main.html");
				}
				else
					res.sendRedirect("http://localhost:8081/network/refresh.html");



				
					/*rollnodb=rs1.getString(1);
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
						//out.println("second time reques");
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
			out.println("n value is "+n);*/
			rs1.close();  
           		ps1.close(); 
			c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}
