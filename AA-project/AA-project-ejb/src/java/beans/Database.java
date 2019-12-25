/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static java.lang.System.out;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Max
 */
@Stateless
@Local ({DatabaseLocal.class})
// @Remote ({DatabaseRemote.class})
public class Database implements DatabaseLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext private EntityManager em;
    
    public Database(){}

    @Override
    public Object getGebruiker(String l){
        Gebruikers gebr = (Gebruikers) em.createNamedQuery("Gebruikers.findByLogin").setParameter("login",l).getSingleResult();
        return gebr;
    }
    
    @Override
    public String getOpleiding(String naam)
    {
        Query query = em.createQuery("SELECT g.opleiding from Gebruikers g WHERE g.gebruikersnaam = :naam");
        query.setParameter("naam", naam);
        return (String)query.getSingleResult();
    }
  
    @Override
    public List getMachines() {
        // List<Machines> machinelijst;
        //machinelijst = (List<Machines>) em.createNamedQuery("Machines.findAll").getResultList();
        List machinelijst  = em.createNamedQuery("Machines.findAll").getResultList();
        return machinelijst;
    }
    

  /*  @Override
    public Machines getMachine(BigDecimal mnr){
        Query query = em.createQuery("SELECT m FROM Machines m WHERE m.mnr = :mnr").setParameter("mnr",mnr);
        query.setParameter("mnr", mnr);
        return (Machines)query.getSingleResult();
    */
    @Override
    public Machines getMachine(BigDecimal mnr){
        Object machine = em.createQuery("SELECT m FROM Machines m WHERE m.mnr = :mnr").setParameter("mnr",mnr).getSingleResult();
        return (Machines) machine;
    }
    
    @Override
    public List getMachineMomenten(BigDecimal mnr){
        List<Momenten> mom = (List<Momenten>) em.createQuery("SELECT m FROM Momenten m WHERE m.mnr = :mnr").setParameter("mnr",mnr).getResultList();
        return mom;
    }
    
    @Override
    public List getMomenten(){
        List mom = em.createNamedQuery("Momenten.findAll").getResultList();
        return mom;
    }
    
    @Override
    public BigDecimal addMachine(String naam, String serienr, String locatie, String opleiding, String aankoopprijs, String huurprijs, String omschrijving){
        try {
            Machines machine = new Machines();
            BigDecimal mnr = volgendMnr();
            machine.setMnr(mnr);
            machine.setMnaam(naam);
            machine.setSerienr(new BigInteger(serienr));
            machine.setMloc(locatie);
            machine.setOpleiding(opleiding);
            machine.setAankoopprijs(new BigInteger(aankoopprijs));
            machine.setHuurprijs(new BigInteger(huurprijs));
            machine.setOmschrijving(omschrijving);
            em.persist(machine);
            return mnr;
        } catch (Exception eee) {
            out.println("Fout bij aanmaken van machine: " + eee);
            return new BigDecimal(0);
        }
    }
    @Override
    public void addMoment(Object mnr, String start, String duurtijd, String datum){
        int nr;
        try{            
            BigDecimal res;
            res =(BigDecimal) em.createQuery("SELECT max(m.momid) FROM Momenten m").getSingleResult();
            nr = res.intValue();
        }
        catch (Exception e) {
            nr = 0;
            out.println("Er werden geen momenten gevonden: " + e);
        }
        
       BigDecimal momid = new BigDecimal(nr+1);
       Momenten moment = new Momenten(momid);
       moment.setMnr((Machines) mnr);
       moment.setStrt(new BigInteger(start));
       moment.setDuur(new BigInteger(duurtijd));
       moment.setDatum(Date.valueOf(datum));
       em.persist(moment);
    }
    
    @Override
    public void wijzigMachine(Object mnr, String naam, String serienr, String locatie, String opleiding, String aankoopprijs, String huurprijs, String omschrijving){
        try {
            Machines machine= em.createNamedQuery("Machines.findByMnr", Machines.class).setParameter("mnr", ((Machines)mnr).getMnr()).getSingleResult();
            //Machines machine = em.find(Machines.class, (Machines) mnr);
            machine.setMnaam(naam);
            machine.setSerienr(new BigInteger(serienr));
            machine.setMloc(locatie);
            machine.setOpleiding(opleiding);
            machine.setAankoopprijs(new BigInteger(aankoopprijs));
            machine.setHuurprijs(new BigInteger(huurprijs));
            machine.setOmschrijving(omschrijving);
            em.persist(machine);
//            return mnr;
        } catch (Exception eee) {
            out.println("Fout bij wijzigen van machine: " + eee);
            //return new BigDecimal(0);
        }
    }

    @Override
    public List getReservaties(int m){
        List<Reservaties> res= em.createQuery("SELECT r FROM Reservaties").getResultList();
        return res;
    }
    
    @Override
    public BigDecimal volgendMnr() {
        BigDecimal laatsteMnr;
        BigDecimal volgendeMnr;
        long aantalRes = (long) em.createQuery("SELECT COUNT(m.mnr) FROM Machines m").getSingleResult();
        if(aantalRes == 0) {
            return new BigDecimal(1);
        }
        else {
            laatsteMnr = (BigDecimal) em.createQuery("SELECT MAX(m.mnr) FROM Machines m").getSingleResult();
            volgendeMnr = laatsteMnr.add(new BigDecimal(1));
            return volgendeMnr;
        }
    }
    
}
