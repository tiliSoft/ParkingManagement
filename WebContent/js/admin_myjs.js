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

    function do_posti(){
    	$("#chart1").html("<img src=\"img/ajax-loader.gif\">");
    	
    	
		$.ajax({
          	url: 'admin_get_post',
          	data: 'json=',
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					do_posti_render(msg_json.data);
		   		}else{
		   			$("#chart1").html("Could not get the data from the Server!");
			   	}
   				
			},
			error: function(msg){
				$("#chart1").html("Error!");
			}
		});
		$("#chart1").html("");
    }
    
    function do_posti_render(jsonData){
    	var chart =  new dhtmlXChart({
    		view: "pie",
    		container: "chart1",
    		value: "#valore#",
    	    label: "#parameter# - <b>#valore#</b>"
    	});
    	chart.parse(jsonData,"json");
    	do_entrate();
    }

    function do_entrate(){
    	$("#chart2").html("<img src=\"img/ajax-loader.gif\">");
		$.ajax({
          	url: 'admin_get_entrate',
          	data: 'json=',
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					do_entrate_render(msg_json.data);
		   		}else{
		   			$("#chart2").html("Could not get the data from the Server!");
			   	}
   				
			},
			error: function(msg){
				$("#chart2").html("Error!");
			}
		});
		$("#chart2").html("");
    }
    
    function do_entrate_render(jsonData) {

    	var barChart =  new dhtmlXChart({
    		view:"bar",
    		container:"chart2",
    	    value:"#valore#",
    		color:"#66cc33",
    		label: "#valore# &euro;",
    		gradient: "valore",
                    width:30,
                    tooltip: "#parameter#",
                    xAxis:{
    			title:"Monthly Earnings",
    			template:"#parameter#"
    		},
    		yAxis:{
                            start:0,
                            end:22000,
                            step:5000,
                            template:"{obj}"
    	}
     
    	});
    	barChart.parse(jsonData,"json");
    }
    