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
public class Republicar extends Post{//republicação de posts; herda de posts, pois é um tipo especifico de post
    private Integer idRepublica;//id da republicação
    private Integer idUsuarioRepublica;//id do usuario que está republicando
    private Integer idPostRepublicado;//id de post a ser republicado
    private Timestamp horaRepublicacao;//hora da republicação
    private String nomeUsuarioRepublica;//nome do usuario que republicou os posts

    public Integer getIdRepublica() {
        return idRepublica;
    }

    public void setIdRepublica(Integer idRepublica) {
        this.idRepublica = idRepublica;
    }

    public Integer getIdUsuarioRepublica() {
        return idUsuarioRepublica;
    }

    public void setIdUsuarioRepublica(Integer idUsuarioRepublica) {
        this.idUsuarioRepublica = idUsuarioRepublica;
    }

    public Integer getIdPostRepublicado() {
        return idPostRepublicado;
    }

    public void setIdPostRepublicado(Integer idPostRepublicado) {
        this.idPostRepublicado = idPostRepublicado;
    }

    public Timestamp getHoraRepublicacao() {
        return horaRepublicacao;
    }

    public void setHoraRepublicacao(Timestamp horaRepublicacao) {
        this.horaRepublicacao = horaRepublicacao;
    }

    public String getNomeUsuarioRepublica() {
        return nomeUsuarioRepublica;
    }

    public void setNomeUsuarioRepublica(String nomeUsuarioRepublica) {
        this.nomeUsuarioRepublica = nomeUsuarioRepublica;
    }
    
    
    
}
