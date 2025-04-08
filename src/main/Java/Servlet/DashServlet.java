package main.Java.Servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import main.Java.Model.*;
import main.Java.DAO.*;

public class DashServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            CreditDAO creditDAO = new CreditDAO();
            List<Dashboard> dash =  creditDAO.getDashBord();
            
            request.setAttribute("listeDash", dash);
            request.getRequestDispatcher("DashBoard.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e.getMessage());
        }
    }
}
