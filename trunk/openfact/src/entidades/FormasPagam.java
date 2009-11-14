/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "formas_pagam", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "FormasPagam.findAll", query = "SELECT f FROM FormasPagam f"), @NamedQuery(name = "FormasPagam.findByFormaPagam", query = "SELECT f FROM FormasPagam f WHERE f.formaPagam = :formaPagam"), @NamedQuery(name = "FormasPagam.findByDescricao", query = "SELECT f FROM FormasPagam f WHERE f.descricao = :descricao")})
public class FormasPagam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "forma_pagam", nullable = false, length = 10)
    private String formaPagam;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;

    public FormasPagam() {
    }

    public FormasPagam(String formaPagam) {
        this.formaPagam = formaPagam;
    }

    public FormasPagam(String formaPagam, String descricao) {
        this.formaPagam = formaPagam;
        this.descricao = descricao;
    }

    public String getFormaPagam() {
        return formaPagam;
    }

    public void setFormaPagam(String formaPagam) {
        this.formaPagam = formaPagam;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formaPagam != null ? formaPagam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormasPagam)) {
            return false;
        }
        FormasPagam other = (FormasPagam) object;
        if ((this.formaPagam == null && other.formaPagam != null) || (this.formaPagam != null && !this.formaPagam.equals(other.formaPagam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FormasPagam[formaPagam=" + formaPagam + "]";
    }

}
