/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "descontos", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Descontos.findAll", query = "SELECT d FROM Descontos d"), @NamedQuery(name = "Descontos.findByDesconto", query = "SELECT d FROM Descontos d WHERE d.desconto = :desconto"), @NamedQuery(name = "Descontos.findByDescricao", query = "SELECT d FROM Descontos d WHERE d.descricao = :descricao")})
public class Descontos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "desconto", nullable = false, length = 4)
    private String desconto;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descontos")
    private Collection<TaxasDesc> taxasDescCollection;

    public Descontos() {
    }

    public Descontos(String desconto) {
        this.desconto = desconto;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<TaxasDesc> getTaxasDescCollection() {
        return taxasDescCollection;
    }

    public void setTaxasDescCollection(Collection<TaxasDesc> taxasDescCollection) {
        this.taxasDescCollection = taxasDescCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (desconto != null ? desconto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descontos)) {
            return false;
        }
        Descontos other = (Descontos) object;
        if ((this.desconto == null && other.desconto != null) || (this.desconto != null && !this.desconto.equals(other.desconto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Descontos[desconto=" + desconto + "]";
    }

}
