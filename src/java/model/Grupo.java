/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;

/**
 *
 * @author Bruno
 */
public class Grupo {
    private Integer idGrupo;//id do grupo
    private Integer idDono;
    private String nomeGrupo;
    private Integer idMembro;//id dos participantes do grupo
    private String descricaoGrupo;
    private Date dataCriacao;//data que o grupo foi criado
    private Date dataEntrada;//dia que entrou para o grupo

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdDono() {
        return idDono;
    }
    
    public void setIdDono(Integer idDono) {
        this.idDono = idDono;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }
    
    public Integer getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(Integer idMembro) {
        this.idMembro = idMembro;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDescricaoGrupo() {
        return descricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }
    
    
}
