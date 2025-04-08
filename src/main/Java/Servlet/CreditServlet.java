package main.Java.Servlet;
import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.Java.Model.*;
import main.Java.DAO.CreditDAO;

public class CreditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            CreditDAO cr = new CreditDAO();
            List<BaseModel> listeCredit = cr.findAll();
            request.setAttribute("liste", listeCredit);
            request.getRequestDispatcher("depense.jsp").forward(request,response);
        }catch(Exception e){
            e.printStackTrace();
            // System.out.println();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
    
}