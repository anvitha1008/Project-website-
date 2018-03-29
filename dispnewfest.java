/*Display fests requests */
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class dispnewfest extends HttpServlet
{
  	PreparedStatement ps;
  	Connection c;
  	public void init(ServletConfig config)
	{
 		
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession();
		//String tabl=(String)session.getAttribute("category");
		//String query="select rollno,sname,comm,DATETIME from ";
		//query=query.concat(tabl);
		 try
		 { 
			try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		 ps=c.prepareStatement("select postid,posts from fests order by datepost desc");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			String post;
			int id;
			out.println("<html><head><style> table { border:solid 2px;color:white;} body{color:blue;}</style></head><body bgcolor=black><h1>FEST,WORKSHOPS Details:</h1><br><br>");
			ps.executeUpdate();
			ResultSet rs=ps.getResultSet();
	
			while(rs.next())
			{
				out.println("<table><tr><td>");
				id=rs.getInt(1);
				post=rs.getString(2);
				out.println(post+"</td></tr></table><br>");		


						
			}
			out.println("</body></html>");


			
		        c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}













