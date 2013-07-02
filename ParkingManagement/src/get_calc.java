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

@WebServlet("/get_calc")
public class get_calc extends HttpServlet {
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
			//System.out.println(e.getMessage());
		}
		JSONObject jsonClient = (JSONObject) jsonClient_Object;
		
		//RawConnection RC = new RawConnection("parking302", "parking302", "db123");
		Calc CLC = new Calc();
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
		boolean  isAdmin;
		try{
			isAdmin = (Boolean)session.getAttribute("admin");
		}catch(NullPointerException e){
			isAdmin = false;
		}
		if(!isAdmin){
			jsonServer.put("result",false);
			jsonServer.put("msg","You're not an administrator!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		
		if(!CLC.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		//System.out.println(query);
		
		try{
		
			Double top = Double.parseDouble((String)jsonClient.get("top"));
			Double media = Double.parseDouble((String)jsonClient.get("media"));
			CLC.set_valori(media, top);
			
			if(!CLC.write_to_LOG("Calculation desired income, Average rent "+ media + " Income desired:"+top, session.getId() )){
				jsonServer.put("result",false);
				jsonServer.put("msg","Unable to write the LOG!");
				out.print(jsonServer);
		        out.flush();
		        CLC.close_Connection();
		        return;
			}
			
		}catch(NullPointerException | ClassCastException | NumberFormatException e){
			//System.out.println(e.getMessage());
		}

		
		jsonServer.put("data", CLC.get_valori());      
        jsonServer.put("result",true);
		jsonServer.put("msg","");

		CLC.close_Connection();
	    out.print(jsonServer);
        out.flush();
        
	}

}
