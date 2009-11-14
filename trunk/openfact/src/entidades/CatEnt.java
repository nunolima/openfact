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
@Table(name = "cat_ent", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "CatEnt.findAll", query = "SELECT c FROM CatEnt c"), @NamedQuery(name = "CatEnt.findByIdCatEnt", query = "SELECT c FROM CatEnt c WHERE c.idCatEnt = :idCatEnt"), @NamedQuery(name = "CatEnt.findByDescricao", query = "SELECT c FROM CatEnt c WHERE c.descricao = :descricao")})
public class CatEnt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cat_ent", nullable = false)
    private Long idCatEnt;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @JoinColumn(name = "tipo_ent", referencedColumnName = "tipo_ent")
    @ManyToOne
    private TiposEnt tipoEnt;
    @OneToMany(mappedBy = "idCatEntOmissao")
    private Collection<TiposEnt> tiposEntCollection;
    @OneToMany(mappedBy = "idCatEnt")
    private Collection<Entidades> entidadesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catEnt")
    private Collection<TaxasDesc> taxasDescCollection;

    public CatEnt() {
    }

    public CatEnt(Long idCatEnt) {
        this.idCatEnt = idCatEnt;
    }

    public Long getIdCatEnt() {
        return idCatEnt;
    }

    public void setIdCatEnt(Long idCatEnt) {
        this.idCatEnt = idCatEnt;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TiposEnt getTipoEnt() {
        return tipoEnt;
    }

    public void setTipoEnt(TiposEnt tipoEnt) {
        this.tipoEnt = tipoEnt;
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

    public Collection<TaxasDesc> getTaxasDescCollection() {
        return taxasDescCollection;
    }

    public void setTaxasDescCollection(Collection<TaxasDesc> taxasDescCollection) {
        this.taxasDescCollection = taxasDescCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatEnt != null ? idCatEnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatEnt)) {
            return false;
        }
        CatEnt other = (CatEnt) object;
        if ((this.idCatEnt == null && other.idCatEnt != null) || (this.idCatEnt != null && !this.idCatEnt.equals(other.idCatEnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CatEnt[idCatEnt=" + idCatEnt + "]";
    }

}
