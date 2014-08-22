package model;

import java.sql.Date;

public class Usuario {

    private Integer id;
    private String login;
    private String senha;
    private String nome; //nome de exibição
    private Date nascimento;
    private String descricao;//descrição, sobre o usuário
    private String sexo;
    private String posts;//posts retornados com um join para acesso ao perfil usuario
    private Integer idPosts;//id posts retornados com um join para acesso ao perfilde usuario
    
    private Integer idGrupoOwn;//id dos grupos que possui
    private String nomeGrupoOwn;//nome dos grupos que usuario possui
    
    private String foto;
    private String nomeFoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getIdGrupoOwn() {
        return idGrupoOwn;
    }

    public void setIdGrupoOwn(Integer idGrupoOwn) {
        this.idGrupoOwn = idGrupoOwn;
    }

    public String getNomeGrupoOwn() {
        return nomeGrupoOwn;
    }

    public void setNomeGrupoOwn(String nomeGrupoOwn) {
        this.nomeGrupoOwn = nomeGrupoOwn;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public Integer getIdPosts() {
        return idPosts;
    }

    public void setIdPosts(Integer idPosts) {
        this.idPosts = idPosts;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }
    
    
    
}
