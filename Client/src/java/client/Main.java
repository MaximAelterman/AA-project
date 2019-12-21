/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import beans.*;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Max
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static DatabaseRemote db = null;
    
    public static void main(String[] args) throws NamingException {
        // TODO code application logic here
        InitialContext ic = new InitialContext();
        db = (DatabaseRemote) ic.lookup(DatabaseRemote.class.getName());
        List<Machines> machines = db.getMachines();
        Gui gui = new Gui(machines);
        gui.setVisible(true);
        
    }
    
}
