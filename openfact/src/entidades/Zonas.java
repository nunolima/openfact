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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "zonas", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Zonas.findAll", query = "SELECT z FROM Zonas z"), @NamedQuery(name = "Zonas.findByZona", query = "SELECT z FROM Zonas z WHERE z.zona = :zona"), @NamedQuery(name = "Zonas.findByDescricao", query = "SELECT z FROM Zonas z WHERE z.descricao = :descricao")})
public class Zonas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "zona", nullable = false, length = 4)
    private String zona;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @ManyToMany(mappedBy = "zonasCollection")
    private Collection<Entidades> entidadesCollection;
    @OneToMany(mappedBy = "zona")
    private Collection<Entidades> entidadesCollection1;

    public Zonas() {
    }

    public Zonas(String zona) {
        this.zona = zona;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Entidades> getEntidadesCollection() {
        return entidadesCollection;
    }

    public void setEntidadesCollection(Collection<Entidades> entidadesCollection) {
        this.entidadesCollection = entidadesCollection;
    }

    public Collection<Entidades> getEntidadesCollection1() {
        return entidadesCollection1;
    }

    public void setEntidadesCollection1(Collection<Entidades> entidadesCollection1) {
        this.entidadesCollection1 = entidadesCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zona != null ? zona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zonas)) {
            return false;
        }
        Zonas other = (Zonas) object;
        if ((this.zona == null && other.zona != null) || (this.zona != null && !this.zona.equals(other.zona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Zonas[zona=" + zona + "]";
    }

}
