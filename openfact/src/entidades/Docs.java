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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "docs", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Docs.findAll", query = "SELECT d FROM Docs d"), @NamedQuery(name = "Docs.findByIdDoc", query = "SELECT d FROM Docs d WHERE d.idDoc = :idDoc"), @NamedQuery(name = "Docs.findByDatahora", query = "SELECT d FROM Docs d WHERE d.datahora = :datahora"), @NamedQuery(name = "Docs.findByNrDoc", query = "SELECT d FROM Docs d WHERE d.nrDoc = :nrDoc"), @NamedQuery(name = "Docs.findByVossoDocRef", query = "SELECT d FROM Docs d WHERE d.vossoDocRef = :vossoDocRef"), @NamedQuery(name = "Docs.findByIdEnt", query = "SELECT d FROM Docs d WHERE d.idEnt = :idEnt"), @NamedQuery(name = "Docs.findByNif", query = "SELECT d FROM Docs d WHERE d.nif = :nif"), @NamedQuery(name = "Docs.findByNome", query = "SELECT d FROM Docs d WHERE d.nome = :nome"), @NamedQuery(name = "Docs.findByMoradaFact1", query = "SELECT d FROM Docs d WHERE d.moradaFact1 = :moradaFact1"), @NamedQuery(name = "Docs.findByMoradaFact2", query = "SELECT d FROM Docs d WHERE d.moradaFact2 = :moradaFact2"), @NamedQuery(name = "Docs.findByLocalidadeFact", query = "SELECT d FROM Docs d WHERE d.localidadeFact = :localidadeFact"), @NamedQuery(name = "Docs.findByCpostalFact", query = "SELECT d FROM Docs d WHERE d.cpostalFact = :cpostalFact"), @NamedQuery(name = "Docs.findByDistritoFact", query = "SELECT d FROM Docs d WHERE d.distritoFact = :distritoFact"), @NamedQuery(name = "Docs.findByPaisFact", query = "SELECT d FROM Docs d WHERE d.paisFact = :paisFact"), @NamedQuery(name = "Docs.findByMoradaCarga1", query = "SELECT d FROM Docs d WHERE d.moradaCarga1 = :moradaCarga1"), @NamedQuery(name = "Docs.findByMoradaCarga2", query = "SELECT d FROM Docs d WHERE d.moradaCarga2 = :moradaCarga2"), @NamedQuery(name = "Docs.findByLocalidadeCarga", query = "SELECT d FROM Docs d WHERE d.localidadeCarga = :localidadeCarga"), @NamedQuery(name = "Docs.findByCpostalCarga", query = "SELECT d FROM Docs d WHERE d.cpostalCarga = :cpostalCarga"), @NamedQuery(name = "Docs.findByDistritoCarga", query = "SELECT d FROM Docs d WHERE d.distritoCarga = :distritoCarga"), @NamedQuery(name = "Docs.findByPaisCarga", query = "SELECT d FROM Docs d WHERE d.paisCarga = :paisCarga"), @NamedQuery(name = "Docs.findByDataCarga", query = "SELECT d FROM Docs d WHERE d.dataCarga = :dataCarga"), @NamedQuery(name = "Docs.findByHoraCarga", query = "SELECT d FROM Docs d WHERE d.horaCarga = :horaCarga"), @NamedQuery(name = "Docs.findByMatriculaCarga", query = "SELECT d FROM Docs d WHERE d.matriculaCarga = :matriculaCarga"), @NamedQuery(name = "Docs.findByObsCarga", query = "SELECT d FROM Docs d WHERE d.obsCarga = :obsCarga"), @NamedQuery(name = "Docs.findByMoradaExp1", query = "SELECT d FROM Docs d WHERE d.moradaExp1 = :moradaExp1"), @NamedQuery(name = "Docs.findByMoradaExp2", query = "SELECT d FROM Docs d WHERE d.moradaExp2 = :moradaExp2"), @NamedQuery(name = "Docs.findByLocalidadeExp", query = "SELECT d FROM Docs d WHERE d.localidadeExp = :localidadeExp"), @NamedQuery(name = "Docs.findByCpostalExp", query = "SELECT d FROM Docs d WHERE d.cpostalExp = :cpostalExp"), @NamedQuery(name = "Docs.findByDistritoExp", query = "SELECT d FROM Docs d WHERE d.distritoExp = :distritoExp"), @NamedQuery(name = "Docs.findByPaisExp", query = "SELECT d FROM Docs d WHERE d.paisExp = :paisExp"), @NamedQuery(name = "Docs.findByDataDescarga", query = "SELECT d FROM Docs d WHERE d.dataDescarga = :dataDescarga"), @NamedQuery(name = "Docs.findByHoraDescarga", query = "SELECT d FROM Docs d WHERE d.horaDescarga = :horaDescarga"), @NamedQuery(name = "Docs.findByObsDescarga", query = "SELECT d FROM Docs d WHERE d.obsDescarga = :obsDescarga"), @NamedQuery(name = "Docs.findByResponsavelRecepcao", query = "SELECT d FROM Docs d WHERE d.responsavelRecepcao = :responsavelRecepcao"), @NamedQuery(name = "Docs.findByModoExp", query = "SELECT d FROM Docs d WHERE d.modoExp = :modoExp"), @NamedQuery(name = "Docs.findByTotalIva", query = "SELECT d FROM Docs d WHERE d.totalIva = :totalIva"), @NamedQuery(name = "Docs.findByTotalSemIva", query = "SELECT d FROM Docs d WHERE d.totalSemIva = :totalSemIva"), @NamedQuery(name = "Docs.findByTotalComIva", query = "SELECT d FROM Docs d WHERE d.totalComIva = :totalComIva"), @NamedQuery(name = "Docs.findByMoeda", query = "SELECT d FROM Docs d WHERE d.moeda = :moeda"), @NamedQuery(name = "Docs.findByTotalMoedaDebito", query = "SELECT d FROM Docs d WHERE d.totalMoedaDebito = :totalMoedaDebito"), @NamedQuery(name = "Docs.findByTotalMoedaCredito", query = "SELECT d FROM Docs d WHERE d.totalMoedaCredito = :totalMoedaCredito"), @NamedQuery(name = "Docs.findByValorDesconto", query = "SELECT d FROM Docs d WHERE d.valorDesconto = :valorDesconto"), @NamedQuery(name = "Docs.findByCondPagam", query = "SELECT d FROM Docs d WHERE d.condPagam = :condPagam"), @NamedQuery(name = "Docs.findByDataVenc", query = "SELECT d FROM Docs d WHERE d.dataVenc = :dataVenc"), @NamedQuery(name = "Docs.findByIdEntVendedor", query = "SELECT d FROM Docs d WHERE d.idEntVendedor = :idEntVendedor"), @NamedQuery(name = "Docs.findByNomeEntVendedor", query = "SELECT d FROM Docs d WHERE d.nomeEntVendedor = :nomeEntVendedor"), @NamedQuery(name = "Docs.findByFormaPagam", query = "SELECT d FROM Docs d WHERE d.formaPagam = :formaPagam"), @NamedQuery(name = "Docs.findBySiglaBanco", query = "SELECT d FROM Docs d WHERE d.siglaBanco = :siglaBanco"), @NamedQuery(name = "Docs.findByNrCheque", query = "SELECT d FROM Docs d WHERE d.nrCheque = :nrCheque"), @NamedQuery(name = "Docs.findByDataCheque", query = "SELECT d FROM Docs d WHERE d.dataCheque = :dataCheque"), @NamedQuery(name = "Docs.findByValorExtenso", query = "SELECT d FROM Docs d WHERE d.valorExtenso = :valorExtenso"), @NamedQuery(name = "Docs.findByObs", query = "SELECT d FROM Docs d WHERE d.obs = :obs"), @NamedQuery(name = "Docs.findByAnulado", query = "SELECT d FROM Docs d WHERE d.anulado = :anulado"), @NamedQuery(name = "Docs.findByAberto", query = "SELECT d FROM Docs d WHERE d.aberto = :aberto"), @NamedQuery(name = "Docs.findByDataAlteracao", query = "SELECT d FROM Docs d WHERE d.dataAlteracao = :dataAlteracao")})
public class Docs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_doc", nullable = false)
    private Long idDoc;
    @Column(name = "datahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;
    @Basic(optional = false)
    @Column(name = "nr_doc", nullable = false)
    private long nrDoc;
    @Column(name = "vosso_doc_ref", length = 2147483647)
    private String vossoDocRef;
    @Column(name = "id_ent")
    private BigInteger idEnt;
    @Column(name = "nif", length = 2147483647)
    private String nif;
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 2147483647)
    private String nome;
    @Column(name = "morada_fact_1", length = 2147483647)
    private String moradaFact1;
    @Column(name = "morada_fact_2", length = 2147483647)
    private String moradaFact2;
    @Column(name = "localidade_fact", length = 2147483647)
    private String localidadeFact;
    @Column(name = "cpostal_fact", length = 2147483647)
    private String cpostalFact;
    @Column(name = "distrito_fact", length = 2147483647)
    private String distritoFact;
    @Column(name = "pais_fact", length = 2147483647)
    private String paisFact;
    @Column(name = "morada_carga_1", length = 2147483647)
    private String moradaCarga1;
    @Column(name = "morada_carga_2", length = 2147483647)
    private String moradaCarga2;
    @Column(name = "localidade_carga", length = 2147483647)
    private String localidadeCarga;
    @Column(name = "cpostal_carga", length = 2147483647)
    private String cpostalCarga;
    @Column(name = "distrito_carga", length = 2147483647)
    private String distritoCarga;
    @Column(name = "pais_carga", length = 2147483647)
    private String paisCarga;
    @Column(name = "data_carga")
    @Temporal(TemporalType.DATE)
    private Date dataCarga;
    @Column(name = "hora_carga", length = 5)
    private String horaCarga;
    @Column(name = "matricula_carga", length = 2147483647)
    private String matriculaCarga;
    @Column(name = "obs_carga", length = 2147483647)
    private String obsCarga;
    @Column(name = "morada_exp_1", length = 2147483647)
    private String moradaExp1;
    @Column(name = "morada_exp_2", length = 2147483647)
    private String moradaExp2;
    @Column(name = "localidade_exp", length = 2147483647)
    private String localidadeExp;
    @Column(name = "cpostal_exp", length = 2147483647)
    private String cpostalExp;
    @Column(name = "distrito_exp", length = 2147483647)
    private String distritoExp;
    @Column(name = "pais_exp", length = 2147483647)
    private String paisExp;
    @Column(name = "data_descarga")
    @Temporal(TemporalType.DATE)
    private Date dataDescarga;
    @Column(name = "hora_descarga", length = 5)
    private String horaDescarga;
    @Column(name = "obs_descarga", length = 2147483647)
    private String obsDescarga;
    @Column(name = "responsavel_recepcao", length = 2147483647)
    private String responsavelRecepcao;
    @Column(name = "modo_exp", length = 2147483647)
    private String modoExp;
    @Column(name = "total_iva")
    private BigInteger totalIva;
    @Column(name = "total_sem_iva")
    private BigInteger totalSemIva;
    @Column(name = "total_com_iva")
    private BigInteger totalComIva;
    @Column(name = "moeda", length = 3)
    private String moeda;
    @Column(name = "total_moeda_debito")
    private BigInteger totalMoedaDebito;
    @Column(name = "total_moeda_credito")
    private BigInteger totalMoedaCredito;
    @Column(name = "valor_desconto")
    private BigInteger valorDesconto;
    @Column(name = "cond_pagam", length = 2147483647)
    private String condPagam;
    @Column(name = "data_venc")
    @Temporal(TemporalType.DATE)
    private Date dataVenc;
    @Column(name = "id_ent_vendedor")
    private BigInteger idEntVendedor;
    @Column(name = "nome_ent_vendedor", length = 2147483647)
    private String nomeEntVendedor;
    @Column(name = "forma_pagam", length = 2147483647)
    private String formaPagam;
    @Column(name = "sigla_banco", length = 2147483647)
    private String siglaBanco;
    @Column(name = "nr_cheque", length = 2147483647)
    private String nrCheque;
    @Column(name = "data_cheque")
    @Temporal(TemporalType.DATE)
    private Date dataCheque;
    @Column(name = "valor_extenso", length = 2147483647)
    private String valorExtenso;
    @Column(name = "obs", length = 2147483647)
    private String obs;
    @Column(name = "anulado")
    private Boolean anulado;
    @Column(name = "aberto")
    private Boolean aberto;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @JoinColumn(name = "ano", referencedColumnName = "ano")
    @ManyToOne
    private AnosFiscais ano;
    @JoinColumn(name = "serie", referencedColumnName = "serie", nullable = false)
    @ManyToOne(optional = false)
    private Series serie;
    @JoinColumn(name = "tipo_doc", referencedColumnName = "tipo_doc", nullable = false)
    @ManyToOne(optional = false)
    private TiposDoc tipoDoc;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;
    @OneToMany(mappedBy = "idDoc")
    private Collection<MovStock> movStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docs")
    private Collection<DocLinhas> docLinhasCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "docs")
    private HistoricoDocs historicoDocs;

    public Docs() {
    }

    public Docs(Long idDoc) {
        this.idDoc = idDoc;
    }

    public Docs(Long idDoc, long nrDoc, String nome) {
        this.idDoc = idDoc;
        this.nrDoc = nrDoc;
        this.nome = nome;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public long getNrDoc() {
        return nrDoc;
    }

    public void setNrDoc(long nrDoc) {
        this.nrDoc = nrDoc;
    }

    public String getVossoDocRef() {
        return vossoDocRef;
    }

    public void setVossoDocRef(String vossoDocRef) {
        this.vossoDocRef = vossoDocRef;
    }

    public BigInteger getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(BigInteger idEnt) {
        this.idEnt = idEnt;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMoradaFact1() {
        return moradaFact1;
    }

    public void setMoradaFact1(String moradaFact1) {
        this.moradaFact1 = moradaFact1;
    }

    public String getMoradaFact2() {
        return moradaFact2;
    }

    public void setMoradaFact2(String moradaFact2) {
        this.moradaFact2 = moradaFact2;
    }

    public String getLocalidadeFact() {
        return localidadeFact;
    }

    public void setLocalidadeFact(String localidadeFact) {
        this.localidadeFact = localidadeFact;
    }

    public String getCpostalFact() {
        return cpostalFact;
    }

    public void setCpostalFact(String cpostalFact) {
        this.cpostalFact = cpostalFact;
    }

    public String getDistritoFact() {
        return distritoFact;
    }

    public void setDistritoFact(String distritoFact) {
        this.distritoFact = distritoFact;
    }

    public String getPaisFact() {
        return paisFact;
    }

    public void setPaisFact(String paisFact) {
        this.paisFact = paisFact;
    }

    public String getMoradaCarga1() {
        return moradaCarga1;
    }

    public void setMoradaCarga1(String moradaCarga1) {
        this.moradaCarga1 = moradaCarga1;
    }

    public String getMoradaCarga2() {
        return moradaCarga2;
    }

    public void setMoradaCarga2(String moradaCarga2) {
        this.moradaCarga2 = moradaCarga2;
    }

    public String getLocalidadeCarga() {
        return localidadeCarga;
    }

    public void setLocalidadeCarga(String localidadeCarga) {
        this.localidadeCarga = localidadeCarga;
    }

    public String getCpostalCarga() {
        return cpostalCarga;
    }

    public void setCpostalCarga(String cpostalCarga) {
        this.cpostalCarga = cpostalCarga;
    }

    public String getDistritoCarga() {
        return distritoCarga;
    }

    public void setDistritoCarga(String distritoCarga) {
        this.distritoCarga = distritoCarga;
    }

    public String getPaisCarga() {
        return paisCarga;
    }

    public void setPaisCarga(String paisCarga) {
        this.paisCarga = paisCarga;
    }

    public Date getDataCarga() {
        return dataCarga;
    }

    public void setDataCarga(Date dataCarga) {
        this.dataCarga = dataCarga;
    }

    public String getHoraCarga() {
        return horaCarga;
    }

    public void setHoraCarga(String horaCarga) {
        this.horaCarga = horaCarga;
    }

    public String getMatriculaCarga() {
        return matriculaCarga;
    }

    public void setMatriculaCarga(String matriculaCarga) {
        this.matriculaCarga = matriculaCarga;
    }

    public String getObsCarga() {
        return obsCarga;
    }

    public void setObsCarga(String obsCarga) {
        this.obsCarga = obsCarga;
    }

    public String getMoradaExp1() {
        return moradaExp1;
    }

    public void setMoradaExp1(String moradaExp1) {
        this.moradaExp1 = moradaExp1;
    }

    public String getMoradaExp2() {
        return moradaExp2;
    }

    public void setMoradaExp2(String moradaExp2) {
        this.moradaExp2 = moradaExp2;
    }

    public String getLocalidadeExp() {
        return localidadeExp;
    }

    public void setLocalidadeExp(String localidadeExp) {
        this.localidadeExp = localidadeExp;
    }

    public String getCpostalExp() {
        return cpostalExp;
    }

    public void setCpostalExp(String cpostalExp) {
        this.cpostalExp = cpostalExp;
    }

    public String getDistritoExp() {
        return distritoExp;
    }

    public void setDistritoExp(String distritoExp) {
        this.distritoExp = distritoExp;
    }

    public String getPaisExp() {
        return paisExp;
    }

    public void setPaisExp(String paisExp) {
        this.paisExp = paisExp;
    }

    public Date getDataDescarga() {
        return dataDescarga;
    }

    public void setDataDescarga(Date dataDescarga) {
        this.dataDescarga = dataDescarga;
    }

    public String getHoraDescarga() {
        return horaDescarga;
    }

    public void setHoraDescarga(String horaDescarga) {
        this.horaDescarga = horaDescarga;
    }

    public String getObsDescarga() {
        return obsDescarga;
    }

    public void setObsDescarga(String obsDescarga) {
        this.obsDescarga = obsDescarga;
    }

    public String getResponsavelRecepcao() {
        return responsavelRecepcao;
    }

    public void setResponsavelRecepcao(String responsavelRecepcao) {
        this.responsavelRecepcao = responsavelRecepcao;
    }

    public String getModoExp() {
        return modoExp;
    }

    public void setModoExp(String modoExp) {
        this.modoExp = modoExp;
    }

    public BigInteger getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(BigInteger totalIva) {
        this.totalIva = totalIva;
    }

    public BigInteger getTotalSemIva() {
        return totalSemIva;
    }

    public void setTotalSemIva(BigInteger totalSemIva) {
        this.totalSemIva = totalSemIva;
    }

    public BigInteger getTotalComIva() {
        return totalComIva;
    }

    public void setTotalComIva(BigInteger totalComIva) {
        this.totalComIva = totalComIva;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigInteger getTotalMoedaDebito() {
        return totalMoedaDebito;
    }

    public void setTotalMoedaDebito(BigInteger totalMoedaDebito) {
        this.totalMoedaDebito = totalMoedaDebito;
    }

    public BigInteger getTotalMoedaCredito() {
        return totalMoedaCredito;
    }

    public void setTotalMoedaCredito(BigInteger totalMoedaCredito) {
        this.totalMoedaCredito = totalMoedaCredito;
    }

    public BigInteger getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigInteger valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getCondPagam() {
        return condPagam;
    }

    public void setCondPagam(String condPagam) {
        this.condPagam = condPagam;
    }

    public Date getDataVenc() {
        return dataVenc;
    }

    public void setDataVenc(Date dataVenc) {
        this.dataVenc = dataVenc;
    }

    public BigInteger getIdEntVendedor() {
        return idEntVendedor;
    }

    public void setIdEntVendedor(BigInteger idEntVendedor) {
        this.idEntVendedor = idEntVendedor;
    }

    public String getNomeEntVendedor() {
        return nomeEntVendedor;
    }

    public void setNomeEntVendedor(String nomeEntVendedor) {
        this.nomeEntVendedor = nomeEntVendedor;
    }

    public String getFormaPagam() {
        return formaPagam;
    }

    public void setFormaPagam(String formaPagam) {
        this.formaPagam = formaPagam;
    }

    public String getSiglaBanco() {
        return siglaBanco;
    }

    public void setSiglaBanco(String siglaBanco) {
        this.siglaBanco = siglaBanco;
    }

    public String getNrCheque() {
        return nrCheque;
    }

    public void setNrCheque(String nrCheque) {
        this.nrCheque = nrCheque;
    }

    public Date getDataCheque() {
        return dataCheque;
    }

    public void setDataCheque(Date dataCheque) {
        this.dataCheque = dataCheque;
    }

    public String getValorExtenso() {
        return valorExtenso;
    }

    public void setValorExtenso(String valorExtenso) {
        this.valorExtenso = valorExtenso;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public AnosFiscais getAno() {
        return ano;
    }

    public void setAno(AnosFiscais ano) {
        this.ano = ano;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }

    public TiposDoc getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TiposDoc tipoDoc) {
        this.tipoDoc = tipoDoc;
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

    public HistoricoDocs getHistoricoDocs() {
        return historicoDocs;
    }

    public void setHistoricoDocs(HistoricoDocs historicoDocs) {
        this.historicoDocs = historicoDocs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDoc != null ? idDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docs)) {
            return false;
        }
        Docs other = (Docs) object;
        if ((this.idDoc == null && other.idDoc != null) || (this.idDoc != null && !this.idDoc.equals(other.idDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Docs[idDoc=" + idDoc + "]";
    }

}
