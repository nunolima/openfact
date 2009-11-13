/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author PedrodeSousa
 */
@Entity
@Table(name = "areas", uniqueConstraints = {@UniqueConstraint(columnNames = {"DSC"})})
@NamedQueries({@NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a"), @NamedQuery(name = "Areas.findById", query = "SELECT a FROM Areas a WHERE a.id = :id"), @NamedQuery(name = "Areas.findByDsc", query = "SELECT a FROM Areas a WHERE a.dsc = :dsc"), @NamedQuery(name = "Areas.findByObs", query = "SELECT a FROM Areas a WHERE a.obs = :obs")})
public class Areas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "DSC", nullable = false, length = 100)
    private String dsc;
    @Column(name = "OBS", length = 50)
    private String obs;
    @OneToMany(mappedBy = "areaId")
    private Collection<Livros> livrosCollection;

    public Areas() {
    }

    public Areas(Long id) {
        this.id = id;
    }

    public Areas(Long id, String dsc) {
        this.id = id;
        this.dsc = dsc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Collection<Livros> getLivrosCollection() {
        return livrosCollection;
    }

    public void setLivrosCollection(Collection<Livros> livrosCollection) {
        this.livrosCollection = livrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Areas[id=" + id + "]";
    }

}
