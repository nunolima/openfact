/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "series", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Series.findAll", query = "SELECT s FROM Series s"), @NamedQuery(name = "Series.findBySerie", query = "SELECT s FROM Series s WHERE s.serie = :serie"), @NamedQuery(name = "Series.findByDescricao", query = "SELECT s FROM Series s WHERE s.descricao = :descricao")})
public class Series implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "serie", nullable = false, length = 4)
    private String serie;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
    private Collection<Docs> docsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "series")
    private Collection<NrDocs> nrDocsCollection;

    public Series() {
    }

    public Series(String serie) {
        this.serie = serie;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Docs> getDocsCollection() {
        return docsCollection;
    }

    public void setDocsCollection(Collection<Docs> docsCollection) {
        this.docsCollection = docsCollection;
    }

    public Collection<NrDocs> getNrDocsCollection() {
        return nrDocsCollection;
    }

    public void setNrDocsCollection(Collection<NrDocs> nrDocsCollection) {
        this.nrDocsCollection = nrDocsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serie != null ? serie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Series)) {
            return false;
        }
        Series other = (Series) object;
        if ((this.serie == null && other.serie != null) || (this.serie != null && !this.serie.equals(other.serie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Series[serie=" + serie + "]";
    }

}
