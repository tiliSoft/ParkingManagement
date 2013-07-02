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


@WebServlet("/Manipulate_Grid_Users")
public class Manipulate_Grid_Users extends HttpServlet {
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

		UsersManipolation UM = new UsersManipolation();
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
		
		
		String logString = jsonClient.toJSONString();
		//System.out.println(logString);
		

		if(!UM.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		
		if(!UM.write_to_LOG("Try to update Grid users: " + logString, session.getId() )){
			jsonServer.put("result",false);
			jsonServer.put("msg","Unable to write the LOG (Servlet: Manipulate_Grid_Users)!");
			out.print(jsonServer);
	        out.flush();
	        UM.close_Connection();
	        return;
		}
		
		
		JSONArray  jsonMainArr = (JSONArray ) jsonClient.get("operations");
		
		boolean returnCode=true;
		String table = "users";
		
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
				if(!UM.Update_Rows_Users(code, table, id, 0, null, ActiveUser)){
					System.out.println(id);
					returnCode = false;
					break;
				}
								
			}else if(code.equals("update")){
				Long Col_Long = (Long) jsonMainElement.get("col");
				int Col = Integer.parseInt(Col_Long.toString());
				JSONArray ColArr = (JSONArray) jsonMainElement.get("data");
				Object oo[] = new Object[ColArr.size()];
                oo[0] = ColArr.get(0);
                
                if(!UM.Update_Rows_Users(code, table, id, Col, oo, ActiveUser)){
                	System.out.println(id);
                	returnCode = false;
					break;
                }

			}else if(code.equals("insert")){
				JSONArray ColArr = (JSONArray) jsonMainElement.get("data");
				Object oo[] = new Object[ColArr.size()];
				for(int k=0;k<ColArr.size();k++)   oo[k] = ColArr.get(k);
				if(!UM.Update_Rows_Users(code, table, id, 0, oo, ActiveUser)){
					returnCode = false;
					break;
				}

			}else{
				returnCode = false;
				break;
			}
		}

		
		if(returnCode && UM.close_Connection()){
			jsonServer.put("result",true);
			jsonServer.put("msg","<b> Successful </ b> save");
			
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
