/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Max
 */
@Stateless
public class Database implements DatabaseLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext private EntityManager em;
    
    @Override
    public List<Machines> getMachines() {
        List<Machines> machinelijst;
        machinelijst = (List<Machines>) em.createQuery("SELECT m FROM Machines m").getResultList();
        return machinelijst;
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
    public List getReservaties(int m){
        List<Reservaties> res= em.createQuery("SELECT r FROM Reservaties").getResultList();
        return res;
    }
    
}
