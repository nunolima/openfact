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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "taxas_adic", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "TaxasAdic.findAll", query = "SELECT t FROM TaxasAdic t"), @NamedQuery(name = "TaxasAdic.findByIdTaxaAdic", query = "SELECT t FROM TaxasAdic t WHERE t.idTaxaAdic = :idTaxaAdic"), @NamedQuery(name = "TaxasAdic.findByDescricao", query = "SELECT t FROM TaxasAdic t WHERE t.descricao = :descricao"), @NamedQuery(name = "TaxasAdic.findByTaxaAdicValor", query = "SELECT t FROM TaxasAdic t WHERE t.taxaAdicValor = :taxaAdicValor"), @NamedQuery(name = "TaxasAdic.findByValorTaxa", query = "SELECT t FROM TaxasAdic t WHERE t.valorTaxa = :valorTaxa")})
public class TaxasAdic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_taxa_adic", nullable = false)
    private Long idTaxaAdic;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @Column(name = "taxa_adic_valor")
    private Boolean taxaAdicValor;
    @Column(name = "valor_taxa")
    private BigInteger valorTaxa;
    @ManyToMany(mappedBy = "taxasAdicCollection")
    private Collection<Itens> itensCollection;

    public TaxasAdic() {
    }

    public TaxasAdic(Long idTaxaAdic) {
        this.idTaxaAdic = idTaxaAdic;
    }

    public TaxasAdic(Long idTaxaAdic, String descricao) {
        this.idTaxaAdic = idTaxaAdic;
        this.descricao = descricao;
    }

    public Long getIdTaxaAdic() {
        return idTaxaAdic;
    }

    public void setIdTaxaAdic(Long idTaxaAdic) {
        this.idTaxaAdic = idTaxaAdic;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getTaxaAdicValor() {
        return taxaAdicValor;
    }

    public void setTaxaAdicValor(Boolean taxaAdicValor) {
        this.taxaAdicValor = taxaAdicValor;
    }

    public BigInteger getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(BigInteger valorTaxa) {
        this.valorTaxa = valorTaxa;
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
        hash += (idTaxaAdic != null ? idTaxaAdic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxasAdic)) {
            return false;
        }
        TaxasAdic other = (TaxasAdic) object;
        if ((this.idTaxaAdic == null && other.idTaxaAdic != null) || (this.idTaxaAdic != null && !this.idTaxaAdic.equals(other.idTaxaAdic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TaxasAdic[idTaxaAdic=" + idTaxaAdic + "]";
    }

}
