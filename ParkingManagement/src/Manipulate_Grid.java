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
import java.io.UnsupportedEncodingException;

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


@WebServlet("/Manipulate_Grid")
public class Manipulate_Grid extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		extracted(request);
		
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

		if(!CM.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		
		Long ContractID_Long =  (Long) jsonClient.get("contractid");
		int ContractID = Integer.parseInt(ContractID_Long.toString());
		
		JSONArray  jsonMainArr = (JSONArray ) jsonClient.get("operations");
		
		
		boolean returnCode=true;
		String table = "scontrini";
		
		for (int i = 0; i < jsonMainArr.size(); i++) {
			JSONObject jsonMainElement = (JSONObject) jsonMainArr.get(i);
			String code = (String) jsonMainElement.get("code");
			table = (String) jsonMainElement.get("table");
			Long id_long =(Long) jsonMainElement.get("id");
			int id;
			try{
				id = Integer.parseInt(id_long.toString());
			}catch(NumberFormatException e){
				id=-1;
			}

			if(code.equals("delete")){
				if(!CM.Update_Rows(code, table, id, ContractID, 0, null, ActiveUser, session.getId() )){
					//System.out.println(id);
					returnCode = false;
					break;
				}
								
			}else if(code.equals("update")){
				Long Col_Long = (Long) jsonMainElement.get("col");
				int Col = Integer.parseInt(Col_Long.toString());
				JSONArray ColArr = (JSONArray) jsonMainElement.get("data");
				Object oo[] = new Object[ColArr.size()];
                oo[0] = ColArr.get(0);
                //System.out.println("COL:" + Col + " oo[0]:" + oo[0]);
                if(!CM.Update_Rows(code, table, id, ContractID, Col, oo, ActiveUser, session.getId()  )){
                	//System.out.println(id);
                	returnCode = false;
					break;
                }

			}else if(code.equals("insert")){
				JSONArray ColArr = (JSONArray) jsonMainElement.get("data");
				Object oo[] = new Object[ColArr.size()];
				for(int k=0;k<ColArr.size();k++)   oo[k] = ColArr.get(k);
				if(!CM.Update_Rows(code, table, id, ContractID, 0, oo, ActiveUser, session.getId()  )){
					returnCode = false;
					break;
				}

			}else{
				returnCode = false;
				break;
			}
		}

		
		if(returnCode){
			CO = CM.Get_Contract(ContractID);
			if(CO!=null && CM.close_Connection()){
				if(table.equals("car")){
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
					jsonServer.put("data",jsonCars);
					
				}else{
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
					jsonServer.put("data",jsonScontrini);
				}
				jsonServer.put("result",true);
				jsonServer.put("msg","<b> Successful </ b> save");
				
			}else{
				jsonServer.put("result",false);
				jsonServer.put("msg","Unable recruitment data!");
			}
			
		}else{
			jsonServer.put("result",false);
			jsonServer.put("msg","<b> Unsuccessful </ b> save!");
		}
			
	    out.print(jsonServer);
        out.flush();
        out.close();
	}

	private void extracted(HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
	}

}
