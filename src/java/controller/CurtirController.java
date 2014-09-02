/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.CurtirDAO;
import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Curtir;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {"/like", //curtir post
                           "/undolike", //descurtir post ja curtido
})
public class CurtirController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        CurtirDAO descurtir;
        Curtir like = new Curtir();
        
        RequestDispatcher dispatcher;
        switch (request.getServletPath()) {
            case "/like":
                try (DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getCurtirDAO();
                    like.setIdUsuarioLike(Integer.parseInt(request.getParameter("iduserliker")));//passa o id do usuario curtidor
                    like.setIdPostLiked(Integer.parseInt(request.getParameter("idpostliked")));//passa id do post curtido
                    like.setIdUsuarioOwn(Integer.parseInt(request.getParameter("idusuarioown")));//passa o id do dono do post
                    
                    dao.create(like);
                    
                    dispatcher = request.getRequestDispatcher("/");
                    dispatcher.forward(request, response);
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            break;
                
            case "/undolike"://descurtir post ja curtido
                try (DAOFactory daoFactory = new DAOFactory();){
                    descurtir = daoFactory.getCurtirDAO();
                    
                    descurtir.descurtir(Integer.parseInt(request.getParameter("iduserliker")), Integer.parseInt(request.getParameter("idpostdisliked")));
                    
                    dispatcher = request.getRequestDispatcher("/");
                    dispatcher.forward(request, response);
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
