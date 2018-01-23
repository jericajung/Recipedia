package recipediaClasses;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class User {
	private String fullname;
	private String fname;
	private String lname;
	private String username;
	private String password;
	private String image;
	private Vector<Recipe> savedRecipes;
	private Vector<Recipe> uploadedRecipes;
	private Set<String> myFans;
	
	public User(String username){
		this.username = username;
		myFans = new HashSet<>();
	}
	
	public void setFullName(String fname, String lname){
		this.fullname = fname + " " + lname;
	}
	
	public String getFullName(){
		return fullname;
	}
	
	public void setFname(String fname){
		this.fname  = fname;
	}
	
	public String getFname(){
		return fname;
	}
	
	public void setLname(String lname){
		this.lname = lname;
	}
	
	public String getLname(){
		return lname;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	
	//SURE WE WANT TO DO THIS?
	public String getPassword(){
		return password;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return image;
	}
	
	
	public void setSavedRecipes(Vector<Recipe> savedRecipes){
		this.savedRecipes = savedRecipes;
	}
	
	
	public void addToSavedRecipes(Recipe recipe){
		savedRecipes.add(recipe);
	}
	
	public Vector<Recipe> getSavedRecipes(){
		return savedRecipes;
	}
	
	public void setUploadedRecipes(Vector<Recipe> uploadedRecipes){
		this.uploadedRecipes = uploadedRecipes;
	}
	
	public void addToUploadedRecipes(Recipe recipe){
		this.uploadedRecipes.add(recipe);
	}
	
	public Vector<Recipe> getUploadedRecipes(){
		return this.uploadedRecipes;
	}
	
	public void setFans(Set<String> fans){
		this.myFans = fans;
	}
	
	public void addFan(String fan){
		myFans.add(fan);
	}
	
	public void removeFan(String fan) {
		myFans.remove(fan);
	}
	
	public Set<String> getFans(){
		return this.myFans;
	}
//	
//	public Vector<String> getFansOf(String username){
//		//have to have a map from user to its fans but don't think this is the right place for it
//	}
	
}


