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
import model.Republicar;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class RepublicarDAO extends DAO<Republicar>{
    
    private static final String republicar = "INSERT INTO republicar(id_usuario_republica, id_usuario_post_own, id_post_republicado, hora) " 
                                            +"VALUES(?, ?, ?, 'now')";
    
    private static final String republicacoes = "SELECT w.nome, sub.texto, sub.nome AS autorPost, sub.id_usuario_republica, sub.id_usuario_post_own, sub.hora FROM " 
            +"(SELECT u.id, u.nome, p.id_post, p.texto, r.id_post_republicado, r.id_usuario_republica, r.id_usuario_post_own, r.hora " 
                +"	FROM usuario u, post p JOIN republicar r ON p.id_post = r.id_post_republicado " 
                +"	WHERE p.id_usuario = u.id AND r.id_usuario_post_own = u.id " 
                +"	ORDER BY r.hora DESC " 
            +") AS sub, usuario w\n" 
            +"WHERE w.id = sub.id_usuario_republica AND w.id = ?";
    //republicações do usuario autenticado

    public RepublicarDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Republicar t) throws SQLException {//republicar post
        try (PreparedStatement statement = connection.prepareStatement(republicar);) {
            statement.setInt(1, t.getIdUsuarioRepublica());
            statement.setInt(2, t.getIdUsuario());
            statement.setInt(3, t.getIdPostRepublicado());
            
            statement.executeUpdate(); 
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Republicar read(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Republicar t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Republicar> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Republicar> all(Republicar t) throws SQLException {//mostra republicaçoes do usuario
        List<Republicar> postList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(republicacoes);) {
            statement.setInt(1, t.getIdUsuario());
            try(ResultSet result = statement.executeQuery();){
                while (result.next()) {
                    Republicar post = new Republicar();
                    post.setNomeUsuarioRepublica(result.getString("nome"));//usuario autenticado que republicou o post
                    post.setTexto(result.getString("texto"));
                    post.setNomeUsuarioPost(result.getString("autorpost"));//nome do autor do post republicado
                    post.setIdUsuarioRepublica(result.getInt("id_usuario_republica"));//id do usuario autenticado que republicou os posts
                    post.setIdUsuario(result.getInt("id_usuario_post_own"));//id do usuario autordo post
                    post.setHoraRepublicacao(result.getTimestamp("hora"));

                    postList.add(post);
                }
            }catch (SQLException ex) {
                throw ex;
            }   
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
