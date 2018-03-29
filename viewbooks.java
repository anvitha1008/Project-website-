import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class viewbooks extends HttpServlet
{
  	PreparedStatement ps,ps1;
  	Connection c;
  	public void init(ServletConfig config)
	{
 		/*try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");

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
		HttpSession session=req.getSession();
		//String query="select rollno,sname,comm,DATETIME from ";
		//query=query.concat(tabl);
		 try
		 { 
			//String category="DFMUSIC";
		try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		 //ps=c.prepareStatement(query);
		//out.println(query);
		 ps=c.prepareStatement("select bid,bookname,author,edition,subject,available_till,rollno,sname from BOOKSAVAIL");
		 ps1=c.prepareStatement("select comm,rollno,sname from commentbpost where bid=?");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			//String date1,time1;
			int id=0;
			//out.println("<html><body>");
			ps.executeUpdate();
			ResultSet rs=ps.getResultSet();
			out.println("<html> <head> <style> table { color:white; font-size:20px;} </style></head><body bgcolor=black>");

			while(rs.next())
			{
				id=rs.getInt(1);
				out.println(" <form action=\"/network/insertbcomm\"><table border=2 width=100%>");
				out.println("<input type=hidden name=val value="+id+">");
				out.println("<tr><td>Book:");
				out.print(rs.getString(2));
				out.println("</td><td>Author:");
				out.println(rs.getString(3));
				out.println("</td><td>Edition:");
				out.println(rs.getInt(4));
				out.println("</td><td>Subject:");
				out.println(rs.getString(5));
				out.println("</td><td>:Availability");
				out.println(rs.getString(6));
				out.println("</td><td>Rollno:");
				out.println(rs.getString(7));
				out.println("</td><td>Name:");
				out.println(rs.getString(8));
				out.println("</td></tr></table>");
				ps1.setInt(1,id);
				ps1.executeUpdate();
				ResultSet rs1=ps1.getResultSet();
				out.println("<table border=1><th>comment</th><th>name,roll</th>");
				while(rs1.next())
				{
					out.println("<tr><td>");
					out.println("<br>&nbsp;&nbsp;&nbsp;&nbsp;"+rs1.getString(1)+"<br></td><td>");
					out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs1.getString(2)+","+rs1.getString(3));                                
					out.println("</td></tr>");
				}
				out.println("</table>");

				out.println("<br>&nbsp;<textarea name=comment rows=2 cols=10></textarea><br>");
				out.println("<input type=submit value=Send></form>");
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
