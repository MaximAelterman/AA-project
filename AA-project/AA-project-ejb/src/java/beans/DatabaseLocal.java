/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Max
 */
@Local
public interface DatabaseLocal {
    public Object getGebruiker(String l);
    public List<Machines> getMachines();
    public String getOpleiding(String naam);
    public Machines getMachine(BigDecimal mnr);
    public List getMachineMomenten(Object mnr);
    public List getMomenten();
    public BigDecimal addMachine(String naam, String serienr, String locatie, String opleiding, String aankoopprijs, String huurprijs, String omschrijving);
    public void wijzigMachine(Object mnr, String naam, String serienr, String locatie, String opleiding, String aankoopprijs, String huurprijs, String omschrijving);
    public List getReservaties(int m);
    public void addMoment(Object mnr, String strt, String duurtijd, String datum);
    public BigDecimal volgendMnr();
    public boolean isVrij(Object momid);
    public String getUserResMomid(Object momid);
}
