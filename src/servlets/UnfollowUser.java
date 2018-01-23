package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;
import data.RecipediaJDBC;

/**
 * Servlet implementation class UnfollowUser
 */
@WebServlet("/UnfollowUser")
public class UnfollowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnfollowUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		HttpSession retrieveUserProfile = request.getSession();
		String user = (String) retrieveUserProfile.getAttribute("username");
		String getUserClicked = request.getParameter("userClicked");		
		RecipediaJDBC jdbcDriver = new RecipediaJDBC();
		jdbcDriver.addtoFollowing(user, getUserClicked);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}