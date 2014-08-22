package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
    //fazer um join no 'authenticateQuery' com table post para resgatar o texto e horario direto
    private static final String createQuery = "INSERT INTO usuario(login, senha, sexo, nome, nascimento, descricao) VALUES(?, md5(?), ?, ?, ?, ?);";
    private static final String readQuery = "SELECT * FROM usuario WHERE id = ?;";
    private static final String updateQuery = "UPDATE usuario SET login = ?, nome = ?, nascimento = ? WHERE id = ?;";
    private static final String updateWithPasswordQuery = "UPDATE usuario SET login = ?, nome = ?, nascimento = ?, senha = md5(?) WHERE id = ?;";
    private static final String deleteQuery = "DELETE FROM usuario WHERE id = ?;";
    private static final String allQuery = "SELECT * FROM usuario;";
    private static final String authenticateQuery = "SELECT id, senha, nome, nascimento, sexo, descricao FROM usuario WHERE login = ?;";
    
    private static final String authenticateAllQuery = "SELECT u.id, u.senha, u.nome, u.nascimento, u.sexo, u.descricao, "
                                                     + "g.id_grupo, g.nome_grupo " +
                                                       "FROM usuario u JOIN grupo g ON u.id = g.id_dono " +
                                                       "WHERE u.login = ?";
//autenticar usuario com os grupos que é dono
    
    private static final String readProfileQuery = "SELECT u.id, u.nome, u.nascimento, u.sexo, u.descricao, p.id_post, p.texto " +
                                                   "FROM usuario u JOIN post p ON u.id = p.id_usuario " +
                                                   "WHERE u.id = ?";
    private static final String upFoto = "INSERT INTO imagem (figura, id_user) VALUES (lo_import(?), ?);";
    
    
    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getSexo());
            statement.setString(4, usuario.getNome());
            statement.setDate(5, usuario.getNascimento());
            statement.setString(6, usuario.getDescricao());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Usuario read(Integer id) throws SQLException {
        Usuario usuario = new Usuario();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setId(result.getInt("id"));
                    usuario.setLogin(result.getString("login"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setNascimento(result.getDate("nascimento"));
                    usuario.setDescricao(result.getString("descricao"));
                    usuario.setSexo(result.getString("sexo"));
                } else {
                    throw new SQLException("Falha ao visualizar: usuário não encontrado.");
                }
            }
        }

        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String query;

        if (usuario.getSenha() == null) {
            query = updateQuery;
        } else {
            query = updateWithPasswordQuery;
        }

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getNome());
            statement.setDate(3, usuario.getNascimento());

            if (usuario.getSenha() != null) {
                statement.setString(4, usuario.getSenha());
                statement.setInt(5, usuario.getId());
            } else {
                statement.setInt(4, usuario.getId());
            }

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao editar: usuário não encontrado.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Falha ao excluir: usuário não encontrado.");
            }
        }
    }

    @Override
    public List<Usuario> all() throws SQLException {
        List<Usuario> usuarioList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(allQuery);
             ResultSet result = statement.executeQuery();) {
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setLogin(result.getString("login"));
                usuario.setNome(result.getString("nome"));

                usuarioList.add(usuario);
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return usuarioList;
    }
    
    public void authenticate(Usuario usuario) throws SQLException, SecurityException {
        try (PreparedStatement statement = connection.prepareStatement(authenticateQuery);) {
            statement.setString(1, usuario.getLogin());

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    MessageDigest md5;

                    String senhaForm;
                    String senhaUsuario;

                    try {
                        md5 = MessageDigest.getInstance("MD5");
                        md5.update(usuario.getSenha().getBytes());

                        senhaForm = new BigInteger(1, md5.digest()).toString(16);
                        senhaUsuario = result.getString("senha");

                        if (!senhaForm.equals(senhaUsuario)) {
                            throw new SecurityException("Senha incorreta.");
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        System.err.println(ex.getMessage());
                    }

                    usuario.setId(result.getInt("id"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setNascimento(result.getDate("nascimento"));
                    usuario.setSexo(result.getString("sexo"));
                    usuario.setDescricao(result.getString("descricao"));
                    
                    //pegar id's e nomes dos grupos do qual é dono
                    //usuario.setIdGrupoOwn(result.getInt("id_grupo"));
                    //usuario.setNomeGrupoOwn(result.getString("nome_grupo"));
                } else {
                    throw new SecurityException("Login incorreto.");
                }
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<Usuario> all(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id, Integer groupId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //perfil de usuário, para visitas de perfil de outros usuarios com os dados e posts, talvez tenha que remover
    @Override
    public List<Usuario> profileUser(Integer id) throws SQLException{
        List<Usuario> usuarioList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(readProfileQuery);) {
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery();){
                while (result.next()) {
                    Usuario user = new Usuario();
                    user.setId(result.getInt("id"));
                    user.setNome(result.getString("nome"));
                    user.setNascimento(result.getDate("nascimento"));
                    user.setSexo(result.getString("sexo"));
                    user.setDescricao(result.getString("descricao"));
                    user.setIdPosts(result.getInt("id_post"));
                    user.setPosts(result.getString("texto"));

                    usuarioList.add(user);
                }
            }catch (SQLException ex) {
                throw ex;
            }   
        }
        return usuarioList;
    }
    
    public void foto(Usuario usuario) throws SQLException{
        
        try (PreparedStatement statement = connection.prepareStatement(upFoto);) {
            statement.setString(1, usuario.getFoto());
            statement.setInt(2, usuario.getId());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
