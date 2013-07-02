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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import RawConnection.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/Get_Contract")
public class Get_Contract extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String dataFromClient = request.getParameter("data");
		request.setCharacterEncoding("UTF-8");
		
		
		JSONParser parser = new JSONParser();
		String jsonClient_String = request.getParameter("json");
		Object jsonClient_Object = null;
		try{
			jsonClient_Object = parser.parse(jsonClient_String);
		}catch(ParseException e){
			System.out.println(e.getMessage());
		}
		JSONObject jsonClient = (JSONObject) jsonClient_Object;
		ContractObj CO = new ContractObj();
	


		ContractManipolation CM = new ContractManipolation();
		JSONObject jsonServer = new JSONObject();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		String ActiveUser = (String) session.getAttribute("username");
		if(ActiveUser==null){
			jsonServer.put("result",false);
			jsonServer.put("msg","You are not logged!");
			out.print(jsonServer);
	        out.flush();
	        out.close();
	        return;
		}
		
		int ContractID = 0;;
		try{
			Long ContractID_Long = (Long) jsonClient.get("ContractID");
			String ContractID_String = ContractID_Long.toString();
			ContractID = Integer.parseInt(ContractID_String);
		}catch(NumberFormatException e){
			jsonServer.put("result",false);
			jsonServer.put("msg","Input Error!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}catch(ClassCastException e){
			ContractID = Integer.parseInt((String) jsonClient.get("ContractID"));
		}catch(Exception e){
			ContractID = 1;
			System.out.println(e.getMessage());
		}
		

		if(!CM.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		

		if(!CM.write_to_LOG_html("Showing contract " + "<a onclick=\"get_contract_from_server("+ ContractID + ");\" href=\"javascript:void(null);\">" + ContractID + "</a>", session.getId() )){
			jsonServer.put("result",false);
			jsonServer.put("msg","Unable to write the LOG (Servlet: Get_Contract)!");
			out.print(jsonServer);
	        out.flush();
	        CM.close_Connection();
	        return;
		}
		
		CO = CM.Get_Contract(ContractID);
		
		if(CO!=null && CM.close_Connection()){
			jsonServer.put("result",true);
			jsonServer.put("msg","");
			
			JSONObject jsonContract = new JSONObject();
			jsonContract.put("iu_extra",CO.iu_extra);
			jsonContract.put("iu_nome",CO.iu_nome);
			jsonContract.put("iu_tele",CO.iu_tele);
			jsonContract.put("iu_cel",CO.iu_cel);
			jsonContract.put("iu_afm",CO.iu_afm);
			jsonContract.put("iu_doy",CO.iu_doy);
			jsonContract.put("iu_address",CO.iu_address);
			jsonContract.put("iu_prof",CO.iu_prof);
			
			jsonContract.put("iu_prezzo",CO.iu_prezzo);
			jsonContract.put("iu_data_ricevutta",CO.iu_data_ricevutta);
			jsonContract.put("iu_start_date",CO.iu_start_date);
			jsonContract.put("iu_end_date",CO.iu_end_date);
			jsonContract.put("iu_caa",CO.iu_caa);
			
			//$$$ ARRAY CARS
			
			JSONObject jsonCars = new JSONObject();
			JSONArray list_of_cars = new JSONArray();
			
			for(int i=0;i<CO.CC.size();i++){
				
				ContractCars CC = (ContractCars)CO.CC.get(i);
				JSONObject jsonCar = new JSONObject();
				JSONArray jsonCarInfo =  new JSONArray();

				jsonCar.put("id", CC.RelazioneID);
				
				jsonCarInfo.add(CC.iu_targa);
				jsonCarInfo.add(CC.iu_modello);
				jsonCarInfo.add(CC.iu_typo_amak);
				
				jsonCarInfo.add(Integer.toString(CC.first));
				
				jsonCar.put("data", jsonCarInfo);
				
				list_of_cars.add(jsonCar);
				
			}
			
			jsonCars.put("rows", list_of_cars);
			jsonContract.put("cars",jsonCars);
			
			//%%%%%%%%%% ARRAY SCONTRINI
			JSONObject jsonScontrini = new JSONObject();
			JSONArray list_of_scontrini = new JSONArray();
			
			for(int i=0;i<CO.CS.size();i++){
				
				ScontrinoObj CS = (ScontrinoObj)CO.CS.get(i);
				JSONObject jsonScontrino = new JSONObject();
				JSONArray jsonScontrinoInfo =  new JSONArray();

				jsonScontrino.put("id", CS.id);
				jsonScontrinoInfo.add(CS.data_pagamento);
				jsonScontrinoInfo.add(CS.af);
				jsonScontrinoInfo.add(CS.start);
				jsonScontrinoInfo.add(CS.end);
				jsonScontrinoInfo.add(CS.prezzo);
				jsonScontrinoInfo.add(CS.aa);
				jsonScontrinoInfo.add(Integer.toString(CS.bank));
				jsonScontrinoInfo.add(CS.user);
				
				jsonScontrino.put("data", jsonScontrinoInfo);
				
				list_of_scontrini.add(jsonScontrino);
				
			}
			
			jsonScontrini.put("rows", list_of_scontrini);
			jsonContract.put("scontrini",jsonScontrini);
			//%%%%%%%%%%%%%%%%%
			
			jsonContract.put("iu_posto",CO.iu_posto);
			jsonContract.put("ContractID",CO.ContractID);
			jsonContract.put("ClientID",CO.ClientID);
			jsonServer.put("contract",jsonContract);
			
		}else{
			jsonServer.put("result",false);
			jsonServer.put("msg","Unable recruitment data!");
		}

	    out.print(jsonServer);
        out.flush();
 
	}

}
