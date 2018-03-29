import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class checkout extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		//String items[],item;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession();
		//out.println("sucessfully checked out");
		
		session.invalidate();
		Res.Cache.SetCacheability(HttpCacheability.NoCache);
   		//Res.Cache.SetExpires(DateTime.UtcNow.AddHours(-1));
		Res.Cache.SetNoStore(); 
		res.sendRedirect("/network/freshnew.html");
	}
}