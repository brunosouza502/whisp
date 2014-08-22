package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.PostDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.Date;
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

@WebServlet(urlPatterns = {"/usuario/create",
                           "/usuario/read",
                           "/usuario/update",
                           "/usuario/delete",
                           "/usuario",
                           "/usuario/upload"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao;
        DAO userDAO;
        Post userPost = new Post();//id de usuario para acessar seus devidos posts; id_usuario na tabela post
        HttpSession session;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);

                break;
            case "/usuario/read"://visualizar perfil(dados e posts) de outros usuarios
                try (DAOFactory daoFactory = new DAOFactory();){
                    dao = daoFactory.getPostDAO();
                    userDAO = daoFactory.getUsuarioDAO();
                    
                    userPost.setIdUsuario(Integer.parseInt(request.getParameter("id")));
                    
                    Usuario usuario = (Usuario) userDAO.read(Integer.parseInt(request.getParameter("id")));
                    List<Post> userPostList = dao.all(userPost);//para visitas de usuario tambem
                    
                    request.setAttribute("userposts", userPostList);
                    request.setAttribute("userprofile", usuario);
                    
                    dispatcher = request.getRequestDispatcher("/view/usuario/read.jsp");//pagina para posts de outro usuarios
                    dispatcher.forward(request, response);
                    
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/view/usuario/posts/allposts.jsp");
                }
            case "/usuario/update":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    Usuario profile = (Usuario) dao.read(Integer.parseInt(request.getParameter("id")));
                    
                    //List<Usuario> perfil = dao.profileUser(profile.getId());
                    request.setAttribute("profile", profile);//visualizar perfil de usuario

                    dispatcher = request.getRequestDispatcher("/view" + request.getServletPath() + ".jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }

                break;
            case "/usuario/delete":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.delete(Integer.parseInt(request.getParameter("id")));
                    
                    session = request.getSession(false);
                    
                    //ao deletar conta de usuario, encerrar sessão
                    if (session != null) {
                        session.invalidate();
                    }
                    
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                //response.sendRedirect(request.getContextPath());

                break;
            case "/usuario":
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    List<Usuario> usuarioList = dao.all();
                    request.setAttribute("usuarioList", usuarioList);

                    dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }
            break;
                
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao;
        UsuarioDAO fotoDAO;
        Usuario usuario = new Usuario();
        HttpSession session;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario/create":
                usuario.setLogin(request.getParameter("login"));
                usuario.setSenha(request.getParameter("senha"));
                usuario.setSexo(request.getParameter("gender"));
                usuario.setNome(request.getParameter("nome"));
                usuario.setNascimento(Date.valueOf(request.getParameter("nascimento")));
                usuario.setDescricao(request.getParameter("desc"));
                
                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.create(usuario);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");

                break;
            case "/usuario/update":
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                usuario.setLogin(request.getParameter("login"));
                usuario.setNome(request.getParameter("nome"));
                usuario.setNascimento(Date.valueOf(request.getParameter("nascimento")));

                if (!request.getParameter("senha").isEmpty()) {
                    usuario.setSenha(request.getParameter("senha"));
                }

                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.update(usuario);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/");

                break;
            case "/usuario/delete":
                String[] usuarios = request.getParameterValues("delete");

                try (DAOFactory daoFactory = new DAOFactory();) {
                    dao = daoFactory.getUsuarioDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (int i = 0; i < usuarios.length; ++i) {
                            dao.delete(Integer.parseInt(usuarios[i]));
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/usuario");
                
                break;
                
                case "/usuario/upload"://upload de fotos
                    usuario.setFoto("fileName");
                    usuario.setId(Integer.parseInt(request.getParameter("iduser")));
                try (DAOFactory daoFactory = new DAOFactory();) {
                    fotoDAO = daoFactory.getUsuarioDAO();
                    
                    fotoDAO.foto(usuario);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/usuario");
                }
                response.sendRedirect(request.getContextPath() + "/usuario");
        }
    }
}
