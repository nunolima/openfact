/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "taxas_desc", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "TaxasDesc.findAll", query = "SELECT t FROM TaxasDesc t"), @NamedQuery(name = "TaxasDesc.findByIdCatEnt", query = "SELECT t FROM TaxasDesc t WHERE t.taxasDescPK.idCatEnt = :idCatEnt"), @NamedQuery(name = "TaxasDesc.findByIdCatItem", query = "SELECT t FROM TaxasDesc t WHERE t.taxasDescPK.idCatItem = :idCatItem"), @NamedQuery(name = "TaxasDesc.findByDesconto", query = "SELECT t FROM TaxasDesc t WHERE t.taxasDescPK.desconto = :desconto"), @NamedQuery(name = "TaxasDesc.findByTaxaDesc", query = "SELECT t FROM TaxasDesc t WHERE t.taxaDesc = :taxaDesc"), @NamedQuery(name = "TaxasDesc.findByDataCriacao", query = "SELECT t FROM TaxasDesc t WHERE t.dataCriacao = :dataCriacao"), @NamedQuery(name = "TaxasDesc.findByDataAlteracao", query = "SELECT t FROM TaxasDesc t WHERE t.dataAlteracao = :dataAlteracao")})
public class TaxasDesc implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaxasDescPK taxasDescPK;
    @Column(name = "taxa_desc")
    private Integer taxaDesc;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @JoinColumn(name = "id_cat_ent", referencedColumnName = "id_cat_ent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CatEnt catEnt;
    @JoinColumn(name = "id_cat_item", referencedColumnName = "id_cat_item", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CatItens catItens;
    @JoinColumn(name = "desconto", referencedColumnName = "desconto", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Descontos descontos;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;

    public TaxasDesc() {
    }

    public TaxasDesc(TaxasDescPK taxasDescPK) {
        this.taxasDescPK = taxasDescPK;
    }

    public TaxasDesc(long idCatEnt, long idCatItem, String desconto) {
        this.taxasDescPK = new TaxasDescPK(idCatEnt, idCatItem, desconto);
    }

    public TaxasDescPK getTaxasDescPK() {
        return taxasDescPK;
    }

    public void setTaxasDescPK(TaxasDescPK taxasDescPK) {
        this.taxasDescPK = taxasDescPK;
    }

    public Integer getTaxaDesc() {
        return taxaDesc;
    }

    public void setTaxaDesc(Integer taxaDesc) {
        this.taxaDesc = taxaDesc;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public CatEnt getCatEnt() {
        return catEnt;
    }

    public void setCatEnt(CatEnt catEnt) {
        this.catEnt = catEnt;
    }

    public CatItens getCatItens() {
        return catItens;
    }

    public void setCatItens(CatItens catItens) {
        this.catItens = catItens;
    }

    public Descontos getDescontos() {
        return descontos;
    }

    public void setDescontos(Descontos descontos) {
        this.descontos = descontos;
    }

    public Utilizadores getUserAlteracao() {
        return userAlteracao;
    }

    public void setUserAlteracao(Utilizadores userAlteracao) {
        this.userAlteracao = userAlteracao;
    }

    public Utilizadores getUserCriacao() {
        return userCriacao;
    }

    public void setUserCriacao(Utilizadores userCriacao) {
        this.userCriacao = userCriacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxasDescPK != null ? taxasDescPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxasDesc)) {
            return false;
        }
        TaxasDesc other = (TaxasDesc) object;
        if ((this.taxasDescPK == null && other.taxasDescPK != null) || (this.taxasDescPK != null && !this.taxasDescPK.equals(other.taxasDescPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TaxasDesc[taxasDescPK=" + taxasDescPK + "]";
    }

}
