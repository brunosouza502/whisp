package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;

public abstract class DAO<T> {

    protected Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public abstract void create(T t) throws SQLException;
    public abstract T read(Integer id) throws SQLException;
    public abstract void update(T t) throws SQLException;
    public abstract void delete(Integer id) throws SQLException;

    public abstract List<T> all() throws SQLException;
    public abstract List<T> all(T t) throws SQLException;
    
    public abstract void delete(Integer id, Integer groupId) throws SQLException;//desassociar membros de grupos; delete de membro_grupo

    public abstract List<Usuario> profileUser(Integer id) throws SQLException;

}
