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

    var barChart;
    
	var data = [
                { sales:19000, year:"Existing Contracts", color:"#00FF00", text:"Existing" },
                { sales:25000, year:"Expected Contracts", color:"#FE2E2E", text:"Expected" }
        ];
    function doOnLoad_calc() {
    	
    }
    
    function init_calc(){

    	
    	barChart =  new dhtmlXChart({
    		view:"pie3D",
    		container:"chart_calc",
    	    value:"#sales#",
    		//color:"#66cc33",
    		color: "#color#",
    		label: "<b>#sales#</b>-#text#",
    		gradient: "sales",
                    width:30,
                    tooltip: "#year#",
                    xAxis:{
    			title:"Monthly Earnings",
    			template:"#year#"
    		},
    		yAxis:{
                            start:0,
                            end:22000,
                            step:5000,
                            template:"{obj}"
    	}
     
    	});
    	get_value_from_server();
    }
    
    var max0;
    var max1;
    var timeID;
    
    
    function load_chart(){
    	data[0].sales = data[0].sales + 1;
    	data[1].sales = data[1].sales + 1;
    	
    	barChart.parse(data,"json");
    	if(max0 == data[0].sales && max1 == data[1].sales){
    		clearInterval(timeID);
    		//alert("OK");
    	}
    	if(max0 == data[0].sales) data[0].sales = data[0].sales - 1;
    	if(max1 == data[1].sales) data[1].sales = data[1].sales - 1;
    }
   
    
	function get_value_from_server(){
		document.getElementById("chart_calc_msg").style.display='none';
		//alert("1");
		var JSONObject = new Object;
	    JSONObject.top = $("#top").val();
	    JSONObject.media = $("#media").val();
	    
		$.ajax({
          	url: 'get_calc',
          	data: 'json=' + JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){

   					
   					$("#media").val(msg_json.data.media);
   					$("#top").val(msg_json.data.top);
   					
   					$("#kathara_top").html( "<font color='green'>" + Math.round(msg_json.data.top/1.23) + " NET</font>");
   					$("#kathara_media").html( "<font color='green'>" + Math.round(msg_json.data.media/1.23) + " NET</font>");

   					var dif = msg_json.data.top -  msg_json.data.lordo;

   					if(dif<=0)dif=0;
   					
   					//setInterval('load_chart();', 200);
   					max0 = msg_json.data.cnt;
   					max1 = Math.round(dif/msg_json.data.media);
   					
   					
   					if(max1>msg_json.data.liberi){
   						//alert(max1 + " " + msg_json.data.liberi);
   						document.getElementById("chart_calc_msg").style.display='';
   						var log_html = "<b>Attention!</b> The free seats (" + msg_json.data.liberi + ") at Parking are not sufficient to satisfy the desired revenue. <u>Solution:</u>";
   						

   						log_html += "<br><u>1.</u> Configure Indicates lower limit of rents <b><u>"  + Math.ceil(dif/msg_json.data.liberi) + "</u></b> &euro;" ;
   						log_html += "<br><u>2.</u> Configure Maximum desired revenues <b><u>"  + Math.floor((msg_json.data.liberi*msg_json.data.media) + msg_json.data.lordo) + "</u></b> &euro;" ;

   						$("#chart_calc_msg").html(log_html);
   						
   					}
   					data[0].sales = -1;
   					data[1].sales = -1;
   					timeID = setInterval('load_chart();', 2);
   					//barChart.parse(data,"json");
		   		}else{
		   			$("#chart_calc").html("Could not get the data from the Server!");
			   	}
   				
			},
			error: function(msg){
				$("#chart_calc").html("Error!");
			}
		});
		//$("#chart_calc").html("");
	}

