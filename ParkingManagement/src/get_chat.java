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

import java.sql.ResultSet;
import java.sql.SQLException;

import RawConnection.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/get_chat")
public class get_chat extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String dataFromClient = request.getParameter("data");
		request.setCharacterEncoding("UTF-8");
		
	
		DBAutocomplete DBA = new DBAutocomplete();
		JSONObject jsonServer = new JSONObject();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		
		
		String ActiveUser = (String) session.getAttribute("username");
		if(ActiveUser==null){
			jsonServer.put("result",false);
			jsonServer.put("logined",false);
			jsonServer.put("msg","You are not logged!");
			out.print(jsonServer);
	        out.flush();
	        out.close();
	        return;
		}
		jsonServer.put("logined",true);
		
		if(!DBA.open_Connection(request.getSession().getServletContext().getInitParameter("db_name"), request.getSession().getServletContext().getInitParameter("db_user"), request.getSession().getServletContext().getInitParameter("db_pass"))){
			jsonServer.put("result",false);
			jsonServer.put("msg","The database is not accessible!");
			out.print(jsonServer);
	        out.flush();
	        return;
		}

		
        ResultSet ResSet ;
        ResSet = DBA.exec_autocomplete_chat();
        
        if(ResSet == null){
			jsonServer.put("result",false);
			jsonServer.put("msg","Could not run the autocomplete in the database!");
			out.print(jsonServer);
	        out.flush();
	        DBA.close_Connection();
	        return;
        }
        
        jsonServer.put("result",true);
		JSONArray list1 = new JSONArray();
        try{
            while(ResSet.next()){
            	JSONObject l1 = new JSONObject();
            	l1.put("id", ResSet.getString("id"));
        		l1.put("targa", ResSet.getString("targa") );
        		list1.add(l1);
            }
        }catch(SQLException e) {
        	jsonServer.put("result",false);
            System.out.println("Error: " + e.getMessage());
        }

		
		jsonServer.put("list", list1);
		
		jsonServer.put("msg","");

		DBA.close_Connection();
	    out.print(jsonServer);
        out.flush();
        
	}

}
