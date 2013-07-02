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


@WebServlet("/register_user")
public class register_user extends HttpServlet {
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
		HttpSession session = request.getSession(true);
		String username = (String) jsonClient.get("username");
		String password = (String) jsonClient.get("password");
		String name = (String) jsonClient.get("name");
		String surname = (String) jsonClient.get("surname");
		
		//RawConnection RC = new RawConnection("parking302", "parking302", "db123");
		UsersManipolation UM = new UsersManipolation();
		JSONObject jsonServer = new JSONObject();
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(!UM.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		
		if(!UM.write_to_LOG("Εγγραφή νέου χρήστη Όνομα:" + name + " Επωνυμο:" + surname + " Όνομα Χρήστη:" + username , session.getId() )){
			jsonServer.put("result",false);
			jsonServer.put("msg","Unable to write the LOG!");
			out.print(jsonServer);
	        out.flush();
	        UM.close_Connection();
	        return;
		}
		
		if(username == null || username.length()<3 || 
		   password == null || password.length()<3 || 
		   name == null || name.length()<3 || 
		   surname == null || surname.length()<3 ){
			jsonServer.put("result",false);
			jsonServer.put("msg","Please, <b>check</b> the completed fields!");
			out.print(jsonServer);
	        out.flush();
	        UM.close_Connection();
	        return;
		}
		
		if(UM.exist_username(username)){
			jsonServer.put("result",false);
			jsonServer.put("msg","There is <b>already</b> a user with that name!");
		}else{
			if(UM.register_new_user(name, surname, username, password)){
				jsonServer.put("result",true);
				jsonServer.put("msg","The new user registration completed <b> successfully </ b>.");
			}else{
				jsonServer.put("result",false);
				jsonServer.put("msg","The new user registration <b> NOT </ b> was successfully!");
			}
			
		}

	    out.print(jsonServer);
        out.flush();
        UM.close_Connection();
	}

}
