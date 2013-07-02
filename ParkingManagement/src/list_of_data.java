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

@WebServlet("/list_of_data")
public class list_of_data extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
		JsonList JL = new JsonList();
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
		
		int CodeID;

		try{
			Long CodeID_L = (Long)jsonClient.get("CodeID");
			
			CodeID = Integer.parseInt(CodeID_L.toString());
		}catch(ClassCastException ee){
			CodeID = Integer.parseInt((String)jsonClient.get("CodeID"));
			
		}catch(NumberFormatException e){
			jsonServer.put("result",false);
			jsonServer.put("msg","Input Error!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		
		if(!JL.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		
		if(!JL.write_to_LOG("Show List id:" + CodeID, session.getId() )){
			jsonServer.put("result",false);
			jsonServer.put("msg","Unable to write the LOG (Servlet: list_of_data)!");
			out.print(jsonServer);
	        out.flush();
	        JL.close_Connection();
	        return;
		}
		
		JSONObject objectFromLib = null;
		if(CodeID==1){
			objectFromLib = JL.get_posti_liberi();
		}else if(CodeID==2){
			objectFromLib = JL.get_0_moto();
		}else if(CodeID==3){
			objectFromLib = JL.get_0_auto();
		}else if(CodeID==4){
			objectFromLib = JL.get_piano(1);
		}else if(CodeID==5){
			objectFromLib = JL.get_piano(2);
		}else if(CodeID==6){
			objectFromLib = JL.get_piano(3);
		}else if(CodeID==7){
			objectFromLib = JL.get_piano(4);
		}else if(CodeID==8){
			objectFromLib = JL.get_piano(5);
		}else if(CodeID==9){
			objectFromLib = JL.get_piano(6);
		}else if(CodeID==10){
			objectFromLib = JL.get_piano(7);
		}else if(CodeID==11){
			objectFromLib = JL.get_piani();
		}else if(CodeID==12){
			objectFromLib = JL.get_extra();
		}else if(CodeID==14){
			objectFromLib = JL.get_X_meno(30);
		}else if(CodeID==15){
			objectFromLib = JL.get_X_meno(45);
		}else if(CodeID==16){
			objectFromLib = JL.get_X_meno(60);
		}else if(CodeID==13){
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
			objectFromLib = JL.get_json_user_list();
		}
	
	
		if(objectFromLib!=null && JL.close_Connection()){
			jsonServer.put("result",true);
			jsonServer.put("msg","");
			jsonServer.put("data", objectFromLib);
		}else{
			jsonServer.put("result",false);
			jsonServer.put("msg","Unable recruitment data!");
		}

	    out.print(jsonServer);
        out.flush();
 
	}

}
