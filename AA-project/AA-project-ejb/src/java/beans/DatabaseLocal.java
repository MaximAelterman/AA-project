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
    public Machines getMachine(BigDecimal mnr);
    public List getReservaties(int m);
}
