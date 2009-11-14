/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "tipos_ent", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "TiposEnt.findAll", query = "SELECT t FROM TiposEnt t"), @NamedQuery(name = "TiposEnt.findByTipoEnt", query = "SELECT t FROM TiposEnt t WHERE t.tipoEnt = :tipoEnt"), @NamedQuery(name = "TiposEnt.findByDescricao", query = "SELECT t FROM TiposEnt t WHERE t.descricao = :descricao"), @NamedQuery(name = "TiposEnt.findByCodPoc", query = "SELECT t FROM TiposEnt t WHERE t.codPoc = :codPoc")})
public class TiposEnt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_ent", nullable = false, length = 4)
    private String tipoEnt;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @Column(name = "cod_poc", length = 2147483647)
    private String codPoc;
    @OneToMany(mappedBy = "tipoEnt")
    private Collection<CatEnt> catEntCollection;
    @JoinColumn(name = "id_cat_ent_omissao", referencedColumnName = "id_cat_ent")
    @ManyToOne
    private CatEnt idCatEntOmissao;
    @JoinColumn(name = "cond_pagam_omissao", referencedColumnName = "cond_pagam")
    @ManyToOne
    private CondPagam condPagamOmissao;
    @OneToMany(mappedBy = "tipoEnt")
    private Collection<Entidades> entidadesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposEnt")
    private Collection<RelEntItens> relEntItensCollection;
    @OneToMany(mappedBy = "tipoEnt")
    private Collection<RelTiposEnt> relTiposEntCollection;
    @OneToMany(mappedBy = "tipoEntRel")
    private Collection<RelTiposEnt> relTiposEntCollection1;

    public TiposEnt() {
    }

    public TiposEnt(String tipoEnt) {
        this.tipoEnt = tipoEnt;
    }

    public TiposEnt(String tipoEnt, String descricao) {
        this.tipoEnt = tipoEnt;
        this.descricao = descricao;
    }

    public String getTipoEnt() {
        return tipoEnt;
    }

    public void setTipoEnt(String tipoEnt) {
        this.tipoEnt = tipoEnt;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodPoc() {
        return codPoc;
    }

    public void setCodPoc(String codPoc) {
        this.codPoc = codPoc;
    }

    public Collection<CatEnt> getCatEntCollection() {
        return catEntCollection;
    }

    public void setCatEntCollection(Collection<CatEnt> catEntCollection) {
        this.catEntCollection = catEntCollection;
    }

    public CatEnt getIdCatEntOmissao() {
        return idCatEntOmissao;
    }

    public void setIdCatEntOmissao(CatEnt idCatEntOmissao) {
        this.idCatEntOmissao = idCatEntOmissao;
    }

    public CondPagam getCondPagamOmissao() {
        return condPagamOmissao;
    }

    public void setCondPagamOmissao(CondPagam condPagamOmissao) {
        this.condPagamOmissao = condPagamOmissao;
    }

    public Collection<Entidades> getEntidadesCollection() {
        return entidadesCollection;
    }

    public void setEntidadesCollection(Collection<Entidades> entidadesCollection) {
        this.entidadesCollection = entidadesCollection;
    }

    public Collection<RelEntItens> getRelEntItensCollection() {
        return relEntItensCollection;
    }

    public void setRelEntItensCollection(Collection<RelEntItens> relEntItensCollection) {
        this.relEntItensCollection = relEntItensCollection;
    }

    public Collection<RelTiposEnt> getRelTiposEntCollection() {
        return relTiposEntCollection;
    }

    public void setRelTiposEntCollection(Collection<RelTiposEnt> relTiposEntCollection) {
        this.relTiposEntCollection = relTiposEntCollection;
    }

    public Collection<RelTiposEnt> getRelTiposEntCollection1() {
        return relTiposEntCollection1;
    }

    public void setRelTiposEntCollection1(Collection<RelTiposEnt> relTiposEntCollection1) {
        this.relTiposEntCollection1 = relTiposEntCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoEnt != null ? tipoEnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposEnt)) {
            return false;
        }
        TiposEnt other = (TiposEnt) object;
        if ((this.tipoEnt == null && other.tipoEnt != null) || (this.tipoEnt != null && !this.tipoEnt.equals(other.tipoEnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TiposEnt[tipoEnt=" + tipoEnt + "]";
    }

}
