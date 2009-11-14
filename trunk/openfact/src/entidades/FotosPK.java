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
public class FotosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_ent", nullable = false)
    private long idEnt;
    @Basic(optional = false)
    @Column(name = "foto_id", nullable = false)
    private long fotoId;

    public FotosPK(long idEnt, long fotoId) {
        this.idEnt = idEnt;
        this.fotoId = fotoId;
    }

    public FotosPK() {
        
    }

    public long getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(long idEnt) {
        this.idEnt = idEnt;
    }

    public long getFotoId() {
        return fotoId;
    }

    public void setFotoId(long fotoId) {
        this.fotoId = fotoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEnt;
        hash += (int) fotoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FotosPK)) {
            return false;
        }
        FotosPK other = (FotosPK) object;
        if (this.idEnt != other.idEnt) {
            return false;
        }
        if (this.fotoId != other.fotoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FotosPK[idEnt=" + idEnt + ", fotoId=" + fotoId + "]";
    }

}
