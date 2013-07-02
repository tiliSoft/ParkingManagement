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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RawConnection
{
  public String DBname;
  public String DBusername;
  public String DBpassword;
  public Connection con;
  public Statement statement;
  public boolean ConnectionOpened = false;

  public String add_replace(String SQL)
  {
    return SQL.replace("'", "''").replace("<", "&#60;").replace("\\", "\\\\");
  }

  public String add_quotes(String SQL) {
    return "'" + add_replace(SQL) + "'";
  }

  public boolean test_Connection()
  {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String dblink = "jdbc:mysql://localhost/" + this.DBname;
    try {
      Class.forName(JDBC_DRIVER);
      Connection con = DriverManager.getConnection(dblink, this.DBusername, this.DBpassword);
      Statement statement = con.createStatement();
      statement.closeOnCompletion();
      con.close();
      return true;
    } catch (ClassNotFoundException|SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }return false;
  }

  public boolean open_Connection(String DBname, String DBusername, String DBpassword)
  {
    this.DBname = DBname;
    this.DBusername = DBusername;
    this.DBpassword = DBpassword;
    this.con = null;
    this.statement = null;
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String dblink = "jdbc:mysql://localhost/" + DBname + "?characterEncoding=utf8";
    try
    {
      Class.forName(JDBC_DRIVER);
      this.con = DriverManager.getConnection(dblink, DBusername, DBpassword);
      this.statement = this.con.createStatement();
      this.con.setAutoCommit(false);
      this.ConnectionOpened = true;
      return true;
    } catch (ClassNotFoundException|SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }return false;
  }

  public boolean close_Connection()
  {
    try {
      this.con.commit();
      this.statement.close();
      this.con.close();
      this.ConnectionOpened = false;
      return true;
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }return false;
  }

  public ResultSet read_from_DB(String SQL)
  {
    ResultSet rs = null;
    try {
      if ((this.con.isClosed()) || (this.statement.isClosed())) {
            return null;
        }
      rs = this.statement.executeQuery(SQL);
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return rs;
  }

  public boolean write_to_DB(String SQL) {
    try {
      if ((this.con.isClosed()) || (this.statement.isClosed())) {
            return false;
        }
      this.statement.executeUpdate(SQL);
      return true;
    }
    catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }return false;
  }

  public boolean write_to_LOG(String log, String SessionID)
  {
    try {
      if ((this.con.isClosed()) || (this.statement.isClosed())) {
            return false;
        }
      this.statement.executeUpdate("INSERT INTO log (log,sessionid) VALUES (" + add_quotes(log) + "," + add_quotes(SessionID) + ")");
      return true;
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }return false;
  }

  public boolean write_to_LOG_html(String log, String SessionID)
  {
    try {
      if ((this.con.isClosed()) || (this.statement.isClosed())) {
            return false;
        }
      this.statement.executeUpdate("INSERT INTO log (log,sessionid) VALUES ('" + log + "'," + add_quotes(SessionID) + ")");
      return true;
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }return false;
  }
}
