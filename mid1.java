/* add cart */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class mid1 extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		String rollnof;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		rollnof=req.getParameter("rollno");
		HttpSession session=req.getSession();
		//session.setAttribute("rollno",rollnof);	
		//RequestDispatcher rd= req.getRequestDispatcher("/button.html");
		//rd.forward(req,res);
		rollnof=(String)session.getAttribute("rollno");
		out.println("<html><body> <h1>");
		out.println(" ROLL NO is "+rollnof);
		out.println("</h1></body></html>");
	}
}
		


