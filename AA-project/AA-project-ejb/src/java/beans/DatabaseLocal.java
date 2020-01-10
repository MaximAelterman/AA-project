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
 * @author r0631
 */
@Local
public interface DatabaseLocal {
    public Object getGebruiker(String l);
    public List getMachines();
    public String getOpleiding(String naam);
    public Machines getMachine(BigDecimal mnr);
    public List getMachineMomenten(Object mnr);
    public List getMomenten();
    public Object getMoment(BigDecimal momid);
    public void addMachine(String naam, String serienr, String locatie, String opleiding, String aankoopprijs, String huurprijs, String omschrijving);
    public void wijzigMachine(BigDecimal mnr, String naam, String serienr, String locatie, String opleiding, String aankoopprijs, String huurprijs, String omschrijving);
    public List getReservaties(BigDecimal Momnr);
    public void addMoment(Object mnr, String strt, String duurtijd, String datum);
    public BigDecimal volgendMnr();
    public boolean isVrij(Object momid);
    public String getUserResMomid(Object momid);
    public void reserveer(Momenten momid, Gebruikers gebruiker);
    public BigDecimal volgendResid();
    public boolean MomentCheck(Object mnr, String start, String duurtijd, String datum);

}
