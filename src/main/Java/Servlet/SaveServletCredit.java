package main.Java.Servlet;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.Java.Model.*;
import main.Java.DAO.*;

public class SaveServletCredit extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pr = response.getWriter();
        String libelle = request.getParameter("libelle");
       // String idCreditParam = request.getParameter("idCredit");
        int montantCredit = Integer.parseInt(request.getParameter("montantCredit"));
    
        try {
            CreditDAO empDao = new CreditDAO();
                // Ajout d'un nouvel credit
                Credit credit = new Credit(0, libelle, montantCredit);
                empDao.SaveCredit(credit);
                request.setAttribute("message", "Credit enregistre avec succes !");
        } catch (Exception ex) {
            pr.println("Erreur lors de l'insertion");
            throw new RuntimeException(ex);
        }
            // Redirection vers index.jsp avec un message de confirmation
            RequestDispatcher rd = request.getRequestDispatcher("page.jsp");
            rd.forward(request, response);
        }
    protected void doGet(HttpServletRequest request , HttpServletResponse response) {
    }
}
