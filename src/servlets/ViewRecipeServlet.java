package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.RecipediaJDBC;

/**
 * Servlet implementation class ViewRecipe
 */
@WebServlet("/ViewRecipe")
public class ViewRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RecipediaJDBC jdbc = new RecipediaJDBC();
		
		String recipeID = request.getParameter("recipeID");
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
}
