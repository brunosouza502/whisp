/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.MembroDAO;
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
import model.Grupo;
import model.Membro;
import model.Usuario;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {"/membro/add",
                           "/membro/allmembers",
                           "/membro/removemember",
                           "/membro/imember", //grupos do qual o usuario autenticado participa
                           "/membro/group", //para visitar outros grupos
                           "/membro/leavegroup"})
public class MembroController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;//acesso a membros de grupos
        DAO grupoDAO;
        MembroDAO imemberDAO;//grupos ao qual o usuario autenticado pertence
        Usuario usuario = new Usuario();
        Membro membro = new Membro();
        RequestDispatcher dispatcher;

        switch (request.getServletPath()){
            case "/membro/add"://listar usuários para adicioná-los a grupo
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();
                    
                    List<Usuario> addList = dao.all();
                    request.setAttribute("addList", addList);

                    dispatcher = request.getRequestDispatcher("/view/usuario/grupo/addMember.jsp");//página que lista os usuarios para adicioná-los
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            break;
                
            case "/membro/allmembers":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getMembroDAO();
                    
                    membro.setGrupoId(Integer.parseInt(request.getParameter("idgrupo")));
                    //passa id de grupo par listar usuarios membros
                    List<Membro> listMembers = dao.all(membro);
                    request.setAttribute("all", listMembers);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/grupo/allMembers.jsp");
                    dispatcher.forward(request, response);
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            break;
                
            case "/membro/imember"://mostra grupos do qual usuario autenticado participa
                try (DAOFactory daoFactory = new DAOFactory();) {
                    imemberDAO = daoFactory.getMembroDAO();
                    
                    membro.setIdParticipante(Integer.parseInt(request.getParameter("id")));
                    
                    List<Membro> listgroup = imemberDAO.memberInGroup(membro);
                    request.setAttribute("listgroup", listgroup);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/grupo/imember.jsp");
                    dispatcher.forward(request, response);
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            break;
                
            case "/membro/leavegroup":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getMembroDAO();
                    membro.setGrupoId(Integer.parseInt(request.getParameter("idgroup")));
                    membro.setIdParticipante(Integer.parseInt(request.getParameter("id")));
                    
                    dao.delete(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("idgroup")));
                    response.sendRedirect(request.getContextPath() + "/");
                    }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            break;
                
            case "/membro/group"://visitar grupo e ver participantes
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getMembroDAO();
                    grupoDAO = daoFactory.getGrupoDAO();
                    membro.setGrupoId(Integer.parseInt(request.getParameter("idgroup")));
                    
                    Grupo grupo = (Grupo) grupoDAO.read(Integer.parseInt(request.getParameter("idgroup")));
                    List<Membro> members = dao.all(membro);
                    
                    request.setAttribute("members", members);
                    request.setAttribute("grupo", grupo);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/grupo/seeGroup.jsp");//pagina para ver membros de grupo
                    dispatcher.forward(request, response);
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        Membro grupoId = new Membro();
        Membro grupo = new Membro();
        RequestDispatcher dispatcher;
        switch (request.getServletPath()){
            case "/membro/add":
                String [] addUsuario = request.getParameterValues("idmembro");//numero de seleções para inserir os usuarios
                //String [] grupo = request.getParameterValues("idgrupo");
                grupo.setGrupoId(Integer.parseInt(request.getParameter("idgrupo")));
                
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getMembroDAO();
                    
                    try {
                        daoFactory.beginTransaction();

                        for (int i = 0; i < addUsuario.length; ++i) {
                            //Membro membro = new Membro();
                            grupo.setIdParticipante(Integer.parseInt(addUsuario[i]));
                            //membro.setGrupoId(Integer.parseInt(request.getParameter("idgrupo")));
                            dao.create(grupo);
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    }catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                    
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
            
                response.sendRedirect(request.getContextPath() + "/");
                
                break;
                
            case "/membro/removemember":
                String[] member = request.getParameterValues("member");
                //String [] group = request.getParameterValues("group");
                grupoId.setGrupoId(Integer.parseInt(request.getParameter("group")));//id de grupo é constante pois esta na session do grupo
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getMembroDAO();
                    
                    try {
                        daoFactory.beginTransaction();

                        for (int i = 0; i < member.length; ++i) {
                            dao.delete(Integer.parseInt(member[i]), grupoId.getGrupoId());
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                    
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            response.sendRedirect(request.getContextPath() + "/");
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
