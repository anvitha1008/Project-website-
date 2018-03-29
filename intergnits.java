/* add cart */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class intergnits extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		//String category;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession();
		session.setAttribute("category","DFGNITS");	
		//RequestDispatcher rd= req.getRequestDispatcher("/button.jsp");
		//rd.forward(req,res);
		res.sendRedirect("http://localhost:8081/network/displaycomm");
	}
}
		

