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

    @Override
    public void init()
    {
        List <Machines> ma = db.getMachines();
        System.out.println("********* TEST ***********");
        System.out.println(ma.get(0).getMnaam());
        System.out.println(ma.get(1).getMnaam());
        getServletContext().setAttribute("machines", ma);
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessie = request.getSession();
        String knop = request.getParameter("knop");
/*
        if(knop.equals("login"))
        {
            username = request.getParameter("username");
            sessie.setAttribute("username", username);
            switch (username) {
                case "student":
                {
                    sessie.setAttribute("groep", "student");
                    RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                    view.forward(request, response);
                    break;
                }
                case "docent":
                {
                    sessie.setAttribute("groep", "docent");
                    RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                    view.forward(request, response);
                    break;
                }
                case "extern":
                {
                    sessie.setAttribute("groep", "extern");
                    RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                    view.forward(request, response);
                    break;
                }
                default:
                {
                    RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                    view.forward(request, response);
                    break;
                }
            }
        }*/
        switch (knop) {
            case "overzicht":
                {
                    RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                    view.forward (request, response);
                    break;
                }
                
            case "logout":
                {
                    sessie.removeAttribute("groep");
                    response.sendRedirect("controller.do" );
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
                    String omschrijving = request.getParameter("opleiding");
                    db.addMachine(naam, locatie, opleiding, aankoopprijs, huurprijs, omschrijving);
                    init();     //om de machinelijst in de applicatie opnieuw in te laden
                    RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                    view.forward(request, response);
                    break;
                }
            default:
                break;
        }
        
        if (request.isUserInRole("Docent")){
            sessie.setAttribute("groep","Docent");
            RequestDispatcher view = request.getRequestDispatcher ("overzicht.jsp");
            view.forward(request, response);
        }
        else if (request.isUserInRole("Student")){
            sessie.setAttribute("groep","Student");
            RequestDispatcher view = request.getRequestDispatcher ("overzicht.jsp");
            view.forward(request, response);
        }
        else{
            sessie.setAttribute("groep","Extern");
            RequestDispatcher view = request.getRequestDispatcher ("overzicht.jsp");
            view.forward(request, response);
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
    
}
