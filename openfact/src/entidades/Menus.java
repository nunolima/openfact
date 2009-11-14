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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "menus", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Menus.findAll", query = "SELECT m FROM Menus m"), @NamedQuery(name = "Menus.findByIdMenu", query = "SELECT m FROM Menus m WHERE m.idMenu = :idMenu"), @NamedQuery(name = "Menus.findByDescricao", query = "SELECT m FROM Menus m WHERE m.descricao = :descricao")})
public class Menus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_menu", nullable = false)
    private Integer idMenu;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @JoinTable(name = "acessos", joinColumns = {@JoinColumn(name = "id_menu", referencedColumnName = "id_menu", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_tipo_user", referencedColumnName = "id_tipo_user", nullable = false)})
    @ManyToMany
    private Collection<TiposUser> tiposUserCollection;

    public Menus() {
    }

    public Menus(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<TiposUser> getTiposUserCollection() {
        return tiposUserCollection;
    }

    public void setTiposUserCollection(Collection<TiposUser> tiposUserCollection) {
        this.tiposUserCollection = tiposUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menus)) {
            return false;
        }
        Menus other = (Menus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Menus[idMenu=" + idMenu + "]";
    }

}
