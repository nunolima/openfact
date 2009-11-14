/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "cond_pagam", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "CondPagam.findAll", query = "SELECT c FROM CondPagam c"), @NamedQuery(name = "CondPagam.findByCondPagam", query = "SELECT c FROM CondPagam c WHERE c.condPagam = :condPagam"), @NamedQuery(name = "CondPagam.findByDescricao", query = "SELECT c FROM CondPagam c WHERE c.descricao = :descricao"), @NamedQuery(name = "CondPagam.findByNrDias", query = "SELECT c FROM CondPagam c WHERE c.nrDias = :nrDias")})
public class CondPagam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cond_pagam", nullable = false, length = 4)
    private String condPagam;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @Column(name = "nr_dias")
    private Integer nrDias;
    @OneToMany(mappedBy = "condPagamOmissao")
    private Collection<TiposEnt> tiposEntCollection;
    @OneToMany(mappedBy = "condPagam")
    private Collection<Entidades> entidadesCollection;

    public CondPagam() {
    }

    public CondPagam(String condPagam) {
        this.condPagam = condPagam;
    }

    public CondPagam(String condPagam, String descricao) {
        this.condPagam = condPagam;
        this.descricao = descricao;
    }

    public String getCondPagam() {
        return condPagam;
    }

    public void setCondPagam(String condPagam) {
        this.condPagam = condPagam;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNrDias() {
        return nrDias;
    }

    public void setNrDias(Integer nrDias) {
        this.nrDias = nrDias;
    }

    public Collection<TiposEnt> getTiposEntCollection() {
        return tiposEntCollection;
    }

    public void setTiposEntCollection(Collection<TiposEnt> tiposEntCollection) {
        this.tiposEntCollection = tiposEntCollection;
    }

    public Collection<Entidades> getEntidadesCollection() {
        return entidadesCollection;
    }

    public void setEntidadesCollection(Collection<Entidades> entidadesCollection) {
        this.entidadesCollection = entidadesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (condPagam != null ? condPagam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CondPagam)) {
            return false;
        }
        CondPagam other = (CondPagam) object;
        if ((this.condPagam == null && other.condPagam != null) || (this.condPagam != null && !this.condPagam.equals(other.condPagam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CondPagam[condPagam=" + condPagam + "]";
    }

}
