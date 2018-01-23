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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//check if input is an empty field
		if(username.equals("") || password.equals("")) {
			response.setContentType("text/plain");
			response.getWriter().write("Input Field is Empty.");
			return;
		}
				
		RecipediaJDBC jdbc = new RecipediaJDBC();
		
		//invalid username
		if(!jdbc.doesUserExist(username)) {
				response.setContentType("text/plain");
				response.getWriter().write("Invalid Username.");
				return;
		}
		
		//invalid password
		else {
			if(!jdbc.passwordNotMatch(password)) {
				response.setContentType("text/plain");
				response.getWriter().write("Incorrect password given.");
				return;
			}
			
			//redirect to feed page
			else {
				HttpSession userChosen = request.getSession(true);
				userChosen.setAttribute("username", username);
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
