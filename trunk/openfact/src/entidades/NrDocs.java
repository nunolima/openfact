/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "nr_docs", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "NrDocs.findAll", query = "SELECT n FROM NrDocs n"), @NamedQuery(name = "NrDocs.findByAno", query = "SELECT n FROM NrDocs n WHERE n.nrDocsPK.ano = :ano"), @NamedQuery(name = "NrDocs.findByTipoDoc", query = "SELECT n FROM NrDocs n WHERE n.nrDocsPK.tipoDoc = :tipoDoc"), @NamedQuery(name = "NrDocs.findBySerie", query = "SELECT n FROM NrDocs n WHERE n.nrDocsPK.serie = :serie"), @NamedQuery(name = "NrDocs.findByNr", query = "SELECT n FROM NrDocs n WHERE n.nr = :nr")})
public class NrDocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NrDocsPK nrDocsPK;
    @Column(name = "nr")
    private BigInteger nr;
    @JoinColumn(name = "ano", referencedColumnName = "ano", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AnosFiscais anosFiscais;
    @JoinColumn(name = "serie", referencedColumnName = "serie", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Series series;
    @JoinColumn(name = "tipo_doc", referencedColumnName = "tipo_doc", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TiposDoc tiposDoc;

    public NrDocs() {
    }

    public NrDocs(NrDocsPK nrDocsPK) {
        this.nrDocsPK = nrDocsPK;
    }

    public NrDocs(int ano, String tipoDoc, String serie) {
        this.nrDocsPK = new NrDocsPK(ano, tipoDoc, serie);
    }

    public NrDocsPK getNrDocsPK() {
        return nrDocsPK;
    }

    public void setNrDocsPK(NrDocsPK nrDocsPK) {
        this.nrDocsPK = nrDocsPK;
    }

    public BigInteger getNr() {
        return nr;
    }

    public void setNr(BigInteger nr) {
        this.nr = nr;
    }

    public AnosFiscais getAnosFiscais() {
        return anosFiscais;
    }

    public void setAnosFiscais(AnosFiscais anosFiscais) {
        this.anosFiscais = anosFiscais;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public TiposDoc getTiposDoc() {
        return tiposDoc;
    }

    public void setTiposDoc(TiposDoc tiposDoc) {
        this.tiposDoc = tiposDoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrDocsPK != null ? nrDocsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NrDocs)) {
            return false;
        }
        NrDocs other = (NrDocs) object;
        if ((this.nrDocsPK == null && other.nrDocsPK != null) || (this.nrDocsPK != null && !this.nrDocsPK.equals(other.nrDocsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.NrDocs[nrDocsPK=" + nrDocsPK + "]";
    }

}
