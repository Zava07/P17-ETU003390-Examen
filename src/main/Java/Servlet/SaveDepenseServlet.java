package main.Java.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.Java.DAO.DepenseDAO;
import main.Java.Model.Depense;
public class SaveDepenseServlet extends HttpServlet{
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
         PrintWriter pr = response.getWriter();
        int idCredit = Integer.parseInt(request.getParameter("credit"));
        int montantCredit = Integer.parseInt(request.getParameter("montantDepense"));
        try {
            DepenseDAO empDao = new DepenseDAO();
                // Ajout d'un nouvel credit
                Depense depense = new Depense(0, idCredit, montantCredit);
                float reste =  empDao.calculReste(depense);
                if (reste < montantCredit){
                    throw new IOException("Impossible d'inserer une depense");
                }
                empDao.saveDepense(depense);
                request.setAttribute("message", "Depense enregistre avec succes !");
            } catch (Exception ex) {
            pr.println(ex.getMessage());
            throw new RuntimeException(ex);

        }
        // Redirection vers depense.jsp avec un message de confirmation
            RequestDispatcher rd = request.getRequestDispatcher("depense.jsp");
            rd.forward(request, response);

        }
    protected void doGet(HttpServletRequest request , HttpServletResponse response) {
    }
}
