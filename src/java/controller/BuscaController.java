/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.BuscaDAO;
import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Busca;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {"/busca"//resultado de buscas
})
public class BuscaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BuscaDAO dao;
        RequestDispatcher dispatcher;
        switch(request.getServletPath()){
            case "/busca":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getBuscaDAO();
                    
                    List<Busca> resultado = dao.buscar(request.getParameter("termo"));
                    
                    request.setAttribute("resultado", resultado);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/busca.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            
            response.sendRedirect(request.getContextPath() + "/view/usuario/busca.jsp");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
