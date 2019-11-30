/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import beans.Machines;
import beans.DatabaseLocal;
import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        List <Machines> ma= db.getMachines();
        getServletContext().setAttribute("machines",ma);
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String knop = request.getParameter("knop");
        String username = request.getParameter("username");

        if (knop.equals("login"))
        {
            if (username.equals("test"))
            {
                RequestDispatcher view = request.getRequestDispatcher("overzicht.jsp");
                 view.forward(request, response);
            }
            else
            {
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                 view.forward(request, response);
            }
        }
        
        if (knop.equals("overzicht"))
        {
           RequestDispatcher view = request.getRequestDispatcher("login.jsp");
           view.forward (request, response);
        }
/*            if (request.isUserInRole("Docent")){
            sessie.setAttribute("type","Docent");
            RequestDispatcher view = request.getRequestDispatcher ("overzicht.jsp" );
            view.forward (request,response );
            }
            else if (request.isUserInRole("Student")){
            sessie.setAttribute("type","Student");
            RequestDispatcher view = request.getRequestDispatcher ("overzicht.jsp" );
            view.forward (request,response );
            }
            else{
            sessie.setAttribute("type","Extern");
            RequestDispatcher view = request.getRequestDispatcher ("overzicht.jsp" );
            view.forward (request,response );*/
                
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
