/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "CATEGORIAS")
@NamedQueries({@NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c"), @NamedQuery(name = "Categorias.findById", query = "SELECT c FROM Categorias c WHERE c.id = :id"), @NamedQuery(name = "Categorias.findByDescricao", query = "SELECT c FROM Categorias c WHERE c.descricao = :descricao"), @NamedQuery(name = "Categorias.findByActivo", query = "SELECT c FROM Categorias c WHERE c.activo = :activo")})
public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ACTIVO")
    private Integer activo;

    public Categorias() {
    }

    public Categorias(Integer id) {
        this.id = id;
    }

    public Categorias(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
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
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Categorias[id=" + id + "]";
    }

}
