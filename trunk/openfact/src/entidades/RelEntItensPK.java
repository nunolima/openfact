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
public class RelEntItensPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_ent", nullable = false)
    private long idEnt;
    @Basic(optional = false)
    @Column(name = "tipo_ent", nullable = false, length = 4)
    private String tipoEnt;
    @Basic(optional = false)
    @Column(name = "id_item", nullable = false)
    private long idItem;

    public RelEntItensPK(long idEnt, String tipoEnt, long idItem) {
        this.idEnt = idEnt;
        this.tipoEnt = tipoEnt;
        this.idItem = idItem;
    }

    public RelEntItensPK() {
        
    }


    public long getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(long idEnt) {
        this.idEnt = idEnt;
    }

    public String getTipoEnt() {
        return tipoEnt;
    }

    public void setTipoEnt(String tipoEnt) {
        this.tipoEnt = tipoEnt;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEnt;
        hash += (tipoEnt != null ? tipoEnt.hashCode() : 0);
        hash += (int) idItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelEntItensPK)) {
            return false;
        }
        RelEntItensPK other = (RelEntItensPK) object;
        if (this.idEnt != other.idEnt) {
            return false;
        }
        if ((this.tipoEnt == null && other.tipoEnt != null) || (this.tipoEnt != null && !this.tipoEnt.equals(other.tipoEnt))) {
            return false;
        }
        if (this.idItem != other.idItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RelEntItensPK[idEnt=" + idEnt + ", tipoEnt=" + tipoEnt + ", idItem=" + idItem + "]";
    }

}
