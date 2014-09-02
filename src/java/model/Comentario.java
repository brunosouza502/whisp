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
public class Comentario extends Post{//comentario em posts, herda da classe posts porque possui id de usuario, 
                                     //e do post respondido
    private Integer idComentario;//id de comentario
    private String textoComentario;//texto comentario
    private Integer idComentador;//id de usuario comentador
    private Timestamp hora;
    private String nomeComentador;//nome do comentador

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public Integer getIdComentador() {
        return idComentador;
    }

    public void setIdComentador(Integer idComentador) {
        this.idComentador = idComentador;
    }

        
    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public String getNomeComentador() {
        return nomeComentador;
    }

    public void setNomeComentador(String nomeComentador) {
        this.nomeComentador = nomeComentador;
    }
    
    
}
