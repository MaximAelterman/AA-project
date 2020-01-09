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
 * @author Max & Jeroen 
 */
@Stateless
@Local ({DatabaseLocal.class})
@Remote ({DatabaseRemote.class})
public class Database implements DatabaseLocal, DatabaseRemote {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext private EntityManager em;
    
    public Database(){}

    // functie voor ophalen van de gegevens van de ingelogde gebruiker    
    @Override
    public Object getGebruiker(String l){
        Object gebr = em.createQuery("SELECT g FROM Gebruikers g WHERE g.gebruikersnaam LIKE :login").setParameter("login",l).getSingleResult();
        return gebr;
    }

    // functie voor ophalen van de opleiding v/d gebruiker    
    @Override
    public String getOpleiding(String naam)
    {
        Query query = em.createQuery("SELECT g.opleiding from Gebruikers g WHERE g.gebruikersnaam = :naam");
        query.setParameter("naam", naam);
        return (String)query.getSingleResult();
    }
  
    // functie voor ophalen alle machines    
    @Override
    public List getMachines() {
        // List<Machines> machinelijst;
        //machinelijst = (List<Machines>) em.createNamedQuery("Machines.findAll").getResultList();
        List machinelijst  = em.createNamedQuery("Machines.findAll").getResultList();
        return machinelijst;
    }

    // functie voor ophalen machine met bepaalde mnr    
    @Override
    public Machines getMachine(BigDecimal mnr){
        Object machine = em.createQuery("SELECT m FROM Machines m WHERE m.mnr = :mnr").setParameter("mnr",mnr).getSingleResult();
        return (Machines) machine;
    }

    // functie voor ophalen van de de momenten van een bepaalde machine met mnr
    @Override
    public List getMachineMomenten(Object mnr){
        List<Momenten> mom = (List<Momenten>) em.createQuery("SELECT m FROM Momenten m WHERE m.mnr = :mnr").setParameter("mnr",mnr).getResultList();
        return mom;
    }
    
    // functie voor ophalen van alle reservaties momenten    
    @Override
    public List getMomenten(){
        List mom = em.createNamedQuery("Momenten.findAll").getResultList();
        return mom;
    }
    
    @Override
    public Object getMoment(BigDecimal momid){
        Object moment = em.createQuery("Select m FROM Momenten m where m.momid = :momid").setParameter("momid", momid).getSingleResult();
        return moment;
    }
    
    // functie voor het toevoegen van een nieuw machine
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
    
    // functie voor het toevoegen van een (reservatie) moment
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

    // functie voor het aanpassen van een bestaande machine 
    @Override
    public void wijzigMachine(BigDecimal mnr, String naam, String serienr, String locatie, String opleiding, String aankoopprijs, String huurprijs, String omschrijving){
        try {
            Machines machine = (Machines) em.createQuery("SELECT m FROM Machines m WHERE m.mnr = :mnr").setParameter("mnr",mnr).getSingleResult();
            //em.createNamedQuery("Machines.findByMnr", Machines.class).setParameter("mnr", ((Machines)mnr).getMnr()).getSingleResult();
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

    // functie voor het ophalen van Reservaties van een bestaande machine    
    @Override
    public List getReservaties(BigDecimal mnr){
        List res = em.createQuery("SELECT r FROM Reservaties r WHERE EXISTS (SELECT 'moment' FROM Momenten m WHERE r.momid = m AND m.mnr.mnr = :mnr)").setParameter("mnr", mnr).getResultList();
        return res;
    }

    
    // functie voor ophalen van het volgende machine nr. wordt gebruikt in Addmachine    
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

    // functie voor ophalen van de gebruiker reservaties momenten
   @Override
    public String getUserResMomid(Object momid) {
        Object res = em.createQuery("SELECT r.gebruikersnaam.gebruikersnaam FROM Reservaties r WHERE r.momid = :momid").setParameter("momid",momid).getSingleResult();
        return (String)res;
    }

    // functie voor het controleren om een moment vrij is. of het moment reeds gebruikt is voor een reservatie
    @Override
    public boolean isVrij(Object momid){
        List res = em.createQuery("SELECT r FROM Reservaties r WHERE r.momid = :momid").setParameter("momid",momid).getResultList();
        return (res.isEmpty()) ;
    }
    
    @Override
    public boolean MomentCheck(Object mnr, String start, String duurtijd, String datum)
    {
        boolean oke = true;
        List<Momenten> mom = getMachineMomenten(mnr);
        Date dat1 = Date.valueOf(datum);
        BigInteger startuur = new BigInteger(start);
        List <Momenten> result = em.createQuery("SELECT m FROM Momenten m WHERE m.datum = :datum AND m.mnr = :mnr").setParameter("datum",dat1).setParameter("mnr", mnr).getResultList();
        int i = 0;
        while (i<result.size() && oke == true)
        {
            int Rstart = (result.get(i).getStrt()).intValue();
            int Rduur = (result.get(i).getDuur()).intValue();
            int sum = Integer.sum(Rstart, Rduur);
            System.out.printf("startuur:%d duurtijd:%d \n", Rstart, Rduur);
            
            if (sum > startuur.doubleValue())
            {
                oke = false;
            }
            if (sum == startuur.doubleValue())
            {
                oke = true;
            }
            
        i++;
        }
      
      return oke;
    }
    
    // functie voor het reserveren van een moment
    @Override
    public void reserveer(Momenten moment, Gebruikers gebruiker) {
        try {
            Reservaties res = new Reservaties();
            BigDecimal resid = volgendResid();
            res.setRnr(resid);
            res.setMomid(moment);
            res.setGebruikersnaam(gebruiker);
            em.persist(res);
        } catch (Exception eee) {
            out.println("Fout bij reservatie van moment: " + eee);
        }

    }
    
    // functie voor ophalen van het volgende reservatie nr. wordt gebruikt in reserveer    
    @Override
    public BigDecimal volgendResid() {
        BigDecimal laatsteResid;
        BigDecimal volgendeResid;
        long aantalRes = (long) em.createQuery("SELECT COUNT(r.rnr) FROM Reservaties r").getSingleResult();
        if(aantalRes == 0) {
            return new BigDecimal(1);
        }
        else {
            laatsteResid = (BigDecimal) em.createQuery("SELECT MAX(r.rnr) FROM Reservaties r").getSingleResult();
            volgendeResid = laatsteResid.add(new BigDecimal(1));
            return volgendeResid;
        }
    }
    
}
