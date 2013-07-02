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

function export_this_list(){
	
	mygrid.toExcel("http://myndrate.com/grid-excel/generate");
}

function select_changed(){
	if($('#select_list').val()==0)return;
	document.getElementById('gridbox_list').style.display='';
	document.getElementById('gridbox_list_export').style.display='';
	document.getElementById('load_img').style.display='';
	
	get_lista($('#select_list').val());
	document.getElementById('load_img').style.display='none';
	
	
	
	
}

function get_lista(codeid){
	$("#tot_grid").html("");
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
					$("#tot_grid").html("Total: <b>" + mygrid.getRowsNum() + "</b>");
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
}


function doOnCellSelect(rowId, cellInd) {
    //alert(rowId);
    get_contract_from_server(rowId);
    return true;
}


function render_grid(codeid,data){

        mygrid = new dhtmlXGridObject('gridbox_list');
        mygrid.setImagePath("framework_dhtmlx/dhtmlxGrid/codebase/imgs/");
        
        if(codeid==1){
            mygrid.setHeader("<b>Floor</b>,<b>Post</b>");
            mygrid.setInitWidths("120,*");
            mygrid.setColAlign("center,center");
            mygrid.setColTypes("ro,ro");
        }else if(codeid==2 || codeid==3){
            mygrid.setHeader("<b>Price</b>,<b>Model</b>,<b>Plate</b>,<b>Name</b>");
            mygrid.setInitWidths("120,120,120,*");
            mygrid.setColAlign("center,center,center,center");
            mygrid.setColTypes("ro,ro,ro,ro");
            mygrid.attachEvent("onRowSelect", doOnCellSelect);
        }else{
            mygrid.setHeader("<b>Price</b>,<b>Model</b>,<b>Plate</b>,<b>Name</b>,<b>Post</b>");
            mygrid.setInitWidths("100,110,110,*,100");
            mygrid.setColAlign("center,center,center,center,center");
            mygrid.setColTypes("ro,ro,ro,ro,ro");
            mygrid.attachEvent("onRowSelect", doOnCellSelect);
        }
       

        mygrid.setSkin("dhx_skyblue");
        mygrid.init();
        mygrid.parse(data,"json");
        
	
}




