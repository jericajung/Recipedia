package servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import data.RecipediaJDBC;
import recipediaClasses.*;

/**
 * Servlet implementation class CreateRecipeServlet
 */
@WebServlet("/CreateRecipeServlet")
public class CreateRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		Gson gson = new Gson();
		HttpSession session = request.getSession(true);
        if (session.getAttribute("username") != null) {
        	String userName = (String)session.getAttribute("username");
    		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if(br != null){
                json = br.readLine();
            }
            JsonRecipe jsonRecipe = gson.fromJson(json, JsonRecipe.class);
            ArrayList<JsonIngredient> jsonIngredients = jsonRecipe.getIngredients();
            ArrayList<JsonInstruction> jsonInstructions = jsonRecipe.getInstructions();
            ArrayList<JsonTag> jsonTags = jsonRecipe.getTags();
            ServletResponse sr = new ServletResponse();
            Vector<Ingredient> ingredients = new Vector<Ingredient>();
            for (int i = 0; i < jsonIngredients.size(); i++) {
            	Ingredient ingredient = new Ingredient(jsonIngredients.get(i).getName(), jsonIngredients.get(i).getUnits(), jsonIngredients.get(i).getQuantity());
            	ingredients.add(ingredient);
            }
            Vector<String> instructions = new Vector<String>();
            for (int i = 0; i < jsonInstructions.size(); i++) {
            	instructions.add(jsonInstructions.get(i).getText());
            }
            Vector<String> tags = new Vector<String>();
            for (int i = 0; i < jsonTags.size(); i++) {
            	tags.add(jsonTags.get(i).getText());
            }
            Recipe recipe = new Recipe(ingredients, instructions, tags, jsonRecipe.getName(), jsonRecipe.getImageURL());
            RecipediaJDBC rjdbc = new RecipediaJDBC();
            int recipeKey = rjdbc.addRecipe(recipe);
            int userID = rjdbc.getUserIDByUsername(userName);
            System.out.println(userID);
            System.out.println(recipeKey);
            rjdbc.addUploadedRecipe(recipeKey, userID);
            rjdbc.addEvent(userName, recipeKey, "Uploaded");
            
            System.out.println(userName);
            sr.setStatus("Success");
            sr.setData("viewRecipes.jsp?recipeID=" + recipeKey);
            String returnJson = gson.toJson(sr);
    		out.write(returnJson);
        } else {
        	ServletResponse sr = new ServletResponse(); 
        	sr.setStatus("Success");
            sr.setData("Login.jsp");
            String returnJson = gson.toJson(sr);
    		out.write(returnJson);
        }
        
	}

	
}
