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
@Table(name = "tipos_mov_stock", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "TiposMovStock.findAll", query = "SELECT t FROM TiposMovStock t"), @NamedQuery(name = "TiposMovStock.findByTipoMovStock", query = "SELECT t FROM TiposMovStock t WHERE t.tipoMovStock = :tipoMovStock"), @NamedQuery(name = "TiposMovStock.findByDescricao", query = "SELECT t FROM TiposMovStock t WHERE t.descricao = :descricao")})
public class TiposMovStock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_mov_stock", nullable = false, length = 4)
    private String tipoMovStock;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "tipoMovStock")
    private Collection<TiposDoc> tiposDocCollection;
    @OneToMany(mappedBy = "tipoMovStock")
    private Collection<MovStock> movStockCollection;

    public TiposMovStock() {
    }

    public TiposMovStock(String tipoMovStock) {
        this.tipoMovStock = tipoMovStock;
    }

    public TiposMovStock(String tipoMovStock, String descricao) {
        this.tipoMovStock = tipoMovStock;
        this.descricao = descricao;
    }

    public String getTipoMovStock() {
        return tipoMovStock;
    }

    public void setTipoMovStock(String tipoMovStock) {
        this.tipoMovStock = tipoMovStock;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<TiposDoc> getTiposDocCollection() {
        return tiposDocCollection;
    }

    public void setTiposDocCollection(Collection<TiposDoc> tiposDocCollection) {
        this.tiposDocCollection = tiposDocCollection;
    }

    public Collection<MovStock> getMovStockCollection() {
        return movStockCollection;
    }

    public void setMovStockCollection(Collection<MovStock> movStockCollection) {
        this.movStockCollection = movStockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoMovStock != null ? tipoMovStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposMovStock)) {
            return false;
        }
        TiposMovStock other = (TiposMovStock) object;
        if ((this.tipoMovStock == null && other.tipoMovStock != null) || (this.tipoMovStock != null && !this.tipoMovStock.equals(other.tipoMovStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TiposMovStock[tipoMovStock=" + tipoMovStock + "]";
    }

}
