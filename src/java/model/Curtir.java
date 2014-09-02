/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Bruno
 */
public class Curtir {
    private Integer idLike;
    private Integer idUsuarioLike;//usuario curtidor
    private Integer idPostLiked;//post curtido
    private Integer idUsuarioOwn;//dono do post
    private Integer nomeUsuarioLike;//nome do usuario curtidor caso precise
    private Integer nomeUsuarioOwn;//nome do dono do post caso precise
    
    private Integer qtdLikes;//mostrar quantidade de likes positivos em cada post

    public Integer getIdLike() {
        return idLike;
    }

    public void setIdLike(Integer idLike) {
        this.idLike = idLike;
    }

    
    public Integer getIdUsuarioLike() {
        return idUsuarioLike;
    }

    public void setIdUsuarioLike(Integer idUsuarioLike) {
        this.idUsuarioLike = idUsuarioLike;
    }

    public Integer getIdPostLiked() {
        return idPostLiked;
    }

    public void setIdPostLiked(Integer idPostLiked) {
        this.idPostLiked = idPostLiked;
    }

    public Integer getIdUsuarioOwn() {
        return idUsuarioOwn;
    }

    public void setIdUsuarioOwn(Integer idUsuarioOwn) {
        this.idUsuarioOwn = idUsuarioOwn;
    }

    public Integer getNomeUsuarioLike() {
        return nomeUsuarioLike;
    }

    public void setNomeUsuarioLike(Integer nomeUsuarioLike) {
        this.nomeUsuarioLike = nomeUsuarioLike;
    }

    public Integer getNomeUsuarioOwn() {
        return nomeUsuarioOwn;
    }

    public void setNomeUsuarioOwn(Integer nomeUsuarioOwn) {
        this.nomeUsuarioOwn = nomeUsuarioOwn;
    }

    public Integer getQtdLikes() {
        return qtdLikes;
    }

    public void setQtdLikes(Integer qtdLikes) {
        this.qtdLikes = qtdLikes;
    }
    
    
    
}
