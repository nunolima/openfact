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
@Table(name = "editoras", uniqueConstraints = {@UniqueConstraint(columnNames = {"NOME"})})
@NamedQueries({@NamedQuery(name = "Editoras.findAll", query = "SELECT e FROM Editoras e"), @NamedQuery(name = "Editoras.findById", query = "SELECT e FROM Editoras e WHERE e.id = :id"), @NamedQuery(name = "Editoras.findByNome", query = "SELECT e FROM Editoras e WHERE e.nome = :nome"), @NamedQuery(name = "Editoras.findByObs", query = "SELECT e FROM Editoras e WHERE e.obs = :obs")})
public class Editoras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;
    @Column(name = "OBS", length = 50)
    private String obs;
    @OneToMany(mappedBy = "editoraId")
    private Collection<Livros> livrosCollection;

    public Editoras() {
    }

    public Editoras(Long id) {
        this.id = id;
    }

    public Editoras(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof Editoras)) {
            return false;
        }
        Editoras other = (Editoras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Editoras[id=" + id + "]";
    }

}
