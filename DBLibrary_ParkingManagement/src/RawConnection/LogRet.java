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
package RawConnection;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LogRet extends RawConnection
{
  public JSONObject get_sessions(int days, int type)
  {
    String SQL = "SELECT distinct ls.sessionid as sessionid, ls.enter_datatime as enter_time,CAST(ls.exit_datatime AS CHAR) as exit_time, ls.username as username \nFROM  users us, log_session ls, log log \nwhere  \nus.username = ls.username AND \nls.sessionid = log.sessionid AND \nus.admin = " + type + " AND \n" + "DATEDIFF(date(current_date),date(ls.enter_datatime)) < " + days + "  \n" + "order by ls.enter_datatime DESC,log.datatime DESC; ";

    JSONObject jsonObj = new JSONObject();
    JSONArray jArr = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonObj_elem = new JSONObject();

        jsonObj_elem.put("sessionid", rs.getString("sessionid"));
        jsonObj_elem.put("enter_time", rs.getString("enter_time"));
        jsonObj_elem.put("exit_time", rs.getString("exit_time"));
        jsonObj_elem.put("username", rs.getString("username"));

        jArr.add(jsonObj_elem);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }

    jsonObj.put("data", jArr);

    return jsonObj;
  }

  public JSONObject get_session(String sessionid)
  {
    String SQL = "SELECT log.datatime as datatime, log.log \n FROM  users us, log_session ls, log log \n where  \n us.username = ls.username AND \n ls.sessionid = log.sessionid AND \n ls.sessionid=" + add_quotes(sessionid) + " \n " + "order by ls.enter_datatime DESC,log.datatime DESC; ";

    JSONObject jsonObj = new JSONObject();
    JSONArray jArr = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonObj_elem = new JSONObject();

        jsonObj_elem.put("datatime", rs.getString("datatime"));
        jsonObj_elem.put("log", rs.getString("log"));

        jArr.add(jsonObj_elem);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }

    jsonObj.put("data", jArr);

    return jsonObj;
  }
}
