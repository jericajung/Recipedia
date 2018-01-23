package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import data.RecipediaJDBC;
import messages.SavedMessage;
import recipediaClasses.Event;
import recipediaClasses.JsonEventData;
import recipediaClasses.ServletResponse;
import recipediaClasses.WebSocketEndpoint;

/**
 * Servlet implementation class FeedEventServlet
 */
@WebServlet("/FeedEventServlet")
public class FeedEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		//HttpSession session = request.getSession(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        RecipediaJDBC rjdbc = new RecipediaJDBC();
        JsonEventData eventData = gson.fromJson(json, JsonEventData.class);
        String action = eventData.getAction();
        if (action.equals("saved")) {
        	rjdbc.addSavedRecipe(rjdbc.getEvent(eventData.getId()).getRecipeID(), rjdbc.getUserIDByUsername(eventData.getUsername()));
        	Event event = new Event();
        	event.setAction("Saved");
        	event.setRecipeID(rjdbc.getEvent(eventData.getId()).getRecipeID());
        	event.setUsernameDidAction(eventData.getUsername());
        	event.setRecipeName(rjdbc.getEvent(eventData.getId()).getRecipeName());
        	rjdbc.addEvent(event.getUsernameDidAction(), event.getRecipeID(), event.getAction());
        	WebSocketEndpoint wse = new WebSocketEndpoint();
        	SavedMessage sm = new SavedMessage(event.getUsernameDidAction(), event.getRecipeName());
//        	System.out.println(rjdbc.getUsernameByUserID(rjdbc.getUserWhoUploadedRecipe(rjdbc.getEvent(eventData.getId()).getRecipeID())));
//        	System.out.println(rjdbc.getUsernameByUserID(rjdbc.getUserWhoUploadedRecipe(rjdbc.getEvent(eventData.getId()).getRecipeID())));
        	ServletResponse sr = new ServletResponse();
            sr.setStatus("Success");
            out.println(gson.toJson(sr));
        	//wse.sendToUser(rjdbc.getUsernameByUserID(rjdbc.getUserWhoUploadedRecipe(rjdbc.getEvent(eventData.getId()).getRecipeID())), gson.toJson(sm));
        	//create event
        } else if (action.equals("liked")) {
        	rjdbc.updateLike(rjdbc.getEvent(eventData.getId()).getRecipeID());
        	Event event = new Event();
        	event.setAction("Liked");
        	event.setRecipeID(rjdbc.getEvent(eventData.getId()).getRecipeID());
        	event.setUsernameDidAction(eventData.getUsername());
        	event.setRecipeName(rjdbc.getEvent(eventData.getId()).getRecipeName());
        	
        	rjdbc.addEvent(event.getUsernameDidAction(), event.getRecipeID(), event.getAction());
        	ServletResponse sr = new ServletResponse();
            sr.setStatus("Success");
            out.println(gson.toJson(sr));
        	
        }
        
	}


}
