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
    private static final String myFollowers = "SELECT u.nome, u.id, s.id_seguidor, s.id_seguido, s.id_following"
                                                + "FROM usuario u JOIN seguir s ON u.id = s.id_seguido "
                                                + "WHERE id_seguido = ?";//ver meus seguidores

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Follow> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Follow> all(Follow t) throws SQLException {
        List<Follow> followers = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(myFollowers)){
            statement.setInt(1, t.getIdSeguido());
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    Follow follower = new Follow();
                    follower.setNomeSeguidor(result.getString("nome"));
                    follower.setIdSeguido(result.getInt("id"));
                    follower.setIdSeguidor(result.getInt("id_seguidor"));
                    follower.setIdFollowing(result.getInt("id_following"));
                    
                    followers.add(follower);
                }else {
                    throw new SQLException("Falha ao visualizar.");
                }
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
    
    
    
}
