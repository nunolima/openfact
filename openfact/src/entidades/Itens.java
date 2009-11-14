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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "itens", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Itens.findAll", query = "SELECT i FROM Itens i"), @NamedQuery(name = "Itens.findByIdItem", query = "SELECT i FROM Itens i WHERE i.idItem = :idItem"), @NamedQuery(name = "Itens.findByDescricao", query = "SELECT i FROM Itens i WHERE i.descricao = :descricao"), @NamedQuery(name = "Itens.findByReferencia", query = "SELECT i FROM Itens i WHERE i.referencia = :referencia"), @NamedQuery(name = "Itens.findByPreco", query = "SELECT i FROM Itens i WHERE i.preco = :preco"), @NamedQuery(name = "Itens.findByPrecoMedioCusto", query = "SELECT i FROM Itens i WHERE i.precoMedioCusto = :precoMedioCusto"), @NamedQuery(name = "Itens.findByPrecoMedioVenda", query = "SELECT i FROM Itens i WHERE i.precoMedioVenda = :precoMedioVenda"), @NamedQuery(name = "Itens.findByDuracao", query = "SELECT i FROM Itens i WHERE i.duracao = :duracao"), @NamedQuery(name = "Itens.findByDescontoActivo", query = "SELECT i FROM Itens i WHERE i.descontoActivo = :descontoActivo"), @NamedQuery(name = "Itens.findByDescontoTaxa", query = "SELECT i FROM Itens i WHERE i.descontoTaxa = :descontoTaxa"), @NamedQuery(name = "Itens.findByPontosActivo", query = "SELECT i FROM Itens i WHERE i.pontosActivo = :pontosActivo"), @NamedQuery(name = "Itens.findByPontosPreco", query = "SELECT i FROM Itens i WHERE i.pontosPreco = :pontosPreco"), @NamedQuery(name = "Itens.findByPontosPremio", query = "SELECT i FROM Itens i WHERE i.pontosPremio = :pontosPremio"), @NamedQuery(name = "Itens.findByStockActual", query = "SELECT i FROM Itens i WHERE i.stockActual = :stockActual"), @NamedQuery(name = "Itens.findByStockEncomendar", query = "SELECT i FROM Itens i WHERE i.stockEncomendar = :stockEncomendar"), @NamedQuery(name = "Itens.findByStockMin", query = "SELECT i FROM Itens i WHERE i.stockMin = :stockMin"), @NamedQuery(name = "Itens.findByTaxaIva", query = "SELECT i FROM Itens i WHERE i.taxaIva = :taxaIva"), @NamedQuery(name = "Itens.findByObs", query = "SELECT i FROM Itens i WHERE i.obs = :obs"), @NamedQuery(name = "Itens.findByBloqueado", query = "SELECT i FROM Itens i WHERE i.bloqueado = :bloqueado"), @NamedQuery(name = "Itens.findByDataCriacao", query = "SELECT i FROM Itens i WHERE i.dataCriacao = :dataCriacao"), @NamedQuery(name = "Itens.findByDataAlteracao", query = "SELECT i FROM Itens i WHERE i.dataAlteracao = :dataAlteracao"), @NamedQuery(name = "Itens.findByStatus", query = "SELECT i FROM Itens i WHERE i.status = :status")})
public class Itens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_item", nullable = false)
    private Long idItem;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @Column(name = "referencia", length = 2147483647)
    private String referencia;
    @Column(name = "preco")
    private BigInteger preco;
    @Column(name = "preco_medio_custo")
    private BigInteger precoMedioCusto;
    @Column(name = "preco_medio_venda")
    private BigInteger precoMedioVenda;
    @Column(name = "duracao")
    private Integer duracao;
    @Column(name = "desconto_activo")
    private Boolean descontoActivo;
    @Column(name = "desconto_taxa")
    private Integer descontoTaxa;
    @Column(name = "pontos_activo")
    private Boolean pontosActivo;
    @Column(name = "pontos_preco")
    private Integer pontosPreco;
    @Column(name = "pontos_premio")
    private Integer pontosPremio;
    @Column(name = "stock_actual")
    private BigInteger stockActual;
    @Column(name = "stock_encomendar")
    private BigInteger stockEncomendar;
    @Column(name = "stock_min")
    private BigInteger stockMin;
    @Basic(optional = false)
    @Column(name = "taxa_iva", nullable = false)
    private int taxaIva;
    @Column(name = "obs", length = 2147483647)
    private String obs;
    @Column(name = "bloqueado")
    private Boolean bloqueado;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Column(name = "status")
    private Character status;
    @JoinTable(name = "taxas_adic_itens", joinColumns = {@JoinColumn(name = "id_item", referencedColumnName = "id_item", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_taxa_adic", referencedColumnName = "id_taxa_adic", nullable = false)})
    @ManyToMany
    private Collection<TaxasAdic> taxasAdicCollection;
    @JoinTable(name = "itens_substituicao", joinColumns = {@JoinColumn(name = "id_item", referencedColumnName = "id_item", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_item_subst", referencedColumnName = "id_item", nullable = false)})
    @ManyToMany
    private Collection<Itens> itensCollection;
    @ManyToMany(mappedBy = "itensCollection")
    private Collection<Itens> itensCollection1;
    @OneToMany(mappedBy = "idItem")
    private Collection<MovStock> movStockCollection;
    @OneToMany(mappedBy = "idItem")
    private Collection<DocLinhas> docLinhasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itens")
    private Collection<RelEntItens> relEntItensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itens")
    private Collection<ItensAnexos> itensAnexosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itens1")
    private Collection<ItensAnexos> itensAnexosCollection1;
    @JoinColumn(name = "id_cat_item", referencedColumnName = "id_cat_item", nullable = false)
    @ManyToOne(optional = false)
    private CatItens idCatItem;
    @JoinColumn(name = "tipo_item", referencedColumnName = "tipo_item", nullable = false)
    @ManyToOne(optional = false)
    private TiposItens tipoItem;
    @JoinColumn(name = "unid_medida", referencedColumnName = "unid_medida")
    @ManyToOne
    private UnidMedida unidMedida;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;

    public Itens() {
    }

    public Itens(Long idItem) {
        this.idItem = idItem;
    }

    public Itens(Long idItem, String descricao, int taxaIva) {
        this.idItem = idItem;
        this.descricao = descricao;
        this.taxaIva = taxaIva;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigInteger getPreco() {
        return preco;
    }

    public void setPreco(BigInteger preco) {
        this.preco = preco;
    }

    public BigInteger getPrecoMedioCusto() {
        return precoMedioCusto;
    }

    public void setPrecoMedioCusto(BigInteger precoMedioCusto) {
        this.precoMedioCusto = precoMedioCusto;
    }

    public BigInteger getPrecoMedioVenda() {
        return precoMedioVenda;
    }

    public void setPrecoMedioVenda(BigInteger precoMedioVenda) {
        this.precoMedioVenda = precoMedioVenda;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Boolean getDescontoActivo() {
        return descontoActivo;
    }

    public void setDescontoActivo(Boolean descontoActivo) {
        this.descontoActivo = descontoActivo;
    }

    public Integer getDescontoTaxa() {
        return descontoTaxa;
    }

    public void setDescontoTaxa(Integer descontoTaxa) {
        this.descontoTaxa = descontoTaxa;
    }

    public Boolean getPontosActivo() {
        return pontosActivo;
    }

    public void setPontosActivo(Boolean pontosActivo) {
        this.pontosActivo = pontosActivo;
    }

    public Integer getPontosPreco() {
        return pontosPreco;
    }

    public void setPontosPreco(Integer pontosPreco) {
        this.pontosPreco = pontosPreco;
    }

    public Integer getPontosPremio() {
        return pontosPremio;
    }

    public void setPontosPremio(Integer pontosPremio) {
        this.pontosPremio = pontosPremio;
    }

    public BigInteger getStockActual() {
        return stockActual;
    }

    public void setStockActual(BigInteger stockActual) {
        this.stockActual = stockActual;
    }

    public BigInteger getStockEncomendar() {
        return stockEncomendar;
    }

    public void setStockEncomendar(BigInteger stockEncomendar) {
        this.stockEncomendar = stockEncomendar;
    }

    public BigInteger getStockMin() {
        return stockMin;
    }

    public void setStockMin(BigInteger stockMin) {
        this.stockMin = stockMin;
    }

    public int getTaxaIva() {
        return taxaIva;
    }

    public void setTaxaIva(int taxaIva) {
        this.taxaIva = taxaIva;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
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

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Collection<TaxasAdic> getTaxasAdicCollection() {
        return taxasAdicCollection;
    }

    public void setTaxasAdicCollection(Collection<TaxasAdic> taxasAdicCollection) {
        this.taxasAdicCollection = taxasAdicCollection;
    }

    public Collection<Itens> getItensCollection() {
        return itensCollection;
    }

    public void setItensCollection(Collection<Itens> itensCollection) {
        this.itensCollection = itensCollection;
    }

    public Collection<Itens> getItensCollection1() {
        return itensCollection1;
    }

    public void setItensCollection1(Collection<Itens> itensCollection1) {
        this.itensCollection1 = itensCollection1;
    }

    public Collection<MovStock> getMovStockCollection() {
        return movStockCollection;
    }

    public void setMovStockCollection(Collection<MovStock> movStockCollection) {
        this.movStockCollection = movStockCollection;
    }

    public Collection<DocLinhas> getDocLinhasCollection() {
        return docLinhasCollection;
    }

    public void setDocLinhasCollection(Collection<DocLinhas> docLinhasCollection) {
        this.docLinhasCollection = docLinhasCollection;
    }

    public Collection<RelEntItens> getRelEntItensCollection() {
        return relEntItensCollection;
    }

    public void setRelEntItensCollection(Collection<RelEntItens> relEntItensCollection) {
        this.relEntItensCollection = relEntItensCollection;
    }

    public Collection<ItensAnexos> getItensAnexosCollection() {
        return itensAnexosCollection;
    }

    public void setItensAnexosCollection(Collection<ItensAnexos> itensAnexosCollection) {
        this.itensAnexosCollection = itensAnexosCollection;
    }

    public Collection<ItensAnexos> getItensAnexosCollection1() {
        return itensAnexosCollection1;
    }

    public void setItensAnexosCollection1(Collection<ItensAnexos> itensAnexosCollection1) {
        this.itensAnexosCollection1 = itensAnexosCollection1;
    }

    public CatItens getIdCatItem() {
        return idCatItem;
    }

    public void setIdCatItem(CatItens idCatItem) {
        this.idCatItem = idCatItem;
    }

    public TiposItens getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TiposItens tipoItem) {
        this.tipoItem = tipoItem;
    }

    public UnidMedida getUnidMedida() {
        return unidMedida;
    }

    public void setUnidMedida(UnidMedida unidMedida) {
        this.unidMedida = unidMedida;
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
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itens)) {
            return false;
        }
        Itens other = (Itens) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Itens[idItem=" + idItem + "]";
    }

}
