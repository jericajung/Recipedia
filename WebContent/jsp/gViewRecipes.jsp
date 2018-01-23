<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 <%@ page import = "data.RecipediaJDBC" %>
 <%@ page import = "recipediaClasses.Recipe" %>
 <%@ page import = "recipediaClasses.Ingredient" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String recipeIDString = (String) request.getParameter("recipeID");
	//String recipeIDString = "5";
	int recipeID = Integer.parseInt(recipeIDString);
	RecipediaJDBC jdbc = new RecipediaJDBC();
	Recipe recipe = jdbc.getRecipe(recipeID);
	String name = recipe.getName();
	Vector<Ingredient> ingredients = recipe.getIngredients();
	Vector<String> instructions = recipe.getInstructions();
	String image = recipe.getImageURL();
	int likes = recipe.getLikes();
%>

<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/recipe.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/feed.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href="../js/lib/noty.css" rel="stylesheet"></link>
		<script src="../js/lib/noty.js" type="text/javascript"></script>
		<script type='text/javascript' src='../js/Notifications.js'></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>View Recipe</title>
	</head>
	<body>
		 <nav class="navbar navbar-inverse navbar-fixed-top">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <img id='recipedia-logo' alt='recipedia-logo' src='../images/Recipedia_logo.png'>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href="${pageContext.request.contextPath}/jsp/gfeed.jsp">Feed</a></li>
		    </ul>
		     <form class="navbar-form navbar-left" action="gresults.jsp">
		      <div class="form-group">
		        <input id="search" type="text" class="form-control" name="searchInput" placeholder="Search Recipes...">
		      </div>
		     <button class="btn btn-default" type="submit" value="searchInput">
	            <span class="glyphicon glyphicon-search"></span>
	          </button>
		    </form>
		    <div class="logout">
	         	<a href="${pageContext.request.contextPath}/jsp/Login.jsp"><img id="logout-button" src="../images/logout.jpg"></a>
	         </div>
		  </div>
		</nav>
		<div class="container">
			<div class="row">
				
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<h1 class="header"><%=name %></h1>
					<div class="image-container">
						<img id="recipeImage" src="<%= image %>" ">
					</div>
					<h3 id="likes">Likes: <%=likes %></h3>
					<h2 class="headers">Ingredients</h2>
					<ul>
					<%
					for (Ingredient ingr : ingredients){
						%><li id = "ingredient-list" type="none"><%= ingr.getQuantity() + " " + ingr.getUnits() + " " + ingr.getName()%></li>
					<% 	
					}
					%>
					</ul>
					<h2 class="headers">Instructions</h2>
					<ol>
					<%
					for (String step : instructions){
						%><li id = "instructions"><%= step %></li><br/><% 
					}
					%>
					</ol>
					<div class="save-container">
						
					</div>
					<!-- <h2 class="headers">Reviews</h2>
					<p>These are the reviews</p><br>
					
					<h2 class="headers">Recommended Recipes</h2>
					<p>Show the other recipes related to this recipe.</p> -->
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
</body>
</html>