/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "historico_docs", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "HistoricoDocs.findAll", query = "SELECT h FROM HistoricoDocs h"), @NamedQuery(name = "HistoricoDocs.findByIdDoc", query = "SELECT h FROM HistoricoDocs h WHERE h.idDoc = :idDoc"), @NamedQuery(name = "HistoricoDocs.findByDocPdf", query = "SELECT h FROM HistoricoDocs h WHERE h.docPdf = :docPdf")})
public class HistoricoDocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_doc", nullable = false)
    private Long idDoc;
    @Basic(optional = false)
    @Column(name = "doc_pdf", nullable = false)
    private long docPdf;
    @JoinColumn(name = "id_doc", referencedColumnName = "id_doc", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Docs docs;

    public HistoricoDocs() {
    }

    public HistoricoDocs(Long idDoc) {
        this.idDoc = idDoc;
    }

    public HistoricoDocs(Long idDoc, long docPdf) {
        this.idDoc = idDoc;
        this.docPdf = docPdf;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }

    public long getDocPdf() {
        return docPdf;
    }

    public void setDocPdf(long docPdf) {
        this.docPdf = docPdf;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDoc != null ? idDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoDocs)) {
            return false;
        }
        HistoricoDocs other = (HistoricoDocs) object;
        if ((this.idDoc == null && other.idDoc != null) || (this.idDoc != null && !this.idDoc.equals(other.idDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HistoricoDocs[idDoc=" + idDoc + "]";
    }

}
