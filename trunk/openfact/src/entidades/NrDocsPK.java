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
public class NrDocsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ano", nullable = false)
    private int ano;
    @Basic(optional = false)
    @Column(name = "tipo_doc", nullable = false, length = 4)
    private String tipoDoc;
    @Basic(optional = false)
    @Column(name = "serie", nullable = false, length = 4)
    private String serie;

    public NrDocsPK(int ano, String tipoDoc, String serie) {
        this.ano = ano;
        this.tipoDoc = tipoDoc;
        this.serie = serie;
    }

    public NrDocsPK() {
        
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ano;
        hash += (tipoDoc != null ? tipoDoc.hashCode() : 0);
        hash += (serie != null ? serie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NrDocsPK)) {
            return false;
        }
        NrDocsPK other = (NrDocsPK) object;
        if (this.ano != other.ano) {
            return false;
        }
        if ((this.tipoDoc == null && other.tipoDoc != null) || (this.tipoDoc != null && !this.tipoDoc.equals(other.tipoDoc))) {
            return false;
        }
        if ((this.serie == null && other.serie != null) || (this.serie != null && !this.serie.equals(other.serie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.NrDocsPK[ano=" + ano + ", tipoDoc=" + tipoDoc + ", serie=" + serie + "]";
    }

}
