<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.*, data.*, recipediaClasses.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Viewing All</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css"> --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/feed.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/viewAll.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href="../js/lib/noty.css" rel="stylesheet"></link>
		<script src="../js/lib/noty.js" type="text/javascript"></script>
		<script type='text/javascript' src='../js/Notifications.js'></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<%
		String viewedUser = request.getParameter("username");
		String idNum = request.getParameter("id");
		System.out.println(viewedUser);
		System.out.println(idNum);
		int chosenNum = Integer.parseInt(idNum);
		RecipediaJDBC jdbc = new RecipediaJDBC();
		User user = jdbc.getUserByUsername(viewedUser);
		
	%>
<body>
		<!-- ===========================NAV BAR============================================ -->
	    <nav class="navbar navbar-inverse navbar-fixed-top">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <img id='recipedia-logo' alt='recipedia-logo' src='../images/Recipedia_logo.png'>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href="${pageContext.request.contextPath}/jsp/feed.jsp">Feed</a></li>
		      <li  class="active"><a href="${pageContext.request.contextPath}/jsp/profile.jsp">View Profile</a></li>
		      <li ><a id="create-recipes-tab" href="${pageContext.request.contextPath}/jsp/CreateRecipe.jsp">Create Recipes</a></li>
		    </ul>
		     <form class="navbar-form navbar-left" action="results.jsp">
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
		
		<!-- WHOLE PAGE -->
			<div id="main-div" class="row">
			<div class="col-md-1"></div>
			<div class="col-md-9 recipe-container">
			
		<%
		
			if(chosenNum == 1) {
			%>
				<h1>Saved Recipes</h1>
			<%	for(Recipe r : user.getSavedRecipes()) {
					String recipeImage = r.getImageURL();%>					
					<div class="my_container">
						<img class="image" style ="width: 150px; height:150px; margin:20px 40px 20px 30px"src="<%=recipeImage%>">
						<div class="overlay">
						 <a href="${pageContext.request.contextPath}/jsp/viewRecipes.jsp?recipeID=<%=r.getId()%>">
							<div class="text"><%=r.getName() %></div>
						</a>
	  					</div>
	  				</div>
			<% }
			}
		
			else if(chosenNum == 2) { %>
				<h1>Uploaded Recipes</h1>
			<%	for(Recipe r : user.getUploadedRecipes()) {
					String recipeImage = r.getImageURL(); %>
					<div class="my_container">
						<img class="image" style ="width: 150px; height:150px; margin:20px 40px 20px 30px"src="<%=recipeImage%>">
						<div class="overlay">
						 <a href="${pageContext.request.contextPath}/jsp/viewRecipes.jsp?recipeID=<%=r.getId()%>">
							<div class="text"><%=r.getName() %></div>
						</a>
	  					</div>
	  				</div>
				<% }
			}
		
			else { %>
			<h1>Fans</h1>
			<%	for(String s : user.getFans()) {
					String url = jdbc.getProfileInfo(s, 1); %>
					<div class="my_container">
						<img class="image" style ="width: 150px; height:150px; margin:20px 40px 20px 30px"src="<%=url%>">
						<div class="overlay">
							<a href="${pageContext.request.contextPath}/jsp/profile.jsp?userClicked=<%= s %>">
								<div class="text"><%=s %></div>
							</a>
	  					</div>
	  				</div>
			<% }
			}
		%>
		</div>
		</div>
		<div id="back-to-profile">
			<form action="${pageContext.request.contextPath}/jsp/profile.jsp?userClicked?=<%=viewedUser%>"><input type="submit" id="back-to-profile-btn" value="Back to Profile" /></form>		
		</div>
</body>
</html>