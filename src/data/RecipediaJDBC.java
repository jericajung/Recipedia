package data;

import java.sql.*;
import java.util.*;

import recipediaClasses.Event;
import recipediaClasses.Ingredient;
import recipediaClasses.Recipe;
import recipediaClasses.User;

public class RecipediaJDBC {
	
	//Data Members
	private static Connection conn = null;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private String userToCheck = null;
	
	//Strings for Database
	private final static String addUser = "INSERT INTO Users(username, pword, fname, lname, image) VALUES(?,?,?,?,?)";
	private final static String inputUsername = "SELECT * FROM Users WHERE username=?";
	private final static String inputPassword = "SELECT * FROM Users WHERE pword=?";
	private final static String followingTable = "SELECT * FROM Fans WHERE userID=?";
	private final static String followerTable = "SELECT * FROM Fans WHERE fanName=?";
	private final static String addFollowing = "INSERT INTO Fans(userID, fanName) VALUES(?,?)";
	private final static String removeFollower = "DELETE FROM Fans WHERE userID=? AND fanName=?";
	private final static String addRecipe = "INSERT INTO Recipes(title, likes, image) VALUES(?,?,?)";
    private final static String resultTable = "SELECT * FROM Recipes WHERE title=?";
	private final static String tresultTable = "SELECT * FROM Tags WHERE tag=?";
	private final static String addIngredient = "INSERT INTO Ingredients(recipeID, quantity, units, ingredient) VALUES(?,?,?,?)";
	private final static String addInstruction = "INSERT INTO Instructions(recipeID, instruction) VALUES(?,?)";
	private final static String addTag = "INSERT INTO Tags(tag) VALUES(?)";
	private final static String addTagConnection = "INSERT INTO TagToRecipe(tagID, recipeID) VALUES(?,?)";
	private final static String addUploadedRecipe = "INSERT INTO UploadedRecipes(userID, recipeID) VALUES (?,?)";
	private final static String addSavedRecipe = "INSERT INTO SavedRecipes(userID, recipeID) VALUES (?,?)";
	private final static String getRecipe = "SELECT * FROM RECIPES WHERE recipeID=?";
	private final static String getIngredients = "SELECT * FROM INGREDIENTS WHERE recipeID=?";
	private final static String getInstructions = "SELECT * FROM INSTRUCTIONS WHERE recipeID=?";
	private final static String getTags = "SELECT * FROM TagToRecipe WHERE recipeID=?";
	private final static String getSavedRecipes = "SELECT * FROM SAVEDRECIPES WHERE userID=?";
	private final static String getUploadedRecipes = "SELECT * FROM UPLOADEDRECIPES WHERE userID=?";
	private final static String getUser = "SELECT * FROM USERS WHERE username=?";
	private final static String getUsernameFromID = "SELECT * FROM Users WHERE userID=?";
	private final static String addEvent = "INSERT INTO ActionEvents(userID, actionString, recipeID, eventTimestamp) VALUES (?,?,?,?)";
	private final static String getEvent = "SELECT * FROM ActionEvents WHERE eventID=?";
	private final static String getUsernameByID = "SELECT * FROM USERS WHERE userID=?";
	private final static String getUserEvents = "SELECT * FROM ActionEvents WHERE userID=?";
	private final static String getTagWithID = "SELECT * FROM TagToRecipe WHERE tagID=?";
	private final static String getAllTags = "SELECT * FROM Tags";
	private final static String getCurrNumLikes = "SELECT * FROM Recipes Where recipeID=?";
	private final static String increaseLikeByOne = "UPDATE Recipes SET likes=? WHERE recipeID=?";
	private final static String getSavedRecipe = "SELECT * FROM SAVEDRECIPES WHERE recipeID=?";
	private final static String getAllRecipes = " SELECT * FROM Recipes";
	private final static String userTable = "SELECT * FROM Users";
	
	public RecipediaJDBC() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//change this according to your inputs

			conn = DriverManager.getConnection("jdbc:mysql://localhost/recipedia?user=root&password=790536e&useSSL=false");


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//liked
	//saves
	//uploads

	public boolean addUser(String fullName, String username, String password, String imageURL) {
		Scanner scanner = new Scanner(fullName);
		String firstName = scanner.next();
		if(!scanner.hasNext()) {
			return false;
		}
		String lastName = scanner.next();
		try {
			ps = conn.prepareStatement(addUser);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, imageURL);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		scanner.close();
		return true;
	}
	public Vector<Recipe> getAllRecipes()
	{
		try {
			ps = conn.prepareStatement(getAllRecipes);
			//ps.setInt(1, userID);
			ResultSet res = ps.executeQuery();
			Vector<Recipe> recipes = new Vector<Recipe>();
			while(res.next()) {
				int recipeID = res.getInt(1);
				Recipe recipe = getRecipe(recipeID);
				recipes.add(recipe);
			}
			return recipes;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	//checks if username exists
	public boolean doesUserExist(String name) {
			try {
				st = conn.createStatement();
				ps = conn.prepareStatement(inputUsername);
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					userToCheck = name;
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
		}
		
	//checks if password matches with username
	public boolean passwordNotMatch(String password) {
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(inputPassword);
			ps.setString(1, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {					
				if(rs.getString(2).equals(userToCheck) && rs.getString(3).equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public Recipe getRecipe(int recipeID) {
		try {
			ps = conn.prepareStatement(getRecipe);
			ps.setInt(1, recipeID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String title = rs.getString(2);
			int likes = rs.getInt(3);
			String image = rs.getString(4);
			ps = conn.prepareStatement(getIngredients);
			ps.setInt(1,  recipeID);
			rs = ps.executeQuery();
			Vector<Ingredient> ingredients = new Vector<Ingredient>();
			while(rs.next()) {
				double quantity = rs.getDouble(2);
				String units = rs.getString(3);
				String name = rs.getString(4);
				Ingredient ingredient = new Ingredient(name, units, quantity);
				ingredients.add(ingredient);
			}
			Vector<String> instructions = new Vector<String>();
			ps = conn.prepareStatement(getInstructions);
			ps.setInt(1, recipeID);
			rs = ps.executeQuery();
			while(rs.next()) {
				String instruction = rs.getString(2);
				instructions.add(instruction);
			}
			Vector<String> tags = new Vector<String>();
			ps = conn.prepareStatement(getTags);
			ps.setInt(1, recipeID);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				String tag = rs.getString(1);
				tags.add(tag);
			}
			Recipe recipe = new Recipe(ingredients, instructions, tags, title, image);
			recipe.setId(recipeID);
			recipe.setLikes(likes);
			return recipe;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void addSavedRecipe(int recipeID, int userID){
		try {
			ps = conn.prepareStatement(addSavedRecipe);
			ps.setInt(1, userID);
			ps.setInt(2, recipeID);
			ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}		  		

	}
	public void addUploadedRecipe(int recipeID, int userID) {
		try {
			ps = conn.prepareStatement(addUploadedRecipe);
			ps.setInt(1, userID);
			ps.setInt(2, recipeID);
			ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
	public Vector<Recipe> getSavedRecipes(int userID) {
		try {
			ps = conn.prepareStatement(getSavedRecipes);
			ps.setInt(1, userID);
			ResultSet res = ps.executeQuery();
			Vector<Recipe> recipes = new Vector<Recipe>();
			while(res.next()) {
				int recipeID = res.getInt(2);
				Recipe recipe = getRecipe(recipeID);
				recipes.add(recipe);
			}
			return recipes;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	public Vector<Recipe> getUploadedRecipes(int userID) {
		Vector<Recipe> recipes = new Vector<Recipe>();
		try {
			ps = conn.prepareStatement(getUploadedRecipes);
			ps.setInt(1, userID);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				int recipeID = res.getInt(2);
				Recipe recipe = getRecipe(recipeID);
				recipes.add(recipe);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}
	public User getUserByUsername(String username) {
		try {
			User user = new User(username);
			//System.out.println("in method: " + username);
			ps = conn.prepareStatement(inputUsername);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int userID = rs.getInt(1);
				user.setPassword(rs.getString(3));
				user.setFname(rs.getString(4));
				user.setLname(rs.getString(5));
				user.setFullName(user.getFname(), user.getLname());
				user.setImage(rs.getString(6));
				user.setSavedRecipes(this.getSavedRecipes(userID));
				user.setUploadedRecipes(this.getUploadedRecipes(userID));
				user.setFans(this.followerSet(username));
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public int addRecipe(Recipe recipe) {
		try {
			ps = conn.prepareStatement(addRecipe, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, recipe.getName());
			ps.setInt(2, 0);
			ps.setString(3, recipe.getImageURL());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			Vector<Ingredient> ingredients = recipe.getIngredients();
			Vector<String> instructions = recipe.getInstructions();
			Vector<String> tags = recipe.getTags();
			int recipeKey;
			if (rs.next()) {
				recipeKey = rs.getInt(1);
				for (int i = 0; i < ingredients.size(); i++) {
					ps = conn.prepareStatement(addIngredient);
					ps.setInt(1, recipeKey);
					ps.setDouble(2, ingredients.get(i).getQuantity());
					ps.setString(3, ingredients.get(i).getUnits());
					ps.setString(4,  ingredients.get(i).getName());
					ps.executeUpdate();
				}
				for (int i = 0; i < instructions.size(); i++) {
					ps = conn.prepareStatement(addInstruction);
					ps.setInt(1,  recipeKey);
					ps.setString(2,  instructions.get(i));
					ps.executeUpdate();
				}
				PreparedStatement ps2 = conn.prepareStatement(getAllTags);
				ResultSet rs2 = ps2.executeQuery();
				Vector<String> addTags = new Vector<String>();
				Vector<String> linkTags = new Vector<String>();
				while(rs2.next()) {
					String tagName = rs2.getString(2);
					if (!recipe.getTags().contains(tagName)) {
						addTags.add(tagName);
					} else {
						linkTags.add(tagName);
					}
				}
				//have list of tags to add
				//need to add and link those tags first
				for (int i = 0; i < addTags.size(); i++) {
					ps = conn.prepareStatement(addTag, Statement.RETURN_GENERATED_KEYS);
					System.out.println("add tags: " + addTags.get(i));
					ps.setString(1, addTags.get(i));
					ps.executeUpdate();
					ResultSet rs3 = ps.getGeneratedKeys();
					if (rs3.next()) {
						ps = conn.prepareStatement(addTagConnection);
						ps.setInt(1, rs3.getInt(1));
						ps.setInt(2, recipeKey);
						ps.executeUpdate();
					}
					
				}
				//have a list of tags to link
				//first get the tagid using the tag
				//then add that to tagtorecipe table
				for (int i = 0; i < linkTags.size(); i++) {
					ps = conn.prepareStatement(tresultTable);
					System.out.println("link: " + linkTags.get(i));
					ps.setString(1, linkTags.get(i));
					ResultSet rs3 = ps.executeQuery();
					rs3.next();
					int tagID = rs3.getInt(1);
					ps = conn.prepareStatement(addTagConnection);
					ps.setInt(1, tagID);
					ps.setInt(2,  recipeKey);
					ps.executeUpdate();
				}
				return recipeKey;
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int getUserIDByUsername(String username){
		ResultSet rs = null;
		Statement st = null;
		int userID = 0;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Users WHERE username = '" + username + "';");
			if (rs.next()){
				userID = Integer.parseInt(rs.getString("userID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userID;
	}
	public String getUsernameByUserID(int userID) {
		try {
			ps = conn.prepareStatement(getUsernameByID);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String username = rs.getString(2);
			return username;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getRecipeIDByRecipeName(String recipeName){
		int recipeID = 0;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Recipes WHERE title = '" + recipeName + "';");
			if (rs.next()){
				recipeID = Integer.parseInt(rs.getString("recipeID"));
			}
		} catch(SQLException sqle){
			System.out.println("sqle: " + sqle.getMessage());
		}
		return recipeID;
	}
		
	//adds a following to loggedinUser and follower for viewedUser
	public void addtoFollowing(String loggedinUser, String viewedUser) {
		int userID = getUserIDByUsername(loggedinUser);
		User user = new User(viewedUser);
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(addFollowing);
			ps.setInt(1, userID);
			ps.setString(2, viewedUser);
			ps.executeUpdate();
			user.addFan(loggedinUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//remove a following to loggedinUser and viewedUser loses a follower
	public void removeFromFollowing(String loggedinUser, String viewedUser) {
		int userID = getUserIDByUsername(loggedinUser);
		User user = new User(viewedUser);
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(removeFollower);
			ps.setInt(1, userID);
			ps.setString(2, viewedUser);
			ps.executeUpdate();
			user.removeFan(loggedinUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public Set<Recipe> nameResult(String entry) {
        Set<Recipe> results = new HashSet<Recipe>();
        try {
			ps = conn.prepareStatement(resultTable);
			ps.setString(1, entry);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				results.add(this.getRecipe(rs.getInt(1)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	public Set<Recipe> tagResult(String entry) {
		Set<Recipe> results = new HashSet<>();
		try {
			
			ps = conn.prepareStatement(tresultTable);
			ps.setString(1, entry);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int tagID = rs.getInt(1);
				ps = conn.prepareStatement(getTagWithID);
				ps.setInt(1, tagID);
				ResultSet rs2 = ps.executeQuery();
				while(rs2.next()) {
					results.add(this.getRecipe(rs2.getInt(2)));
				}
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	// print the following set on profile page
	public Set<String> profileFollowingSet(String name) {
		int IDnum = getUserIDByUsername(name);
		Set<String> following = new HashSet<>();
		try {
			ps = conn.prepareStatement(followingTable);
			ps.setInt(1, IDnum);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				following.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return following;
	}

	
	// get username from ID
	public String getUsername(String name) {
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(getUsernameFromID);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	
	// print follower
	public Set<String> followerSet(String name) {
		Set<String> follower = new HashSet<>();
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(followerTable);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				follower.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Set<String> names = new HashSet<>();
		for(String s : follower) {
			String fan = getUsername(s);
			names.add(fan);
		}

		return names;
	}

	//get profile information
	public String getProfileInfo(String name, int num) {
		try {
			ps = conn.prepareStatement(inputUsername);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(num == 1) { return rs.getString(6);}
				else if(num == 2) { return rs.getString(4) + " " + rs.getString(5);}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}

	public void addEvent(String userName, int recipeID, String action) {
		try {
			Timestamp timeStamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			int userID = this.getUserIDByUsername(userName);
			ps = conn.prepareStatement(addEvent);
			ps.setInt(1, userID);
			ps.setString(2, action);
			ps.setInt(3,  recipeID);
			ps.setTimestamp(4, timeStamp);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Event getEvent(int eventID) {
		try {
			
			ps = conn.prepareStatement(getEvent);
			ps.setInt(1,  eventID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Event event = new Event();
			event.setUsernameDidAction(this.getUsernameByUserID(rs.getInt(2)));
			event.setAction(rs.getString(3));
			event.setRecipeID(rs.getInt(4));
			event.setRecipeName(this.getRecipe(event.getRecipeID()).getName());
			event.setTimestamp(rs.getTimestamp(5));
			event.setEventID(rs.getInt(1));
			return event;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<Event> getUserEvents(String username) {
		try {
			ps = conn.prepareStatement(getUserEvents);
			int userID = this.getUserIDByUsername(username);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			Vector<Event> events = new Vector<Event>();
			while(rs.next()) {
				Event event = getEvent(rs.getInt(1));
				events.add(event);
			}
			return events;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateLike(int recipeID) {
		try {
			ps = conn.prepareStatement(getCurrNumLikes);
			ps.setInt(1, recipeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				updateRecipeRow(recipeID, rs.getInt(3));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRecipeRow(int recipeID, int numLikes) {
		int updatedLikes = numLikes +1;
		System.out.println("updated likes: " + updatedLikes);
		try {
			ps = conn.prepareStatement(increaseLikeByOne);
			ps.setInt(2, recipeID);
			ps.setInt(1, updatedLikes);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getUserWhoUploadedRecipe(int recipeID) {
		try {
			ps = conn.prepareStatement(getSavedRecipe);
			ps.setInt(1, recipeID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// user search
		public Set<String> userSearch(String search) {
			Set<String> searchResults = new HashSet<>();
			try {
				st = conn.createStatement();
				ps = conn.prepareStatement(userTable);
				ResultSet rs = ps.executeQuery();
				User user = getUserByUsername(search);
				//System.out.println("wanting to search: " + search);
				//System.out.println(user.getFullName());
				while (rs.next()) {
					if (rs.getString(2).toLowerCase().equals(search) || rs.getString(4).toLowerCase().equals(search)
							|| rs.getString(5).toLowerCase().equals(search)) {
						searchResults.add(rs.getString(2));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return searchResults;
		}
	
}

