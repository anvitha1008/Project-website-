import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class displaycomm extends HttpServlet
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
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession();
		String tabl=(String)session.getAttribute("category");
		String query="select rollno,sname,comm,DATETIME from ";
		query=query.concat(tabl);
		 try
		 { 
			//String category="DFMUSIC";
			try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
		 ps=c.prepareStatement(query);
		//out.println(query);
		 // ps=c.prepareStatement("select rollno,sname,comm,DATETIME from DFMUSIC");
		 // ps1=c.prepareStatement("select rollno,sname,comm,DATETIME from dfmusic");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			String date1,time1;
			out.println("<html><head><style> table { color:white; font-size:20px;} </style></head><body bgcolor=black>");
			out.println("<table border=1 width=100%><th>ROLLNO,Name</th><th>Comment</th><th>DateTime</th>");
			//ps.setString(1,"DFMUSIC");
			ps.executeUpdate();
			ResultSet rs=ps.getResultSet();
			while(rs.next())
			{
				out.println("<tr><td align=left>");
				out.print(rs.getString(1));
				out.println(",");
				out.println(rs.getString(2));
				out.println("</td><td align=center>");
				out.println(rs.getString(3));
				out.println("</td><td align=left>");
				date1=rs.getString(4);
		 		 out.println(date1);
				//out.println("</td><td>");
				//time1=rs.getString(5);
				//out.println(time1);
				out.println("</td></tr>");
			}
			out.println("</table><br>");
			out.println("<form name=inscomm action=\"/network/insertcomm\">");
			//out.println("<input type=text id=rollno name=rollno><br>");
			out.println("<textarea name=comment rows=3 cols=60></textarea><br>");
			out.println("<input type=submit value=send></form></body></html>");
		        c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}

























