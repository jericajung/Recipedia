<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.*, data.*, recipediaClasses.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/feed.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">

	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href="../js/lib/noty.css" rel="stylesheet"></link>
		<script src="../js/lib/noty.js" type="text/javascript"></script>
		<script type='text/javascript' src='../js/Notifications.js'></script>
		<script>
 	function followchange() {
 		var change = document.getElementById("followButton");
 		var xhttp = new XMLHttpRequest();
 	   	if (change.value == "Unfan")
 	   	{
 	   		change.value = "Become a fan";
 	   		xhttp.open("GET", "${pageContext.request.contextPath}/FollowUser?userClicked=" + document.getElementById("userClicked").value, false);		    	  
 	    	xhttp.send();
 	   	}
 	
 	   else if(change.value == "Become a fan")
 	   {
 	       	change.value = "Unfan";
 	       	xhttp.open("GET", "${pageContext.request.contextPath}/UnfollowUser?userClicked=" + document.getElementById("userClicked").value, false);		    	  
	    	xhttp.send();
 	   	}
 	}
	</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Profile</title>
	</head>
	<%
		//get the user that is needed to show on profile
		String loggedInUser = (String) session.getAttribute("username");
		String viewedUser = (String) request.getParameter("userClicked");
		RecipediaJDBC jdbc = new RecipediaJDBC();
		String name="";
		if(viewedUser != null) {
			name = viewedUser;
		}
		
		else {
			name = loggedInUser;
			
		}
		
		System.out.println("viewing user: " + name);
		User forLoggedIn = jdbc.getUserByUsername(loggedInUser);
		User user = jdbc.getUserByUsername(name);
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
		      <li ><a id="create-recipes-tab" href="${pageContext.request.contextPath}/jsp/CreateRecipe.jsp">Create Recipe</a></li>
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
		
		
		<!-- =============================WHOLE PAGE================================== -->
		<div id="main-div" class = "row">
			<div class="col-md-10">
				<div class="col-md-3">
					<div class="profile-image-container">
						<img id = "profile-image" src="<%=user.getImage() %>"/>
					</div>
					
				</div>
				<div class="col-md-2"></div>
				<div class="col-md-6">
					<h1 id = "name"><%=user.getFullName() %></h1>
					<h1 id="username">@<%=user.getUsername() %></h1>
					<div>
					<%
						if(name.equals(viewedUser) && !viewedUser.equals(loggedInUser)) {
							Set<String> followOrNot = user.getFans();
							System.out.println("user: " + user.getUsername());
							for(String s : followOrNot) {
								System.out.println("fan name: "+s);
							}
							
							System.out.println("see if loggedin user exists: " + loggedInUser);
							
							if(!followOrNot.contains(loggedInUser)) { %>
								<form name="followform" method="GET" onsubmit="followchange()">
									<input type="hidden" id="userClicked" name="userClicked" value="<%= name %>">
									<input  type="submit" id="followButton" value="Become a fan">
								</form>
					<%		}
							
							else { %>
								<form name="followform" method="GET" onsubmit="followchange()">
									<input type="hidden" id="userClicked" name="userClicked" value="<%=name  %>">
									<input type="submit" id="followButton" value="Unfan">
								</form>		
					<%		}
						}
					%>
					</div>
				</div>
			</div>
		</div>
		<div class = "row">
			<div class="col-md-1"></div>
			<div class="col-md-5 recipe-container" id="savedRecipes">
				<div class = "header-container">
					<%String urlSave = "viewAll.jsp?username=" +user.getUsername() + "&id=1"; %>
					<h1 class="header"><a class="link-header" href="<%=urlSave%>">Saved Recipes</a></h1>
				</div>
				<%
					int countSave = 1;
					for(Recipe s : user.getSavedRecipes()) {
						String recipeImage = s.getImageURL();
						if (countSave < 5){
							%>
							<a href="${pageContext.request.contextPath}/jsp/viewRecipes.jsp?recipeID=<%=s.getId()%>">
								<img style ="width: 150px; height:150px; margin:20px 40px 20px 30px"src="<%=recipeImage%>"/>
							</a><%
						}
					countSave++;
				} %>
			</div>
			<div class="col-md-5 recipe-container">
				<div class="header-container-upload">
					<% String urlUpload ="viewAll.jsp?username=" +user.getUsername() + "&id=2"; %>
					<h1 class="header"><a class="link-header" href="<%=urlUpload %>" + >Uploaded Recipes</a></h1>
				</div>
				<%
					int count = 1;
					for(Recipe s : user.getUploadedRecipes()) {
						String recipeImage = s.getImageURL();
					if (count < 5){
						%><a href="${pageContext.request.contextPath}/jsp/viewRecipes.jsp?recipeID=<%= s.getId() %>">
						<img style ="width: 150px; height:150px; margin:20px 40px 20px 30px"src="<%=recipeImage%>"/>
						</a>
					<%} %>
					<%count++;
				} %>
			</div>
			<div class="col-md-1"></div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class= "col-md-6 recipe-container-fans" >
					<div class="header-container-fans">
						<%String urlFans = "viewAll.jsp?username=" +user.getUsername() + "&id=3"; %>
						<h1 class="header" ><a class="link-header" href="<%=urlFans%>">Fans</a></h1>
					</div>
					<%
						 for(String s : user.getFans()) {
							String url = jdbc.getProfileInfo(s, 1);
							/* session.setAttribute("go_to_user", s) */; %>
							
							<a href="${pageContext.request.contextPath}/jsp/profile.jsp?userClicked=<%= s %>">
								<img id="fan-image" src="<%=url %>"/>
							</a>
							
					<% 	} %>
				</div>
				<div class="col-md-3"></div>
			</div>	
		</div> 
		
	</body>
</html>