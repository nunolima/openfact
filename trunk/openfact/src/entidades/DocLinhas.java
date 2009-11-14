/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "doc_linhas", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "DocLinhas.findAll", query = "SELECT d FROM DocLinhas d"), @NamedQuery(name = "DocLinhas.findByIdDoc", query = "SELECT d FROM DocLinhas d WHERE d.docLinhasPK.idDoc = :idDoc"), @NamedQuery(name = "DocLinhas.findByLinha", query = "SELECT d FROM DocLinhas d WHERE d.docLinhasPK.linha = :linha"), @NamedQuery(name = "DocLinhas.findByRefDocOrigem", query = "SELECT d FROM DocLinhas d WHERE d.refDocOrigem = :refDocOrigem"), @NamedQuery(name = "DocLinhas.findByRefItem", query = "SELECT d FROM DocLinhas d WHERE d.refItem = :refItem"), @NamedQuery(name = "DocLinhas.findByDescricaoItem", query = "SELECT d FROM DocLinhas d WHERE d.descricaoItem = :descricaoItem"), @NamedQuery(name = "DocLinhas.findByQtd", query = "SELECT d FROM DocLinhas d WHERE d.qtd = :qtd"), @NamedQuery(name = "DocLinhas.findByPrecoUnit", query = "SELECT d FROM DocLinhas d WHERE d.precoUnit = :precoUnit"), @NamedQuery(name = "DocLinhas.findByDataExp", query = "SELECT d FROM DocLinhas d WHERE d.dataExp = :dataExp"), @NamedQuery(name = "DocLinhas.findByDescricaoLinha", query = "SELECT d FROM DocLinhas d WHERE d.descricaoLinha = :descricaoLinha"), @NamedQuery(name = "DocLinhas.findByTaxaIva", query = "SELECT d FROM DocLinhas d WHERE d.taxaIva = :taxaIva"), @NamedQuery(name = "DocLinhas.findByValorIvaLinha", query = "SELECT d FROM DocLinhas d WHERE d.valorIvaLinha = :valorIvaLinha"), @NamedQuery(name = "DocLinhas.findByDescontoLinha", query = "SELECT d FROM DocLinhas d WHERE d.descontoLinha = :descontoLinha"), @NamedQuery(name = "DocLinhas.findByTotalLinhaDebito", query = "SELECT d FROM DocLinhas d WHERE d.totalLinhaDebito = :totalLinhaDebito"), @NamedQuery(name = "DocLinhas.findByTotalLinhaCredito", query = "SELECT d FROM DocLinhas d WHERE d.totalLinhaCredito = :totalLinhaCredito"), @NamedQuery(name = "DocLinhas.findByMoeda", query = "SELECT d FROM DocLinhas d WHERE d.moeda = :moeda"), @NamedQuery(name = "DocLinhas.findByCambio", query = "SELECT d FROM DocLinhas d WHERE d.cambio = :cambio"), @NamedQuery(name = "DocLinhas.findByMoedaEstrangeiraDebito", query = "SELECT d FROM DocLinhas d WHERE d.moedaEstrangeiraDebito = :moedaEstrangeiraDebito"), @NamedQuery(name = "DocLinhas.findByMoedaEstrangeiraCredito", query = "SELECT d FROM DocLinhas d WHERE d.moedaEstrangeiraCredito = :moedaEstrangeiraCredito")})
public class DocLinhas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocLinhasPK docLinhasPK;
    @Column(name = "ref_doc_origem", length = 2147483647)
    private String refDocOrigem;
    @Column(name = "ref_item", length = 2147483647)
    private String refItem;
    @Column(name = "descricao_item", length = 2147483647)
    private String descricaoItem;
    @Column(name = "qtd")
    private Integer qtd;
    @Column(name = "preco_unit")
    private BigInteger precoUnit;
    @Column(name = "data_exp")
    @Temporal(TemporalType.DATE)
    private Date dataExp;
    @Column(name = "descricao_linha", length = 2147483647)
    private String descricaoLinha;
    @Column(name = "taxa_iva")
    private Integer taxaIva;
    @Basic(optional = false)
    @Column(name = "valor_iva_linha", nullable = false)
    private BigInteger valorIvaLinha;
    @Column(name = "desconto_linha")
    private BigInteger descontoLinha;
    @Basic(optional = false)
    @Column(name = "total_linha_debito", nullable = false)
    private BigInteger totalLinhaDebito;
    @Basic(optional = false)
    @Column(name = "total_linha_credito", nullable = false)
    private BigInteger totalLinhaCredito;
    @Column(name = "moeda", length = 3)
    private String moeda;
    @Column(name = "cambio")
    private BigInteger cambio;
    @Basic(optional = false)
    @Column(name = "moeda_estrangeira_debito", nullable = false)
    private BigInteger moedaEstrangeiraDebito;
    @Basic(optional = false)
    @Column(name = "moeda_estrangeira_credito", nullable = false)
    private BigInteger moedaEstrangeiraCredito;
    @OneToMany(mappedBy = "docLinhas")
    private Collection<DocLinhas> docLinhasCollection;
    @JoinColumns({@JoinColumn(name = "id_doc_origem", referencedColumnName = "id_doc"), @JoinColumn(name = "linha_origem", referencedColumnName = "linha")})
    @ManyToOne
    private DocLinhas docLinhas;
    @JoinColumn(name = "id_doc", referencedColumnName = "id_doc", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Docs docs;
    @JoinColumn(name = "id_item", referencedColumnName = "id_item")
    @ManyToOne
    private Itens idItem;
    @JoinColumns({@JoinColumn(name = "zona_iva", referencedColumnName = "zona_iva"), @JoinColumn(name = "id_taxa", referencedColumnName = "id_taxa")})
    @ManyToOne
    private Ivas ivas;
    @JoinColumn(name = "unid_medida", referencedColumnName = "unid_medida")
    @ManyToOne
    private UnidMedida unidMedida;

    public DocLinhas() {
    }

    public DocLinhas(DocLinhasPK docLinhasPK) {
        this.docLinhasPK = docLinhasPK;
    }

    public DocLinhas(DocLinhasPK docLinhasPK, BigInteger valorIvaLinha, BigInteger totalLinhaDebito, BigInteger totalLinhaCredito, BigInteger moedaEstrangeiraDebito, BigInteger moedaEstrangeiraCredito) {
        this.docLinhasPK = docLinhasPK;
        this.valorIvaLinha = valorIvaLinha;
        this.totalLinhaDebito = totalLinhaDebito;
        this.totalLinhaCredito = totalLinhaCredito;
        this.moedaEstrangeiraDebito = moedaEstrangeiraDebito;
        this.moedaEstrangeiraCredito = moedaEstrangeiraCredito;
    }

    public DocLinhas(long idDoc, long linha) {
        this.docLinhasPK = new DocLinhasPK(idDoc, linha);
    }

    public DocLinhasPK getDocLinhasPK() {
        return docLinhasPK;
    }

    public void setDocLinhasPK(DocLinhasPK docLinhasPK) {
        this.docLinhasPK = docLinhasPK;
    }

    public String getRefDocOrigem() {
        return refDocOrigem;
    }

    public void setRefDocOrigem(String refDocOrigem) {
        this.refDocOrigem = refDocOrigem;
    }

    public String getRefItem() {
        return refItem;
    }

    public void setRefItem(String refItem) {
        this.refItem = refItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public BigInteger getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigInteger precoUnit) {
        this.precoUnit = precoUnit;
    }

    public Date getDataExp() {
        return dataExp;
    }

    public void setDataExp(Date dataExp) {
        this.dataExp = dataExp;
    }

    public String getDescricaoLinha() {
        return descricaoLinha;
    }

    public void setDescricaoLinha(String descricaoLinha) {
        this.descricaoLinha = descricaoLinha;
    }

    public Integer getTaxaIva() {
        return taxaIva;
    }

    public void setTaxaIva(Integer taxaIva) {
        this.taxaIva = taxaIva;
    }

    public BigInteger getValorIvaLinha() {
        return valorIvaLinha;
    }

    public void setValorIvaLinha(BigInteger valorIvaLinha) {
        this.valorIvaLinha = valorIvaLinha;
    }

    public BigInteger getDescontoLinha() {
        return descontoLinha;
    }

    public void setDescontoLinha(BigInteger descontoLinha) {
        this.descontoLinha = descontoLinha;
    }

    public BigInteger getTotalLinhaDebito() {
        return totalLinhaDebito;
    }

    public void setTotalLinhaDebito(BigInteger totalLinhaDebito) {
        this.totalLinhaDebito = totalLinhaDebito;
    }

    public BigInteger getTotalLinhaCredito() {
        return totalLinhaCredito;
    }

    public void setTotalLinhaCredito(BigInteger totalLinhaCredito) {
        this.totalLinhaCredito = totalLinhaCredito;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigInteger getCambio() {
        return cambio;
    }

    public void setCambio(BigInteger cambio) {
        this.cambio = cambio;
    }

    public BigInteger getMoedaEstrangeiraDebito() {
        return moedaEstrangeiraDebito;
    }

    public void setMoedaEstrangeiraDebito(BigInteger moedaEstrangeiraDebito) {
        this.moedaEstrangeiraDebito = moedaEstrangeiraDebito;
    }

    public BigInteger getMoedaEstrangeiraCredito() {
        return moedaEstrangeiraCredito;
    }

    public void setMoedaEstrangeiraCredito(BigInteger moedaEstrangeiraCredito) {
        this.moedaEstrangeiraCredito = moedaEstrangeiraCredito;
    }

    public Collection<DocLinhas> getDocLinhasCollection() {
        return docLinhasCollection;
    }

    public void setDocLinhasCollection(Collection<DocLinhas> docLinhasCollection) {
        this.docLinhasCollection = docLinhasCollection;
    }

    public DocLinhas getDocLinhas() {
        return docLinhas;
    }

    public void setDocLinhas(DocLinhas docLinhas) {
        this.docLinhas = docLinhas;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    public Itens getIdItem() {
        return idItem;
    }

    public void setIdItem(Itens idItem) {
        this.idItem = idItem;
    }

    public Ivas getIvas() {
        return ivas;
    }

    public void setIvas(Ivas ivas) {
        this.ivas = ivas;
    }

    public UnidMedida getUnidMedida() {
        return unidMedida;
    }

    public void setUnidMedida(UnidMedida unidMedida) {
        this.unidMedida = unidMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docLinhasPK != null ? docLinhasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocLinhas)) {
            return false;
        }
        DocLinhas other = (DocLinhas) object;
        if ((this.docLinhasPK == null && other.docLinhasPK != null) || (this.docLinhasPK != null && !this.docLinhasPK.equals(other.docLinhasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DocLinhas[docLinhasPK=" + docLinhasPK + "]";
    }

}
