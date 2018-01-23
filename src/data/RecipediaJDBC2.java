package data;

import java.sql.*;
import java.util.*;

import recipediaClasses.User;

public class RecipediaJDBC2 {
	
	//Data Members
	private static Connection conn = null;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private String userToCheck = null;
	
	//Strings for Database
	private final static String addUser = "INSERT INTO Users(username, pword, fname, lname, image) VALUES(?,?,?,?,?)";
	private final static String inputUsername = "SELECT * FROM Users WHERE username=?";
	private final static String inputPassword = "SELECT * FROM Users WHERE pword=?";
	private final static String followingTable = "SELECT * FROM Fans WHERE userID=?";
	private final static String addFollowing = "INSERT INTO Fans(userID, fanName) VALUES(?,?)";
	private final static String removeFollower = "DELETE FROM Fans WHERE userID=? AND fanName=?";
	private final static String addRecipe = "INSERT INTO Recipes(title, tagID, likes) VALUES(?,?,?)";
	private final static String getUsernameFromID = "SELECT * FROM Users WHERE userID=?"; //COPY AND PASTE
	private final static String followerTable = "SELECT * FROM Following WHERE followingName=?";


	
	public RecipediaJDBC2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//change this according to your inputs
			conn = DriverManager.getConnection("jdbc:mysql://localhost/recipedia?user=root&password=Pickoftheweek1!&useSSL=false");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(String fullName, String username, String password) {
		Scanner scanner = new Scanner(fullName);
		String firstName = scanner.next();
		String lastName = scanner.next();
		try {
			ps = conn.prepareStatement(addUser);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, "");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		scanner.close();
	}
	
	//checks if username exists
	public boolean doesUserExist(String name) {
			try {
				st = conn.createStatement();
				ps = conn.prepareStatement(inputUsername);
				ps.setString(1, name);
				rs = ps.executeQuery();
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
			rs = ps.executeQuery();
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
	
	public void saveRecipe(String username, String recipeName){
		Statement st = null;
		int userID = this.getUserIDByUsername(username);
		int recipeID = this.getRecipeIDByRecipeName(recipeName);
		
		if (userID != 0 && recipeID != 0){
			try {
				st.executeUpdate("INSERT INTO SavedRecipes(userID, recipeID) " + 
						"VALUES ('" + userID + "','" + recipeID + "');");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void uploadRecipe(String recipeName){
		
		int recipeID = 0;
		
	}
	
	//adds a recipe into the database (INCOMPLETE)
	public void addRecipe(String recipeName) {
		try {
			ps = conn.prepareStatement(addRecipe);
			ps.setString(1, recipeName);
			ps.setString(2, "");
			ps.setInt(3, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
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

	public int getRecipeIDByRecipeName(String recipeName){
		int recipeID = 0;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Recipes WHERE title = '" + recipeName + "';");
			if (rs.next()){
				recipeID = Integer.parseInt(rs.getString("recipeID"));
			}
		} catch(SQLException sqle){
			System.out.println("sqle: " + sqle.getMessage());
		}
		return recipeID;
	}
	
	//print following
	public Set<String> followingSet() {
			String temp = userToCheck;
			int check = getUserIDByUsername(temp);
			String usernameID = Integer.toString(check);
			Set<String> following = new HashSet<>();
			try {
				st = conn.createStatement();
				ps = conn.prepareStatement(followingTable);
				ps.setString(1, usernameID);
				rs = ps.executeQuery();
				while(rs.next()) {
					following.add(rs.getString(3));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return following;
		}
		
	//adds a following to loggedinUser and follower for viewedUser
	public void addtoFollowing(String loggedinUser, String viewedUser) {
		int userID = getUserIDByUsername(loggedinUser);
		String usernameID = Integer.toString(userID);
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(addFollowing);
			ps.setString(1, usernameID);
			ps.setString(2, viewedUser);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//remove a following to loggedinUser and viewedUser loses a follower
	public void removeFromFollowing(String loggedinUser, String viewedUser) {
		int userID = getUserIDByUsername(loggedinUser);
		String usernameID = Integer.toString(userID);
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(removeFollower);
			ps.setString(1, usernameID);
			ps.setString(2, viewedUser);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//print the following set on profile page
	public Set<String> profileFollowingSet(String name) {
		int userID = getUserIDByUsername(name);
		String usernameID = Integer.toString(userID);
		Set<String> following = new HashSet<>();
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(followingTable);
			ps.setString(1, usernameID);
			rs = ps.executeQuery();
			while(rs.next()) {
				following.add(rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return following;
	}

	//follower set
	public Set<String> followerSet(String name) {
		Set<String> follower = new HashSet<>();
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(followerTable);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				follower.add(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return follower;
	}
	
	//get profile information
	public String getProfileInfo(String name, int num) {
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(inputUsername);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				if(num == 1) { return rs.getString(6);}
				else if(num == 2) { return rs.getString(4) + " " + rs.getString(5);}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}
}
