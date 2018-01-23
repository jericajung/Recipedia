package recipediaClasses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Recipe {
	public Recipe(Vector<Ingredient> ingred, Vector<String> instruct,
					Vector<String> tg, String nm, String imageURL)
	{
		ingredient=ingred;
		instructions=instruct;
		tags=tg;
		name=nm;
		this.imageURL = imageURL;
	}
	private Vector<Ingredient> ingredient;
	private Vector<String> instructions;
	private Vector<String> tags;
	private String name;
	private String imageURL;
	private int id;
	
	//not part of constructor
	private int likes = 0;
	private Vector<String> reviews;
	
	public void setIngredients(Vector<Ingredient> ingred)
	{
		ingredient=ingred;
	}
	public void addIngredient(Ingredient ingred)
	{
		ingredient.add(ingred);
	}
	//Should be case insensitive
	public void removeIngredient(Ingredient ingred)
	{
		ingredient.remove(ingred);
	}
	public Vector<Ingredient> getIngredients()
	{
		return ingredient;
	}
	public void setReviews(Vector<String> rev)
	{
		reviews=rev;
	}
	//probably should change this to a counter
	public void setLikes(int lk)
	{
		likes=lk;
	}
	public int getLikes()
	{
		return likes;
	}
	public void setInstructions(Vector<String> instruct)
	{
		instructions=instruct;
	}
	public Vector<String> getInstructions()
	{
		return instructions;
	}
	public void addInstruction(String instruct)
	{
		instructions.add(instruct);
	}
	//should be case insensitive
	//maybe switch to a numbering system
	public void removeInstruction(String instruct)
	{
		instructions.remove(instruct);
	}
	public void setTags(Vector<String> tg)
	{
		tags=tg;
	}
	public Vector<String> getTags()
	{
		return tags;
	}
	public void addTag(String tg)
	{
		tags.add(tg);
	}
	//should be case insensitive
	public void removeTag(String tg)
	{
		tags.remove(tg);
	}
	public void setName(String nm)
	{
		name=nm;
	}
	public String getName()
	{
		return name;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void addLike() {
		this.likes +=1;
	}
	
}
