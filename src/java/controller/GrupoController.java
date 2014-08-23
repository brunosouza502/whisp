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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Grupo;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {"/grupos",
                           "/grupo/create",
                           "/grupo/read",
                           "/grupo/update",
                           "/grupo/delete"})
public class GrupoController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        DAO dao;
        Grupo owner = new Grupo();
        switch(request.getServletPath()){
            case "/grupos"://melhor mostrar todos os grupos que o usuario criou, uma lista de seus grupos
                try (DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getGrupoDAO();
                    
                    owner.setIdDono(Integer.parseInt(request.getParameter("id")));
                    List<Grupo> groupList = dao.all(owner);
                    
                    request.setAttribute("groups", groupList);
                    dispatcher = request.getRequestDispatcher("/view/usuario/grupo/groups.jsp");
                    dispatcher.forward(request, response); 
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                
                break;
                
            case "/grupo/read":
                try (DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getGrupoDAO();
                    
                    Grupo grupo = (Grupo) dao.read(Integer.parseInt(request.getParameter("idgroup")));
                    //request.setAttribute("grupo", grupo);
                    HttpSession session = request.getSession();//criar session para adicionar usuarios
                    session.setAttribute("grupo", grupo);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/grupo/readGroup.jsp");
                    dispatcher.forward(request, response);
                }catch(SQLException ex){
                    System.err.println(ex.getMessage());
                }
            break;
                
            case "/grupo/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/grupo/createGroup.jsp");
                dispatcher.forward(request, response);
                
                break;
                
            case "/grupo/delete":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getGrupoDAO();

                    dao.delete(Integer.parseInt(request.getParameter("idgrupo")));
                    
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        Grupo usuarioGrupo = new Grupo();
        Grupo grupo = new Grupo();
        RequestDispatcher dispatcher;
        switch(request.getServletPath()){
            case "/grupo/create"://acrescentar 'adicionar membros' ao criar grupo
                usuarioGrupo.setIdDono(Integer.parseInt(request.getParameter("idowner")));
                usuarioGrupo.setNomeGrupo(request.getParameter("nomegrupo"));
                usuarioGrupo.setDescricaoGrupo(request.getParameter("descricaogrupo"));
                
                try (DAOFactory daoFactory = new DAOFactory()){
                    dao = daoFactory.getGrupoDAO();
                    
                    dao.create(usuarioGrupo);
                }catch(SQLException ex){
                    System.err.println(ex.getMessage());
                }
                response.sendRedirect(request.getContextPath() + "/");
                
                break;
                
            case "/grupo/update":
                grupo.setNomeGrupo(request.getParameter("nomegrupo"));
                grupo.setDescricaoGrupo(request.getParameter("descgroup"));
                grupo.setIdGrupo(Integer.parseInt(request.getParameter("idgroup")));
                
                try (DAOFactory daoFactory = new DAOFactory()){
                    dao = daoFactory.getGrupoDAO();
                    
                    dao.update(grupo);
                }catch(SQLException ex){
                    System.err.println(ex.getMessage());
                }
            
            response.sendRedirect(request.getContextPath() + "/");
            
            break;
        }
        
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
