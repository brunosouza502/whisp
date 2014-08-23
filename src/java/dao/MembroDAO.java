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
import model.Membro;
import model.Usuario;

/**
 *
 * @author Bruno
 */
public class MembroDAO extends DAO<Membro>{
    private static final String addMemberQuery = "INSERT INTO membro_grupo(grupo_id, id_membro, data_entrada) VALUES(?, ?, 'now')";
    private static final String removeMemberQuery = "DELETE FROM membro_grupo WHERE id_membro = ? AND grupo_id = ?";
    private static final String allMembersQuery = "SELECT m.grupo_id, m.id_membro, u.id, u.nome " +
                                                  "FROM membro_grupo m JOIN usuario u ON m.id_membro = u.id " +
                                                  "WHERE m.grupo_id = ?";
    //mostra todos os membros do grupo
    private static final String iMemberQuery = "SELECT m.grupo_id, m.id_membro, g.nome_grupo FROM membro_grupo m JOIN grupo g ON m.grupo_id = g.id_grupo WHERE m.id_membro = ?";
    //grupos do qual o usuario logado participa
    private static final String leaveGroupQuery = "DELETE FROM membro_grupo WHERE grupo_id = ? AND id_membro = ?";
    //abandonar grupo
    
    public MembroDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Membro t) throws SQLException {//inserir usuarios em grupos
        try (PreparedStatement statement = connection.prepareStatement(addMemberQuery);) {
            statement.setInt(1, t.getGrupoId());
            statement.setInt(2, t.getIdParticipante());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Membro read(Integer id) throws SQLException {//fazer read individual de membros
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Membro t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(removeMemberQuery);) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao excluir: usuário não encontrado.");
            }
        }
    }

    @Override
    public List<Membro> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Membro> all(Membro t) throws SQLException {
        List<Membro> memberList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(allMembersQuery);) {
            statement.setInt(1, t.getGrupoId());
            try(ResultSet result = statement.executeQuery();){
                while (result.next()) {
                    Membro member = new Membro();
                    member.setGrupoId(result.getInt("grupo_id"));
                    member.setIdParticipante(result.getInt("id_membro"));
                    member.setNomeMembro(result.getString("nome"));
                            
                    memberList.add(member);
                }
            }catch (SQLException ex) {
                throw ex;
            }   
        }
        
        return memberList;
    }
    
    //remover membro de grupo, passando como parametro grupo_id e usuario_id
  

    @Override
    public void delete(Integer id, Integer groupId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(removeMemberQuery);) {
            statement.setInt(1, id);
            statement.setInt(2, groupId);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao excluir: usuário não encontrado.");
            }
        }
    }

    @Override
    public List<Usuario> profileUser(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Membro> memberInGroup(Membro t) throws SQLException{//mostra grupos do qual usuario auenticado participa
        List<Membro> groupList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(iMemberQuery);) {
            statement.setInt(1, t.getIdParticipante());
            try(ResultSet result = statement.executeQuery();){
                while (result.next()) {
                    Membro member = new Membro();
                    member.setGrupoId(result.getInt("grupo_id"));
                    member.setIdParticipante(result.getInt("id_membro"));
                    member.setGrupoNome(result.getString("nome_grupo"));
                            
                    groupList.add(member);
                }
            }catch (SQLException ex) {
                throw ex;
            }   
        }
        
        return groupList;
    }
    
}
