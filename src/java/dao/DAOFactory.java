package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import jdbc.ConnectionFactory;

public class DAOFactory implements AutoCloseable {

    private Connection connection = null;

    public DAOFactory() {
        connection = ConnectionFactory.getInstance().getConnection();
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public Savepoint createSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
    }

    public void rollbackTransactionTo(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    public void endTransaction() throws SQLException {
        connection.setAutoCommit(true);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO(connection);
    }
    
    //não sei se precisa, para abrir conexão para posts
    public PostDAO getPostDAO(){
        return new PostDAO(connection);
    }
    
    public GrupoDAO getGrupoDAO(){
        return new GrupoDAO(connection);
    }
    
    //inserir usuarios em grupos
    public MembroDAO getMembroDAO(){
        return new MembroDAO(connection);
    }
    
    //para seguir usuarios
    public FollowDAO getFollowDAO(){
        return new FollowDAO(connection);
    }
    
    //para Curtir
    public CurtirDAO getCurtirDAO(){
        return new CurtirDAO(connection);
    }
    
    //para tags de temas
    public TagDAO getTagDAO(){
        return new TagDAO(connection);
    }
    
    //para comentarios em posts
    public ComentarioDAO getComentarioDAO(){
        return new ComentarioDAO(connection);
    }
    
    //para republicações
    public RepublicarDAO getRepublicarDAO(){
        return new RepublicarDAO(connection);
    }
    
    //para formar buscas
    public BuscaDAO getBuscaDAO(){
        return new BuscaDAO(connection);
    }
    
    @Override
    public void close() throws SQLException {
        closeConnection();
    }
}
