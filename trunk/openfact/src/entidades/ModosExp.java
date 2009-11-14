/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "modos_exp", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "ModosExp.findAll", query = "SELECT m FROM ModosExp m"), @NamedQuery(name = "ModosExp.findByIdModoExp", query = "SELECT m FROM ModosExp m WHERE m.idModoExp = :idModoExp"), @NamedQuery(name = "ModosExp.findByDescricao", query = "SELECT m FROM ModosExp m WHERE m.descricao = :descricao"), @NamedQuery(name = "ModosExp.findByCusto", query = "SELECT m FROM ModosExp m WHERE m.custo = :custo")})
public class ModosExp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_modo_exp", nullable = false)
    private Long idModoExp;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @Column(name = "custo")
    private BigInteger custo;
    @OneToMany(mappedBy = "idModoExp")
    private Collection<Entidades> entidadesCollection;

    public ModosExp() {
    }

    public ModosExp(Long idModoExp) {
        this.idModoExp = idModoExp;
    }

    public ModosExp(Long idModoExp, String descricao) {
        this.idModoExp = idModoExp;
        this.descricao = descricao;
    }

    public Long getIdModoExp() {
        return idModoExp;
    }

    public void setIdModoExp(Long idModoExp) {
        this.idModoExp = idModoExp;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigInteger getCusto() {
        return custo;
    }

    public void setCusto(BigInteger custo) {
        this.custo = custo;
    }

    public Collection<Entidades> getEntidadesCollection() {
        return entidadesCollection;
    }

    public void setEntidadesCollection(Collection<Entidades> entidadesCollection) {
        this.entidadesCollection = entidadesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModoExp != null ? idModoExp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModosExp)) {
            return false;
        }
        ModosExp other = (ModosExp) object;
        if ((this.idModoExp == null && other.idModoExp != null) || (this.idModoExp != null && !this.idModoExp.equals(other.idModoExp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ModosExp[idModoExp=" + idModoExp + "]";
    }

}
