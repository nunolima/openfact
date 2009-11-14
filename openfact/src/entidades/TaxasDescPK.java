/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author User
 */
@Embeddable
public class TaxasDescPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_cat_ent", nullable = false)
    private long idCatEnt;
    @Basic(optional = false)
    @Column(name = "id_cat_item", nullable = false)
    private long idCatItem;
    @Basic(optional = false)
    @Column(name = "desconto", nullable = false, length = 4)
    private String desconto;

    public TaxasDescPK(long idCatEnt, long idCatItem, String desconto) {
        this.idCatEnt = idCatEnt;
        this.idCatItem = idCatItem;
        this.desconto = desconto;
    }

    public TaxasDescPK() {
        
    }

    public long getIdCatEnt() {
        return idCatEnt;
    }

    public void setIdCatEnt(long idCatEnt) {
        this.idCatEnt = idCatEnt;
    }

    public long getIdCatItem() {
        return idCatItem;
    }

    public void setIdCatItem(long idCatItem) {
        this.idCatItem = idCatItem;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCatEnt;
        hash += (int) idCatItem;
        hash += (desconto != null ? desconto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxasDescPK)) {
            return false;
        }
        TaxasDescPK other = (TaxasDescPK) object;
        if (this.idCatEnt != other.idCatEnt) {
            return false;
        }
        if (this.idCatItem != other.idCatItem) {
            return false;
        }
        if ((this.desconto == null && other.desconto != null) || (this.desconto != null && !this.desconto.equals(other.desconto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TaxasDescPK[idCatEnt=" + idCatEnt + ", idCatItem=" + idCatItem + ", desconto=" + desconto + "]";
    }

}
