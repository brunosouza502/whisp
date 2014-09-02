/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Tag;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class TagDAO extends DAO<Tag>{
    private static final String tag = "INSERT INTO tag(nome_tema) VALUES(?)";
                                      //criar tag que não existe, claro
    private static final String checkTag = "SELECT id_tag, nome_tema FROM tag WHERE nome_tema = ?";
                                            //verificar se existe a tag
    
    
    public TagDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Tag t) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(tag);) {
            statement.setInt(1, t.getIdTag());
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Tag read(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Tag t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tag> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tag> all(Tag t) throws SQLException {
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
    
    //verificar se existe tag
    public boolean checkTag(Tag tag){
        return true;
    }
    
}
