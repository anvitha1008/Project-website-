import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class clubs extends HttpServlet
{
  	PreparedStatement ps,ps1;
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
		String tabl=(String)session.getAttribute("category1");
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
		 ps1=c.prepareStatement("select role1,role2,role3 from students where rollno=?");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			String date1,time1,rollnof,role1="",role2="",role3="",r1,n1;
			rollnof=(String)session.getAttribute("rollno");
			out.println("<html><head><style> table { color:white; font-size:20px;} </style></head><body bgcolor=black>");
			//ps.setString(1,"DFMUSIC");
			ps1.setString(1,rollnof);
			ps1.executeUpdate();
			ResultSet rs1=ps1.getResultSet();
			while(rs1.next())
			{
				role1=rs1.getString(1);
				role2=rs1.getString(2);
				role3=rs1.getString(3);	
			}

			ps.executeUpdate();
			ResultSet rs=ps.getResultSet();
			out.println("<table  border=1 width=100%><th>nameroll</th><th>comment</th><th>time</th>");
			while(rs.next())
			{
				out.println("<tr><td align=left>");
				//out.println(rs.getString(1)+",");
				//out.println(rs.getString(2));
				r1=rs.getString(1);
				n1=rs.getString(2);
				r1=r1.concat(n1);
				out.println(r1);
				out.println("</td><td align=center>");
				out.println("<br>"+rs.getString(3));
				out.println("</td><td align=left>");
				date1=rs.getString(4);
		 		 out.println("<br>"+date1);
	
				out.println("</td></tr>");
				out.println("-------------------------------------------------");
			}
			out.println("<br></table>");
			if((role1.equals(tabl))||(role2.equals(tabl))||(role3.equals(tabl)))
			{
			out.println("<form name=inscomm action=\"/network/insertclub\">");
			//out.println("<input type=text id=rollno name=rollno><br>");
			out.println("<textarea name=comment rows=10 cols=10></textarea><br>");
			out.println("<input type=submit value=send></form>");
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

























