/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.ComentarioDAO;
import dao.CurtirDAO;
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
import model.Comentario;
import model.Curtir;
import model.Follow;
import model.Post;

/**
 *
 * @author Bruno
 */
@WebServlet(urlPatterns = {"/post/comentarios",//pagina com o post e os ses comentarios
                           "/comentar"//comentar eem post
                           })
public class ComentarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ComentarioDAO dao;
        DAO postDAO;
        CurtirDAO curtirDAO;
        RequestDispatcher dispatcher;
        Comentario comment = new Comentario();
        Curtir ilike = new Curtir();//verifica se usuario autenticado curtiu o post para ter a opção de undo like
        switch(request.getServletPath()){
            case "/post/comentarios":
                
                try(DAOFactory daoFactory = new DAOFactory()){
                    dao = daoFactory.getComentarioDAO();
                    postDAO = daoFactory.getPostDAO();
                    curtirDAO = daoFactory.getCurtirDAO();
                    
                    //fazer PostDao para colocar o post na pagina e numero de Likes e dislikes também
                    Post post = (Post) postDAO.read(Integer.parseInt(request.getParameter("idpost")));
                    Curtir likes = (Curtir) curtirDAO.qtdLikes(Integer.parseInt(request.getParameter("idpost")));
                    List<Comentario> comentarios = dao.postComments(Integer.parseInt(request.getParameter("idpost")));
                    
                    ilike.setIdUsuarioLike(Integer.parseInt(request.getParameter("id")));
                    ilike.setIdPostLiked(Integer.parseInt(request.getParameter("idpost")));
                    
                    //Curtir check = (Curtir) curtirDAO.checkLike(ilike);
                    
                    request.setAttribute("comentarios", comentarios);
                    request.setAttribute("post", post);
                    request.setAttribute("likes", likes);
                    //verificar se o usuario autenticado curtiu o post para ter a opção de descurtir tal post; e o mesmo para dislike
                    
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/posts/comentarios.jsp");
                    dispatcher.forward(request, response);
                    
                    
                }catch(SQLException ex){
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/");
                }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao;
        Comentario comment = new Comentario();
        switch(request.getServletPath()){
            
            case "/comentar"://postar um comentario para um post
                comment.setIdPost(Integer.parseInt(request.getParameter("idpost")));
                comment.setIdComentador(Integer.parseInt(request.getParameter("idcomentador")));
                comment.setTextoComentario(request.getParameter("comentario"));
                
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getComentarioDAO();
                    
                    dao.create(comment);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                response.sendRedirect(request.getContextPath() + "/");
            
            break;
        }
    }

}
