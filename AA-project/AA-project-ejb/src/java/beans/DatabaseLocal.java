/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Max
 */
@Local
public interface DatabaseLocal {
    public Object getGebruiker(String l);
    public List getMachines();
    public List getReservaties(int m);
}