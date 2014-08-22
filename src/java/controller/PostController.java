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
import model.Post;
import model.Usuario;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {
                            "/post",
                            "/allposts",
                            "/post/delete",
                            "/post/update",
                            "/post/user"})//visualizar posts de outros usuarios
public class PostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        DAO userDAO;
        Post userPost = new Post();//id de usuario para acessar seus devidos posts; id_usuario na tabela post
        RequestDispatcher dispatcher;
        //HttpSession session = request.getSession();
        //session.getAttribute("usuario");
        switch(request.getServletPath()){
            case "/allposts": //mostrar os posts
                try (DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getPostDAO();
                    
                    userPost.setIdUsuario(Integer.parseInt(request.getParameter("id")));

                    //passa o id do usuario ja existente para obj Post                    
                    List<Post> postList = dao.all(userPost);//para visitas de usuario tambem
                    request.setAttribute("posts", postList);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/posts/allposts.jsp");
                    dispatcher.forward(request, response);
                    
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/view/usuario/posts/allposts.jsp");
                }
            break;
                
            case "/post/delete":
                try(DAOFactory daoFactory = new DAOFactory();){
                    
                    dao = daoFactory.getPostDAO();
                    dao.delete(Integer.parseInt(request.getParameter("idpost")));
                    
                }catch(SQLException ex){
                    System.err.println(ex.getMessage());
                }
                response.sendRedirect(request.getContextPath() + "/");
            break;
                
            case "/post/update":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getPostDAO();

                    Post post = (Post) dao.read(Integer.parseInt(request.getParameter("idpost")));
                    request.setAttribute("postedit", post);

                    dispatcher = request.getRequestDispatcher("/view/usuario/posts/editpost.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    dispatcher = request.getRequestDispatcher("/view/usuario/posts/editpost.jsp");
                }
            break;
                
            case "/post/user":///visualiza post de outro usuario no perfil deste
                try (DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getPostDAO();
                    userDAO = daoFactory.getUsuarioDAO();
                    
                    userPost.setIdUsuario(Integer.parseInt(request.getParameter("id")));
                    
                    Usuario usuario = (Usuario) userDAO.read(Integer.parseInt(request.getParameter("id")));
                    
                    List<Post> userPostList = dao.all(userPost);//para visitas de usuario tambem
                    //dao.read(userPost.getIdPost());
                    request.setAttribute("userposts", userPostList);
                    request.setAttribute("userprofile", usuario);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/posts/userPosts.jsp");//pagina para posts de outro usuarios
                    dispatcher.forward(request, response);
                    
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/view/usuario/posts/allposts.jsp");
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        RequestDispatcher dispatcher;
        Post post = new Post();
        Usuario usuario = new Usuario();//talvez precise por causa do id de usuario
        Integer idUser;
        String tema;//procura o tema que o post está associado
        
        switch(request.getServletPath()){
            case "/post": //post: create para Posts
                post.setIdUsuario(Integer.parseInt(request.getParameter("idusuario")));
                //tema = request.getParameter("wallpost");//verificar se possui #
                post.setTexto(request.getParameter("wallpost"));
                
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getPostDAO();
                    dao.create(post);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                
            response.sendRedirect(request.getContextPath() + "/");
                break;
                
            case "/post/update":
                post.setTexto(request.getParameter("editpost"));
                post.setIdPost(Integer.parseInt(request.getParameter("postid")));
                try(DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getPostDAO();
                    dao.update(post);
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                response.sendRedirect(request.getContextPath() + "/");
            break;
                
            case "/post/delete":
                String [] postDelete = request.getParameterValues("delete");
                try(DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getPostDAO();
                    try{
                        daoFactory.beginTransaction();
                        //selecionar vários posts e deleta-los de uma vez
                        for(int i=0; i<postDelete.length; ++i)
                            dao.delete(Integer.parseInt(postDelete[i]));
                        
                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    }catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                    //dao.delete(Integer.parseInt(request.getParameter("delete")));
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
