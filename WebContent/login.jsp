<%--
/*******************************************************************************************
 * Copyright (c) 2013 Sotiraki Sima                                                         *
 *                                                                                         *
 * This program is free software; you can redistribute it and/or modify it under the terms *
 * of the GNU General Public License as published by the Free Software Foundation; either  *
 * version 2 of the License, or (at your option) any later version.                        *
 *                                                                                         *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY         *
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 	     *
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.                *
 *                                                                                         *
 * You should have received a copy of the GNU General Public License along with this       *
 * program; if not, write to the:                                                          *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,                    *
 * MA  02111-1307, USA.                                                                    *
 *                                                                                         *
 * --------------------------------------------------------------------------------------- *
 * Project:  Parking Management                                                            *
 * WebSite:  http://ww.simasware.com                                                       *
 * Author :  Sotiraki Sima (Sotiraq.Sima@gmail.com)                                         *  
 *                                                                                         *
 *******************************************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

request.setCharacterEncoding("UTF-8");
String ActiveSession = (String)session.getAttribute("username");

if(ActiveSession!=null){
	%>
<jsp:forward page="search.jsp" />
<%
}else{
	session.invalidate();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign in</title>
<link rel="icon" href="img/favicon.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #E1E1E1;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link rel="shortcut icon" href="ico/favicon.png">
</head>

<body onload="document.getElementById('login_id').focus();">

	<div class="container">
		<div align="center">
			<img src="img/LogoParkingEfi2.png"></img>
		</div>
		<form class="form-signin" name="login_frm" id="login_frm" action=""
			method="post">
			<blockquote>
				<p>Signing In</p>
			</blockquote>
			<input name="login_id" id="login_id" type="text"
				class="input-block-level" placeholder="Username"> <input
				name="password" id="password" type="password"
				class="input-block-level" placeholder="Password">
			<div align="center">
				<button name="login" id="login" class="btn btn-large btn-primary"
					type="submit">
					&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-user icon-white"></i>&nbsp;&nbsp;Sign in&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</button>
			</div>
			<br />
			<div id="msgbox"></div>

		</form>

		<a href="Register-User.jsp"><b>Create Account</b></a>
		<hr>

		<div class="footer">
			<p>
				Copyright &copy; --- 2013 - (v1.34 - Powered by
				<a href="http://ww.simasware.com" target="_blank">SimaS</a>)
			</p>
		</div>

	</div>
	<!-- /container -->
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>

	<script src="js/bootstrap-transition.js"></script>
	<script src="js/bootstrap-alert.js"></script>
	<script src="js/bootstrap-modal.js"></script>
	<script src="js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap-scrollspy.js"></script>
	<script src="js/bootstrap-tab.js"></script>
	<script src="js/bootstrap-tooltip.js"></script>
	<script src="js/bootstrap-popover.js"></script>
	<script src="js/bootstrap-button.js"></script>
	<script src="js/bootstrap-collapse.js"></script>
	<script src="js/bootstrap-carousel.js"></script>
	<script src="js/bootstrap-typeahead.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$("#login_frm").submit(function(){

		        
		        $("#msgbox").html("<div class=\"alert\">Please wait <img src=\"img/ajax-loader.gif\" /> </div>");

			    var JSONObject = new Object;
			    JSONObject.username = $('#login_id').val();
			    JSONObject.password = $('#password').val();
			    JSONstring = JSON.stringify(JSONObject);
			    
				this.timer = setTimeout(function () {
					$.ajax({
			          	url: 'check_signin',
			          	data: 'json='+ JSONstring,
			          	type: 'post',
			   			success: function(msg){
			   				var msg_json = eval('(' + msg +')');;
			   				//alert(msg);
			   				//alert(data);
			   				if(msg_json.result){
			   					$("#msgbox").html("<div class=\"alert alert-success\"><b>Successful Access</b></div>");
			   					document.location='search.jsp';
					   		}else{
				   				$("#msgbox").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
						   	}
						},
						error: function(msg){
							$("#msgbox").html("Error");
						}
					
					});
				}, 200);
				return false;
	 		});		
	
		});
	</script>

</body>
</html>
