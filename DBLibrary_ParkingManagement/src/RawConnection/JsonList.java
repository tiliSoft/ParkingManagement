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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonList extends RawConnection
{
  public JSONObject get_posti_liberi()
  {
    String SQL = "select po.id as id,po.piano as piano, po.number as posto\n\t\tfrom posto po\n\t\twhere po.id NOT IN (select id_posto from contract where al is null)";

    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("piano"));
        jsonRow_data.add(rs.getString("posto"));
        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));

        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);

    return jsonObj;
  }

  public JSONObject get_0_moto() {
    String SQL = "select co.id as id, co.prezzo as prezzo, ve.model as model, ve.number_plate as targa, cl.name as nome\nfrom contract co, vehicles ve, client cl, contract_vehicles cv, posto po\nwhere\nco.al is null AND\nco.id_posto = po.id AND\nco.id_client = cl.id AND\ncv.id_contract = co.id AND\ncv.first = 1 AND\nco.extra = 0 AND\ncv.id_vehicle = ve.id AND\n (ve.type = 2 OR ve.type=3) AND\n(po.piano = 0 or po.piano = -1);";

    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("prezzo"));
        jsonRow_data.add(rs.getString("model"));
        jsonRow_data.add(rs.getString("targa"));
        jsonRow_data.add(rs.getString("nome"));

        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));
        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);
    return jsonObj;
  }

  public JSONObject get_0_auto() {
    String SQL = "select co.id as id, co.prezzo as prezzo, ve.model as model, ve.number_plate as targa, cl.name as nome\nfrom contract co, vehicles ve, client cl, contract_vehicles cv, posto po\nwhere\nco.al is null AND\nco.id_posto = po.id AND\nco.id_client = cl.id AND\ncv.id_contract = co.id AND\ncv.id_vehicle = ve.id AND\ncv.first = 1 AND\nve.type = 1 AND\nco.extra = 0 AND\n(po.piano = 0 or po.piano = -1);";

    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("prezzo"));
        jsonRow_data.add(rs.getString("model"));
        jsonRow_data.add(rs.getString("targa"));
        jsonRow_data.add(rs.getString("nome"));

        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));
        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);
    return jsonObj;
  }

  public JSONObject get_piano(int piano) {
    String SQL = "select co.id as id, co.prezzo as prezzo, ve.model as model, ve.number_plate as targa, cl.name as nome, po.number as posto\nfrom contract co, vehicles ve, client cl, contract_vehicles cv, posto po\nwhere\nco.al is null AND\nco.id_posto = po.id AND\nco.id_client = cl.id AND\ncv.id_contract = co.id AND\ncv.first = 1 AND\ncv.id_vehicle = ve.id AND\nco.extra = 0 AND\npo.piano = " + piano + " \n" + "order by po.id";

    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("prezzo"));
        jsonRow_data.add(rs.getString("model"));
        jsonRow_data.add(rs.getString("targa"));
        jsonRow_data.add(rs.getString("nome"));
        jsonRow_data.add(rs.getString("posto"));

        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));
        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);
    return jsonObj;
  }

  public JSONObject get_piani() {
    String SQL = "select co.id as id, co.prezzo as prezzo, ve.model as model, ve.number_plate as targa, cl.name as nome, po.number, po.number as posto\nfrom contract co, vehicles ve, client cl, contract_vehicles cv, posto po\nwhere\nco.al is null AND\nco.id_posto = po.id AND\nco.id_client = cl.id AND\ncv.id_contract = co.id AND\ncv.first = 1 AND\nco.extra = 0 AND\ncv.id_vehicle = ve.id \norder by po.id";

    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("prezzo"));
        jsonRow_data.add(rs.getString("model"));
        jsonRow_data.add(rs.getString("targa"));
        jsonRow_data.add(rs.getString("nome"));
        jsonRow_data.add(rs.getString("posto"));

        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));
        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);
    return jsonObj;
  }

  public JSONObject get_extra() {
    String SQL = "select co.id as id, co.prezzo as prezzo, ve.model as model, ve.number_plate as targa, cl.name as nome, po.number, po.number as posto\nfrom contract co, vehicles ve, client cl, contract_vehicles cv, posto po\nwhere\nco.al is null AND\nco.id_posto = po.id AND\nco.id_client = cl.id AND\ncv.id_contract = co.id AND\ncv.first = 1 AND\ncv.id_vehicle = ve.id AND co.extra = 1 \norder by po.id";

    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("prezzo"));
        jsonRow_data.add(rs.getString("model"));
        jsonRow_data.add(rs.getString("targa"));
        jsonRow_data.add(rs.getString("nome"));
        jsonRow_data.add(rs.getString("posto"));

        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));
        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);
    return jsonObj;
  }

  public JSONObject get_X_meno(int Days) {
    String SQL = "select co.id as id, co.prezzo as prezzo, ve.model as model, ve.number_plate as targa, cl.name as nome, po.number as posto \n " +
                "from contract co, vehicles ve, client cl, contract_vehicles cv, posto po  \n " +
                "where  \n " +
                "co.al is null AND  \n " +
                "co.extra = 0 AND  \n " +
                "co.id_posto = po.id AND \n " +
                "co.id_client = cl.id AND  \n " +
                "cv.id_contract = co.id AND \n " +
                "cv.id_vehicle = ve.id AND \n " +
                "cv.first = 1 AND \n " +
                "co.id in (  \n " +
                "select tab1.id_contract as id \n " +
                "from( \n " +
                "        select id_contract, max(end) as end \n " +
                "        from scontrino  \n " +
                "        where data_pagamento is not null \n " +
                "        GROUP BY id_contract \n " +
                "    ) as tab1 \n " +
                "    where  DATEDIFF(current_date,tab1.end)  > " + Days + "  \n " +
                "    union all  \n " +
                "    select id \n " +
                "    from contract \n " +
                "    where \n " +
                "    id not in ( \n " +
                "        select sc.id_contract as id \n " +
                "        from  scontrino sc \n " +
                "        GROUP BY id_contract )  \n " +
                "    AND \n " +
                "    al is null AND  \n " +
                "    extra = 0 \n " +
                "    union all  \n " +
                "    select id \n " +
                "    from contract \n " +
                "    where \n " +
                "    id not in ( \n " +
                "            select id_contract \n " +
                "            from scontrino  \n " +
                "            where data_pagamento is not null)  \n " +
                "    AND \n " +
                "    al is null AND  \n " +
                "    extra = 0 \n " +
                ")order by po.id;"; 

    
    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("prezzo"));
        jsonRow_data.add(rs.getString("model"));
        jsonRow_data.add(rs.getString("targa"));
        jsonRow_data.add(rs.getString("nome"));
        jsonRow_data.add(rs.getString("posto"));

        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));
        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);
    return jsonObj;
  }

  public JSONObject get_json_user_list() {
    String SQL = "select id,username,name,surname,enabled,admin from users;";

    JSONObject jsonObj = new JSONObject();
    JSONArray list_of_rows = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonRow = new JSONObject();
        JSONArray jsonRow_data = new JSONArray();

        jsonRow_data.add(rs.getString("username"));
        jsonRow_data.add(rs.getString("name"));
        jsonRow_data.add(rs.getString("surname"));
        jsonRow_data.add(Integer.toString(rs.getInt("enabled")));
        jsonRow_data.add(Integer.toString(rs.getInt("admin")));

        jsonRow.put("id", Integer.valueOf(rs.getInt("id")));
        jsonRow.put("data", jsonRow_data);

        list_of_rows.add(jsonRow);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj.put("rows", list_of_rows);
    return jsonObj;
  }
}
