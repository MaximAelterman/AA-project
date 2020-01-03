/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import beans.Machines;
import beans.DatabaseLocal;
import beans.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author r0631
 */
// @WebServlet(urlPatterns = {"/controller.do"})
public class controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB private DatabaseLocal db;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessie = request.getSession();
        String knop = request.getParameter("knop");
        
        String gebruiker = request.getRemoteUser();
        String opleid = db.getOpleiding(gebruiker);
        sessie.setAttribute("gebruiker", gebruiker);
        sessie.setAttribute("opleiding", opleid);
        if (request.isUserInRole("Docent")){
            sessie.setAttribute("groep","Docent");
        }
        else if (request.isUserInRole("Student")){
            sessie.setAttribute("groep","Student");
        }
        else{
            sessie.setAttribute("groep","Extern");
        }
        
        switch (knop) {
            case "Overzicht":
            {
                HerladenMachines();
                RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                view.forward (request, response);
                break;
            }
            case "Logout":
            {
               /* sessie.removeAttribute("groep");
                sessie.removeAttribute("gebruiker");
                sessie.removeAttribute("opleiding");*/
                sessie.invalidate();
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward (request, response);
                //response.sendRedirect("controller.do" );
                break;
            }
            case "Details":
            {
                BigDecimal mnr = new BigDecimal(request.getParameter("details"));
                Machines machine = db.getMachine(mnr);
                sessie.setAttribute("machine", machine);
                RequestDispatcher view = request.getRequestDispatcher("details.jsp");
                view.forward(request, response);
                break;
            }
            case "Nieuwe machine":
            {
                RequestDispatcher view = request.getRequestDispatcher("machine.jsp");
                view.forward(request, response);
                break;
            }
            case "Machine toevoegen":
            {
                String naam = request.getParameter("naam");
                String locatie = request.getParameter("locatie");
                String opleiding = request.getParameter("opleiding");
                String serienr = request.getParameter("serienr");
                String aankoopprijs = request.getParameter("aankoopprijs");
                String huurprijs = request.getParameter("huurprijs");
                String omschrijving = request.getParameter("omschrijving");
                
                BigDecimal nr =  db.addMachine(naam,serienr, locatie, opleiding, aankoopprijs, huurprijs, omschrijving);
                HerladenMachines();                                         //om de machinelijst in de applicatie opnieuw in te laden
                
                RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                view.forward(request, response);
                break;
            }
            case "Wijzig":
            {
                BigDecimal mnr = new BigDecimal(request.getParameter("details"));
                Machines machine = db.getMachine(mnr);
                
                sessie.setAttribute("machine", machine);
                RequestDispatcher view = request.getRequestDispatcher("wijzig_machine.jsp");
                view.forward(request, response);
                break;
            }
            case "Wijzigingen opslaan":
            {
                BigDecimal mnr = new BigDecimal(request.getParameter("mnr"));
                
                String naam = request.getParameter("naam");
                String locatie = request.getParameter("locatie");
                String opleiding = request.getParameter("opleiding");
                String serienr = request.getParameter("serienr");
                String aankoopprijs = request.getParameter("aankoopprijs");
                String huurprijs = request.getParameter("huurprijs");
                String omschrijving = request.getParameter("omschrijving");
                
                db.wijzigMachine(mnr, naam, serienr, locatie, opleiding, aankoopprijs, huurprijs, omschrijving);
               
                HerladenMachines();     //om de machinelijst in de applicatie opnieuw in te laden
                          
                RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                view.forward(request, response);
                break;
            }
            
            case "MachineMoment":
            {
                BigDecimal mnr = new BigDecimal(request.getParameter("details"));
                Machines machine = db.getMachine(mnr);
                List<Momenten> machinemom = db.getMachineMomenten(machine);   // ophalen van machine momenten
                sessie.setAttribute("machinemom",machinemom);
                sessie.setAttribute("test", "oke");
                RequestDispatcher view = request.getRequestDispatcher ("details.jsp" );
                view.forward (request,response );
            }
            
            case "Moment toevoegen":
            {
                String start = request.getParameter("start");
                String duurtijd = request.getParameter("duur");
                String datum = request.getParameter("datum");
                db.addMoment(sessie.getAttribute("machine"), start, duurtijd, datum);
                
                RequestDispatcher view = request.getRequestDispatcher("details.jsp");
                view.forward (request,response);
                break;
            }
            
            case "Reserveer":
            {
                BigDecimal mnr = new BigDecimal(request.getParameter("details"));
                
                Machines mach = (Machines) db.getMachine(mnr);
                sessie.setAttribute("machine",mach);
               
                List <Momenten> ResMom = new ArrayList<>();
                List <Momenten> VrijMom = db.getMachineMomenten(mach);   // ophalen van machine momenten
                List<String> user = new ArrayList<>();
                
                for(int i=0;i<VrijMom.size();){
                    if(!db.isVrij(VrijMom.get(i))) {
                        ResMom.add(VrijMom.get(i));
                        user.add(db.getUserResMomid(VrijMom.get(i)));
                        VrijMom.remove(i);  
                    } 
                    else{
                        i++;
                    }
                }
                sessie.setAttribute("vrijmom",VrijMom);
                sessie.setAttribute("resmom",ResMom);
                sessie.setAttribute("user",user);

                
                RequestDispatcher view = request.getRequestDispatcher ("reservatie.jsp" );
                view.forward (request,response );
                break;
            }
            case "Reserveer moment":
            {
                Gebruikers gebr = (Gebruikers)db.getGebruiker(gebruiker);
                Momenten moment = (Momenten)db.getMoment(new BigDecimal(request.getParameter("momid")));
                db.reserveer(moment, gebr);
                
                    // lijst met reservaties herladen
                Machines mach = (Machines) sessie.getAttribute("machine");
                List <Momenten> ResMom = new ArrayList<>();
                List <Momenten> VrijMom = db.getMachineMomenten(mach);
                List<String> user = new ArrayList<>();
                
                for(int i=0;i<VrijMom.size();){
                    if(!db.isVrij(VrijMom.get(i))) {
                        ResMom.add(VrijMom.get(i));
                        user.add(db.getUserResMomid(VrijMom.get(i)));
                        VrijMom.remove(i);  
                    } 
                    else{
                        i++;
                    }
                }
                sessie.setAttribute("vrijmom",VrijMom);
                sessie.setAttribute("resmom",ResMom);
                sessie.setAttribute("user",user);
                
                RequestDispatcher view = request.getRequestDispatcher ("reservatie.jsp" );
                view.forward (request,response );  
                break;
            }
            
            default:
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    @Override
    public void init()
    {
        HerladenMachines();
    }
    
    public void HerladenMachines()
    {
        List<Machines> ma= db.getMachines();
        getServletContext().setAttribute("machines",ma);
    }
}

