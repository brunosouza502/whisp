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
import java.util.List;
import model.Curtir;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class CurtirDAO extends DAO<Curtir> {
    private static final String curtir = "INSERT INTO curtir(id_usuario_liker, id_post_liked, id_usuario_owner) " 
                                        +"VALUES(?, ?, ?)";
    
    private static final String descurtir = "DELETE FROM curtir WHERE id_usuario_liker = ? AND id_post_liked = ?";
    
    private static final String qtdLikes = "SELECT COUNT(*) AS qtdLikes FROM curtir WHERE id_post_liked = ?";//quantidade de likes que um post tem
    private static final String checkLike = "SELECT id_like, id_usuario_liker, id_post_liked FROM curtir WHERE id_post_liked = ? AND id_usuario_liker = ?";
    //verificar se usuario curtiu o post para ter aopção de undo like
    
    public CurtirDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Curtir t) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(curtir);) {
            statement.setInt(1, t.getIdUsuarioLike());
            statement.setInt(2, t.getIdPostLiked());
            statement.setInt(3, t.getIdUsuarioOwn());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Curtir read(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Curtir t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(descurtir);) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao excluir: usuário não encontrado.");
            }
        }
    }

    @Override
    public List<Curtir> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Curtir> all(Curtir t) throws SQLException {
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
    
    public Curtir qtdLikes(Integer idPostLiked) throws SQLException{//mostra quantidade de likes que um post tem
        Curtir like = new Curtir();
        try (PreparedStatement statement = connection.prepareStatement(qtdLikes);) {
            statement.setInt(1, idPostLiked);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {//so um resultado no momento
                    like.setQtdLikes(result.getInt("qtdLikes"));//quantidade de likes
                    
                }else {
                    throw new SQLException("Falha ao visualizar.");
                }
            }
        }
        return like;
    }
    
    public int checkLike(Curtir t) throws SQLException{//verificar se usuario autenticado curtiu post
        Curtir like = new Curtir();
        int check;
        try (PreparedStatement statement = connection.prepareStatement(checkLike);) {
            statement.setInt(1, t.getIdPostLiked());
            statement.setInt(2, t.getIdUsuarioLike());
            
            check = statement.executeUpdate();
            return check;
        }catch (SQLException ex) {
            throw ex;
        }
        
        //System.out.println("oi"+check);
        
        //return check;
    }
    
    public void descurtir(Integer idUsuario, Integer idPost)throws SQLException{//descurtir post os parâmetros são id de usuario curtidor e post curtido
        try(PreparedStatement statement = connection.prepareStatement(descurtir)){
            statement.setInt(1, idUsuario);
            statement.setInt(2, idPost);
            
            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao descurtir post.");
            }
        }
    }
}
