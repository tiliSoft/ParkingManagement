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
import javax.servlet.http.*;

import RawConnection.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

@WebServlet(description = "Servlet for the validation of username and the password, this servlet create the Session for the user", urlPatterns = { "/check_signin" })

public class check_signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public check_signin() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
		String username = (String) jsonClient.get("username");
		String password = (String) jsonClient.get("password");
		
		UsersManipolation UM = new UsersManipolation();
		
		
		JSONObject jsonServer=new JSONObject();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		HttpSession session = request.getSession(true);
		session.invalidate();
		session = request.getSession(true);
		
		
		if(!UM.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}
		
		
		


		if(UM.exist_username_with_password(username, password)){
			if(UM.username_is_enabled(username)){
				//session.invalidate();
				jsonServer.put("result",true);
				session.setAttribute("username", username);
				session.setAttribute("admin", UM.username_is_admin(username));

				UM.write_log_session_start(session.getId(),  (String)username,request.getRemoteAddr() , (String)request.getHeader("User-Agent"));
				if(!UM.write_to_LOG("User Login", session.getId())){
					jsonServer.put("result",false);
					jsonServer.put("msg","Unable to write the LOG!");
					out.print(jsonServer);
			        out.flush();
			        UM.close_Connection();
			        return;
				}				
			}else{
				if(!UM.write_to_LOG("Failure User Login", session.getId())){
					jsonServer.put("result",false);
					jsonServer.put("msg","Unable to write the LOG!");
					out.print(jsonServer);
			        out.flush();
			        UM.close_Connection();
			        return;
				}
				jsonServer.put("result",false);
				jsonServer.put("msg","The user is not activated, please contact the administrator!");
			}
			
		}else{
			jsonServer.put("result",false);
			jsonServer.put("msg","The user is not was recognized !");
		}

	    out.print(jsonServer);
        out.flush();
        UM.close_Connection();
	}
}
