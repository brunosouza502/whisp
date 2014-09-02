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
public class Follow {
    Integer idFollowing;//id da relação de seguir
    Integer idSeguidor;//id do seguidor
    Integer idSeguido;//id de quem está sendo seguido, como o usuario autenticado
    String nomeSeguidor;//nome de seguidores, para listá-los
    String nomeSeguido;//nome dos usuarios seguidos

    public Integer getIdFollowing() {
        return idFollowing;
    }

    public void setIdFollowing(Integer idFollowing) {
        this.idFollowing = idFollowing;
    }

    public Integer getIdSeguidor() {
        return idSeguidor;
    }

    public void setIdSeguidor(Integer idSeguidor) {
        this.idSeguidor = idSeguidor;
    }

    public Integer getIdSeguido() {
        return idSeguido;
    }

    public void setIdSeguido(Integer idSeguido) {
        this.idSeguido = idSeguido;
    }

    public String getNomeSeguidor() {
        return nomeSeguidor;
    }

    public void setNomeSeguidor(String nomeSeguidor) {
        this.nomeSeguidor = nomeSeguidor;
    }

    public String getNomeSeguido() {
        return nomeSeguido;
    }

    public void setNomeSeguido(String nomeSeguido) {
        this.nomeSeguido = nomeSeguido;
    }
    
    
}
