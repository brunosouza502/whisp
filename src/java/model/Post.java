/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Timestamp;

/**
 *
 * @author Bruno
 */
public class Post {
    private Integer idUsuario;
    private Integer idPost;
    private String texto;
    private Timestamp horario;
    private String nomeUsuarioPost;//nome usuario dono do post

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public String getNomeUsuarioPost() {
        return nomeUsuarioPost;
    }

    public void setNomeUsuarioPost(String nomeUsuarioPost) {
        this.nomeUsuarioPost = nomeUsuarioPost;
    }
    
    
}
