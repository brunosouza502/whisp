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
import model.Follow;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class FollowDAO extends DAO<Follow>{
    
    private static final String follow = "INSERT INTO seguir(id_seguidor, id_seguido) VALUES(?,?)";
    
    private static final String unfollow = "DELETE FROM seguir WHERE id_following = ?";
    
    private static final String myFollowers = "SELECT s.id_seguidor, u.nome, s.id_following " 
                                              +"FROM seguir s  JOIN usuario u ON s.id_seguidor = u.id " 
                                              +"WHERE s.id_seguido = ?";//ver meus seguidores
    
    private static final String following = "SELECT u.nome, s.id_seguido, s.id_seguidor, s.id_following " 
                                            +"FROM seguir s JOIN usuario u " 
                                            +"ON s.id_seguido = u.id " 
                                            +"WHERE s.id_seguidor = ?";//ver quem eu sigo

    public FollowDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Follow t) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(follow);) {
            statement.setInt(1, t.getIdSeguidor());
            statement.setInt(2, t.getIdSeguido());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Follow read(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Follow t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(unfollow);) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao excluir: usuário não encontrado.");
            }
        }
    }

    @Override
    public List<Follow> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Follow> all(Follow t) throws SQLException {//mostra nome dos seguidores
        List<Follow> followers = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(myFollowers)){
            statement.setInt(1, t.getIdSeguido());
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Follow followed = new Follow();
                    followed.setIdSeguidor(result.getInt("id_seguidor"));
                    followed.setNomeSeguidor(result.getString("nome"));
                    //followed.setIdSeguido(result.getInt("id_seguido"));
                    followed.setIdFollowing(result.getInt("id_following"));
                    
                    followers.add(followed);
                }
            }catch (SQLException ex) {
                throw ex;
            }  
        }
        return followers;
    }

    @Override
    public void delete(Integer id, Integer groupId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> profileUser(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Follow> seguindo(Integer id) throws SQLException{//ver usuarios que o usuario autenticado esta seguindo
        List<Follow> followers = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(following)){
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    Follow follower = new Follow();
                    follower.setNomeSeguido(result.getString("nome"));
                    follower.setIdSeguido(result.getInt("id_seguido"));
                    follower.setIdSeguidor(result.getInt("id_seguidor"));
                    follower.setIdFollowing(result.getInt("id_following"));
                    
                    followers.add(follower);
                }
            }catch (SQLException ex) {
                throw ex;
            }
            
            return followers;
        }
    
    }
    
}
