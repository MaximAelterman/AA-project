/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import beans.Machines;
import beans.DatabaseLocal;
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
                String gebruiker = request.getRemoteUser();
                String opleiding = db.getOpleiding(gebruiker);
                sessie.setAttribute("gebruiker", gebruiker);
                sessie.setAttribute("opleiding", opleiding);
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
                BigDecimal aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
                BigDecimal huurprijs = new BigDecimal(request.getParameter("huurprijs"));
                String omschrijving = request.getParameter("omschrijving");
                db.addMachine(naam, locatie, opleiding, aankoopprijs, huurprijs, omschrijving);
                init();     //om de machinelijst in de applicatie opnieuw in te laden
                RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                view.forward(request, response);
                break;
            }
            case "Wijzig":
            {
                BigDecimal mnr = new BigDecimal(request.getParameter("details"));
                Machines machine = db.getMachine(mnr);
                sessie.setAttribute("mnr", mnr);
                sessie.setAttribute("machine", machine);
                RequestDispatcher view = request.getRequestDispatcher("wijzig_machine.jsp");
                view.forward(request, response);
                break;
            }
            case "Wijzigingen opslaan":
            {
                BigDecimal mnr = (BigDecimal)sessie.getAttribute("details");
                String naam = request.getParameter("naam");
                String locatie = request.getParameter("locatie");
                String opleiding = request.getParameter("opleiding");
                BigDecimal aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
                BigDecimal huurprijs = new BigDecimal(request.getParameter("huurprijs"));
                String omschrijving = request.getParameter("omschrijving");
                db.wijzigMachine(mnr, naam, locatie, opleiding, aankoopprijs, huurprijs, omschrijving);
                init();     //om de machinelijst in de applicatie opnieuw in te laden
                RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                view.forward(request, response);
                break;
            }
            case "Reserveer":
            {
              RequestDispatcher view = request.getRequestDispatcher ("reservatie.jsp" );
              view.forward (request,response );   
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
        List <Machines> ma = db.getMachines();
        getServletContext().setAttribute("machines", ma);
    }
    
}
