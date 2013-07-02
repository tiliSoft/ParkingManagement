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

public class Calc extends RawConnection
{
  public boolean set_valori(double media, double top)
  {
    String SQL = "update calc_fut set top = " + top + " , media = " + media + " where id=1";
    return write_to_DB(SQL);
  }

  public boolean set_valori(String d_from, String d_to)
  {
    String SQL = "update calc_fut set d_from = " + add_quotes(d_from) + " , d_to = " + add_quotes(d_to) + " where id=1";
    return write_to_DB(SQL);
  }

  public JSONObject get_valori()
  {
    JSONObject jsonObj = new JSONObject();

    String SQL = "select a.lordo as lordo, b.cnt as cnt, c.top as top, c.media as media  \nfrom  \n(SELECT ROUND( SUM( prezzo ) , 2 ) AS lordo, ROUND( SUM( prezzo ) / 1.23, 2 ) AS neto  \nFROM contract  \nWHERE al IS NULL  AND extra=0) as a,  \n(SELECT COUNT( * )  as cnt  \nFROM contract  \nWHERE al IS NULL AND extra=0) as b,  \n(SELECT media,top FROM calc_fut WHERE id=1) as c ;";

    String SQL2 = "select  (select count(*) as occupati \nfrom contract co, posto po, contract_vehicles cv, vehicles ve \nwhere \nco.id_posto = po.id AND \ncv.id_contract = co.id AND \ncv.id_vehicle = ve.id AND \nco.al is null AND \ncv.first = 1 AND \nco.extra =0 AND \nve.type = 1 ) as occupati, \n( select sum(po.space) as totale \nfrom posto po) as totale;";

    ResultSet rs = read_from_DB(SQL2);
    try {
      while (rs.next())
      {
        jsonObj.put("liberi", Integer.valueOf(rs.getInt("totale") - rs.getInt("occupati")));
      }
    } catch (SQLException e) {
      return null;
    }

    rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        jsonObj.put("media", Double.valueOf(rs.getDouble("media")));
        jsonObj.put("top", Double.valueOf(rs.getDouble("top")));
        jsonObj.put("cnt", Integer.valueOf(rs.getInt("cnt")));
        jsonObj.put("lordo", Double.valueOf(rs.getDouble("lordo")));
      }
    }
    catch (SQLException e) {
      return null;
    }

    return jsonObj;
  }

  public JSONObject get_valori_real(String d_from, String d_to)
  {
    set_valori(d_from, d_to);

    String SQL = "SELECT data_pagamento, DAYOFMONTH( sc.data_pagamento ) AS year, \nSUM( sc.prezzo ) AS sales3,  \nSUM(IF(co.extra=0,sc.prezzo,0)) AS sales2,   \nSUM(IF(co.extra=1,sc.prezzo,0)) AS sales1,  \nCOUNT( sc.data_pagamento ) as cnt3,  \nSUM( IF(co.extra=0,1,0) ) as cnt2,   \nSUM( IF(co.extra=1,1,0) ) as cnt1,    \nSUM( IF(co.extra=1 AND sc.prezzo=0,1,0) ) as cntg  \nFROM scontrino sc, contract co \nWHERE   \nco.id = sc.id_contract AND \nsc.data_pagamento <= STR_TO_DATE('" + add_replace(d_to) + "', '%d/%m/%Y') AND  \n" + "sc.data_pagamento >= STR_TO_DATE('" + add_replace(d_from) + "', '%d/%m/%Y') \n" + "group by sc.data_pagamento ;";

    int tot3 = 0;
    int tot2 = 0;
    int tot1 = 0;
    int cnt_tot3 = 0;
    int cnt_tot2 = 0;
    int cnt_tot1 = 0;
    int cnt_totg = 0;

    JSONObject jsonObj_Global = new JSONObject();
    JSONArray List1 = new JSONArray();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        JSONObject jsonObj = new JSONObject();

        int sales3 = rs.getInt("sales3");
        int sales2 = rs.getInt("sales2");
        int sales1 = rs.getInt("sales1");

        int cnt3 = rs.getInt("cnt3");
        int cnt2 = rs.getInt("cnt2");
        int cnt1 = rs.getInt("cnt1");
        int cntg = rs.getInt("cntg");

        jsonObj.put("sales3", Integer.valueOf(sales3));
        jsonObj.put("sales2", Integer.valueOf(sales2));
        jsonObj.put("sales1", Integer.valueOf(sales1));
        jsonObj.put("year", rs.getString("year"));
        jsonObj.put("data_pag", rs.getString("data_pagamento"));
        jsonObj.put("cnt3", Integer.valueOf(cnt3));
        jsonObj.put("cnt2", Integer.valueOf(cnt2));
        jsonObj.put("cnt1", Integer.valueOf(cnt1));

        tot3 += sales3;
        tot2 += sales2;
        tot1 += sales1;
        cnt_tot3 += cnt3;
        cnt_tot2 += cnt2;
        cnt_tot1 += cnt1;
        cnt_totg += cntg;

        List1.add(jsonObj);
      }
    }
    catch (SQLException e) {
      return null;
    }

    jsonObj_Global.put("tot3", Integer.valueOf(tot3));
    jsonObj_Global.put("tot2", Integer.valueOf(tot2));
    jsonObj_Global.put("tot1", Integer.valueOf(tot1));
    jsonObj_Global.put("cnt_tot3", Integer.valueOf(cnt_tot3));
    jsonObj_Global.put("cnt_tot2", Integer.valueOf(cnt_tot2));
    jsonObj_Global.put("cnt_tot1", Integer.valueOf(cnt_tot1));
    jsonObj_Global.put("cnt_g", Integer.valueOf(cnt_totg));

    jsonObj_Global.put("list", List1);

    return jsonObj_Global;
  }

  public JSONObject get_valori_real_from_to()
  {
    String SQL = "SELECT * FROM calc_fut ;";

    JSONObject jsonObj = new JSONObject();

    ResultSet rs = read_from_DB(SQL);
    try {
      while (rs.next()) {
        jsonObj.put("d_from", rs.getString("d_from"));
        jsonObj.put("d_to", rs.getString("d_to"));
      }
    } catch (SQLException e) {
      return null;
    }

    return jsonObj;
  }
}
