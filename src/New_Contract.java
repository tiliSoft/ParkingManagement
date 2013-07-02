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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/New_Contract")
public class New_Contract extends HttpServlet {
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
		
		CO.iu_nome = (String) jsonClient.get("iu_nome");		
		CO.iu_tele = (String) jsonClient.get("iu_tele");	
		CO.iu_cel = (String) jsonClient.get("iu_cel");	
		CO.iu_afm = (String) jsonClient.get("iu_afm");	
		CO.iu_doy = (String) jsonClient.get("iu_doy");	
		CO.iu_address = (String) jsonClient.get("iu_address");	
		
		try{
			CO.iu_prof = Integer.parseInt((String) jsonClient.get("iu_prof"));	
		}catch(NumberFormatException | ClassCastException e){
			CO.iu_prof = 0;
		}
		
		ContractCars CC = new ContractCars();
		
		CC.iu_targa = (String) jsonClient.get("iu_targa");	
		
		try{
			CC.iu_typo_amak = Integer.parseInt((String) jsonClient.get("iu_typo_amak"));
		}catch(NumberFormatException | ClassCastException e){
			CC.iu_typo_amak = 1;
		}

		CC.first = 1;
		CC.iu_modello = (String) jsonClient.get("iu_modello");
		
		CO.iu_posto = (String) jsonClient.get("iu_posto");
		
		try{
			CO.iu_prezzo = Double.parseDouble((String)jsonClient.get("iu_prezzo"));
		}catch(NumberFormatException | ClassCastException e){
			CO.iu_prezzo = 0.00;
		}
		
		CO.iu_data_ricevutta = (String) jsonClient.get("iu_data_ricevutta");
		
		try{
			CO.iu_caa = Integer.parseInt((String) jsonClient.get("iu_caa"));
		}catch(NumberFormatException | ClassCastException e){
			CO.iu_caa = 0;
		}

		try{
			CO.iu_extra = Integer.parseInt((String) jsonClient.get("iu_extra"));
		}catch(NumberFormatException | ClassCastException e){
			System.out.println(e.getMessage());
			CO.iu_extra = 0;
		}
		
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
		
		String JsonElement;
		if(CO.iu_extra==1){
			JsonElement = "Temporary ";
			JsonElement += ", License plate" + CC.iu_targa;
			
		}else{
			JsonElement = "Permanent ";
			JsonElement += ", Name" + CO.iu_nome + " ";
			JsonElement += ", License plate" + CC.iu_targa+ " ";
		}
		
		if(!CM.write_to_LOG("Create new CONTRACT " + JsonElement, session.getId() )){
			jsonServer.put("result",false);
			jsonServer.put("msg","Unable to write the LOG (Servlet: New_Contract)!");
			out.print(jsonServer);
	        out.flush();
	        CM.close_Connection();
	        return;
		}
		
		CO.CC.put(0, CC);
		
		if(CM.New_Contract(CO, ActiveUser) && CM.close_Connection()){
			jsonServer.put("result",true);
			jsonServer.put("msg","The new recording completed <b> successfully </ b>.");
		}else{
			jsonServer.put("result",false);
			jsonServer.put("msg","The new recording <b> NOT </ b> was successfully!");
		}

	    out.print(jsonServer);
        out.flush();
 
	}

}
