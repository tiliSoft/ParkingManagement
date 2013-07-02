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
import org.json.simple.JSONObject;

public class Statistic_Data extends RawConnection
{
  public JSONObject get_posti_liberi()
  {
    String SQL = "  select  (select count(*) as occupati \nfrom contract co, posto po, contract_vehicles cv, vehicles ve \nwhere \nco.id_posto = po.id AND \ncv.id_contract = co.id AND \ncv.id_vehicle = ve.id AND \nco.al is null AND \ncv.first = 1 AND \nco.extra =0 AND \nve.type = 1 ) as occupati, \n( select sum(po.space) as totale \nfrom posto po) as totale;";

    JSONObject jsonObj = new JSONObject();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        jsonObj.put("totale", Integer.valueOf(rs.getInt("totale")));
        jsonObj.put("occupati", Integer.valueOf(rs.getInt("occupati")));
        jsonObj.put("liberi", Integer.valueOf(rs.getInt("totale") - rs.getInt("occupati")));
      }
    } catch (SQLException e) {
      return null;
    }

    return jsonObj;
  }

  public JSONObject get_lordo_neto()
  {
    String SQL = " SELECT ROUND(sum(prezzo), 2) as lordo,  ROUND(sum(prezzo)/1.23, 2) as neto FROM contract where al is null AND extra=0;";

    JSONObject jsonObj = new JSONObject();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        jsonObj.put("lordo", Double.valueOf(rs.getDouble("lordo")));
        jsonObj.put("neto", Double.valueOf(rs.getDouble("neto")));
      }
    } catch (SQLException e) {
      return null;
    }

    return jsonObj;
  }
}
