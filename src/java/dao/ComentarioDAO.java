/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Comentario;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class ComentarioDAO extends DAO<Comentario>{
    private static final String comentario = "INSERT INTO comentarios(id_post_com, id_comentador, comentario, hora) " 
                                            +"VALUES(?,?,?,'now')";
    
    private static final String comentarios = "SELECT p.texto, u.nome, p.id_post, c.id_comentador, p.id_usuario, c.comentario, c.hora " 
                                             +"FROM usuario u, post p JOIN comentarios c " 
                                             +"ON p.id_post = c.id_post_com " 
                                             +"WHERE c.id_comentador = u.id " 
                                             +"AND c.id_post_com = ?"
                                             +"ORDER BY c.hora DESC ";//mostrar comentrios de um post

    public ComentarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Comentario t) throws SQLException {//comentar um post
        try (PreparedStatement statement = connection.prepareStatement(comentario);) {
            statement.setInt(1, t.getIdPost());
            statement.setInt(2, t.getIdComentador());
            statement.setString(3, t.getTextoComentario());
            
            statement.executeUpdate();            
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Comentario read(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Comentario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comentario> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comentario> all(Comentario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id, Integer groupId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> profileUser(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Comentario> postComments(Integer idpost) throws SQLException{//pode ser all(Comentario t)
        List<Comentario> comments = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(comentarios)){
            statement.setInt(1, idpost);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Comentario postcomment = new Comentario();
                    postcomment.setTexto(result.getString("texto"));//post
                    postcomment.setNomeComentador(result.getString("nome"));
                    postcomment.setIdPost(result.getInt("id_post"));
                    postcomment.setIdComentador(result.getInt("id_comentador"));//id do comentador
                    postcomment.setIdUsuario(result.getInt("id_usuario"));//id de autor do post
                    postcomment.setTextoComentario(result.getString("comentario"));//comentario do post
                    postcomment.setHora(result.getTimestamp("hora"));//hora do comentario
                    
                    comments.add(postcomment);
                }
            }catch (SQLException ex) {
                throw ex;
            }  
        }
        return comments;
    }
    
}
