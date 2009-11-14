/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
public class TipoEntidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=100, unique=true, nullable=false)
    private String descricao;

    @OneToMany(mappedBy="tipoEntidade")
    private Set<Entidades> entidades;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Entidades> getEntidades() {
        return entidades;
    }

    public void setEntidades(Set<Entidades> entidades) {
        this.entidades = entidades;
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
        if (!(object instanceof TipoEntidades)) {
            return false;
        }
        TipoEntidades other = (TipoEntidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoEntidades[id=" + id + ", descricao=" + descricao + "]";
    }

}
