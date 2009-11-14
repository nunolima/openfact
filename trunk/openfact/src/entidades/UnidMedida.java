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

/**
 *
 * @author User
 */
@Entity
@Table(name = "unid_medida", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "UnidMedida.findAll", query = "SELECT u FROM UnidMedida u"), @NamedQuery(name = "UnidMedida.findByUnidMedida", query = "SELECT u FROM UnidMedida u WHERE u.unidMedida = :unidMedida"), @NamedQuery(name = "UnidMedida.findByDescricao", query = "SELECT u FROM UnidMedida u WHERE u.descricao = :descricao")})
public class UnidMedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "unid_medida", nullable = false, length = 4)
    private String unidMedida;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "unidMedida")
    private Collection<DocLinhas> docLinhasCollection;
    @OneToMany(mappedBy = "unidMedida")
    private Collection<Itens> itensCollection;

    public UnidMedida() {
    }

    public UnidMedida(String unidMedida) {
        this.unidMedida = unidMedida;
    }

    public String getUnidMedida() {
        return unidMedida;
    }

    public void setUnidMedida(String unidMedida) {
        this.unidMedida = unidMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<DocLinhas> getDocLinhasCollection() {
        return docLinhasCollection;
    }

    public void setDocLinhasCollection(Collection<DocLinhas> docLinhasCollection) {
        this.docLinhasCollection = docLinhasCollection;
    }

    public Collection<Itens> getItensCollection() {
        return itensCollection;
    }

    public void setItensCollection(Collection<Itens> itensCollection) {
        this.itensCollection = itensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidMedida != null ? unidMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidMedida)) {
            return false;
        }
        UnidMedida other = (UnidMedida) object;
        if ((this.unidMedida == null && other.unidMedida != null) || (this.unidMedida != null && !this.unidMedida.equals(other.unidMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UnidMedida[unidMedida=" + unidMedida + "]";
    }

}
