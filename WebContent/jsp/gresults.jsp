<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* "%>
    <%@ page import= "java.util.*, data.*, recipediaClasses.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Search Results</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/results.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link href="../js/lib/noty.css" rel="stylesheet"></link>
		<script src="../js/lib/noty.js" type="text/javascript"></script>
		<script type='text/javascript' src='../js/Notifications.js'></script>
	</head>
	<%
		RecipediaJDBC jdbc = new RecipediaJDBC();
		
		String searchTerm = request.getParameter("searchInput");
		//System.out.println(search_result);
		Set<Recipe> results = new HashSet<Recipe>();
		Set<Recipe> nameResults = jdbc.nameResult(searchTerm);
		Iterator<Recipe> it = nameResults.iterator();
		while(it.hasNext())
		{
			Recipe re = it.next();
			results.add(re);
		}
		Set<Recipe> tagResults = jdbc.tagResult(searchTerm);
		Iterator<Recipe> it2 = tagResults.iterator();
		while(it2.hasNext()) {
			Recipe re = it2.next();
			results.add(re);
		}
		Vector<Recipe> finalResults = new Vector<Recipe>();
		Iterator<Recipe> it3 = results.iterator();
		while(it3.hasNext()) {
			Recipe re = it3.next();
			finalResults.add(re);
		}	
	%>
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
 		<div id ="search-div" style="background-color:#F7C59F">
 			<h2 align="center" id="header" >Search Results</h2>
 		</div>
	    <div id="main-div" class="row ">
	    
	    	<%
	    		//play around with counter to control how many elements appear
	    		int counter = finalResults.size();
	    		Vector<String> images= new Vector<String>();
	    		
	    		for(int i=0;i<counter;i++)
	    		{
	    			if(i%3==0)
	    			{
	    				if(i!=0) out.println("<div class=\"row\">");
	    	%>
	    				<div class="col-md-2"></div>
	    					<div class="col-md-2">
	    						<div class="my_container">
									<img  class="image" src="<%=finalResults.get(i).getImageURL() %>">
									<div class="overlay">
										<a href="${pageContext.request.contextPath}/jsp/gViewRecipes.jsp?recipeID=<%=finalResults.get(i).getId() %>">
											<div class="text"><%= finalResults.get(i).getName() %></div>
										</a>
									</div>
								</div>
	    					</div>
	    			<%
	    			}
	    			else
	    			{
	    			%>
		    			<div class="col-sm-1"></div>
		    				<div class="col-md-2">
		    					<div class="my_container">
									<img  class="image" src="<%=finalResults.get(i).getImageURL() %>">
									<div class="overlay">
										<a href="${pageContext.request.contextPath}/jsp/gViewRecipes.jsp?recipeID=<%=finalResults.get(i).getId() %>">
											<div class="text"><%= finalResults.get(i).getName() %></div>
										</a>
									</div>
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