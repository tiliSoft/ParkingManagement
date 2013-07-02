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

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersManipolation extends RawConnection
{
  public boolean exist_username(String username)
  {
    boolean ReturnCode = false;
    if (!this.ConnectionOpened) {
          return false;
      }

    String SQL = "SELECT username FROM `users` WHERE username = " + add_quotes(username);
    ResultSet rs = read_from_DB(SQL);
    try
    {
      ReturnCode = rs.first();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return ReturnCode;
  }

  public boolean username_is_enabled(String username)
  {
    boolean ReturnCode = false;
    if (!this.ConnectionOpened) return false;

    String SQL = "SELECT username FROM `users` WHERE  enabled = 1 AND username = " + add_quotes(username);
    ResultSet rs = read_from_DB(SQL);
    try
    {
      ReturnCode = rs.first();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return ReturnCode;
  }

  public boolean username_is_admin(String username)
  {
    boolean ReturnCode = false;
    if (!this.ConnectionOpened) {
      return false;
    }

    String SQL = "SELECT username FROM `users` WHERE  enabled = 1 AND admin = 1 AND username = " + add_quotes(username);
    ResultSet rs = read_from_DB(SQL);
    try
    {
      ReturnCode = rs.first();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return ReturnCode;
  }

  public boolean exist_username_with_password(String username, String password)
  {
    boolean ReturnCode = false;
    if (!this.ConnectionOpened) {
      return false;
    }

    String SQL = "SELECT username FROM `users` WHERE username = " + add_quotes(username) + " AND password = " + add_quotes(password);
    ResultSet rs = read_from_DB(SQL);
    try
    {
      ReturnCode = rs.first();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return ReturnCode;
  }

  public boolean register_new_user(String name, String surname, String username, String password)
  {
    if (!this.ConnectionOpened) {
      return false;
    }

    String SQL = "INSERT INTO users(username, password, name, surname, enabled, admin) VALUES ( " + add_quotes(username) + ", " + add_quotes(password) + " , " + add_quotes(name) + ", " + add_quotes(surname) + ",0,0)";

    return write_to_DB(SQL);
  }

  public boolean write_log_session_start(String sessionid, String username, String IP, String Info)
  {
    if (!this.ConnectionOpened) {
      return false;
    }

    String SQL = "INSERT INTO log_session(sessionid, username, ip, info) VALUES (" + add_quotes(sessionid) + ", " + add_quotes(username) + ", " + add_quotes(IP) + ", " + add_quotes(Info) + ");";

    return write_to_DB(SQL);
  }

  public boolean write_log_session_end(String sessionid)
  {
    if (!this.ConnectionOpened) {
      return false;
    }

    String SQL = "UPDATE log_session SET exit_datatime=CURRENT_TIMESTAMP WHERE sessionid=" + add_quotes(sessionid) + ";";

    return write_to_DB(SQL);
  }

  public boolean Update_Rows_Users(String operation, String table, int id, int col, Object[] val, String username)
  {
    String SQL = "";
    int ret_i = -1;

    switch (operation) {
    case "delete":
      return false;
    case "insert":
      return false;
    case "update":
      if (table.equals("users"))
      {
        if (col == 1)
          try {
            SQL = "update users\nset name=" + add_quotes(val[0].toString()) + " \n" + "where id =" + id;
          }
          catch (NumberFormatException e)
          {
            return false;
          }
        else if (col == 2) {
          SQL = "update users\nset surname=" + add_quotes(val[0].toString()) + " \n" + "where id =" + id;
        }
        else if (col == 3) {
          SQL = "update users\nset enabled=" + Integer.parseInt(val[0].toString()) + " \n" + "where id =" + id;
        }
        else if (col == 4) {
          SQL = "update users\nset admin=" + Integer.parseInt(val[0].toString()) + " \n" + "where id =" + id;
        }
        else
        {
          return false;
        }
      }

      return write_to_DB(SQL);
    }

    return false;
  }
}
