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

    
	var myCalendar;
	var chart_real3;
	var chart_real2;
	
	var data_real = [
                { sales:260, year:"1", data_pag:"assas" },
                { sales:21, year:"1" },
                { sales:5, year:"1" },
                { sales:4445, year:"1" },
                { sales:234, year:"2" }
        ];
	function get_and_render(d_from,d_to){
		//alert("1");
		var JSONObject = new Object;
	    JSONObject.d_from = d_from;
	    JSONObject.d_to = d_to;
	    //alert(JSON.stringify(JSONObject));
	    
		$.ajax({
          	url: 'get_calc_real',
          	data: 'json=' + JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					//alert(msg);
   					$("#totale_3").html("Overal Total:  <font color='red'><b>" + msg_json.data.tot3 + "</b>&euro; Gross</font>, <font color='green'><b>" + Math.round(msg_json.data.tot3/1.23) + "</b>&euro; NET</font><br>Total Receipts: <font color='blue'><b>" + (msg_json.data.cnt_tot3 - msg_json.data.cnt_g ) + "</b></font> (+ " + msg_json.data.cnt_g + " Free)"  );
   					$("#totale_2").html("Total Permanent:  <font color='red'><b>" + msg_json.data.tot2 + "</b>&euro; Gross</font>, <font color='green'><b>" + Math.round(msg_json.data.tot2/1.23) + "</b>&euro; NET</font><br>Total Receipts Permanent: <font color='blue'><b>" + msg_json.data.cnt_tot2 + "</b></font><br><br>");
   					$("#totale_1").html("Total Temporary:  <font color='red'><b>" + msg_json.data.tot1 + "</b>&euro; Gross</font>, <font color='green'><b>" + Math.round(msg_json.data.tot1/1.23) + "</b>&euro; NET</font><br>Total Receipts Temporary: <font color='blue'><b>" + (msg_json.data.cnt_tot1 - msg_json.data.cnt_g ) + "</b></font><br>Total Free Temporary: <font color='blue'><b>" + msg_json.data.cnt_g + "</b></font>");
   					
   					chart_real3.clearAll();
   					chart_real3.parse(msg_json.data.list, "json");
   					chart_real2.clearAll();
   					chart_real2.parse(msg_json.data.list, "json");
		   		}else{
		   			$("#chart_calc2").html("Could not get the data from the Server!");
			   	}
   				
			},
			error: function(msg){
				$("#chart_calc2").html("Error!");
			}
		});
	}
	
	function pre_get_and_render(){
		//alert(byId("date_from").value);
		get_and_render(byId("date_from").value,byId("date_to").value);
	}
	
	function doOnLoad_real_render(){
		//alert(byId("date_from").value + "-" + byId("date_to").value);
		chart_real2 = new dhtmlXChart({
	        view: "line",
	        container: "chart_calc2_2",
	        value: "#sales2#",
	        label: "#sales2#",
	        tooltip: {
	            template: "#sales2#&euro; - #data_pag# (#cnt2#)"
	        },
	        line: {
	            color: "#FE2E2E",
	            width: 2
	        },
	        item: {
	            borderColor: "#FE2E2E"
	        },
	        preset: "plot",
	        yAxis: {},
	        xAxis: {
	            template: "#year#"
	        }
	    });
		
		chart_real3 = new dhtmlXChart({
	        view: "line",
	        container: "chart_calc2_3",
	        value: "#sales3#",
	        label: "#sales3#",
	        tooltip: {
	            template: "#sales3#&euro; - #data_pag# (#cnt3#)"
	        },
	        line: {
	            color: "#0000FF",
	            width: 2
	        },
	        item: {
	            borderColor: "#0000FF"
	        },
	        preset: "plot",
	        yAxis: {},
	        xAxis: {
	            template: "#year#"
	        }
	    });
		
		chart_real2.addSeries({
	        value: "#sales1#",
	        label: "#sales1#",
	        tooltip: {
	            template: "#sales1#&euro; - #data_pag# (#cnt1#)"
	        },
	        item: {
	            borderColor: "#66cc00"
	        },
	        line: {
	            color: "#66cc00",
	            width: 2
	        }
	    });
	    //chart.parse(data_real, "json");
	    pre_get_and_render();
	    
	    
	}
	
	function doOnLoad_real_get_valori(){
		var JSONObject = new Object;
	    JSONObject.d_from = "";
	    JSONObject.d_to = "";
	    //alert(JSON.stringify(JSONObject));
	    
		$.ajax({
          	url: 'get_calc_real',
          	data: 'json=' + JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					//alert(msg);
   					byId("date_from").value = msg_json.data.d_from;
   					byId("date_to").value = msg_json.data.d_to;
   					get_and_render(byId("date_from").value,byId("date_to").value);
		   		}else{
		   			$("#chart_calc2_3").html("Could not get the data from the Server!");
			   	}
   				
			},
			error: function(msg){
				$("#chart_calc2_3").html("Could not get the data from the Server(ERROR)!");
			}
		});
		//pre_get_and_render();
	}
	
	function doOnLoad_real() {

	    myCalendar = new dhtmlXCalendarObject(["date_from", "date_to"]);
	    myCalendar.hideTime();
	    myCalendar.setDateFormat("%d/%m/%Y");
	    // init values
	    var t = new Date();
	    //byId("date_from").value = myCalendar.getFormatedDate(null, t);
	    t.setDate(t.getDate() + 10);
	    //byId("date_to").value = myCalendar.getFormatedDate(null, t);
	    doOnLoad_real_get_valori();
	    doOnLoad_real_render();
	}
	 
	function setSens(id, k) {
	    // update range
	    if (k == "min") {
	        //myCalendar.setSensitiveRange(byId(id).value, null);
	    } else {
	        //myCalendar.setSensitiveRange(null, byId(id).value);
	    }
	}
	function byId(id) {
	    return document.getElementById(id);
	}
	
    