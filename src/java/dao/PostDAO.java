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
import model.Post;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class PostDAO extends DAO<Post>{//DAO de criação de posts
    private static final String postQuery = "INSERT INTO post(id_usuario, texto, horario) VALUES(?, ?, 'now')";
    private static final String showPostQuery = "SELECT * FROM post WHERE id_usuario = ?";
    private static final String editPostQuery = "SELECT * FROM post WHERE id_post = ?";
    private static final String deletePostQuery = "DELETE FROM post WHERE id_post = ?";
    private static final String updatePostQuery = "UPDATE post SET texto = ? WHERE id_post = ?";
    
    public PostDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Post post) throws SQLException {//create Post
                try (PreparedStatement statement = connection.prepareStatement(postQuery);) {
                    statement.setInt(1, post.getIdUsuario());
                    statement.setString(2, post.getTexto());
            
                    statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Post read(Integer id) throws SQLException {
        Post post = new Post();
        try(PreparedStatement statement = connection.prepareStatement(editPostQuery);){
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery();){
                if(result.next()){
                    post.setIdPost(result.getInt("id_post"));
                    post.setIdUsuario(result.getInt("id_usuario"));
                    post.setTexto(result.getString("texto"));
                    post.setHorario(result.getTimestamp("horario"));
                }else
                    throw new SQLException("Erro: Nenhum post para editar");
            }
        }
        return post;
    }

    @Override
    public void update(Post t) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(updatePostQuery);){
            statement.setString(1, t.getTexto());
            statement.setInt(2, t.getIdPost());
            
            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao editar.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(deletePostQuery)){
            statement.setInt(1, id);
            
            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao excluir: post não existe.");
            }
        }
    }

    //Talvez precise do parametro para acessar id usuario 
    @Override
    public List<Post> all(Post t) throws SQLException {
        List<Post> postList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(showPostQuery);) {
            statement.setInt(1, t.getIdUsuario());
            try(ResultSet result = statement.executeQuery();){
                while (result.next()) {
                    Post post = new Post();
                    post.setIdPost(result.getInt("id_post"));
                    //post.setIdUsuario(result.getInt("id_usuario"));
                    post.setTexto(result.getString("texto"));
                    post.setHorario(result.getTimestamp("horario"));

                    postList.add(post);
                }
            }catch (SQLException ex) {
                throw ex;
            }   
        }
            
        return postList;
    }

    @Override
    public List<Post> all() throws SQLException {//pode ser no UsuarioDAO, pois posts pertencem a usuario
        List<Post> postList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(showPostQuery);
             ResultSet result = statement.executeQuery();) {
            while (result.next()) {
                Post post = new Post();
                post.setIdPost(result.getInt("id_post"));
                post.setIdUsuario(result.getInt("id_usuario"));
                post.setTexto(result.getString("texto"));
                post.setHorario(result.getTimestamp("horario"));

                postList.add(post);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return postList;
    }

    @Override
    public void delete(Integer id, Integer groupId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> profileUser(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
