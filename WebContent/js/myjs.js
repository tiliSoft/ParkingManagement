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

var g_ContractID = -1;
    var g_ClientID = -1;
    var g_CarID;
    var g_RelazioneID;
    var winID;
    var dhxWins;
    var mygrid;
    var mygrid_cars;
    var extra_id = 0;
    
    function load_chat(){
		$.ajax({
          	url: 'get_chat',
          	data: 'json=',
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				//alert(msg);
   				if(msg_json.result){
   					var html_new="";

   					for(var i = 0; i < msg_json.list.length; i++){
   						html_new = html_new + "<a onclick=\"get_contract_from_server(" + msg_json.list[i].id + ");\" href=\"javascript:void(null);\" >" +  msg_json.list[i].targa + "</a> <br>";
   					}
   					
   					$("#contenuto_chat").html(html_new);
   					
   	   			}else{
   	   				if(msg_json.logined){
   	   					$("#contenuto_chat").html("Error");
   	   				}else{
   	   					window.location="./login.jsp";
   	   				}
		   			
			   	}
			},
			error: function(msg){
				$("#contenuto_chat").html("Error");
			}
		
		});
    } 
    
    function new_contract_extra(codeid){
    	get_free_posti('iu_posto','');
    	extra_id = codeid;
    	
    	//alert(extra_id);
    	
    	if(extra_id==0){
    		document.getElementById("AA_In_Contract").style.display='none';
    		$('#iu_head_nome').html("Customer Information");
    		$('#iu_nome').val("");
    		$('#iu_tele').val("");
    		$('#iu_cel').val("");
    		$('#iu_afm').val("");
    		$('#iu_doy').val("");
    		$('#iu_address').val("");
    		$('#iu_prof').val("0");
    		$('#iu_caa').val("0");
    		$('#iu_prezzo').val("0");
    		
    		$('#New_Contract_Modal').modal('show');
    	}else{
    		document.getElementById("AA_In_Contract").style.display='';
    		
    		$('#iu_head_nome').html("TEMPORARY");
    		$('#iu_nome').val("TEMPORARY");
    		$('#iu_tele').val("000");
    		$('#iu_cel').val("000");
    		$('#iu_afm').val("000");
    		$('#iu_doy').val("000");
    		$('#iu_address').val("TEMPORARY");
    		$('#iu_prof').val("0");
    		$('#iu_modello').val("TEMPORARY");
    		$('#iu_caa').val("0");
    		$('#iu_prezzo').val("0");

    		
    		$('#New_Contract_Modal').modal('show');
    		
    	}
    }
    
    function first_function(cid){

    	g_CarID = new Array();
    	g_RelazioneID = new Array();
    	windowInit();
    	load_chat();
    	try{
    		document.getElementById("query").focus();
    	}catch(ee){
    		
    	}
    	
    	setInterval('load_chat();', 3000);

    	var cid2 = cid.replace(" ","").replace("'","").replace("'","");
    	var cid3 = cid2;
    	
    	if(!isNaN(cid3)){
    		get_contract_from_server(cid3);
    	}

    	
    }
    
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    function windowInit(){
    	winID = "winID";
   	 	dhxWins = new dhtmlXWindows();
   	    dhxWins.setImagePath("framework_dhtmlx/dhtmlxWindows/codebase/imgs/");
   	 	var win = dhxWins.createWindow(winID, 44, 4, 900, 562);
   	 	
   	 	dhxWins.enableAutoViewport(true);
   	 	dhxWins.window(winID).bringToTop();
   	 	dhxWins.window(winID).progressOn();
   	 	//dhxWins.window("mywin").center();
   	 	dhxWins.window(winID).centerOnScreen();
   	 	dhxWins.window(winID).setText("Contract");
   	 	dhxWins.window(winID).appendObject("Contract_Modal");
   	 	
   	 	dhxWins.window(winID).progressOff();
   	 	//dhxWins.window(winID).button("minmax2").hide();
   	 	//dhxWins.window(winID).stick();
   	 	dhxWins.window(winID).button("close").attachEvent("onClick", windowClose);
   	 	dhxWins.window(winID).hide();
   	 	dhxWins.window(winID).setModal(false);
    }
    
    function windowLoad(){
    	dhxWins.window(winID).show();
    	dhxWins.window(winID).setModal(true);
    	
    }
    
    function windowClose(){
    	Clean_Modal_S();
    	dhxWins.window(winID).hide();
    	dhxWins.window(winID).setModal(false);
    }
    
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    
    function Controla_Campi_New(){
    	if($('#iu_nome').val().length<3){
    		alert("The name must contain at least 3 characters!");
    		return false;
    	}else if($('#iu_tele').val().length<3 && extra_id==0){
    		alert("The phone must be at least 3 characters!");
    		return false;
    	}else if($('#iu_address').val().length<3 && extra_id==0){
    		alert("The address must contain at least 3 characters!");
    		return false;		
    	}else if($('#iu_targa').val().length<3){
    		alert("The plate must be at least 3 characters!");
    		return false;
    	}else if(isNaN($('#iu_prezzo').val())){
    		alert("The price of rent is not valid!");
    		return false;
    	}
    	return true;
    }
    
    function Delete_Contract(){
    	if($('#why_del').val().length<1 && $('#siu_extra').val()==0){
    		alert("Please fill in the cause of deletion");
    		return;
    	}
    
    	
    	if(g_ContractID < 0){
    		$('#Dialog_Modal_Body').html("<div class=\"alert alert-error\"><b>Error: g_ContractID = -1 </b></div>" +
    				"<button onclick='$(\"#Dialog_Modal\").modal(\"hide\");' class='btn btn-small btn-primary' type='button'>OK</button>");
    		return;
    	}
    	
		var JSONObject = new Object;
	    JSONObject.ContractID = g_ContractID;
	    JSONObject.why = $('#why_del').val();
	    $('#why_del').val("");
	    document.getElementById('Dialog_Modal_Body_ok').style.display='';
	    document.getElementById('Dialog_Modal_Body').style.display='none';
		$.ajax({
          	url: 'Delete_Contract',
          	data: 'json='+ JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					$('#Dialog_Modal_Body_ok').html("<div class=\"alert alert-success\"><b>The deletion of the contract executed with success!</b></div>" +
							                    "<button onclick='$(\"#Dialog_Modal\").modal(\"hide\");' class='btn btn-small btn-primary' type='button'>OK</button>");
   	   			}else{
   	   				$('#Dialog_Modal_Body_ok').html("<div class=\"alert alert-error\"><b>" + msg_json.msg +"</b></div>" +
   	   											"<button onclick='$(\"#Dialog_Modal\").modal(\"hide\");' class='btn btn-small btn-primary' type='button'>OK</button>");
				}
			},
			error: function(msg){
				$('#Dialog_Modal_Body_ok').html("<div class=\"alert alert-error\"><b>Error</b></div>" +
						"<button onclick='$(\"#Dialog_Modal\").modal(\"hide\");' class='btn btn-small btn-primary' type='button'>OK</button>");
			}
		});
		windowClose();
    	try{
    		document.getElementById("query").focus();
    	}catch(ee){
    		
    	}
		//Clean_Modal_S();
    	//$('#Dialog_Modal').modal('hide');
    }
	
    function Non_Delete_Contract(){
    	$('#why_del').val("");
    	$('#Dialog_Modal').modal('hide');
    	try{
    		document.getElementById("query").focus();
    	}catch(ee){
    		
    	}
    	//$('#Contract_Modal').modal('show');
    }
	
    function Delete_Contract_Question(){
    	
    	if(($('#siu_prezzo').val().length<1 || $('#siu_prezzo').val()==0 || $('#siu_prezzo').val()=="0" || $('#siu_prezzo_span').html()=="0" || $('#siu_prezzo_span').html()==0) && $('#siu_extra').val()==1){
    		alert("Make sure that the rent is right (0 euro)");
    		
    	}
    	
    	document.getElementById('Dialog_Modal_Body').style.display='';
    	document.getElementById('Dialog_Modal_Body_ok').style.display='none';
    	$('#Dialog_Modal').modal('show');
    }
    
	function correct_number(e,code){
		$('#' + code).val($('#' + code).val().replace(",","."));
		return;
	}
	
    function update_metadata_on_DB(tabela,id,collona,nuovo){
    	//alert("Tabella:"  + tabela + " ID:" + id + " Collona:" + collona + " New:" + nuovo);
    	//return;
    	
		var JSONObject = new Object;
	    JSONObject.Tabella = tabela;
	    JSONObject.Collona = collona;
	    JSONObject.New = nuovo;
	    JSONObject.ID = id;
	    
	    JSONstring = JSON.stringify(JSONObject);
	    
		$.ajax({
          	url: 'Update_Contract',
          	data: 'json='+ JSONstring,
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					//return true;
   	   			}else{
		   			$("#msgbox_modal_save").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
				}
			},
			error: function(msg){
				document.getElementById('Contract_Modal_body').style.display='none';
				$("#msgbox_modal_save").html("Error");
			}
		});
    }

	function key_press_(e,code){
		//alert(code + " - " + code.substring(0,9));
		//alert(code.substring(0,9) == 'siu_first');
		//return;
		
		if(code.substring(0,9) == 'siu_first' || code=='siu_posto' ||  code=='siu_prof'){
			
			if(code.substring(0,9)=='siu_first'){
				update_metadata_on_DB("RelazioneID",g_RelazioneID[code.substring(9)],"siu_first",$('#' + code).val());
				if($('#' + code ).val()==0){
   					$('#siu_first' + code.substring(9) + '_span').html("OXI");
   	   	   		}else{
   	   	   			$('#siu_first' + code.substring(9) + '_span').html("NAI");
   	   	   	   	}
			}else if(code=='siu_prof'){
				update_metadata_on_DB("ClientID",g_ClientID,"siu_prof",$('#' + code).val());
				
				if($('#' + code ).val()==1){
					$('#siu_prof_span').html("1");
	   			}else{
	   				$('#siu_prof_span').html("0");
	   	   		}
			}else{
				update_metadata_on_DB("ContractID",g_ContractID,"siu_posto",$('#' + code).val());
				$('#' + code + '_span').html($('#' + code).val());
			}
			document.getElementById(code + '_span').style.display='';
			document.getElementById(code + '_pencil').style.display='';
			document.getElementById(code ).style.display='none';
			
		}else{
			
			if(e.keyCode == 13 ||  window.event.which == 13){
				if($('#' + code + '_span').html() != $('#' + code).val()){
					
					if(code=='siu_nome'){
						update_metadata_on_DB("ClientID",g_ClientID,"siu_nome",$('#' + code).val());
					}else if(code=='siu_tele'){
						update_metadata_on_DB("ClientID",g_ClientID,"siu_tele",$('#' + code).val());
					}else if(code=='siu_cel'){
						update_metadata_on_DB("ClientID",g_ClientID,"siu_cel",$('#' + code).val());
					}else if(code=='siu_afm'){
						update_metadata_on_DB("ClientID",g_ClientID,"siu_afm",$('#' + code).val());
					}else if(code=='siu_doy'){
						update_metadata_on_DB("ClientID",g_ClientID,"siu_doy",$('#' + code).val());
					}else if(code=='siu_address'){
						update_metadata_on_DB("ClientID",g_ClientID,"siu_address",$('#' + code).val());
					}else if(code=='siu_caa'){
						if(isNaN($('#' + code).val())){
							alert("The value is not valid !");
							return;
						}
						if($('#' + code).val().length<1){
							alert($('#' + code).val());
							$('#' + code).val(0);
						}
						update_metadata_on_DB("ContractID",g_ContractID,"siu_caa",$('#' + code).val());
					}else if(code=='siu_posto'){
						update_metadata_on_DB("ContractID",g_ContractID,"siu_posto",$('#' + code).val());
					}else if(code=='siu_prezzo'){
						if(isNaN($('#' + code).val())){
							alert("The value is not valid !");
							return;
						}
						if($('#' + code).val().length<1){
							alert($('#' + code).val());
							$('#' + code).val(0.00);
						}
						update_metadata_on_DB("ContractID",g_ContractID,"siu_prezzo",$('#' + code).val());
					
					}else if(code=='siu_data_ricevutta'){
						update_metadata_on_DB("ContractID",g_ContractID,"siu_data_ricevutta",$('#' + code).val());
					}

					if(code=='siu_nome'){
						$('#siu_head_nome').html($('#' + code).val());
					}else if(code=='siu_targa'){
						$('#siu_head_targa').html($('#' + code).val());
					}else if(code=='siu_posto'){
						$('#siu_head_posto').html($('#' + code).val() + " (" + $('#siu_prezzo').val() + " &euro;)");
					}
					$('#' + code + '_span').html($('#' + code).val());
				}
				
				document.getElementById(code + '_span').style.display='';
				document.getElementById(code + '_pencil').style.display='';
				document.getElementById(code ).style.display='none';

			}else{
				if(code=='siu_prezzo' ||  code=='siu_caa' ){
					correct_number(e,code);
				}
			}
		}

	}
	
	function go_to_edit(code){
		document.getElementById(code + '_span').style.display='none';
		document.getElementById(code + '_pencil').style.display='none';
		document.getElementById(code ).style.display='';
		document.getElementById(code).focus();
	}
	
	function go_to_view(code){
	
		document.getElementById(code + '_span').style.display='';
		document.getElementById(code + '_pencil').style.display='';
		document.getElementById(code).style.display='none';
	}
		
	function get_contract_from_server(coid){
		$('html, body').animate({ scrollTop: 0 }, 'fast');
		windowLoad();
		try{
			document.getElementById('load_img').style.display='';
		}catch(err){
			
		}
   		
   		document.getElementById('Contract_Modal_body').style.display='';
   		
		//Clean_Modal_S();
		var JSONObject = new Object;
	    JSONObject.ContractID = coid;
	    JSONstring = JSON.stringify(JSONObject);
	    
		$.ajax({
          	url: 'Get_Contract',
          	data: 'json='+ JSONstring,
          	type: 'post',
   			success: function(msg){
   				
   				var msg_json = eval('(' + msg +')');
   			
   				if(msg_json.result){
   					
   					document.getElementById('sb_delete').style.display='';	
   					$('#siu_head_nome').html(msg_json.contract.iu_nome);
   					$('#siu_nome').val(msg_json.contract.iu_nome);
   					$('#siu_nome_span').html(msg_json.contract.iu_nome);
   					
   				    $('#siu_tele').val(msg_json.contract.iu_tele);
   				 	$('#siu_tele_span').html(msg_json.contract.iu_tele);
   				 
   				    $('#siu_cel').val(msg_json.contract.iu_cel);
   					$('#siu_cel_span').html(msg_json.contract.iu_cel);
   				    
   				    $('#siu_afm').val(msg_json.contract.iu_afm);
   				    $('#siu_afm_span').html(msg_json.contract.iu_afm);
   				    
   				    $('#siu_doy').val(msg_json.contract.iu_doy);
   				    $('#siu_doy_span').html(msg_json.contract.iu_doy);
   				    
   				    $('#siu_address').val(msg_json.contract.iu_address);
   				    $('#siu_address_span').html(msg_json.contract.iu_address);
   				    
   					$('#siu_prof').val(msg_json.contract.iu_prof);
   					if(msg_json.contract.iu_prof==1){
   						$('#siu_prof_span').html("1");
   					
   	   				}else{
   	   					$('#siu_prof_span').html("0");
   	   				    
   	   	   			}

   					$('#siu_prezzo').val(msg_json.contract.iu_prezzo);
   					$('#siu_prezzo_span').html(msg_json.contract.iu_prezzo);

   					$('#siu_data_ricevutta').val(msg_json.contract.iu_data_ricevutta);
   					$('#siu_data_ricevutta_span').html(msg_json.contract.iu_data_ricevutta);
   					
   					$('#siu_caa').val(msg_json.contract.iu_caa);
   					$('#siu_caa_span').html(msg_json.contract.iu_caa);
   					
   					
   					$('#siu_posto_span').html(msg_json.contract.iu_posto);
   					
   					$('#siu_head_posto').html(msg_json.contract.iu_posto + " (" + msg_json.contract.iu_prezzo + " &euro;)");
   					
   					
   					get_free_posti('siu_posto',msg_json.contract.iu_posto);
   						
   					$('#siu_posto').val(msg_json.contract.iu_posto);
   					
   					g_ContractID = msg_json.contract.ContractID;
   					g_ClientID = msg_json.contract.ClientID;
   					
   					doOnLoad_cars(msg_json.contract.cars);
   					
   					if(msg_json.contract.iu_extra==1){
   						$('#siu_extra').val(1);
   						var html_star_end = "";
   						
   						html_star_end = "<h5>Date of Entry: <font color='green'>" + msg_json.contract.iu_start_date + "</font></h5>";
   						if( msg_json.contract.iu_end_date=="null" || msg_json.contract.iu_end_date==null){
   							document.getElementById('sb_delete').style.display='';
   						}else{
   							html_star_end += "<h5>Date Exit: <font color='red'>" + msg_json.contract.iu_end_date + "</font></h5>";
   							document.getElementById('sb_delete').style.display='none';
   						}
   						$('#msgbox_start_date').html(html_star_end);
   						
   						document.getElementById('id_aa_show').style.display='';
   						document.getElementById('con_gridbox').style.display='none';
   						document.getElementById('con_gridbox_extra').style.display='';
   						
   					}else{
   						document.getElementById('id_aa_show').style.display='none';
   						document.getElementById('con_gridbox_extra').style.display='none';
   						document.getElementById('con_gridbox').style.display='';
   						
   						$('#siu_extra').val(0);
   					}
   					doOnLoad(msg_json.contract.scontrini,msg_json.contract.iu_prof);
   					
   					dhxWins.window(winID).setText("Contract " + g_ContractID);


   	   			}else{
   	   			    document.getElementById('Contract_Modal_body').style.display='none';
		   			$("#msgbox_modal_save").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
			   	}
			},
			error: function(msg){
				document.getElementById('Contract_Modal_body').style.display='none';
				$("#msgbox_modal_save").html("Error");
			}
		
		});
		try{
			document.getElementById('load_img').style.display='none';
		}catch(err){
			
		}

	}
	
	function get_free_posti(code,posto){
		var JSONObject = new Object;
	    JSONObject.posto = posto;
	    
		$.ajax({
          	url: 'get_free_post',
          	data: 'json='+ JSON.stringify(JSONObject),
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				//alert(msg);
   				if(msg_json.result){
   					var html_new="";

   					for(var i = 0; i < msg_json.posti.length; i++){
   						html_new = html_new + "<option value='" +  msg_json.posti[i] + "' ";
   						if(msg_json.posti[i]==posto){
   							html_new = html_new + " selected=\"selected\"";
   						}
   						html_new = html_new + ">" + msg_json.posti[i] + "</option>";
   					}
   					$("#" + code).html(html_new);
   					
   	   			}else{
		   			$("#msgbox_modal_save").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
			   	}
			},
			error: function(msg){
				$("#msgbox_modal_save").html("Error");
			}
		
		});
	}
	
	
	function Save_New_Contract(){
		if(Controla_Campi_New()==false)return;
		
		$("#msgbox_modal").html("<div class=\"alert\">Please wait <img src=\"img/ajax-loader.gif\" /> </div>");
			
		var JSONObject = new Object;
		//alert(extra_id);
		JSONObject.iu_extra = "" + extra_id;
	    JSONObject.iu_nome = $('#iu_nome').val();
	    JSONObject.iu_tele = $('#iu_tele').val();
	    JSONObject.iu_cel = $('#iu_cel').val();
	    JSONObject.iu_afm = $('#iu_afm').val();
	    JSONObject.iu_doy = $('#iu_doy').val();
	    JSONObject.iu_address = $('#iu_address').val();
	    JSONObject.iu_prof = $('#iu_prof').val();

	    JSONObject.iu_targa = $('#iu_targa').val();
	    JSONObject.iu_typo_amak = $('#iu_typo_amak').val();
	    JSONObject.iu_modello = $('#iu_modello').val();

	    JSONObject.iu_posto = $('#iu_posto').val();
	    JSONObject.iu_prezzo = $('#iu_prezzo').val();
	    JSONObject.iu_data_ricevutta = $('#iu_data_ricevutta').val();
	    JSONObject.iu_caa = $('#iu_caa').val();
	    
	    JSONstring = JSON.stringify(JSONObject);
	    //alert(JSONstring);
	    
	    //JSONObject.hobby = new Array;
	 
	    //for(var i=0; i<3; i++)
	   // {
	   //     JSONObject.hobby[i] = new Object;
		//JSONObject.hobby[i].hobbyName = p['hobby'][i].value;
		//JSONObject.hobby[i].isHobby = p['hobby'][i].checked;
	   // }
	 	
	    
		$.ajax({
          	url: 'New_Contract',
          	data: 'json='+ JSONstring,
          	type: 'post',
   			success: function(msg){
   				
   				var msg_json = eval('(' + msg +')');
   				//alert(msg);
   				if(msg_json.result){
   					$("#msgbox_modal").html("<div class=\"alert alert-success\"><b>The store successfully!</b></div>");
   					//bootbox.alert("Η αποθήκευση ολοκληρώθηκε με επιτυχία!");
   					document.getElementById('b_close').style.display='';
   					document.getElementById('b_cancel').style.display='none';
   					document.getElementById('b_save').style.display='none';
   					//Clean_Modal();
   	   			}else{
		   			$("#msgbox_modal").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
			   	}
			},
			error: function(msg){
				$("#msgbox_modal").html("Error");
			}
		
		});
	    
	}
	
	// ##################################################

    
 
    function doOnLoad_cars(jsonCars) {
    	
    	mygrid_cars = new dhtmlXGridObject('gridbox_cars');
        //mygrid.setDateFormat(mask);
        mygrid_cars.setImagePath("framework_dhtmlx/dhtmlxGrid/codebase/imgs/");
        mygrid_cars.setHeader("<b>PLATE</b>,<b>MODEL</b>,<b>VEHICLE</b>,<b>MAIN</b>");
        mygrid_cars.setInitWidths("120,*,120,120");
        mygrid_cars.setColAlign("left,left,left,center");
        mygrid_cars.setColTypes("ed,ed,coro,coro");
       // mygrid.setColSorting("int,str,str,int,str,str,na");
        mygrid_cars.getCombo(3).put(0, "SUBSIDARY");
        mygrid_cars.getCombo(3).put(1, "MAIN");
        
        mygrid_cars.getCombo(2).put(1, "CAR");
        mygrid_cars.getCombo(2).put(2, "MOTOR BIKE");
        mygrid_cars.getCombo(2).put(3, "BIKE");
        mygrid_cars.attachEvent("onEditCell", doOnCellEdit_mygrid_cars);
        //mygrid_cars.enableLightMouseNavigation(true);
        mygrid_cars.setSkin("dhx_skyblue");
        mygrid_cars.init();
        loadRows_cars(jsonCars);
        //mygrid_cars.loadXML("../common/grid_big_18_styles_skins.xml");
    }
    
    function doOnLoad(jsonScontrini,prof) {
    	//doOnLoad_cars();
    	
        mygrid = new dhtmlXGridObject('gridbox');
        //mygrid.setDateFormat(mask);
        mygrid.setImagePath("framework_dhtmlx/dhtmlxGrid/codebase/imgs/");
        
        if(prof==1){
        	 mygrid.setHeader("<b>DATE</b>,<b>1</b>,<b>START DATE</b>,<b>END DATE</b>,<b>&euro;</b>,<b>Α/Α</b>,<b>PAYMENT</b>,<b>OFFICER</b>,<b>STATUS</b>");
             
        }else{
        	 mygrid.setHeader("<b>DATE</b>,<b>0</b>,<b>START DATE</b>,<b>END DATE</b>,<b>&euro;</b>,<b>Α/Α</b>,<b>PAYMENT</b>,<b>OFFICER</b>,<b>STATUS</b>");
        }
       
        mygrid.setInitWidths("120,60,120,120,60,60,80,*,120");
        mygrid.setColAlign("center,center,center,center,left,center,center,left,center");
        mygrid.setColTypes("dhxCalendar,ed,dhxCalendar,dhxCalendar,price,ed,coro,ro,img");
       // mygrid.setColSorting("int,str,str,int,str,str,na");
        mygrid.attachEvent("onEditCell", doOnCellEdit_mygrid);
        mygrid.getCombo(6).put(0, "CASH");
        mygrid.getCombo(6).put(1, "BANK");
        //mygrid.enableLightMouseNavigation(true);
        mygrid.setSkin("dhx_skyblue");
        mygrid.init();
        loadRows_scontrini(jsonScontrini);
        //mygrid.loadXML("../common/grid_big_18_styles_skins.xml");
    }
    
    function doOnCellEdit_mygrid(stage, rowId, cellInd) {
        if (stage == 2) {
        	document.getElementById('save_scontrini_save').style.background='#FFFC00';
        	mygrid.setRowTextStyle(rowId, "background-color: red");
        }
        return true;
    }
    
    function doOnCellEdit_mygrid_cars(stage, rowId, cellInd) {
        if (stage == 2) {
        	document.getElementById('save_cars_save').style.background='#FFFC00';
        	mygrid_cars.setRowTextStyle(rowId, "background-color: red");
        }
        return true;
    }
    
    function addRow_scontrini(){
    	document.getElementById('save_scontrini_save').style.background='#FFFC00';
    	
        var newId = (new Date()).valueOf();
        var newId = (new Date()).valueOf();
        var today = new Date();
        
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var mm_next = today.getMonth()+2;
        var yyyy = today.getFullYear();
        if(dd<10){dd='0'+dd} 
        if(mm<10){mm='0'+mm} 
        if(mm_next<10){mm_next='0'+mm_next} 
        
        today = dd+'/'+mm+'/'+yyyy;
        
        today_next = dd+'/'+mm_next +'/'+yyyy;
        //alert(today + "--" + today_next);
        
        
        mygrid.addRow(newId,",0," + today + "," + today_next + ",0,0,0,,img/bianco.png",mygrid.getRowsNum());
        mygrid.setRowTextStyle(newId, "background-color: red");
        mygrid.selectRow(mygrid.getRowIndex(newId),false,false,true);
    }
    
    function removeRow_scontrini(){
    	document.getElementById('save_scontrini_save').style.background='#FFFC00';
        var selId = mygrid.getSelectedId();
        mygrid.deleteRow(selId);
    }
    
    function controla_Rows_Valid_scontrini(){
    	
	   	 for(var i=0; i < mygrid.getRowsNum(); i++){
	   		
	   		// mygrid.cellById(mygrid.getRowId(i), 7).setValue(url);
	   		 mygrid.cells2(i,1).setValue(mygrid.cells2(i,1).getValue().replace(",","."));
	   		 mygrid.cells2(i,5).setValue(mygrid.cells2(i,5).getValue().replace(",","."));
	   		 mygrid.cells2(i,4).setValue(mygrid.cells2(i,4).getValue().replace(",","."));
	   		 
	   		if(mygrid.cells2(i,4).getValue().length<1){
	   			mygrid.cells2(i,4).setValue("0");
	   		}
	   		 
	   		 if(isNaN(mygrid.cells2(i,4).getValue())){
	   			 alert("Enter the correct value of the proof !");
	   			 return false;
	   		 }
	   		 
	   		 if(mygrid.cells2(i,1).getValue().length<1 || isNaN(mygrid.cells2(i,1).getValue())){
	   			 alert("Enter number in the \"A/F\" !");
	   			 return false;
	   		 }
	   		 if(mygrid.cells2(i,5).getValue().length<1 || isNaN(mygrid.cells2(i,5).getValue())){
	   			 alert("Enter number in the \"Α/Α\" !");
	   			 return false;
	   		 } 
	   		if(mygrid.cells2(i,2).getValue().length<3){
	   			 alert("Enter the start date!");
	   			 return false;
	   		 }
	   		if(mygrid.cells2(i,3).getValue().length<3){
	   			 alert("Enter the end date!");
	   			 return false;
	   		}
	   	 }
	   	return true;
    }
   
    function saveRows_scontrini(){

    	if(!controla_Rows_Valid_scontrini())return;
    	document.getElementById('save_scontrini_save').style.background='#FFFFFF';
    	
    	$("#msgbox_scontrini").html("<img src=\"img/ajax-loader.gif\">");

    	var array_cnt = 0;
    	var JSONObject = new Object;
		
	    JSONObject.operations = new Array;
	    JSONObject.contractid = g_ContractID;

		for(var i=0;i<Init_Scontrini_Array.length; i++){
			 var rowEx = mygrid.doesRowExist(Init_Scontrini_Array[i][Init_Scontrini_Array[0].length-1]);
			 if(!rowEx){
				//alert("Delete: " + Init_Scontrini_Array[i][Init_Scontrini_Array[0].length-1]);
			    JSONObject.operations[array_cnt] = new Object;
			    JSONObject.operations[array_cnt].code = "delete";
			    JSONObject.operations[array_cnt].table = "scontrini";
			    JSONObject.operations[array_cnt].id = Init_Scontrini_Array[i][Init_Scontrini_Array[0].length-1];
			    JSONObject.operations[array_cnt].data = new Array;
			    JSONObject.operations[array_cnt].col = null;
			    array_cnt++;
		     }
		}

		 for(var i=0; i < mygrid.getRowsNum(); i++){
			var rowEx = false;
			var currentID = mygrid.getRowId(i);
			for(var j=0; j<Init_Scontrini_Array.length; j++){
				if(currentID == Init_Scontrini_Array[j][Init_Scontrini_Array[0].length-1]) rowEx = true;
			}
			if(!rowEx){
				//alert("Insert: " + currentID);
			    JSONObject.operations[array_cnt] = new Object;
			    JSONObject.operations[array_cnt].code = "insert";
			    JSONObject.operations[array_cnt].table = "scontrini";
			    JSONObject.operations[array_cnt].id = currentID;
			    JSONObject.operations[array_cnt].data = new Array;
			    
			    for(var k=0; k < mygrid.getColumnsNum()-1; k++){
			    	JSONObject.operations[array_cnt].data[k] = mygrid.cells(currentID,k).getValue();
			    }
			    JSONObject.operations[array_cnt].col = null;
			    
			    array_cnt++;
			    
			}
		 }

		for(var i=0;i<Init_Scontrini_Array.length; i++){
			var currentID = Init_Scontrini_Array[i][Init_Scontrini_Array[0].length-1];
			var rowEx = mygrid.doesRowExist(currentID);
			if(rowEx){
				for(var j=0; j<mygrid.getColumnsNum()-1; j++){
					//alert(mygrid.cells(currentID,j).getValue());
					if(mygrid.cells(currentID,j).getValue() != Init_Scontrini_Array[i][j]){
						//alert("Update: " + Init_Scontrini_Array[i][Init_Scontrini_Array[0].length-1] + mygrid.cells(currentID,j).getValue() + "!=" + Init_Scontrini_Array[i][j]);
					    
						JSONObject.operations[array_cnt] = new Object;
					    JSONObject.operations[array_cnt].code = "update";
					    JSONObject.operations[array_cnt].table = "scontrini";
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
	          	url: 'Manipulate_Grid',
	          	data: 'json=' + JSON.stringify(JSONObject),
	          	type: 'post',
	   			success: function(msg){
	   				var msg_json = eval('(' + msg +')');
	   				if(msg_json.result){
	   					$("#msgbox_scontrini").html("<i class='icon-ok'></i>" + msg_json.msg );
	   					mygrid.clearAll();
	   					loadRows_scontrini(msg_json.data);
	   				}else{
			   			$("#msgbox_scontrini").html("<i class='icon-remove'></i>" + msg_json.msg );
				   	}
	   				document.getElementById('load_img').style.display='none';
				},
				error: function(msg){
					$("#msgbox_scontrini").html("<i class='icon-remove'></i>" + "Error");
				}
			
			});
		}else{
			$("#msgbox_scontrini").html("");
		}
		//start_Init_Scontrini_Array();	
    }
    
    var Init_Cars_Array;
    var Init_Scontrini_Array;
    
    function loadRows_cars(jsonCars){

        mygrid_cars.parse(jsonCars,"json");

        for(var i=0; i < mygrid_cars.getRowsNum(); i++){
        	if(mygrid_cars.cells2(i,3).getValue() == 1){
        		$("#siu_head_targa").html(mygrid_cars.cells2(i,0).getValue());
        	}
        }
        
        start_Init_Cars_Array();
    }
    
    function loadRows_scontrini(jsonScontrini){

        mygrid.parse(jsonScontrini,"json");        
        start_Init_Scontrini_Array();

    }

    function start_Init_Cars_Array(){
        Init_Cars_Array = new Array(mygrid_cars.getRowsNum());
        for(var i=0; i < mygrid_cars.getRowsNum(); i++){
        	Init_Cars_Array[i] = new Array(mygrid_cars.getColumnsNum()+1);
        	Init_Cars_Array[i][mygrid_cars.getColumnsNum()] = mygrid_cars.getRowId(i);
        	for(var j=0; j < mygrid_cars.getColumnsNum(); j++){
        		Init_Cars_Array[i][j] =  mygrid_cars.cells2(i,j).getValue() + "";
            }
        }
    }
    
    function start_Init_Scontrini_Array(){
    	Init_Scontrini_Array = new Array(mygrid.getRowsNum());
        for(var i=0; i < mygrid.getRowsNum(); i++){
        	Init_Scontrini_Array[i] = new Array(mygrid.getColumnsNum());
        	Init_Scontrini_Array[i][mygrid.getColumnsNum()-1] = mygrid.getRowId(i);
        	for(var j=0; j < mygrid.getColumnsNum()-1; j++){
        		Init_Scontrini_Array[i][j] =  mygrid.cells2(i,j).getValue() + "";
            }
        }
        colora_stato_semaforo();
    }
    
    function colora_stato_semaforo(){
        for(var i=0; i < mygrid.getRowsNum(); i++){
        	var url = get_semafor_url(mygrid.cells2(i,0).getValue(),mygrid.cells2(i,2).getValue(),mygrid.cells2(i,3).getValue(),mygrid.cells2(i,4).getValue());
        	mygrid.cellById(mygrid.getRowId(i), 8).setValue(url);
        }
    }

    function controla_Rows_Valid_car(){
    	 for(var i=0; i < mygrid_cars.getRowsNum(); i++){
    		 if(mygrid_cars.cells2(i,0).getValue().length<3){
    			 alert("The plate must be at least 3 characters!");
    			 return false;
    		 }    		  
    	 }
    	return true;
    }
    
    function saveRows_cars(){
    	
    	if(!controla_Rows_Valid_car())return;
    	document.getElementById('save_cars_save').style.background='#FFFFFF';
    	$("#msgbox_cars").html("<img src=\"img/ajax-loader.gif\">");
    	
    	//Update
		//Delete
		//Insert
    	var array_cnt = 0;
    	var JSONObject = new Object;
		
	    JSONObject.operations = new Array;
	    JSONObject.contractid = g_ContractID;
	 
		//alert(JSON.stringify(JSONObject));
		
		
		for(var i=0;i<Init_Cars_Array.length; i++){
			 var rowEx = mygrid_cars.doesRowExist(Init_Cars_Array[i][Init_Cars_Array[0].length-1]);
			 if(!rowEx){
				//alert("Delete: " + Init_Cars_Array[i][Init_Cars_Array[0].length-1]);
			    JSONObject.operations[array_cnt] = new Object;
			    JSONObject.operations[array_cnt].code = "delete";
			    JSONObject.operations[array_cnt].table = "car";
			    JSONObject.operations[array_cnt].id = Init_Cars_Array[i][Init_Cars_Array[0].length-1];
			    JSONObject.operations[array_cnt].data = new Array;
			    JSONObject.operations[array_cnt].col = null;
			    array_cnt++;
		     }
		}

		 for(var i=0; i < mygrid_cars.getRowsNum(); i++){
			var rowEx = false;
			var currentID = mygrid_cars.getRowId(i);
			for(var j=0; j<Init_Cars_Array.length; j++){
				if(currentID == Init_Cars_Array[j][Init_Cars_Array[0].length-1]) rowEx = true;
			}
			if(!rowEx){
				//alert("Insert: " + currentID);
			    JSONObject.operations[array_cnt] = new Object;
			    JSONObject.operations[array_cnt].code = "insert";
			    JSONObject.operations[array_cnt].table = "car";
			    JSONObject.operations[array_cnt].id = currentID;
			    JSONObject.operations[array_cnt].data = new Array;
			    
			    for(var k=0; k < mygrid_cars.getColumnsNum(); k++){
			    	JSONObject.operations[array_cnt].data[k] = mygrid_cars.cells(currentID,k).getValue();
			    }
			    JSONObject.operations[array_cnt].col = null;
			    
			    array_cnt++;
			    
			}
		 }

		for(var i=0;i<Init_Cars_Array.length; i++){
			var currentID = Init_Cars_Array[i][Init_Cars_Array[0].length-1];
			var rowEx = mygrid_cars.doesRowExist(currentID);
			if(rowEx){
				for(var j=0; j<mygrid_cars.getColumnsNum(); j++){
					//alert(mygrid_cars.cells(currentID,j).getValue());
					if(mygrid_cars.cells(currentID,j).getValue() != Init_Cars_Array[i][j]){
						//alert("Update: " + Init_Cars_Array[i][Init_Cars_Array[0].length-1] + mygrid_cars.cells(currentID,j).getValue() + "!=" + Init_Cars_Array[i][j]);
					    
						JSONObject.operations[array_cnt] = new Object;
					    JSONObject.operations[array_cnt].code = "update";
					    JSONObject.operations[array_cnt].table = "car";
					    JSONObject.operations[array_cnt].id = currentID;
					    JSONObject.operations[array_cnt].data = new Array;
					    
					    JSONObject.operations[array_cnt].col = j;

					    JSONObject.operations[array_cnt].data[0] = mygrid_cars.cells(currentID,j).getValue();
				    	    
					    array_cnt++;
					}
				}
			}
		}

		if(array_cnt>0){
			//alert(JSON.stringify(JSONObject));
			$.ajax({
	          	url: 'Manipulate_Grid',
	          	data: 'json=' + JSON.stringify(JSONObject),
	          	type: 'post',
	   			success: function(msg){
	   				var msg_json = eval('(' + msg +')');
	   				if(msg_json.result){
	   					$("#msgbox_cars").html("<i class='icon-ok'></i>" + msg_json.msg );
	   					mygrid_cars.clearAll();
	   					loadRows_cars(msg_json.data);
	   				}else{
			   			$("#msgbox_cars").html("<i class='icon-remove'></i>" + msg_json.msg );
				   	}
	   				document.getElementById('load_img').style.display='none';
				},
				error: function(msg){
					$("#msgbox_cars").html("<i class='icon-remove'></i>" + "Error");
				}
			
			});
		}else{
			$("#msgbox_cars").html("");
		}
		//start_Init_Cars_Array();	 
		
    }
    
    
    
    function add_New_Row_cars(){
    	document.getElementById('save_cars_save').style.background='#FFFC00';

        var newId = (new Date()).valueOf();
        //alert("OK");
        mygrid_cars.setRowTextStyle(newId, "background-color: red");
        mygrid_cars.addRow(newId,",,1,0",mygrid_cars.getRowsNum());
    }
    
    function removeRow_cars(){
    	document.getElementById('save_cars_save').style.background='#FFFC00';

        var selId = mygrid_cars.getSelectedId();
        mygrid_cars.deleteRow(selId);
    }
    
    function prova_get(){
    	alert( getAllRowsAsJson());
     }
    //#############################################
   
    function Clean_Modal_S(){
    	document.getElementById('save_cars_save').style.background='#FFFFFF';
    	document.getElementById('save_scontrini_save').style.background='#FFFFFF';
    	
    	mygrid_cars.clearAll();
    	mygrid.clearAll();
    	$("#msgbox_cars").html("");
    	$("#msgbox_scontrini").html("");
		$("#msgbox_modal_save").html("");
		dhxWins.window(winID).setText("Contract");
		document.getElementById('sb_delete').style.display='none';	
		
	 	$('#siu_nome').val("");
	 	$('#siu_nome_span').html("");
	 	go_to_view('siu_nome');
	 	
	    $('#siu_tele').val("");
	    $('#siu_tele_span').html("");
	    go_to_view('siu_tele');
	    
	    $('#siu_cel').val("");
	    $('#siu_cel_span').html("");
	    go_to_view('siu_cel');
	    
	    $('#siu_afm').val("");
	    $('#siu_afm_span').html("");
	    go_to_view('siu_afm');
	    
	    $('#siu_doy').val("");
	    $('#siu_doy_span').html("");
	    go_to_view('siu_doy');
	    
	    $('#siu_address').val("");
	    $('#siu_address_span').html("");
	    go_to_view('siu_address');
	    
	    $('#siu_prof').val(0);
	    $('#siu_prof_span').html("");
	    go_to_view('siu_prof');


	    $('#siu_posto').val("");
	    $('#siu_posto_span').html("");
	    go_to_view('siu_posto');
	    
	    $('#siu_prezzo').val("");
	    $('#siu_prezzo_span').html("");
	    go_to_view('siu_prezzo');
	    
	    $('#siu_data_ricevutta').val("");
	    $('#siu_data_ricevutta_span').html("");
	    go_to_view('siu_data_ricevutta');
	    
	    $('#siu_caa').val("");
	    $('#siu_caa_span').html("");
	    go_to_view('siu_caa');

	    $('#siu_head_nome').html("- NAME -");
	    $('#siu_head_targa').html("- PLATE -");
	    $('#siu_head_posto').html("- POST -");
	   
	    $('#selected_contract').val(-1);
	    g_ContractID = -1;
	    g_ClientID = -1;
	    

    }
    
    function Clean_Modal(){

		document.getElementById('b_close').style.display='none';
		document.getElementById('b_cancel').style.display='';
		document.getElementById('b_save').style.display='';
		$("#msgbox_modal").html("");
	 	$('#iu_nome').val("");
	    $('#iu_tele').val("");
	    $('#iu_cel').val("");
	    $('#iu_afm').val("");
	    $('#iu_doy').val("");
	    $('#iu_address').val("");
	    $('#iu_prof').val(0);

	    $('#iu_targa').val("");
	    $('#iu_typo_amak').val(1);
	    $('#iu_modello').val("");

	    $('#iu_posto').val("");
	    $('#iu_prezzo').val(0);
	    $('#iu_data_ricevutta').val("");
	    $('#iu_caa').val("");
   
    }

	function lista_completta(){

		
    	document.getElementById('load_img').style.display='';
    	//$("#msgbox").html("");
		//alert("Key UP!");
		//$("#msgbox").html("<div class=\"alert\">Παρακαλώ περιμένετε <img src=\"img/ajax-loader.gif\" /> </div>");
		
		var JSONObject = new Object;
		JSONObject.query = '';
		JSONstring = JSON.stringify(JSONObject);
		//alert(JSONstring);
		$.ajax({
          	url: 'autocomplete',
          	data: 'json='+ JSONstring,
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					//$("#msgbox").html(msg_json.list.length);
   					var outputHTML_List="<ul>";
   					for(var i = 0; i < msg_json.list.length; i++){
   						outputHTML_List = outputHTML_List + "<a href='javascript:void(null);' onclick='get_contract_from_server(" + msg_json.list[i].contractid + ");' ><p class=\"text-info\">";
   						outputHTML_List = outputHTML_List + "<img src='img/next.png' />&nbsp;&nbsp;" + msg_json.list[i].text;
   						outputHTML_List = outputHTML_List + "</p></a>";
   	   	   			}
   					outputHTML_List = outputHTML_List + "</ul>";
   					$("#msgbox").html(outputHTML_List);
		   		}else{
	   				$("#msgbox").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
			   	}
   				document.getElementById('load_img').style.display='none';
			},
			error: function(msg){
				$("#msgbox").html("Error");
				document.getElementById('load_img').style.display='none';
			}
		
		});
    }
    
	function get_semafor_url(pagamento,start,end,prezzo){
		var pagato=false;

		if(pagamento!='' && pagamento!=' '){
			pagato=true;
			var a0 = parseInt(pagamento.substring(0,2),10);
			var b0 = parseInt(pagamento.substring(3,5),10);
			var c0 = parseInt(pagamento.substring(6,10),10);
			var date0 = new Date(c0, b0, a0);
		}else{
			pagato=false
		}

		var a1 = parseInt(start.substring(0,2),10);
		var b1 = parseInt(start.substring(3,5),10);
		var c1 = parseInt(start.substring(6,10),10);

		var a2 = parseInt(end.substring(0,2),10);
		var b2 = parseInt(end.substring(3,5),10);
		var c2 = parseInt(end.substring(6,10),10);
		
	    var date1 = new Date(c1, b1, a1);
	    var date2 = new Date(c2, b2, a2);
	    //alert(b2);
	    if(date1 > date2){
			return "img/rosso.png";
		}else if(pagato){
			//alert(prezzo);
			if(prezzo>0){
				return "img/verde.png";
			}else{
				return "img/rosso.png";
			}
			
		}else if(prezzo>0){
			return "img/arancione.png";
		}else{
			 var today = new Date();
			 var at = parseInt(today.getDate(),10);
			 var bt = parseInt(today.getMonth()+1,10);
			 var ct = parseInt(today.getFullYear(),10);
				
			 var today_ok = new Date(ct,bt,at);

			 if(today_ok > date2){
				  return "img/rosso.png";
			  }else{
				  return "img/bianco.png";
			  }
		}
		
		return "img/remove.png";
		
	}
	
	function call_autocomplete(){
    	if( $('#query').val().length < 1 ){
    		$("#msgbox").html("");
			return;
		}
		
    	document.getElementById('load_img').style.display='';
    	//$("#msgbox").html("");
		//alert("Key UP!");
		//$("#msgbox").html("<div class=\"alert\">Παρακαλώ περιμένετε <img src=\"img/ajax-loader.gif\" /> </div>");
		
		var JSONObject = new Object;
		JSONObject.query = $('#query').val();
		JSONstring = JSON.stringify(JSONObject);
		
		$.ajax({
          	url: 'autocomplete',
          	data: 'json='+ JSONstring,
          	type: 'post',
   			success: function(msg){
   				var msg_json = eval('(' + msg +')');
   				if(msg_json.result){
   					//$("#msgbox").html(msg_json.list.length);
   					var outputHTML_List="<ul>";
   					for(var i = 0; i < msg_json.list.length; i++){
   						outputHTML_List = outputHTML_List + "<a href='javascript:void(null);' onclick='get_contract_from_server(" + msg_json.list[i].contractid + ");' ><p class=\"text-info\">";
   						outputHTML_List = outputHTML_List + "<img src='img/next.png' />&nbsp;&nbsp;" + msg_json.list[i].text;
   						outputHTML_List = outputHTML_List + "</p></a>";
   	   	   			}
   					outputHTML_List = outputHTML_List + "</ul>";
   					$("#msgbox").html(outputHTML_List);
		   		}else{
	   				$("#msgbox").html("<div class=\"alert alert-error\"><b>" + msg_json.msg + "</b></div>");
			   	}
   				document.getElementById('load_img').style.display='none';
			},
			error: function(msg){
				$("#msgbox").html("Error");
				document.getElementById('load_img').style.display='none';
			}
		
		});
    }
	$(document).ready(function(){
		$("#form-search").submit(function(){
			return false;
 		});		

	});
	