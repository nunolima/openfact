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
public class IvasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "zona_iva", nullable = false, length = 6)
    private String zonaIva;
    @Basic(optional = false)
    @Column(name = "id_taxa", nullable = false)
    private int idTaxa;

    public IvasPK(String zonaIva, int idTaxa) {
        this.zonaIva = zonaIva;
        this.idTaxa = idTaxa;
    }

    public IvasPK() {
        
    }

    public String getZonaIva() {
        return zonaIva;
    }

    public void setZonaIva(String zonaIva) {
        this.zonaIva = zonaIva;
    }

    public int getIdTaxa() {
        return idTaxa;
    }

    public void setIdTaxa(int idTaxa) {
        this.idTaxa = idTaxa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zonaIva != null ? zonaIva.hashCode() : 0);
        hash += (int) idTaxa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IvasPK)) {
            return false;
        }
        IvasPK other = (IvasPK) object;
        if ((this.zonaIva == null && other.zonaIva != null) || (this.zonaIva != null && !this.zonaIva.equals(other.zonaIva))) {
            return false;
        }
        if (this.idTaxa != other.idTaxa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.IvasPK[zonaIva=" + zonaIva + ", idTaxa=" + idTaxa + "]";
    }

}
