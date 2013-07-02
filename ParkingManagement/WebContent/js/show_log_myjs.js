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

	function get_session_from_server(sid){
    	var JSONObject = new Object;
	    JSONObject.sessionid = sid.replace("+","%2B");;
	   // alert(sid);
	    JSONObject.type = '0';
	    
    	$("#div_" + sid).html("<img src=\"img/ajax-loader.gif\">");
		$.ajax({
          	url: 'get_log',
          	data: 'json=' + JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					
   					var output_html = "";
   					for(var i=0; i<msg_json.data.data.length;i++){
   						output_html += " <b>" + msg_json.data.data[i].datatime + "</b>: " + msg_json.data.data[i].log + "<br>";
   						
   					}
   					
   					document.getElementById("div_" + sid).innerHTML = output_html;

		   		}else{
		   			$("#div_" + sid).html("Could not get the data from the Server!");
			   	}
   				
			},
			error: function(msg){
				$("#div_" + sid).html("Could not get the data from the Server (Error)!");
			}
		});
	}

	function get_session(sid){
		if(document.getElementById("div_" + sid).style.display==''){
			document.getElementById("div_" + sid).style.display='none';
			
		}else{
			document.getElementById("div_" + sid).style.display='';
			get_session_from_server(sid);
		}
		
	}
	
    function get_all_sessions(type_admin){
    	
    	//alert(type_admin);
    	
    	var JSONObject = new Object;
	    JSONObject.sessionid = 'all';
	    JSONObject.type = type_admin;
	    
    	$("#log_output").html("<img src=\"img/ajax-loader.gif\">");
		$.ajax({
          	url: 'get_log',
          	data: 'json=' + JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					//get_all_sessions_render(msg_json.data);
   					//$("#log_output").html(msg_json.data);
   					var output_html = "";
   					for(var i=0; i<msg_json.data.data.length;i++){
   						output_html += "<a href=\"javascript:void(null);\" onclick=\"get_session('" + msg_json.data.data[i].sessionid + "');\" > <b><span style='font-size:17px;'>" + msg_json.data.data[i].username + "</span></b> - " + msg_json.data.data[i].enter_time + "</a><br>";
   						output_html += "<div style=\"display: none\" id=\"div_" +  msg_json.data.data[i].sessionid + "\" class='Zs' ><img src=\"img/ajax-loader.gif\"></div>";
   					}
   					
   					$("#log_output").html(output_html);
   					//$("#log_output").html("<p style='font-size:12px;'>" + output_html + "<p>");
		   		}else{
		   			$("#log_output").html("Could not get the data from the Server!");
			   	}
   				
			},
			error: function(msg){
				$("#log_output").html("Could not get the data from the Server (Error)!");
			}
		});
		//$("#chart2").html("");
    	
    }

    