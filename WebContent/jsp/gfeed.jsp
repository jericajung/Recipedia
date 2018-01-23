c<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
    <%@ page import= "java.util.*, data.*, recipediaClasses.*, data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Guest Feed</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/feed.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href="../js/lib/noty.css" rel="stylesheet"></link>
		<script src="../js/lib/noty.js" type="text/javascript"></script>
		<script src="../js/WebSocketEndpoint.js"></script>
		<script type='text/javascript' src='../js/Notifications.js'></script>
		<script type='text/javascript' src='../js/feed.js'></script>
		
		
	</head>
	<body>
<!-- ===========================NAV BAR============================================ -->
	    
	   <nav class="navbar navbar-inverse navbar-fixed-top">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <img id='recipedia-logo' alt='recipedia-logo' src='../images/Recipedia_logo.png'>
		    </div>
		    <ul class="nav navbar-nav">
		      <li class="active"><a href="${pageContext.request.contextPath}/jsp/gfeed.jsp">Feed</a></li>
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
 <!-- =============================WHOLE PAGE================================== -->
	    <div id="main-div" class="row ">
	    
	    		<%
	    		//need to grab all events, sort them by timestamp
	    		RecipediaJDBC rjdbc = new RecipediaJDBC();
	    		session = request.getSession(true);
	    		Vector<Recipe> gRecipes = rjdbc.getAllRecipes();
	    		//String username = (String)session.getAttribute("username");
	    		//User user = rjdbc.getUserByUsername(username);
	    		//Set<String> followingSet = rjdbc.profileFollowingSet(username);
	    		//Vector<Event> events = new Vector<Event>();
	    		//Iterator<String> it = followingSet.iterator();
	    		/*while(it.hasNext())
	    		{
	    			String name = it.next();
	    			Vector<Event> newEvents = rjdbc.getUserEvents(name);
	    			for (int i = 0; i < newEvents.size(); i++) {
	    				events.add(newEvents.get(i));
	    			}
	    		}*/
	    		//Collections.sort(events);
	    		//rjdbc.getUserEvents();
	    		
	    		int counter = /*gRecipes.size()*/9;
	    		Vector<String> images= new Vector<String>();
	    		for(int i=0;i<counter;i++)
	    		{
	    			if(i%3==0)
	    			{
	    				if(i!=0) out.println("<div class=\"row\">");
	    		%>
	    				<div class="col-md-2"></div>
	    					<div class="col-md-2">
	    						<div class="image-container">
		    						<a href="${pageContext.request.contextPath}/jsp/gViewRecipes.jsp?recipeID=<%=gRecipes.get(i).getId() %>"><img style="margin-bottom:10px" src="<%=gRecipes.get(i).getImageURL() %>"></a>
	    						</div>
	    					</div>
	    			<%
	    			}
	    			else
	    			{
	    			%>
		    			<div class="col-sm-1"></div>
		    				<div class="col-md-2">
		    					<div class="image-container">
		    						<a href="${pageContext.request.contextPath}/jsp/gViewRecipes.jsp?recipeID=<%=gRecipes.get(i).getId() %>"><img style="margin-bottom:10px" src="<%=gRecipes.get(i).getImageURL() %>"></a>
		    					</div>
		    				</div>
		    			<%
		    				if(i%3==2) out.println("</div>");
	    			}
	    		}
	    	
	    				%>
	   </div>
	  </div>
	  	
	  
	 
	  
	
	
	</body>
</html>