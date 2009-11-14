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
public class RelEntPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_ent", nullable = false)
    private long idEnt;
    @Basic(optional = false)
    @Column(name = "id_ent_rel", nullable = false)
    private long idEntRel;

    public RelEntPK(long idEnt, long idEntRel) {
        this.idEnt = idEnt;
        this.idEntRel = idEntRel;
    }

    public RelEntPK() {
        
    }

    public long getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(long idEnt) {
        this.idEnt = idEnt;
    }

    public long getIdEntRel() {
        return idEntRel;
    }

    public void setIdEntRel(long idEntRel) {
        this.idEntRel = idEntRel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEnt;
        hash += (int) idEntRel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelEntPK)) {
            return false;
        }
        RelEntPK other = (RelEntPK) object;
        if (this.idEnt != other.idEnt) {
            return false;
        }
        if (this.idEntRel != other.idEntRel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RelEntPK[idEnt=" + idEnt + ", idEntRel=" + idEntRel + "]";
    }

}
