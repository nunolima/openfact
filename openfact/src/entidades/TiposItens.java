/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "tipos_itens", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "TiposItens.findAll", query = "SELECT t FROM TiposItens t"), @NamedQuery(name = "TiposItens.findByTipoItem", query = "SELECT t FROM TiposItens t WHERE t.tipoItem = :tipoItem"), @NamedQuery(name = "TiposItens.findByDescricao", query = "SELECT t FROM TiposItens t WHERE t.descricao = :descricao"), @NamedQuery(name = "TiposItens.findByIdCatItemOmissao", query = "SELECT t FROM TiposItens t WHERE t.idCatItemOmissao = :idCatItemOmissao")})
public class TiposItens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_item", nullable = false, length = 4)
    private String tipoItem;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @Column(name = "id_cat_item_omissao")
    private BigInteger idCatItemOmissao;
    @OneToMany(mappedBy = "tipoItem")
    private Collection<CatItens> catItensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoItem")
    private Collection<Itens> itensCollection;

    public TiposItens() {
    }

    public TiposItens(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public TiposItens(String tipoItem, String descricao) {
        this.tipoItem = tipoItem;
        this.descricao = descricao;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigInteger getIdCatItemOmissao() {
        return idCatItemOmissao;
    }

    public void setIdCatItemOmissao(BigInteger idCatItemOmissao) {
        this.idCatItemOmissao = idCatItemOmissao;
    }

    public Collection<CatItens> getCatItensCollection() {
        return catItensCollection;
    }

    public void setCatItensCollection(Collection<CatItens> catItensCollection) {
        this.catItensCollection = catItensCollection;
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
        hash += (tipoItem != null ? tipoItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposItens)) {
            return false;
        }
        TiposItens other = (TiposItens) object;
        if ((this.tipoItem == null && other.tipoItem != null) || (this.tipoItem != null && !this.tipoItem.equals(other.tipoItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TiposItens[tipoItem=" + tipoItem + "]";
    }

}
