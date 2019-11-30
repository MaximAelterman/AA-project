/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_pkg;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Max
 */
@Local
public interface DatabaseLocal {
    //List<Machines> getMachines();
    Object getGebruiker(String l);
    List getMachines();
    List getReservaties(int m);
}
