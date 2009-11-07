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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "TESTE", catalog = "", schema = "ROOT", uniqueConstraints=@UniqueConstraint(columnNames={"NOME", "TIPO", "LOCAL"}))
@NamedQueries({@NamedQuery(name = "Teste.findAll", query = "SELECT t FROM Teste t"), @NamedQuery(name = "Teste.findById", query = "SELECT t FROM Teste t WHERE t.id = :id"), @NamedQuery(name = "Teste.findByNome", query = "SELECT t FROM Teste t WHERE t.nome = :nome"), @NamedQuery(name = "Teste.findByTipo", query = "SELECT t FROM Teste t WHERE t.tipo = :tipo"), @NamedQuery(name = "Teste.findByLocal", query = "SELECT t FROM Teste t WHERE t.local = :local")})
public class Teste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NOME", length = 100)
    private String nome;
    @Column(name = "TIPO", length = 100)
    private String tipo;
    @Column(name = "LOCAL", length = 200)
    private String local;

    public Teste() {
    }

    public Teste(Long id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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
        if (!(object instanceof Teste)) {
            return false;
        }
        Teste other = (Teste) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Teste[id=" + id + "]";
    }

}
