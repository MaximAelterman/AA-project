/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import beans.*;
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
            //wijzen naar Database bean via InitialContext
        InitialContext ic = new InitialContext();
        db = (DatabaseRemote) ic.lookup(DatabaseRemote.class.getName());
        Gui gui = new Gui(db);
        gui.setVisible(true);
        
    }
    
}
