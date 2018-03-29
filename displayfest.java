/*Display fests requests */
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class displayfest extends HttpServlet
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
		 ps=c.prepareStatement("select postid,posts from festrequest order by datepost desc");
		ps1=c.prepareStatement("select role1,role2,role3 from students where rollno=?");
		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
			String date1,time1,post,rollnof,role1="",role2="",role3="";
			rollnof=(String)session.getAttribute("rollno");
			int id,x=0;
			ps1.setString(1,rollnof);
			ps1.executeUpdate();
			ResultSet rs1=ps1.getResultSet();
			while(rs1.next())
			{
				role1=rs1.getString(1);
				role2=rs1.getString(2);
				role3=rs1.getString(3);	
			}
			if((role1.equals("CR"))||(role2.equals("CR"))||(role3.equals("CR"))||(role1.equals("ICR"))||(role2.equals("ICR"))||(role3.equals("ICR")))	
			{






			out.println("<html><head><style> table { border:solid 2px;color:white;} body{color:white;} #b{ background-color: blue;  color:white;} </style></head><body bgcolor=black><h1>FESTS REQUESTS :</h1><br><br>");
			ps.executeUpdate();
			ResultSet rs=ps.getResultSet();
				
			while(rs.next())
			{
				x=1;
				out.println("<table><tr><td>");
				id=rs.getInt(1);
				post=rs.getString(2);
				out.println(post+"</td>");
				out.println("<form action=\"/network/interfest\">");
				out.println("<input type=hidden name=val value="+id+">");
				out.println("<td><input type=submit id=b value=accept></td>");
				out.println("</form>");
				out.println("<form action=\"/network/delfest\">");
				out.println("<input type=hidden name=delval  value="+id+">");
				out.println("<td><input type=submit id=b value=decline></td>");
				out.println("</form><br></tr></table>");
			
						
			}
			if(x==0)
			    out.println("NO Fest Requests");	
			out.println("</body></html>");
		}
		else
			out.println("YOU ARE NOT AUTHENTICATED TO VIEW THIS PAGE");


			
		        c.close();
	   	 }
		
	 	  catch(Exception e)
	  	 {
	        	e.printStackTrace();
	   	 }	
	    
	}
}

























