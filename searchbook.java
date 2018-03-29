import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class searchbook extends HttpServlet
{
  	PreparedStatement ps,ps1;
  	Connection c;
  	public void init(ServletConfig config)
	{
	}

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  	{
		HttpSession session=req.getSession();
		String rollnof,sname,bookname,available,author,subject;
		int edition;
		res.setContentType("text/html");
   		PrintWriter out=res.getWriter();
		String search,query,find;
		search=req.getParameter("search");
		find=req.getParameter("search1").toUpperCase();
		//out.println(search+"---"+find);
		query="select bookname,author,edition,subject,available_till,rollno,sname from booksavail where ";
		query=query.concat(search);
		query=query.concat(" LIKE '%");
		query=query.concat(find);
		query=query.concat("%'");
		//out.println("before query");
		//out.println(query);
		 try
		 { 
		try
   		{
    			Class.forName("oracle.jdbc.driver.OracleDriver");
    			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			ps=c.prepareStatement(query);
			//ps2=c.prepareStatement("select bookname,author,edition,subject,available_till,rollno,sname from booksavail where author LIKE '%t%'");
			//ps3=c.prepareStatement("select bookname,author,edition,subject,available_till,rollno,sname from booksavail where subject LIKE '%t%'");
			
		}
		  catch(Exception e)
  		{
      			   e.printStackTrace();
  		}
		
		//out.println(search);
		//out.println("before execute");
		ps.executeUpdate();
		//out.println("after execute");
		ResultSet rs=ps.getResultSet();
		out.println("<html><head><style> table { color:white; font-size:20px;} </style></head><body bgcolor=black><table border=1 width=100%> <th>BOOK NAME </th><th> AUTHOR</th><th>EDITION</th><th>SUBJECT</th><th>VALIDITY</th><th>ROLL NO</th><th>NAME </th>");
		 while(rs.next())
		{
			out.println("<tr><td>");
			out.print(rs.getString(1));
			out.println("</td><td>");
			out.print(rs.getString(2));
			out.println("</td><td>");
			out.print(rs.getInt(3));
			out.println("</td><td>");
			out.print(rs.getString(4));
			out.println("</td><td>");
			out.print(rs.getString(5));
			out.println("</td><td>");
			out.print(rs.getString(6));
			out.println("</td><td>");
			out.print(rs.getString(7));
			out.println("</td></tr>");
		}
		out.println("</table> </body> </html>");
		 c.close();
	   	 }
		 catch(Exception e)
	  	 {
	  	      	e.printStackTrace();
	   	 }
	}
}
	


































