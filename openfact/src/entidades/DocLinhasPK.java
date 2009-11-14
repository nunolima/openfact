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
public class DocLinhasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_doc", nullable = false)
    private long idDoc;
    @Basic(optional = false)
    @Column(name = "linha", nullable = false)
    private long linha;

    public DocLinhasPK() {
    }

    public DocLinhasPK(long idDoc, long linha) {
        this.idDoc = idDoc;
        this.linha = linha;
    }

    public long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(long idDoc) {
        this.idDoc = idDoc;
    }

    public long getLinha() {
        return linha;
    }

    public void setLinha(long linha) {
        this.linha = linha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDoc;
        hash += (int) linha;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocLinhasPK)) {
            return false;
        }
        DocLinhasPK other = (DocLinhasPK) object;
        if (this.idDoc != other.idDoc) {
            return false;
        }
        if (this.linha != other.linha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DocLinhasPK[idDoc=" + idDoc + ", linha=" + linha + "]";
    }

}
