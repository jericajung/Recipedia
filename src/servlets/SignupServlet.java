package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.*;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fullName = request.getParameter("fullname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String imageURL = request.getParameter("imageURL");
		
		if(fullName.length() == 0 || username.length() == 0 || password.length() == 0) {
			response.setContentType("text/plain");
			response.getWriter().write("Input field is empty.");
			return;
		}
		
		RecipediaJDBC jdbc = new RecipediaJDBC();
		if(jdbc.doesUserExist(username)) {
			response.setContentType("text/plain");
			response.getWriter().write("Username aleady taken.");
			return;
		}

		else {
			boolean temp = jdbc.addUser(fullName, username, password, imageURL);
			if(temp == true) {
				HttpSession userChosen = request.getSession(true);
				userChosen.setAttribute("username", username);
			}
			
			else {
				response.setContentType("text/plain");
				response.getWriter().write("Need to provide first and last name.");
				return;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
