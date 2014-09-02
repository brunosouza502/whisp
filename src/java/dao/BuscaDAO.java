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
import model.Busca;
import model.Post;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class BuscaDAO extends DAO<Busca>{

    public BuscaDAO(Connection connection) {
        super(connection);
    }

    public List<Busca> buscar(String termo)throws SQLException{
        List<Busca> resultado = new ArrayList<>();
        String query = "SELECT id, nome FROM usuario WHERE nome ILIKE ('%"+ termo +"%')";
        
        try (PreparedStatement statement = connection.prepareStatement(query);) {
            //statement.setString(1, termo);
            try(ResultSet result = statement.executeQuery();){
                while (result.next()) {
                    Busca usuario = new Busca();
                    usuario.setIdUsuarioBusca(result.getInt("id"));
                    usuario.setNomeUsuarioBusca(result.getString("nome"));

                    resultado.add(usuario);
                }
            }catch (SQLException ex) {
                throw ex;
            }   
        }
            
        return resultado;
    }
    
    @Override
    public void create(Busca t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Busca read(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Busca t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Busca> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Busca> all(Busca t) throws SQLException {
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
    
}
