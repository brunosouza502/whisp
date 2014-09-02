/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comentario;
import model.Republicar;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {"/post/republicar",//republicar post
                            })
public class RepublicarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        Republicar republicar = new Republicar();
        switch(request.getServletPath()){
            case "/post/republicar":
                republicar.setIdUsuarioRepublica(Integer.parseInt(request.getParameter("idrepublicador")));//usuario republicador
                republicar.setIdUsuario(Integer.parseInt(request.getParameter("idusuariopost")));//id do dono do post republicado
                republicar.setIdPostRepublicado(Integer.parseInt(request.getParameter("idpostrepublicado")));//id do post republicado
                
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getRepublicarDAO();
                    
                    dao.create(republicar);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            
            response.sendRedirect(request.getContextPath() + "/");
                
                break;
                
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
