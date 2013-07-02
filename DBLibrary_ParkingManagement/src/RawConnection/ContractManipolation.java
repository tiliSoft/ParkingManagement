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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContractManipolation extends RawConnection
{
  public int Create_Client_and_GetID(String iu_nome, String iu_tele, String iu_cel, String iu_afm, String iu_doy, String iu_address, int iu_prof)
  {
    int client_id = -1;
    String SQL_insert_Client = "Insert into client (name,phone,mobile,afm,doy,address,type) VALUES (" + add_quotes(iu_nome.toUpperCase()) + "," + add_quotes(iu_tele.toUpperCase()) + "," + add_quotes(iu_cel.toUpperCase()) + "," + add_quotes(iu_afm.toUpperCase()) + "," + add_quotes(iu_doy.toUpperCase()) + "," + add_quotes(iu_address.toUpperCase()) + "," + iu_prof + ");";
    write_to_DB(SQL_insert_Client);

    String SQL_select_Client = "Select id from client where name= " + add_quotes(iu_nome.toUpperCase()) + " AND phone=" + add_quotes(iu_tele.toUpperCase()) + " AND mobile=" + add_quotes(iu_cel.toUpperCase()) + " AND afm=" + add_quotes(iu_afm.toUpperCase());

    ResultSet rs = read_from_DB(SQL_select_Client);
    try {
      while (rs.next()) {
        String client_id_sting = rs.getString("id");
        try {
          client_id = Integer.parseInt(client_id_sting);
        } catch (NumberFormatException e) {
        }
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return client_id;
  }

  public int Create_Vehicles_and_GetID(String iu_targa, int iu_typo_amak, String iu_modello) {
    int car_id = -1;

    String SQL_insert_Vehicles = "INSERT INTO vehicles (number_plate,model,type) VALUES (" + add_quotes(iu_targa.toUpperCase()) + "," + add_quotes(iu_modello.toUpperCase()) + "," + iu_typo_amak + ");";
    write_to_DB(SQL_insert_Vehicles);

    String SQL_select_Vehicles = "Select id from vehicles where number_plate = " + add_quotes(iu_targa.toUpperCase());

    ResultSet rs = read_from_DB(SQL_select_Vehicles);
    try {
      while (rs.next()) {
        String car_id_sting = rs.getString("id");
        try {
          car_id = Integer.parseInt(car_id_sting);
        } catch (NumberFormatException e) {
        }
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return car_id;
  }

  public int Create_Contract_and_GetID(int id_posto, int id_client, double iu_prezzo, String iu_data_ricevutta, int iu_caa, int iu_extra, String username) {
    int Contract_id = -1;

    String SQL_insert_Contract = "Insert into contract (id_posto,id_client,prezzo,data_ricevutta,aa,extra,user) VALUES (" + id_posto + "," + id_client + "," + iu_prezzo + "," + add_quotes(iu_data_ricevutta.toUpperCase()) + "," + iu_caa + "," + iu_extra + "," + add_quotes(username) + ");";
    write_to_DB(SQL_insert_Contract);

    String SQL_select_Contract = "Select max(id) as id from contract where id_posto = " + id_posto + " AND id_client = " + id_client + " AND prezzo = " + iu_prezzo + " AND data_ricevutta = " + add_quotes(iu_data_ricevutta.toUpperCase()) + " AND aa = " + iu_caa + " AND extra=" + iu_extra + ";";

    ResultSet rs = read_from_DB(SQL_select_Contract);
    try {
      while (rs.next()) {
        String Contract_id_sting = rs.getString("id");
        try {
          Contract_id = Integer.parseInt(Contract_id_sting);
        } catch (NumberFormatException e) {
        }
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Contract_id;
  }

  public int Get_id_from_Posto(String iu_posto)
  {
    int posto_id = -1;

    String SQL_select_Posto = "Select id from posto where number = " + add_quotes(iu_posto.toUpperCase());

    ResultSet rs = read_from_DB(SQL_select_Posto);
    try {
      while (rs.next()) {
        String posto_id_sting = rs.getString("id");
        try {
          posto_id = Integer.parseInt(posto_id_sting);
        } catch (NumberFormatException e) {
        }
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return posto_id;
  }

  public int relazione_Contract_With_Car_and_GetID(int contract_id, int car_id, int first) {
    int Relazione_id = -1;

    String SQL_insert_Relazione = "Insert into contract_vehicles (id_contract,id_vehicle,first) VALUES (" + contract_id + "," + car_id + "," + first + ");";
    write_to_DB(SQL_insert_Relazione);

    String SQL_select_Relazione = "Select max(id) as id from contract_vehicles where id_contract = " + contract_id + " AND id_vehicle = " + car_id + " AND first = " + first;

    ResultSet rs = read_from_DB(SQL_select_Relazione);
    try {
      while (rs.next()) {
        String Relazione_id_sting = rs.getString("id");
        try {
          Relazione_id = Integer.parseInt(Relazione_id_sting);
        } catch (NumberFormatException e) {
        }
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Relazione_id;
  }

  public boolean New_Contract(ContractObj CO, String username)
  {
    CO.ClientID = Create_Client_and_GetID(CO.iu_nome, CO.iu_tele, CO.iu_cel, CO.iu_afm, CO.iu_doy, CO.iu_address, CO.iu_prof);
    if (CO.ClientID < 0) {
      try {
        this.con.rollback();
      } catch (SQLException ex) {
        Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    ContractCars CC = (ContractCars)CO.CC.get(Integer.valueOf(0));

    CC.CarID = Create_Vehicles_and_GetID(CC.iu_targa, CC.iu_typo_amak, CC.iu_modello);
    CC.first = 1;

    if (CC.CarID < 0) {
      try {
        this.con.rollback();
      } catch (SQLException ex) {
        Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    int posto_id = Get_id_from_Posto(CO.iu_posto);
    if (posto_id < 0) {
      try {
        this.con.rollback();
      } catch (SQLException ex) {
        Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    CO.ContractID = Create_Contract_and_GetID(posto_id, CO.ClientID, CO.iu_prezzo.doubleValue(), CO.iu_data_ricevutta, CO.iu_caa, CO.iu_extra, username);
    if (CO.ContractID < 0) {
      try {
        this.con.rollback();
      } catch (SQLException ex) {
        Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    CC.RelazioneID = relazione_Contract_With_Car_and_GetID(CO.ContractID, CC.CarID, CC.first);
    if (CC.RelazioneID < 0) {
      try {
        this.con.rollback();
      } catch (SQLException ex) {
        Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    CO.CC.put(Integer.valueOf(0), CC);
    return true;
  }

  public ContractObj Get_Contract(int contract_id) {
    ContractObj CO = new ContractObj();
    String SQL = "select    cl.name as iu_nome, cl.phone as iu_tele, cl.mobile as iu_cel, cl.afm as iu_afm, cl.doy as iu_doy, cl.address as iu_address, cl.type as iu_prof, \n\t\tco.dal as dal, co.al as al, co.prezzo as iu_prezzo, co.data_ricevutta as iu_data_ricevutta, co.aa as iu_caa, co.extra as iu_extra, \n\t\tv.number_plate as iu_targa, v.model as iu_modello, v.type as iu_typo_amak, \n\t\tp.piano as iu_piano, p.number as iu_posto,\n\t\tcv.first as first,\n\t\tco.id as ContractID, cl.id as ClientID, v.id as CarID, cv.id as RelazioneID\nfrom contract co, client cl, posto p, contract_vehicles cv, vehicles v\nwhere\nco.id_client = cl.id AND\nco.id_posto = p.id AND\nco.id = cv.id_contract AND\ncv.id_vehicle = v.id AND\nco.id = " + contract_id + "\n" + "order by first DESC ; ";

    ResultSet rs = read_from_DB(SQL);
    int loop_cic = 0;
    try {
      while (rs.next()) {
        CO.iu_extra = rs.getInt("iu_extra");
        CO.iu_nome = rs.getString("iu_nome");
        CO.iu_tele = rs.getString("iu_tele");
        CO.iu_cel = rs.getString("iu_cel");
        CO.iu_afm = rs.getString("iu_afm");
        CO.iu_doy = rs.getString("iu_doy");
        CO.iu_address = rs.getString("iu_address");
        CO.iu_prof = rs.getInt("iu_prof");
        CO.iu_start_date = rs.getString("dal");
        CO.iu_end_date = rs.getString("al");

        CO.iu_prezzo = Double.valueOf(rs.getDouble("iu_prezzo"));
        CO.iu_data_ricevutta = rs.getString("iu_data_ricevutta");
        CO.iu_caa = rs.getInt("iu_caa");

        ContractCars CC = new ContractCars();

        CC.iu_targa = rs.getString("iu_targa");
        CC.iu_modello = rs.getString("iu_modello");
        CC.iu_typo_amak = rs.getInt("iu_typo_amak");
        CC.CarID = rs.getInt("CarID");
        CC.RelazioneID = rs.getInt("RelazioneID");
        CC.first = rs.getInt("first");

        CO.CC.put(Integer.valueOf(loop_cic), CC);
        loop_cic++;

        CO.iu_posto = rs.getString("iu_posto");

        CO.ContractID = rs.getInt("ContractID");

        CO.ClientID = rs.getInt("ClientID");
      }
    }
    catch (SQLException ex)
    {
      CO = null;
      Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
    }

    SQL = "SELECT id, id_contract, prezzo, DATE_FORMAT(start, '%d/%m/%Y') as start,\nDATE_FORMAT(end, '%d/%m/%Y') as end,\nDATE_FORMAT(data_pagamento, '%d/%m/%Y') as data_pagamento,user, aa, af, bank\nFROM scontrino \n where id_contract=" + contract_id + " order by id;";

    rs = read_from_DB(SQL);
    loop_cic = 0;
    try
    {
      while (rs.next()) {
        ScontrinoObj CS = new ScontrinoObj();
        CS.id = rs.getInt("id");
        CS.ContractID = contract_id;
        CS.aa = rs.getInt("aa");
        CS.af = rs.getInt("af");
        CS.prezzo = Double.valueOf(rs.getDouble("prezzo"));
        CS.start = rs.getString("start");
        CS.end = rs.getString("end");
        CS.bank = rs.getInt("bank");
        CS.data_pagamento = rs.getString("data_pagamento");

        CS.user = rs.getString("user");

        CO.CS.put(Integer.valueOf(loop_cic), CS);
        loop_cic++;
      }
    } catch (SQLException ex) {
      CO = null;
      Logger.getLogger(ContractManipolation.class.getName()).log(Level.SEVERE, null, ex);
    }

    return CO;
  }

  public boolean Update_Contract(String Tabela, String Collona, int ID, Object Nuovo) {
    String SQL = "Update ";

    switch (Tabela) {
    case "ClientID":
      SQL = SQL + "client set ";
      if (Collona.equals("siu_nome")) {
        SQL = SQL + "name=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_tele")) {
        SQL = SQL + "phone=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_cel")) {
        SQL = SQL + "mobile=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_afm")) {
        SQL = SQL + "afm=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_doy")) {
        SQL = SQL + "doy=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_address")) {
        SQL = SQL + "address=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_prof")) {
        try {
          SQL = SQL + "type=" + Integer.parseInt(Nuovo.toString());
        } catch (NumberFormatException e) {
          SQL = SQL + "type=" + 0;
        }
      }
      else {
        return false;
      }
      break;
    case "CarID":
      SQL = SQL + "vehicles set ";
      if (Collona.equals("siu_targa")) {
        SQL = SQL + "number_plate=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_modello")) {
        SQL = SQL + "model=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_typo_amak"))
        try {
          SQL = SQL + "type=" + Integer.parseInt(Nuovo.toString());
        } catch (NumberFormatException e) {
          SQL = SQL + "type=" + 1;
        }
      else {
        return false;
      }
      break;
    case "RelazioneID":
      SQL = SQL + "contract_vehicles set ";
      if (Collona.equals("siu_first")) {
        try {
          SQL = SQL + "first=" + Integer.parseInt(Nuovo.toString());
        } catch (NumberFormatException e) {
          SQL = SQL + "first=" + 0;
        }
      }
      else {
        return false;
      }

      break;
    case "ContractID":
      SQL = SQL + "contract set ";
      if (Collona.equals("siu_data_ricevutta")) {
        SQL = SQL + "data_ricevutta=" + add_quotes(Nuovo.toString().toUpperCase());
      }
      else if (Collona.equals("siu_prezzo")) {
        try {
          try {
            SQL = SQL + "prezzo=" + ((Double)Nuovo).doubleValue();
          } catch (NumberFormatException e) {
            SQL = SQL + "prezzo=" + 0.0D;
          }
        }
        catch (ClassCastException e) {
          try {
            int Nuovo_int = ((Integer)Nuovo).intValue();
            Double Nuovo_double = Double.valueOf(Nuovo_int);
            SQL = SQL + "prezzo=" + Nuovo_double;
          } catch (ClassCastException ee) {
            SQL = SQL + "prezzo=" + Double.parseDouble(Nuovo.toString());
          }
        }

      }
      else if (Collona.equals("siu_caa")) {
        try {
          SQL = SQL + "aa=" + Integer.parseInt(Nuovo.toString());
        } catch (NumberFormatException e) {
          SQL = SQL + "aa=" + 0;
        }
      }
      else if (Collona.equals("siu_posto")) {
        int posto_id = Get_id_from_Posto(Nuovo.toString());
        SQL = SQL + "id_posto=" + posto_id;
      }
      else {
        return false;
      }
      break;
    default:
      return false;
    }

    SQL = SQL + " where id=" + ID;

    return write_to_DB(SQL);
  }

  public boolean Delete_Contract(int ContractID, String why, String Username)
  {
    String SQL = "UPDATE `contract` SET al = (select CURRENT_TIMESTAMP()), why=" + add_quotes(why) + " where id = " + ContractID + ";";
    String SQL2 = "insert into scontrino (id_contract,prezzo,start,end,data_pagamento,user,aa) \nselect id as id_contract, prezzo, date(dal), date(al), date(al), " + add_quotes(Username) + ", aa \n" + "from contract \n" + "where id=" + ContractID + " AND extra=1; ";

    if (write_to_DB(SQL)) {
      return write_to_DB(SQL2);
    }
    return false;
  }

  public boolean Update_Rows(String operation, String table, int id, int ContractID, int col, Object[] val, String username, String SessionID)
  {
    String SQL = "";
    int ret_i = -1;
    String SQL_log = "";
    String ContractID_html = "<a onclick=\"get_contract_from_server("+ ContractID + ");\" href=\"javascript:void(null);\">" + ContractID + "</a>";

    if (operation.equals("delete")) {
      SQL = SQL + "delete from ";
      SQL_log = SQL_log + "deletion ";

      if (table.equals("car")) {
        SQL = SQL + "contract_vehicles ";
        SQL_log = SQL_log + "Car  ";
      } else if (table.equals("scontrini")) {
        SQL = SQL + "scontrino ";
        SQL_log = SQL_log + "proof ";
      }

      SQL = SQL + "where id = " + id;
      SQL_log = SQL_log + "from the contract " + ContractID_html;

      if (write_to_DB(SQL)) {
        write_to_LOG_html(SQL_log, SessionID);
        return true;
      }
      return false;
    }

    if (operation.equals("insert")) {
      SQL_log = SQL_log + "Registration ";

      if (table.equals("car")) {
        int carID = Create_Vehicles_and_GetID(val[0].toString(), Integer.parseInt(val[2].toString()), val[1].toString());
        int RelID = relazione_Contract_With_Car_and_GetID(ContractID, carID, Integer.parseInt(val[3].toString()));

        SQL_log = SQL_log + "car for the contract " + ContractID_html;
        write_to_LOG_html(SQL_log, SessionID);
        return true;
      }
      if (table.equals("scontrini")) {
        String data_pagamento = "null";
        try
        {
          if (val[0].toString().length() < 3) data_pagamento = "null"; else
            data_pagamento = "STR_TO_DATE('" + add_replace(val[0].toString()) + "', '%d/%m/%Y')";
        } catch (NullPointerException r) {
          data_pagamento = "null";
        }

        String prezzo_str = "";
        try {
          prezzo_str = prezzo_str + ((Double)val[4]).doubleValue();
        } catch (NumberFormatException e) {
          return false;
        } catch (ClassCastException e) {
          try {
            int Nuovo_int = ((Integer)val[4]).intValue();
            Double Nuovo_double = Double.valueOf(Nuovo_int);
            prezzo_str = prezzo_str + Nuovo_double;
          } catch (ClassCastException ee) {
            prezzo_str = prezzo_str + Double.parseDouble(val[4].toString());
          }
        }
        try
        {
          SQL = "INSERT INTO `scontrino` (`id_contract`, `prezzo`, `start`, `end`, `data_pagamento`, `user`, `aa`, `af`,bank) \nVALUES (" + ContractID + "," + prezzo_str + "," + "STR_TO_DATE('" + add_replace(val[2].toString()) + "', '%d/%m/%Y')," + "STR_TO_DATE('" + add_replace(val[3].toString()) + "', '%d/%m/%Y')," + data_pagamento + "," + add_quotes(username) + "," + Integer.parseInt(val[5].toString()) + "," + Integer.parseInt(val[1].toString()) + "," + Integer.parseInt(val[6].toString()) + ")";
        }
        catch (NumberFormatException e)
        {
          return false;
        }
        SQL_log = SQL_log + "Proof for the contract " + ContractID_html + ", Price: " + prezzo_str + "&euro;, Α/Α: " + Integer.parseInt(val[1].toString());
        write_to_LOG_html(SQL_log, SessionID);
        return write_to_DB(SQL);
      }

    }
    else if (operation.equals("update")) {
      SQL_log = SQL_log + "Update ";
      if (table.equals("car")) {
        if (col == 0) {
          SQL = "update vehicles\nset number_plate=" + add_quotes(val[0].toString().toUpperCase()) + " \n" + "where id = \n" + "(SELECT id_vehicle FROM contract_vehicles WHERE id=" + id + ");";
        }
        else if (col == 1) {
          SQL = "update vehicles\nset model=" + add_quotes(val[0].toString().toUpperCase()) + " \n" + "where id = \n" + "(SELECT id_vehicle FROM contract_vehicles WHERE id=" + id + ");";
        }
        else if (col == 2) {
          SQL = "update vehicles\nset type=" + Integer.parseInt(val[0].toString()) + " \n" + "where id = \n" + "(SELECT id_vehicle FROM contract_vehicles WHERE id=" + id + ");";
        }
        else if (col == 3) {
          SQL = "update contract_vehicles\nset first=" + Integer.parseInt(val[0].toString()) + " \n" + " WHERE id=" + id + ";";
        }

        SQL_log = SQL_log + "car for the contract " + ContractID_html;
      }
      else if (table.equals("scontrini")) {
        SQL_log = SQL_log + "proof for the contract " + ContractID_html + ", ";
        String data_pagamento = "null";
        try
        {
          if (val[0].toString().length() < 3) data_pagamento = "null"; else
            data_pagamento = "STR_TO_DATE('" + add_replace(val[0].toString()) + "', '%d/%m/%Y')";
        } catch (NullPointerException r) {
          data_pagamento = "null";
        }

        if (col == 0) {
          SQL = "update scontrino\nset user= " + add_quotes(username) + ", data_pagamento=" + data_pagamento + " \n" + "where id =" + id;

          SQL_log = SQL_log + "Date of proof:" + data_pagamento;
        }
        else if (col == 1) {
          try {
            SQL = "update scontrino\nset  user= " + add_quotes(username) + ", af=" + Integer.parseInt(val[0].toString()) + " \n" + "where id =" + id;

            SQL_log = SQL_log + "Α/F:" + Integer.parseInt(val[0].toString());
          } catch (NumberFormatException e) {
            return false;
          }
        }
        else if (col == 2) {
          SQL = "update scontrino\nset  user= " + add_quotes(username) + ", start=" + "STR_TO_DATE('" + add_replace(val[0].toString()) + "', '%d/%m/%Y')" + " \n" + "where id =" + id;

          SQL_log = SQL_log + "Ημερομηνία Έναρξης:" + add_replace(val[0].toString());
        } else if (col == 3) {
          SQL = "update scontrino\nset  user= " + add_quotes(username) + ", end=" + "STR_TO_DATE('" + add_replace(val[0].toString()) + "', '%d/%m/%Y')" + " \n" + "where id =" + id;

          SQL_log = SQL_log + "Ημερομηνία λήξης:" + add_replace(val[0].toString());
        } else if (col == 4) {
          SQL = "update scontrino\nset  user= " + add_quotes(username) + ", prezzo=";
          try
          {
            SQL = SQL + ((Double)val[0]).doubleValue();
            SQL_log = SQL_log + "Price: " + ((Double)val[0]).doubleValue() + "&euro; ";
          } catch (NumberFormatException e) {
            return false;
          }
          catch (ClassCastException e) {
            try {
              int Nuovo_int = ((Integer)val[0]).intValue();
              Double Nuovo_double = Double.valueOf(Nuovo_int);
              SQL = SQL + Nuovo_double;
              SQL_log = SQL_log + "Price: " + Nuovo_double + "&euro; ";
            } catch (ClassCastException ee) {
              SQL = SQL + Double.parseDouble(val[0].toString());
              SQL_log = SQL_log + "Price: " + Double.parseDouble(val[0].toString()) + "&euro; ";
            }
          }
          SQL = SQL + "  where id =" + id;
        }
        else if (col == 5) {
          try {
            SQL = "update scontrino\nset  user= " + add_quotes(username) + ", aa=" + Integer.parseInt(val[0].toString()) + " \n" + "where id =" + id;

            SQL_log = SQL_log + "Α/Α: " + Integer.parseInt(val[0].toString());
          } catch (NumberFormatException e) {
            return false;
          }
        } else if (col == 6) {
          try {
            SQL = "update scontrino\nset  user= " + add_quotes(username) + ", bank=" + Integer.parseInt(val[0].toString()) + " \n" + "where id =" + id;

            SQL_log = SQL_log + "Payment Method: " + Integer.parseInt(val[0].toString());
          } catch (NumberFormatException e) {
            return false;
          }
        }
      }
      if (write_to_DB(SQL)) {
        write_to_LOG_html(SQL_log, SessionID);
        return true;
      }
      return false;
    }

    return true;
  }
}
