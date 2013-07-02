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

import java.util.Hashtable;

public class ContractObj
{
  public int ContractID;
  public int ClientID;
  public Hashtable CC;
  public Hashtable CS;
  public String iu_nome;
  public String iu_tele;
  public String iu_cel;
  public String iu_afm;
  public String iu_doy;
  public String iu_address;
  public int iu_prof;
  public String iu_start_date;
  public String iu_end_date;
  public String iu_posto;
  public Double iu_prezzo;
  public String iu_data_ricevutta;
  public int iu_caa;
  public int iu_extra;

  public ContractObj()
  {
    this.CC = new Hashtable();
    this.CS = new Hashtable();

    this.ContractID = -1;
    this.ClientID = -1;
    this.iu_extra = 0;
    this.iu_nome = "";
    this.iu_address = "";
    this.iu_tele = "";
    this.iu_cel = "";
    this.iu_afm = "";
    this.iu_doy = "";
    this.iu_address = "";
    this.iu_prof = 0;
    this.iu_start_date = "";
    this.iu_end_date = "";
    this.iu_posto = "";

    this.iu_prezzo = Double.valueOf(0.0D);
    this.iu_data_ricevutta = "";
    this.iu_caa = 0;
  }
}
