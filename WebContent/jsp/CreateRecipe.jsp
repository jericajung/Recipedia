<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
<!-- Optional theme -->

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- Latest compiled and minified JavaScript -->
<link href="../js/lib/noty.css" rel="stylesheet"></link>
<script src="../js/lib/noty.js" type="text/javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type='text/javascript' src='../js/Notifications.js'></script>
<script type='text/javascript' src='../js/Recipe.js'></script>
<script type='text/javascript' src='../js/Ingredient.js'></script>
<script type='text/javascript' src='../js/Instruction.js'></script>
<script type='text/javascript' src='../js/CreateRecipe.js'></script>
<script type='text/javascript' src='../js/Tag.js'></script>



<link rel="stylesheet" href="../css/CreateRecipe.css">


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Recipedia - Create a recipe</title>
	</head>
	<body>
	  <nav class="navbar navbar-inverse navbar-fixed-top">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <img id='recipedia-logo' alt='recipedia-logo' src='../images/Recipedia_logo.png'>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href="${pageContext.request.contextPath}/jsp/feed.jsp">Feed</a></li>
		      <li><a href="${pageContext.request.contextPath}/jsp/profile.jsp">View Profile</a></li>
		      <li class="active" ><a id="create-recipes-tab" href="${pageContext.request.contextPath}/jsp/CreateRecipe.jsp">Create Recipe</a></li>
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
	<div class="container" id="main-container">
		<h2 id="title">Create a recipe</h2>
			<p> Step 1: Add ingredients<p>
			<form class="form-inline">
				<div class="form-group " id="ingredient-name-form">
					<label for="ingredient" class="control-label" '>Ingredient</label>
					<input id="ingredient-input" type="text" class="form-control"  placeholder="Enter Ingredient">
					<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
  					<span id="inputError2Status" class="sr-only">(error)</span>
				</div>
				<div class="form-group " id="ingredient-quantity-form">
					<!-- <label for="quantity">Quantity</label> -->
					<input id="quantity-input" type="text" class="form-control"  placeholder="Enter Quantity">
					<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
  					<span id="inputError2Status" class="sr-only">(error)</span>
					
					
				</div>
				
				<div class="form-group">
					<select id="unit-input" class="form-control">
						  <option>Ounces</option>
						  <option>Pounds</option>
						  <option>Tablespoons</option>
						  <option>Teaspoons</option>
						  <option>Milliters</option>
						  <option>Liters</option>
						  <option>Quarts</option>
						  <option>Cups</option>
						  <option>Pints</option>
						  <option>Quarts</option>
						  <option>Grams</option>
						  <option>Milligrams</option>
						  <option>Fluid Ounces</option>
						  <option>Units</option>
						  <option>Gallons</option>
						  <option>Slices</option>
						  
						  <option>Other (Specify in instructions)</option>
					</select>
				</div>
				<button id="submit-ingredient" type="submit" class="btn btn-default">Add Ingredient</button>
			</form>
			<div id="ingredient-box" class="container">
				
			</div>
			<p>Step 2: Add instructions</p>
			<form>
				<div class="form-group" id="instruction-form">
					<textarea id="instruction-input" class="form-control" rows="2"></textarea>
					<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
  					<span id="inputError2Status" class="sr-only">(error)</span>
				</div>
				<button id="submit-instruction" type="submit" class="btn btn-default">Add Instruction Step</button>
			</form>
			<div id="instructions-box" class="container">
				
			</div>
			<p>Step 3: Add tags</p>
			<form class="form-inline">
				<div class="form-group" id="tag-form">
					<label for="tag" class="control-label">Tag</label>
					<input id="tag-input" type="text" class="form-control"  placeholder="Enter Tag">
					<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
  					<span id="inputError2Status" class="sr-only">(error)</span>
				</div>
				<button id="submit-tag" type="submit" class="btn btn-default">Add Tag</button>
				
			</form>
			<div id="tag-box" class="container">
			</div>
			
			<p>Step 4: Add an image</p>
			
			<!-- <input type='file' onchange="readURL(this);" />-->
	    	
	    	<form class="form-inline">
	    		<div class="form-group" id="image-url-form">
	    			<label  for="input-lg">Image URL</label>
	    			<input id="image-url-input" type="text" class="form-control"  placeholder="Enter Image URL" >
	    			<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
  					<span id="inputError2Status" class="sr-only">(error)</span>
	    		</div>
	    		<button id="submit-image-url" type="submit" class="btn btn-default">Preview Image</button>
	    	</form>
	    	<br>
	    	<div>
	    		<img id="recipe-image" src="#" alt="your image" /> 
    		</div>
	    	<br>
	    	<p>Step 5: Give it a name</p>
	    	<form id="recipe-name-form" class="form-inline">
	    		<div class="form-group" id="name-form">
	    			<label  for="input-lg">Recipe Name</label>
	    			<input id="recipe-name-input" type="text" class="form-control"  placeholder="Enter Name" >
	    			<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
  					<span id="inputError2Status" class="sr-only">(error)</span>
	    		</div>
	    	</form>
	    	<button type="submit" class="btn btn-default" id="final-submit-button">Submit Recipe</button>
	    	
		
		</div>
	</body>
</html>