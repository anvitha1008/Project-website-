/* progarm to access data base using servlets */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class regdb extends HttpServlet
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
		   try
		  { 
			 try
   		{
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
  		  ps=c.prepareStatement("INSERT INTO students (SNAME,BRANCH,SECTION,ROLE1,ROLE2,ROLE3,ROLLNO,PASSWORD,DATEREG) VALUES(?,?,?,?,?,?,?,?,TO_DATE(SYSDATE)) ");
		ps1=c.prepareStatement("select rollno from students");
  		}
		  catch(Exception e)
  		{
      		   e.printStackTrace();
  		}
	 	   	String namef, rollnof, passwordf, bhf, secf, role1f, role2f, role3f,test;
			int n=0;
			rollnof=req.getParameter("roll").toUpperCase();
  			namef=req.getParameter("name").toUpperCase();
			passwordf=req.getParameter("password");
			bhf=req.getParameter("bh").toUpperCase();
			
			secf=req.getParameter("sec").toUpperCase();
			role1f=req.getParameter("role1");
			role2f=req.getParameter("role2");
			role3f=req.getParameter("role3");
			//out.println(""+rollnof+namef+bhf+yrf+secf+role1f+role2f+role3f);
    			//out.println("read");
			ps1.executeUpdate();
			ResultSet rs1=ps1.getResultSet();
			out.println("vhjgdyrs1");
			while(rs1.next())
			{
			 test=rs1.getString(1);
			 out.println(test);
		 	 if(test.equals(rollnof))
			 {
  				n=1;
			 }
	 		}
   			if(n==1)
			  res.sendRedirect("http://localhost:8081/network/rereg.html");
			else
			{
			out.println("inside else");

 			ps.setString(1,namef);  
			ps.setString(2,bhf);   
			ps.setString(3,secf);     
			ps.setString(4,role1f);
			ps.setString(5,role2f);
			ps.setString(6,role3f); 
			ps.setString(7,rollnof);
			ps.setString(8,passwordf);
			
			out.println("REfvS");  
    			ps.executeUpdate();
			session.setAttribute("rollno",rollnof);
			session.setAttribute("name",namef);
			//RequestDispatcher rd2= req.getRequestDispatcher("/main.html");
			res.sendRedirect("http://localhost:8081/network/main.html");
			}
			c.close();
   		 }
		 catch(Exception e)
    		{
     		   out.println("exception occured");
   	 	}

	}
}