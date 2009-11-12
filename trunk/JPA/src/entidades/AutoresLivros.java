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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author PedrodeSousa
 */
@Entity
@Table(name = "autores_livros", uniqueConstraints = {@UniqueConstraint(columnNames = {"livro_id", "autor_id"})})
@NamedQueries({@NamedQuery(name = "AutoresLivros.findAll", query = "SELECT a FROM AutoresLivros a"), @NamedQuery(name = "AutoresLivros.findById", query = "SELECT a FROM AutoresLivros a WHERE a.id = :id")})
public class AutoresLivros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @JoinColumn(name = "autor_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Autores autorId;
    @JoinColumn(name = "livro_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Livros livroId;

    public AutoresLivros() {
    }

    public AutoresLivros(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autores getAutorId() {
        return autorId;
    }

    public void setAutorId(Autores autorId) {
        this.autorId = autorId;
    }

    public Livros getLivroId() {
        return livroId;
    }

    public void setLivroId(Livros livroId) {
        this.livroId = livroId;
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
        if (!(object instanceof AutoresLivros)) {
            return false;
        }
        AutoresLivros other = (AutoresLivros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AutoresLivros[id=" + id + "]";
    }

}
