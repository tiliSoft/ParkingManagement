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
String ActiveUsername = (String)session.getAttribute("username");

boolean  isAdmin;
try{
	isAdmin = (Boolean)session.getAttribute("admin");
}catch(NullPointerException e){
	isAdmin = false;
}
  
if(ActiveUsername==null){
	%>
<jsp:forward page="login.jsp" />
<%
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="icon" href="img/favicon.png" />
<title>Lists</title>

<link rel="STYLESHEET" type="text/css"
	href="framework_dhtmlx/dhtmlxWindows/codebase/dhtmlxwindows.css" />
<link rel="STYLESHEET" type="text/css"
	href="framework_dhtmlx/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css" />

<link rel="STYLESHEET" type="text/css"
	href="framework_dhtmlx/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
<link rel="STYLESHEET" type="text/css"
	href="framework_dhtmlx/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
<link rel="STYLESHEET" type="text/css"
	href="framework_dhtmlx/dhtmlxGrid/codebase/dhtmlxgrid.css">
<link rel="stylesheet" type="text/css"
	href="framework_dhtmlx/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css">
body {
	padding-bottom: 10px;
}

/* Custom container */
.container-narrow {
	margin: 0 auto;
	max-width: 700px;
}

.container-narrow>hr {
	margin: 30px 0;
}

/* Main marketing message and sign up button */
.jumbotron {
	margin: 60px 0;
	text-align: center;
}

.jumbotron h1 {
	font-size: 72px;
	line-height: 1;
}

.jumbotron .btn {
	font-size: 21px;
	padding: 14px 24px;
}

/* Supporting marketing content */
.marketing {
	margin: 60px 0;
}

.marketing p+h4 {
	margin-top: 28px;
}

.footer_div {
	position: fixed;
	bottom: 0%;
}

.chat-bubble {
	position: fixed;
	left: 85%;
	top: 90px;
	opacity: 0.85;
	filter: alpha(opacity =   0.5);
	font-size: 12px;
	font-weight: 800;
	line-height: 1.3em;
	margin: 6px auto;
	padding: 6px;
	text-align: center;
	width: 100px;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0, rgb(90,
		150, 40) ), color-stop(0.28, rgb(160, 215, 60) ),
		color-stop(1, rgb(165, 255, 69) ) );
	background: -moz-linear-gradient(center top, rgb(90, 150, 40) 0%,
		rgb(160, 215, 60) 28%, rgb(165, 255, 69) 100% );
	border: 1px solid #38691a;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: rgba(160, 215, 60, 0.7) 2px 4px 5px;
	-moz-box-shadow: rgba(160, 215, 60, 0.7) 2px 4px 5px; /* FF 3.5+ */
	box-shadow: rgba(160, 215, 60, 0.7) 2px 4px 5px;
}

.chat-bubble-glare {
	position: absolute;
	top: 0px;
	left: 4px;
	height: 6px;
	width: 100px;
	padding: 6px 0;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	background: -moz-linear-gradient(top, rgba(255, 255, 255, 0.9),
		rgba(255, 255, 255, 0) );
	background: -webkit-gradient(linear, 0% 0%, 0% 95%, from(rgba(255, 255, 255, 0.9)
		), to(rgba(255, 255, 255, 0) ) );
}

.chat-bubble-arrow {
	border-color: transparent #a5ff45 transparent transparent;
	border-style: solid;
	border-width: 6px;
	height: 0;
	width: 0;
	position: absolute;
	bottom: -1px;
	left: -3px;
	-webkit-transform: rotate(70deg);
	-moz-transform: rotate(70deg);
	transform: rotate(70deg);
}

.chat-bubble-arrow-border {
	border-color: transparent #38691a transparent transparent;
	border-style: solid;
	border-width: 6px;
	height: 0;
	width: 0;
	position: absolute;
	bottom: -3px;
	left: -5px;
	-webkit-transform: rotate(70deg);
	-moz-transform: rotate(70deg);
}
</style>
<link href="css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="ico/favicon.png">
</head>

<body onload="first_function('<%= request.getParameter("cid") %>');">

	<div align="center" class="navbar-inner">
		<img src="img/LogoParkingEfi2.png"></img>
	</div>

	<div class="chat-bubble">
		<div class="chat-bubble-glare"></div>
		<b><span style="color: white;"><u>Temporarily</u></span></b><br>
		<div id="contenuto_chat">
			<img src="img/ajax-loader.gif">
		</div>

		<div class="chat-bubble-arrow-border"></div>
		<div class="chat-bubble-arrow"></div>
	</div>
	<div class="container-narrow">

	<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<div class="btn-group">
					<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
						href="#"> <i class="icon-list icon-white"></i> Options <span
						class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="search.jsp"><i class="icon-search"></i>&nbsp;Search</a></li>
						<li><a onclick="new_contract_extra(1);"
							href="javascript:void(null);"><i class="icon-file"></i>&nbsp;<b>Temporary</b></a></li>
						<li><a onclick="new_contract_extra(0);"
							href="javascript:void(null);"><i class="icon-file"></i>&nbsp;<b>New</b>
								Registration</a></li>
						<li><a href="list.jsp"><i class="icon-align-justify"></i>&nbsp;Lists</a></li>
						<% if(isAdmin){ %>
						<li class="divider"></li>
						<li><a href="admin.jsp"><i class="icon-wrench"></i>&nbsp;Statistics</a></li>
						<li><a href="income.jsp"><i class="icon-wrench"></i>&nbsp;Income</a></li>
						<li><a href="calc.jsp"><i class="icon-wrench"></i>&nbsp;Calculation</a></li>
						<li><a href="show_log.jsp"><i class="icon-wrench"></i>&nbsp;Live</a></li>
						<li><a href="user_management.jsp"><i class="icon-wrench"></i>&nbsp;Users</a></li>
						<% } %>
					</ul>
				</div>
			</ul>
			<form name="logout_frm" id="logout_frm" action="" method="post">
				<h3 class="muted">
					<button class="btn btn-danger" type="submit">
						<i class="icon-off icon-white"></i>&nbsp;Exit
					</button>
					<p>
						<small>(User: <b>
								<% out.print(ActiveUsername); %>
						</b>)
						</small>
					</p>
					<p>
				</h3>
			</form>
		</div>

		<div align="center">
			<select onchange="select_changed();" id="select_list"
				name="select_list" class="input-xxlarge">
				<option selected="selected" value='0'>Choose a list...</option>
				<option value='1'>List of free position</option>
				<option value='2'>Customer lists ground floor and basement (Motor Bike)</option>
				<option value='3'>Customer lists ground floor and basement (Car)</option>
				<option value='4'>Customer List 1st Floor</option>
				<option value='5'>Customer List 2st Floor</option>
				<option value='6'>Customer List 3st Floor</option>
				<option value='7'>Customer List 4st Floor</option>
				<option value='8'>Customer List 5st Floor</option>
				<option value='9'>Customer List 6st Floor</option>
				<option value='10'>Customer List 7st Floor</option>
				<option value='11'>List of all customers</option>
				<option value='12'>List of TEMPORARY Customer</option>
				<option value='14'>Customer lists owe more than 30 days</option>
				<option value='15'>Customer lists owe more than 45 days</option>
				<option value='16'>Customer lists owe more than 60 days</option>
			</select>
			<div>
				&nbsp;&nbsp;<img style='display: none' id="load_img"
					src="img/ajax-loader.gif" />&nbsp;&nbsp;
			</div>
		</div>
		<div class="row-fluid">
			<div id="con_msgbox" class="span10 offset1">
				<div id="msgbox"></div>
			</div>
			<div id="con_gridbox_list" class="span10 offset1">
				<div id="gridbox_list"
					style="display: none; width: 100%; height: 350px; background-color: white;"></div>
				<div id="gridbox_list_export" style='display: none' align="center">
					<a href="javascript:void(null);" onclick="export_this_list();"><img
						src="img/excel.gif"></img>Download Excel</a>&nbsp;
				</div>
				<div id="tot_grid" align="left"></div>
			</div>

		</div>

		<jsp:include page="body.jsp" />
	</div>

	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script src="js/bootbox.js"></script>
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
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_dhxcalendar.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_calendar.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_ra_str.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_acheck.js"></script>

	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxWindows/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxWindows/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
	<script type="text/javascript"
		src="framework_dhtmlx/dhtmlxGrid/codebase/ext/dhtmlxgrid_export.js"></script>

	<script src="js/list_myjs.js"></script>
	<script src="js/myjs.js"></script>

	<script type="text/javascript">
    $(document).ready(function(){
		$("#logout_frm").submit(function(){
			bootbox.alert("<div id='msgbox_logout'>Please wait <img src=\"img/ajax-loader.gif\" /></div>",function(){document.location='login.jsp';});
			
		    var JSONObject = new Object;
		    JSONObject.username = '<% out.print(ActiveUsername); %>';
		    JSONstring = JSON.stringify(JSONObject);

			this.timer = setTimeout(function () {
				$.ajax({
		          	url: 'check_signout',
		          	data: 'json=' + JSONstring,
		          	type: 'post',
		   			success: function(msg){
		   				var msg_json = eval('(' + msg +')');;
						
		   				if(msg_json.result){
			   				$("#msgbox_logout").html(msg_json.msg);
				   		}else{
				   			$("#msgbox_logout").html(msg_json.msg);
					   	}
					},
					error: function(msg){
						$("#msgbox_logout").html("Error");
					}
				
				});
			}, 200);
			return false;
 		});		

	});
	
    </script>


</body>
</html>
