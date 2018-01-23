<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import = "java.util.Random" %>
<%@ page import = "java.lang.Math" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Recipedia</title>
	</head>
	<script>
      function validate() {
    	  var xhttp = new XMLHttpRequest();
    	  xhttp.open("GET", "${pageContext.request.contextPath}/LoginServlet?username=" + document.loginform.username.value + "&password="+document.loginform.password.value, false);
    	  xhttp.send();
       	  if(xhttp.responseText.trim().length > 0) {
           	  document.getElementById("result").innerHTML = xhttp.responseText;
           	  return false;
             }
       	  
       	  else {
       		window.location.href = "${pageContext.request.contextPath}/jsp/feed.jsp";
       		return false;
       	  }
      }
      
      function signup() {
    	  var xhttp = new XMLHttpRequest();
    	  xhttp.open("GET", "${pageContext.request.contextPath}/SignupServlet?fullname=" + document.signupform.fullname.value + "&username=" + document.signupform.username.value + "&password="+document.signupform.password.value + "&imageURL="+document.signupform.imageURL.value, false);
    	  xhttp.send();
       	  if(xhttp.responseText.trim().length > 0) {
           	  document.getElementById("result_1").innerHTML = xhttp.responseText;
           	  return false;
             }
       	  
       	  else {
       		window.location.href = "${pageContext.request.contextPath}/jsp/feed.jsp";
       		return false;
       	  }
      }
      </script>
	<body>
	<%
	String [] images = new String [13];
	images[0] = "https://static01.nyt.com/images/2014/05/14/dining/14REDVELVET/14REDVELVET-superJumbo-v4.jpg";
	images[1] = "http://food.fnr.sndimg.com/content/dam/images/food/fullset/2011/2/4/1/RX-FNM_030111-Lighten-Up-012_s4x3.jpg.rend.hgtvcom.616.462.jpeg";
	images[2] = "https://s-media-cache-ak0.pinimg.com/originals/cd/48/d0/cd48d067a07c8c62d38a1b8bb1c0c4f3.jpg";
	images[3] = "http://www.gourmandize.com/uploads/media/banana-bread-2.jpg?1392422985";
	images[4] = "http://d3cizcpymoenau.cloudfront.net/images/24330/SFS_Buttermilk_Pancakes-25.jpg";
	images[5] = "http://s3.amazonaws.com/studio-me/system/photos/photos/000/182/764/large/french-toast-recipe.jpg?1387531457&id=182764&caption=true";
	images[6] = "http://clv.h-cdn.co/assets/15/43/thanksgiving-sides-10-1115.jpg ";
	images[7] = "http://img.sndimg.com/food/image/upload/h_465,w_620,c_fit/v1/img/recipes/52/62/0/picCuHrVj.jpg ";
	images[8] = "http://www.newhealthadvisor.com/images/1HT00234/california-roll.jpg";
	images[9] = "http://www.wikihow.com/images/a/a5/Cook-Frozen-Lobster-Tails-Step-19.jpg";
	images[10] ="http://life-in-the-lofthouse.com/wp-content/uploads/2015/07/Chicken_Fried_Rice4.jpg";
	images[11] = "http://www.bakerssquare.com/i/pies/profile/strawberryrhubarb_main1.jpg";
	images[12] = "http://www.bakerssquare.com/i/pies/profile/strawberryrhubarb_main1.jpg";
	
	Random rn = new Random();
	int randomNum = 0;
	int i = rn.nextInt() % 13 -1;
	if (i < 13 && i >= 0){
		randomNum = i;
	}
	else {
		randomNum = 0;
	}
	
	%>
	<!-- ===========================NAV BAR============================================ -->
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      
		      <img id='recipedia-logo' alt='recipedia-logo' src='../images/Recipedia_logo.png'>
		    </div>
		      
		      <%-- <form action="${pageContext.request.contextPath}/LoginServlet" class="navbar-form navbar-left" role="search"> --%>
		      <form name="loginform" id = "login-form"  onsubmit="return validate()">
		      	<span class="text-box">Username</span>
		      	 <input id = "fillers" type="text" name ="username" placeholder ="username" />
		      	<span class = "text-box"> Password</span>
		      	 <input id="fillers" type="text" name ="password" placeholder ="password" />
		      	 <input type="submit"  value="Login" name="login" class="btn btn-default"/>
		      	 <div id="result"></div>
		      </form>
		  </div>
		</nav>
		
		<!-- =============================WHOLE PAGE================================== -->
		<div class="row">
			<div class="col-md-6">			<!-- image -->
				<div id = "image-container">
					<img src="<%=images[randomNum] %>" id="featured-image" alt="recipedia-logo">
				</div>
			</div>
			<div class="col-md-6">		<!--  sign up -->
				
				<h1 id="header" >Sign Up</h1>
					<form name="signupform" id="signup-form" onsubmit="return signup()">
						<div class="form-group">
							<label>Full name</label>
							<input type="text" placeholder="fullname" name="fullname" class="form-control">
					      	
				      	</div>
				      	<div class="form-group">
				      		<label>Username</label>
					      	<input type="text" name = "username" placeholder="username" class="form-control"/>
					      	
				    	</div>
				    	<div class="form-group">
				    		<label> Password</label>
					      	<input type="password" name="password" placeholder="password" class="form-control"/>
				    	</div>	
				    	<div class="form-group">
							<label>Image</label>
							<input type="text" name="imageURL"  placeholder="url" class="form-control" />
				      	</div>
					      	 <input type="submit" value="Sign Up" class="btn btn-default"/>
					      	 <div id="result_1"></div>	
					   <!--  <div class="col-md-6"> -->
					    	<br/>
					    	<br/>
					    	<br/>
							<a id ="continue-as-guest-btn" href="gfeed.jsp" class ="btn btn-default">Continue as guest</a>
						<!-- </div> -->
		      </form>
			</div>
		</div>
	</body>
</html>