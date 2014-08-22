/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.google.gson.Gson;
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
import model.Follow;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {"/follow",
                           "/followers",//meus seguidores
})
public class FollowController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        Follow follow = new Follow();
        RequestDispatcher dispatcher;
        
        switch (request.getServletPath()){
            case "/follow":
                try (DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getFollowDAO();
                    
                    follow.setIdSeguidor(Integer.parseInt(request.getParameter("idfollower")));
                    follow.setIdSeguido(Integer.parseInt(request.getParameter("idfollowed")));
                    
                    dao.create(follow);
                    
                    dispatcher = request.getRequestDispatcher("/");//pagina para posts de outro usuarios
                    dispatcher.forward(request, response);
                    
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            
            case "/followers":
                try(DAOFactory daoFactory = new DAOFactory()){
                    dao = daoFactory.getFollowDAO();
                    follow.setIdSeguido(Integer.parseInt(request.getParameter("id")));
                    
                    List<Follow> followers = dao.all(follow);
                    
                    Gson gson = new Gson();
                    String json = gson.toJson(followers);
                    
                    response.getOutputStream().print(json);
                    
                }catch(SQLException ex){
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
