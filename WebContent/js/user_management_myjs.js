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

var mygrid;
var Init_Users_Array;


function export_this_list(){
	
	mygrid.toExcel("http://myndrate.com/grid-excel/generate");
}

function start_Init_users_Array(){
	Init_Users_Array = new Array(mygrid.getRowsNum());
    for(var i=0; i < mygrid.getRowsNum(); i++){
    	Init_Users_Array[i] = new Array(mygrid.getColumnsNum()+1);
    	Init_Users_Array[i][mygrid.getColumnsNum()] = mygrid.getRowId(i);
    	for(var j=0; j < mygrid.getColumnsNum(); j++){
    		Init_Users_Array[i][j] =  mygrid.cells2(i,j).getValue() + "";
        }
    }
}


function saveRows_users(){
	
	
	$("#msgbox_save").html("<img src=\"img/ajax-loader.gif\">");

	var array_cnt = 0;
	var JSONObject = new Object;
	
    JSONObject.operations = new Array;

	for(var i=0;i<Init_Users_Array.length; i++){
		 var rowEx = mygrid.doesRowExist(Init_Users_Array[i][Init_Users_Array[0].length-1]);
		 if(!rowEx){
			//alert("Delete: " + Init_Users_Array[i][Init_Users_Array[0].length-1]);
		    JSONObject.operations[array_cnt] = new Object;
		    JSONObject.operations[array_cnt].code = "delete";
		    JSONObject.operations[array_cnt].table = "users";
		    JSONObject.operations[array_cnt].id = Init_Users_Array[i][Init_Users_Array[0].length-1];
		    JSONObject.operations[array_cnt].data = new Array;
		    JSONObject.operations[array_cnt].col = null;
		    array_cnt++;
	     }
	}

	 for(var i=0; i < mygrid.getRowsNum(); i++){
		var rowEx = false;
		var currentID = mygrid.getRowId(i);
		for(var j=0; j<Init_Users_Array.length; j++){
			if(currentID == Init_Users_Array[j][Init_Users_Array[0].length-1]) rowEx = true;
		}
		if(!rowEx){
			//alert("Insert: " + currentID);
		    JSONObject.operations[array_cnt] = new Object;
		    JSONObject.operations[array_cnt].code = "insert";
		    JSONObject.operations[array_cnt].table = "users";
		    JSONObject.operations[array_cnt].id = currentID;
		    JSONObject.operations[array_cnt].data = new Array;
		    
		    for(var k=0; k < mygrid.getColumnsNum()-1; k++){
		    	JSONObject.operations[array_cnt].data[k] = mygrid.cells(currentID,k).getValue();
		    }
		    JSONObject.operations[array_cnt].col = null;
		    
		    array_cnt++;
		    
		}
	 }

	for(var i=0;i<Init_Users_Array.length; i++){
		var currentID = Init_Users_Array[i][Init_Users_Array[0].length-1];
		var rowEx = mygrid.doesRowExist(currentID);
		if(rowEx){
			for(var j=0; j<mygrid.getColumnsNum(); j++){
				//alert(mygrid.cells(currentID,j).getValue());
				if(mygrid.cells(currentID,j).getValue() != Init_Users_Array[i][j]){
					//alert("Update: " + Init_Users_Array[i][Init_Users_Array[0].length-1] + mygrid.cells(currentID,j).getValue() + "!=" + Init_Users_Array[i][j]);
				    
					JSONObject.operations[array_cnt] = new Object;
				    JSONObject.operations[array_cnt].code = "update";
				    JSONObject.operations[array_cnt].table = "users";
				    JSONObject.operations[array_cnt].id = currentID;
				    JSONObject.operations[array_cnt].data = new Array;
				    
				    JSONObject.operations[array_cnt].col = j;

				    JSONObject.operations[array_cnt].data[0] = mygrid.cells(currentID,j).getValue();
			    	    
				    array_cnt++;
				}
			}
		}
	}

	if(array_cnt>0){
		//alert(JSON.stringify(JSONObject));
		$.ajax({
          	url: 'Manipulate_Grid_Users',
          	data: 'json=' + JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					$("#msgbox_save").html("<i class='icon-ok'></i>" + msg_json.msg );
   					mygrid.clearAll();
   					get_lista(13);
   					
   					//loadRows_scontrini(msg_json.data);
   				}else{
		   			$("#msgbox_save").html("<i class='icon-remove'></i>" + msg_json.msg );
			   	}
   				document.getElementById('load_img').style.display='none';
			},
			error: function(msg){
				$("#msgbox_save").html("<i class='icon-remove'></i>" + "Error");
			}
		
		});
	}else{
		$("#msgbox_save").html("");
	}
	
}

function get_lista(codeid){

	$("#tot_grid").html("");
	document.getElementById('gridbox_list').style.display='';
	document.getElementById('load_img').style.display='';
	
	var JSONObject = new Object;
    JSONObject.CodeID = codeid;    
    JSONstring = JSON.stringify(JSONObject);
    
	$.ajax({
      	url: 'list_of_data',
      	data: 'json='+ JSONstring,
      	type: 'post',
			success: function(msg){
				var msg_json = eval('(' + msg +')');
				if(msg_json.result){
					document.getElementById('con_msgbox').style.display='none';
					render_grid(codeid,msg_json.data);
					start_Init_users_Array();
					$("#tot_grid").html(mygrid.getRowsNum());
					
	   			}else{
	   				document.getElementById('con_msgbox').style.display='';
	   				document.getElementById('gridbox_list').style.display='none';
	   				$("#msgbox").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
	   			}
		},
		error: function(msg){
			document.getElementById('con_msgbox').style.display='';
			document.getElementById('gridbox_list').style.display='none';
			$("#msgbox").html("<div class=\"alert alert-error\"><b>ERROR</b></div>");
		}
	});
	document.getElementById('load_img').style.display='none';
	
}

function render_grid(codeid,data){

        mygrid = new dhtmlXGridObject('gridbox_list');
        mygrid.setImagePath("framework_dhtmlx/dhtmlxGrid/codebase/imgs/");
        
       
        mygrid.setHeader("<b>User</b>,<b>Name</b>,<b>Surname</b>,<b>Status</b>,<b>Profil</b>");
        mygrid.setInitWidths("100,110,110,*,100");
        mygrid.setColAlign("center,center,center,center,center");
        mygrid.setColTypes("ro,ed,ed,coro,coro");
        
        mygrid.getCombo(3).put("0", "Disable");
        mygrid.getCombo(3).put("1", "Enable"); 
       
        mygrid.getCombo(4).put("0", "Officer");
        mygrid.getCombo(4).put("1", "Operator"); 
        
        mygrid.setSkin("dhx_skyblue");
        mygrid.init();
        mygrid.parse(data,"json");
        
	
}





