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
import model.Grupo;
import model.Post;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class GrupoDAO extends DAO<Grupo>{
    private static final String createGroupQuery = "INSERT INTO grupo(id_dono, nome_grupo, descricao_grupo, data_criacao) VALUES(?, ?, ?, 'now')";
    private static final String readGroupQuery = "SELECT * FROM grupo WHERE id_grupo = ?";
    private static final String allGroupQuery = "SELECT * FROM grupo WHERE id_dono = ?";
    private static final String updateGroupQuery = "UPDATE grupo SET nome_grupo = ?, descricao_grupo = ? WHERE id_grupo = ?";
    private static final String deleteGroupQuery = "DELETE FROM grupo WHERE id_grupo = ?";
    
    public GrupoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Grupo t) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(createGroupQuery)){
            statement.setInt(1, t.getIdDono());
            statement.setString(2, t.getNomeGrupo());
            statement.setString(3, t.getDescricaoGrupo());
            
            statement.executeQuery();
        }catch(SQLException ex){
            throw ex;
        }
    }

    @Override
    public Grupo read(Integer id) throws SQLException {//ler 'perfil' do grupo
        Grupo grupo = new Grupo();
        try(PreparedStatement statement = connection.prepareStatement(readGroupQuery);){
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery();){
                if(result.next()){
                    grupo.setIdGrupo(result.getInt("id_grupo"));
                    grupo.setIdDono(result.getInt("id_dono"));
                    grupo.setNomeGrupo(result.getString("nome_grupo"));
                    grupo.setDescricaoGrupo(result.getString("descricao_grupo"));
                    grupo.setDataCriacao(result.getDate("data_criacao"));
                }else
                    throw new SQLException("Sem grupo para remover");
            }
        }
        return grupo;
    }

    @Override
    public void update(Grupo t) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(updateGroupQuery);) {
            statement.setString(1, t.getNomeGrupo());
            statement.setString(2, t.getDescricaoGrupo());
            statement.setInt(3, t.getIdGrupo());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao editar: usuário não encontrado.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteGroupQuery);) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao excluir: usuário não encontrado.");
            }
        }
    }

    @Override
    public List<Grupo> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Grupo> all(Grupo t) throws SQLException {
        List<Grupo> grupoList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(allGroupQuery);) {
            statement.setInt(1, t.getIdDono());
            try(ResultSet result = statement.executeQuery();){
                while (result.next()) {
                    Grupo grupo = new Grupo();
                    grupo.setIdGrupo(result.getInt("id_grupo"));
                    grupo.setNomeGrupo(result.getString("nome_grupo"));
                    grupo.setDescricaoGrupo(result.getString("descricao_grupo"));
                    grupo.setDataCriacao(result.getDate("data_criacao"));
                    
                    grupoList.add(grupo);
                }
            }catch (SQLException ex) {
                throw ex;
            }   
        }
            
        return grupoList;
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
