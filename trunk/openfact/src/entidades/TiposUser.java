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
@Table(name = "tipos_user", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "TiposUser.findAll", query = "SELECT t FROM TiposUser t"), @NamedQuery(name = "TiposUser.findByIdTipoUser", query = "SELECT t FROM TiposUser t WHERE t.idTipoUser = :idTipoUser"), @NamedQuery(name = "TiposUser.findByDescricao", query = "SELECT t FROM TiposUser t WHERE t.descricao = :descricao")})
public class TiposUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_user", nullable = false)
    private Integer idTipoUser;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @ManyToMany(mappedBy = "tiposUserCollection")
    private Collection<Menus> menusCollection;
    @OneToMany(mappedBy = "idTipoUser")
    private Collection<Utilizadores> utilizadoresCollection;

    public TiposUser() {
    }

    public TiposUser(Integer idTipoUser) {
        this.idTipoUser = idTipoUser;
    }

    public TiposUser(Integer idTipoUser, String descricao) {
        this.idTipoUser = idTipoUser;
        this.descricao = descricao;
    }

    public Integer getIdTipoUser() {
        return idTipoUser;
    }

    public void setIdTipoUser(Integer idTipoUser) {
        this.idTipoUser = idTipoUser;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Menus> getMenusCollection() {
        return menusCollection;
    }

    public void setMenusCollection(Collection<Menus> menusCollection) {
        this.menusCollection = menusCollection;
    }

    public Collection<Utilizadores> getUtilizadoresCollection() {
        return utilizadoresCollection;
    }

    public void setUtilizadoresCollection(Collection<Utilizadores> utilizadoresCollection) {
        this.utilizadoresCollection = utilizadoresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUser != null ? idTipoUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposUser)) {
            return false;
        }
        TiposUser other = (TiposUser) object;
        if ((this.idTipoUser == null && other.idTipoUser != null) || (this.idTipoUser != null && !this.idTipoUser.equals(other.idTipoUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TiposUser[idTipoUser=" + idTipoUser + "]";
    }

}
