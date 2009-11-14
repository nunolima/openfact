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
@Table(name = "cat_itens", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "CatItens.findAll", query = "SELECT c FROM CatItens c"), @NamedQuery(name = "CatItens.findByIdCatItem", query = "SELECT c FROM CatItens c WHERE c.idCatItem = :idCatItem"), @NamedQuery(name = "CatItens.findByDescricao", query = "SELECT c FROM CatItens c WHERE c.descricao = :descricao")})
public class CatItens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cat_item", nullable = false)
    private Long idCatItem;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catItens")
    private Collection<TaxasDesc> taxasDescCollection;
    @JoinColumn(name = "tipo_item", referencedColumnName = "tipo_item")
    @ManyToOne
    private TiposItens tipoItem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatItem")
    private Collection<Itens> itensCollection;

    public CatItens() {
    }

    public CatItens(Long idCatItem) {
        this.idCatItem = idCatItem;
    }

    public Long getIdCatItem() {
        return idCatItem;
    }

    public void setIdCatItem(Long idCatItem) {
        this.idCatItem = idCatItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<TaxasDesc> getTaxasDescCollection() {
        return taxasDescCollection;
    }

    public void setTaxasDescCollection(Collection<TaxasDesc> taxasDescCollection) {
        this.taxasDescCollection = taxasDescCollection;
    }

    public TiposItens getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TiposItens tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Collection<Itens> getItensCollection() {
        return itensCollection;
    }

    public void setItensCollection(Collection<Itens> itensCollection) {
        this.itensCollection = itensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatItem != null ? idCatItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatItens)) {
            return false;
        }
        CatItens other = (CatItens) object;
        if ((this.idCatItem == null && other.idCatItem != null) || (this.idCatItem != null && !this.idCatItem.equals(other.idCatItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CatItens[idCatItem=" + idCatItem + "]";
    }

}
