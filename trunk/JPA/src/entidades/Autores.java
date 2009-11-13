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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "autores", uniqueConstraints = {@UniqueConstraint(columnNames = {"NOME"})})
@NamedQueries({@NamedQuery(name = "Autores.findAll", query = "SELECT a FROM Autores a"), @NamedQuery(name = "Autores.findById", query = "SELECT a FROM Autores a WHERE a.id = :id"), @NamedQuery(name = "Autores.findByNome", query = "SELECT a FROM Autores a WHERE a.nome = :nome")})
public class Autores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;
    @OneToMany(mappedBy = "autorId")
    @JoinTable(name = "AUTORES_LIVROS",
    joinColumns = @JoinColumn(name = "AUTOR_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "LIVRO_ID"))
    private Collection<Livros> livrosCollection;

    public Autores() {
    }

    public Autores(Long id) {
        this.id = id;
    }

    public Autores(Long id, String nome) {
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
        if (!(object instanceof Autores)) {
            return false;
        }
        Autores other = (Autores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Autores[id=" + id + "]";
    }

}
