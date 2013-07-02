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

public class DBAutocomplete extends RawConnection
{
  public ResultSet exec_autocomplete(String query)
  {
    ResultSet rs;
    String SQL ;
    
    SQL = "select co.id as contractid, cl.name as name, p.number as posto, v.number_plate as targa \n " +
            "from contract co, client cl, posto p, contract_vehicles cv, vehicles v \n " +
            "where  \n " +
            "co.al IS NULL AND  \n " +
            "co.id_client = cl.id AND \n " +
            "co.id_posto = p.id AND \n " +
            "co.id = cv.id_contract AND \n " +
            "cv.id_vehicle = v.id AND \n " +
            "cv.first = 1 AND \n " +
            "co.id in ( \n " +
            "    Select co.id as contractid \n " +
            "    from contract co, client cl, posto p, contract_vehicles cv, vehicles v \n " +
            "    where  \n " +
            "    co.al IS NULL AND  \n " +
            "    co.id_client = cl.id AND \n " +
            "    co.id_posto = p.id AND \n " +
            "    co.id = cv.id_contract AND \n " +
            "    cv.id_vehicle = v.id AND \n " +
            "    (cl.name like '%" + add_replace(query) + "%' OR  \n " +
            "    p.number like '%" + add_replace(query) + "%' OR  \n " +
            "    v.number_plate like '%" + add_replace(query) + "%') order by name \n " +
            ") \n " +
            "order by name; ";
  
    try
    {
      if ((this.con.isClosed()) || (this.statement.isClosed())) {
            return null;
        }
      rs = this.statement.executeQuery(SQL);
    } catch (SQLException e) {
      rs = null;
      System.out.println("Error: " + e.getMessage());
    }
    return rs;
  }

  public ResultSet exec_autocomplete_chat()
  {
    ResultSet rs;

    String SQL = "SELECT co.id as id, ve.number_plate as targa \nFROM contract co, contract_vehicles cv, vehicles ve \nWHERE  \nco.id = cv.id_contract AND \ncv.id_vehicle = ve.id AND \nco.extra=1 AND \nco.al is null order by id";
    try
    {
      if ((this.con.isClosed()) || (this.statement.isClosed())) {
            return null;
        }
      rs = this.statement.executeQuery(SQL);
    } catch (SQLException e) {
      rs = null;
      System.out.println("Error: " + e.getMessage());
    }
    return rs;
  }
}
