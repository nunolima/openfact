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
public class ItensAnexosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_item", nullable = false)
    private long idItem;
    @Basic(optional = false)
    @Column(name = "id_item_anexo", nullable = false)
    private long idItemAnexo;

    public ItensAnexosPK(long idItem, long idItemAnexo) {
        this.idItem = idItem;
        this.idItemAnexo = idItemAnexo;
    }

    public ItensAnexosPK() {
        
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public long getIdItemAnexo() {
        return idItemAnexo;
    }

    public void setIdItemAnexo(long idItemAnexo) {
        this.idItemAnexo = idItemAnexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idItem;
        hash += (int) idItemAnexo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensAnexosPK)) {
            return false;
        }
        ItensAnexosPK other = (ItensAnexosPK) object;
        if (this.idItem != other.idItem) {
            return false;
        }
        if (this.idItemAnexo != other.idItemAnexo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ItensAnexosPK[idItem=" + idItem + ", idItemAnexo=" + idItemAnexo + "]";
    }

}
