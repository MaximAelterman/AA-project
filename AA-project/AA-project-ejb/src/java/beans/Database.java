/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static java.lang.System.out;
import java.math.BigDecimal;
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
@Remote ({DatabaseRemote.class})
public class Database implements DatabaseLocal, DatabaseRemote {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext private EntityManager em;
    
        public Database(){}

    
    @Override
    public List<Machines> getMachines() {
        List<Machines> machinelijst;
        machinelijst = (List<Machines>) em.createQuery("SELECT m FROM Machines m").getResultList();
        return machinelijst;
    }
    
    @Override
    public String getOpleiding(String naam)
    {
        Query query = em.createQuery("SELECT g.opleiding from Gebruikers g WHERE g.gebruikersnaam = :naam");
        query.setParameter("naam", naam);
        return (String)query.getSingleResult();
    }
    
    @Override
    public Object getGebruiker(String l){
        Gebruikers gebr = (Gebruikers) em.createNamedQuery("Gebruikers.findByLogin").setParameter("login",l).getSingleResult();
        return gebr;
    }
    
    @Override
    public Machines getMachine(BigDecimal mnr){
        Query query = em.createQuery("SELECT m FROM Machines m WHERE m.mnr = :mnr");
        query.setParameter("mnr", mnr);
        return (Machines)query.getSingleResult();
    }
    
    @Override
    public BigDecimal addMachine(String naam, String locatie, String opleiding, BigDecimal aankoopprijs, BigDecimal huurprijs, String omschrijving){
        try {
            Machines machine = new Machines();
            BigDecimal mnr = volgendMnr();
            machine.setMnr(mnr);
            machine.setMnaam(naam);
            machine.setMloc(locatie);
            machine.setOpleiding(opleiding);
            machine.setAankoopprijs(aankoopprijs.doubleValue());
            machine.setHuurprijs(huurprijs.doubleValue());
            machine.setOmschrijving(omschrijving);
            em.persist(machine);
            return mnr;
        } catch (Exception eee) {
            out.println("Fout bij aanmaken van machine: " + eee);
            return new BigDecimal(0);
        }
    }
    
    @Override
    public BigDecimal wijzigMachine(BigDecimal mnr, String naam, String locatie, String opleiding, BigDecimal aankoopprijs, BigDecimal huurprijs, String omschrijving){
        try {
            Machines machine = em.find(Machines.class, mnr);
            machine.setMnaam(naam);
            machine.setMloc(locatie);
            machine.setOpleiding(opleiding);
            machine.setAankoopprijs(aankoopprijs.doubleValue());
            machine.setHuurprijs(huurprijs.doubleValue());
            machine.setOmschrijving(omschrijving);
            em.persist(machine);
            return mnr;
        } catch (Exception eee) {
            out.println("Fout bij wijzigen van machine: " + eee);
            return new BigDecimal(0);
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
