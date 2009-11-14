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
@Table(name = "entidades", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Entidades.findAll", query = "SELECT e FROM Entidades e"), @NamedQuery(name = "Entidades.findByIdEnt", query = "SELECT e FROM Entidades e WHERE e.idEnt = :idEnt"), @NamedQuery(name = "Entidades.findByConservatoria", query = "SELECT e FROM Entidades e WHERE e.conservatoria = :conservatoria"), @NamedQuery(name = "Entidades.findByNrRegComercial", query = "SELECT e FROM Entidades e WHERE e.nrRegComercial = :nrRegComercial"), @NamedQuery(name = "Entidades.findBySigla", query = "SELECT e FROM Entidades e WHERE e.sigla = :sigla"), @NamedQuery(name = "Entidades.findByNif", query = "SELECT e FROM Entidades e WHERE e.nif = :nif"), @NamedQuery(name = "Entidades.findByNome", query = "SELECT e FROM Entidades e WHERE e.nome = :nome"), @NamedQuery(name = "Entidades.findByCodCartao", query = "SELECT e FROM Entidades e WHERE e.codCartao = :codCartao"), @NamedQuery(name = "Entidades.findByFotoEnt", query = "SELECT e FROM Entidades e WHERE e.fotoEnt = :fotoEnt"), @NamedQuery(name = "Entidades.findByDataNascimento", query = "SELECT e FROM Entidades e WHERE e.dataNascimento = :dataNascimento"), @NamedQuery(name = "Entidades.findByMoradaFact1", query = "SELECT e FROM Entidades e WHERE e.moradaFact1 = :moradaFact1"), @NamedQuery(name = "Entidades.findByMoradaFact2", query = "SELECT e FROM Entidades e WHERE e.moradaFact2 = :moradaFact2"), @NamedQuery(name = "Entidades.findByLocalidadeFact", query = "SELECT e FROM Entidades e WHERE e.localidadeFact = :localidadeFact"), @NamedQuery(name = "Entidades.findByCpostalFact", query = "SELECT e FROM Entidades e WHERE e.cpostalFact = :cpostalFact"), @NamedQuery(name = "Entidades.findByDistritoFact", query = "SELECT e FROM Entidades e WHERE e.distritoFact = :distritoFact"), @NamedQuery(name = "Entidades.findByPaisFact", query = "SELECT e FROM Entidades e WHERE e.paisFact = :paisFact"), @NamedQuery(name = "Entidades.findByMoradaExp1", query = "SELECT e FROM Entidades e WHERE e.moradaExp1 = :moradaExp1"), @NamedQuery(name = "Entidades.findByMoradaExp2", query = "SELECT e FROM Entidades e WHERE e.moradaExp2 = :moradaExp2"), @NamedQuery(name = "Entidades.findByLocalidadeExp", query = "SELECT e FROM Entidades e WHERE e.localidadeExp = :localidadeExp"), @NamedQuery(name = "Entidades.findByCpostalExp", query = "SELECT e FROM Entidades e WHERE e.cpostalExp = :cpostalExp"), @NamedQuery(name = "Entidades.findByDistritoExp", query = "SELECT e FROM Entidades e WHERE e.distritoExp = :distritoExp"), @NamedQuery(name = "Entidades.findByPaisExp", query = "SELECT e FROM Entidades e WHERE e.paisExp = :paisExp"), @NamedQuery(name = "Entidades.findByMoradaCorresp1", query = "SELECT e FROM Entidades e WHERE e.moradaCorresp1 = :moradaCorresp1"), @NamedQuery(name = "Entidades.findByMoradaCorresp2", query = "SELECT e FROM Entidades e WHERE e.moradaCorresp2 = :moradaCorresp2"), @NamedQuery(name = "Entidades.findByLocalidadeCorresp", query = "SELECT e FROM Entidades e WHERE e.localidadeCorresp = :localidadeCorresp"), @NamedQuery(name = "Entidades.findByCpostalCorresp", query = "SELECT e FROM Entidades e WHERE e.cpostalCorresp = :cpostalCorresp"), @NamedQuery(name = "Entidades.findByDistritoCorresp", query = "SELECT e FROM Entidades e WHERE e.distritoCorresp = :distritoCorresp"), @NamedQuery(name = "Entidades.findByPaisCorresp", query = "SELECT e FROM Entidades e WHERE e.paisCorresp = :paisCorresp"), @NamedQuery(name = "Entidades.findByIdContaOmissao", query = "SELECT e FROM Entidades e WHERE e.idContaOmissao = :idContaOmissao"), @NamedQuery(name = "Entidades.findByPlafond", query = "SELECT e FROM Entidades e WHERE e.plafond = :plafond"), @NamedQuery(name = "Entidades.findBySaldo", query = "SELECT e FROM Entidades e WHERE e.saldo = :saldo"), @NamedQuery(name = "Entidades.findByTotalFacturado", query = "SELECT e FROM Entidades e WHERE e.totalFacturado = :totalFacturado"), @NamedQuery(name = "Entidades.findByMoeda", query = "SELECT e FROM Entidades e WHERE e.moeda = :moeda"), @NamedQuery(name = "Entidades.findByNomeContacto", query = "SELECT e FROM Entidades e WHERE e.nomeContacto = :nomeContacto"), @NamedQuery(name = "Entidades.findByTelef", query = "SELECT e FROM Entidades e WHERE e.telef = :telef"), @NamedQuery(name = "Entidades.findByTelem", query = "SELECT e FROM Entidades e WHERE e.telem = :telem"), @NamedQuery(name = "Entidades.findByFax", query = "SELECT e FROM Entidades e WHERE e.fax = :fax"), @NamedQuery(name = "Entidades.findByEmail", query = "SELECT e FROM Entidades e WHERE e.email = :email"), @NamedQuery(name = "Entidades.findByWeb", query = "SELECT e FROM Entidades e WHERE e.web = :web"), @NamedQuery(name = "Entidades.findByObs", query = "SELECT e FROM Entidades e WHERE e.obs = :obs"), @NamedQuery(name = "Entidades.findByEnvioEmail", query = "SELECT e FROM Entidades e WHERE e.envioEmail = :envioEmail"), @NamedQuery(name = "Entidades.findByEnvioSms", query = "SELECT e FROM Entidades e WHERE e.envioSms = :envioSms"), @NamedQuery(name = "Entidades.findByBloqueado", query = "SELECT e FROM Entidades e WHERE e.bloqueado = :bloqueado"), @NamedQuery(name = "Entidades.findByContaSuspensa", query = "SELECT e FROM Entidades e WHERE e.contaSuspensa = :contaSuspensa"), @NamedQuery(name = "Entidades.findByLogoRelat", query = "SELECT e FROM Entidades e WHERE e.logoRelat = :logoRelat"), @NamedQuery(name = "Entidades.findByLogoProg", query = "SELECT e FROM Entidades e WHERE e.logoProg = :logoProg"), @NamedQuery(name = "Entidades.findByDataCriacao", query = "SELECT e FROM Entidades e WHERE e.dataCriacao = :dataCriacao"), @NamedQuery(name = "Entidades.findByDataAlteracao", query = "SELECT e FROM Entidades e WHERE e.dataAlteracao = :dataAlteracao"), @NamedQuery(name = "Entidades.findByStatus", query = "SELECT e FROM Entidades e WHERE e.status = :status"), @NamedQuery(name = "Entidades.findByNomeApresentar", query = "SELECT e FROM Entidades e WHERE e.nomeApresentar = :nomeApresentar")})
public class Entidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_ent", nullable = false)
    private Long idEnt;
    @Column(name = "conservatoria", length = 2147483647)
    private String conservatoria;
    @Column(name = "nr_reg_comercial", length = 2147483647)
    private String nrRegComercial;
    @Column(name = "sigla", length = 2147483647)
    private String sigla;
    @Column(name = "nif", length = 2147483647)
    private String nif;
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 2147483647)
    private String nome;
    @Column(name = "cod_cartao", length = 2147483647)
    private String codCartao;
    @Column(name = "foto_ent")
    private BigInteger fotoEnt;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
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
    @Column(name = "morada_corresp_1", length = 2147483647)
    private String moradaCorresp1;
    @Column(name = "morada_corresp_2", length = 2147483647)
    private String moradaCorresp2;
    @Column(name = "localidade_corresp", length = 2147483647)
    private String localidadeCorresp;
    @Column(name = "cpostal_corresp", length = 2147483647)
    private String cpostalCorresp;
    @Column(name = "distrito_corresp", length = 2147483647)
    private String distritoCorresp;
    @Column(name = "pais_corresp", length = 2147483647)
    private String paisCorresp;
    @Column(name = "id_conta_omissao")
    private BigInteger idContaOmissao;
    @Column(name = "plafond")
    private BigInteger plafond;
    @Column(name = "saldo")
    private BigInteger saldo;
    @Column(name = "total_facturado")
    private BigInteger totalFacturado;
    @Column(name = "moeda", length = 2147483647)
    private String moeda;
    @Column(name = "nome_contacto", length = 2147483647)
    private String nomeContacto;
    @Column(name = "telef", length = 2147483647)
    private String telef;
    @Column(name = "telem", length = 2147483647)
    private String telem;
    @Column(name = "fax", length = 2147483647)
    private String fax;
    @Column(name = "email", length = 2147483647)
    private String email;
    @Column(name = "web", length = 2147483647)
    private String web;
    @Column(name = "obs", length = 2147483647)
    private String obs;
    @Column(name = "envio_email")
    private Boolean envioEmail;
    @Column(name = "envio_sms")
    private Boolean envioSms;
    @Column(name = "bloqueado")
    private Boolean bloqueado;
    @Column(name = "conta_suspensa")
    private Boolean contaSuspensa;
    @Column(name = "logo_relat", length = 2147483647)
    private String logoRelat;
    @Column(name = "logo_prog", length = 2147483647)
    private String logoProg;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Column(name = "status")
    private Character status;
    @Basic(optional = false)
    @Column(name = "nome_apresentar", nullable = false, length = 2147483647)
    private String nomeApresentar;
    @JoinTable(name = "vendedores_zonas", joinColumns = {@JoinColumn(name = "id_ent_vendedor", referencedColumnName = "id_ent", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "zona", referencedColumnName = "zona", nullable = false)})
    @ManyToMany
    private Collection<Zonas> zonasCollection;
    @ManyToMany(mappedBy = "entidadesCollection")
    private Collection<ContasBanco> contasBancoCollection;
    @OneToMany(mappedBy = "idEnt")
    private Collection<ContasBanco> contasBancoCollection1;
    @OneToMany(mappedBy = "idEnt")
    private Collection<MovStock> movStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidades")
    private Collection<Fotos> fotosCollection;
    @JoinColumn(name = "id_cat_ent", referencedColumnName = "id_cat_ent")
    @ManyToOne
    private CatEnt idCatEnt;
    @JoinColumn(name = "cond_pagam", referencedColumnName = "cond_pagam")
    @ManyToOne
    private CondPagam condPagam;
    @OneToMany(mappedBy = "idEntVendedor")
    private Collection<Entidades> entidadesCollection;
    @JoinColumn(name = "id_ent_vendedor", referencedColumnName = "id_ent")
    @ManyToOne
    private Entidades idEntVendedor;
    @JoinColumn(name = "id_modo_exp", referencedColumnName = "id_modo_exp")
    @ManyToOne
    private ModosExp idModoExp;
    @JoinColumn(name = "tipo_ent", referencedColumnName = "tipo_ent")
    @ManyToOne
    private TiposEnt tipoEnt;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;
    @JoinColumn(name = "zona", referencedColumnName = "zona")
    @ManyToOne
    private Zonas zona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidades")
    private Collection<RelEntItens> relEntItensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidades")
    private Collection<RelEnt> relEntCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidades1")
    private Collection<RelEnt> relEntCollection1;

    public Entidades() {
    }

    public Entidades(Long idEnt) {
        this.idEnt = idEnt;
    }

    public Entidades(Long idEnt, String nome, String nomeApresentar) {
        this.idEnt = idEnt;
        this.nome = nome;
        this.nomeApresentar = nomeApresentar;
    }

    public Long getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(Long idEnt) {
        this.idEnt = idEnt;
    }

    public String getConservatoria() {
        return conservatoria;
    }

    public void setConservatoria(String conservatoria) {
        this.conservatoria = conservatoria;
    }

    public String getNrRegComercial() {
        return nrRegComercial;
    }

    public void setNrRegComercial(String nrRegComercial) {
        this.nrRegComercial = nrRegComercial;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    public String getCodCartao() {
        return codCartao;
    }

    public void setCodCartao(String codCartao) {
        this.codCartao = codCartao;
    }

    public BigInteger getFotoEnt() {
        return fotoEnt;
    }

    public void setFotoEnt(BigInteger fotoEnt) {
        this.fotoEnt = fotoEnt;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getMoradaCorresp1() {
        return moradaCorresp1;
    }

    public void setMoradaCorresp1(String moradaCorresp1) {
        this.moradaCorresp1 = moradaCorresp1;
    }

    public String getMoradaCorresp2() {
        return moradaCorresp2;
    }

    public void setMoradaCorresp2(String moradaCorresp2) {
        this.moradaCorresp2 = moradaCorresp2;
    }

    public String getLocalidadeCorresp() {
        return localidadeCorresp;
    }

    public void setLocalidadeCorresp(String localidadeCorresp) {
        this.localidadeCorresp = localidadeCorresp;
    }

    public String getCpostalCorresp() {
        return cpostalCorresp;
    }

    public void setCpostalCorresp(String cpostalCorresp) {
        this.cpostalCorresp = cpostalCorresp;
    }

    public String getDistritoCorresp() {
        return distritoCorresp;
    }

    public void setDistritoCorresp(String distritoCorresp) {
        this.distritoCorresp = distritoCorresp;
    }

    public String getPaisCorresp() {
        return paisCorresp;
    }

    public void setPaisCorresp(String paisCorresp) {
        this.paisCorresp = paisCorresp;
    }

    public BigInteger getIdContaOmissao() {
        return idContaOmissao;
    }

    public void setIdContaOmissao(BigInteger idContaOmissao) {
        this.idContaOmissao = idContaOmissao;
    }

    public BigInteger getPlafond() {
        return plafond;
    }

    public void setPlafond(BigInteger plafond) {
        this.plafond = plafond;
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public void setSaldo(BigInteger saldo) {
        this.saldo = saldo;
    }

    public BigInteger getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(BigInteger totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getNomeContacto() {
        return nomeContacto;
    }

    public void setNomeContacto(String nomeContacto) {
        this.nomeContacto = nomeContacto;
    }

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public String getTelem() {
        return telem;
    }

    public void setTelem(String telem) {
        this.telem = telem;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getEnvioEmail() {
        return envioEmail;
    }

    public void setEnvioEmail(Boolean envioEmail) {
        this.envioEmail = envioEmail;
    }

    public Boolean getEnvioSms() {
        return envioSms;
    }

    public void setEnvioSms(Boolean envioSms) {
        this.envioSms = envioSms;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Boolean getContaSuspensa() {
        return contaSuspensa;
    }

    public void setContaSuspensa(Boolean contaSuspensa) {
        this.contaSuspensa = contaSuspensa;
    }

    public String getLogoRelat() {
        return logoRelat;
    }

    public void setLogoRelat(String logoRelat) {
        this.logoRelat = logoRelat;
    }

    public String getLogoProg() {
        return logoProg;
    }

    public void setLogoProg(String logoProg) {
        this.logoProg = logoProg;
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

    public String getNomeApresentar() {
        return nomeApresentar;
    }

    public void setNomeApresentar(String nomeApresentar) {
        this.nomeApresentar = nomeApresentar;
    }

    public Collection<Zonas> getZonasCollection() {
        return zonasCollection;
    }

    public void setZonasCollection(Collection<Zonas> zonasCollection) {
        this.zonasCollection = zonasCollection;
    }

    public Collection<ContasBanco> getContasBancoCollection() {
        return contasBancoCollection;
    }

    public void setContasBancoCollection(Collection<ContasBanco> contasBancoCollection) {
        this.contasBancoCollection = contasBancoCollection;
    }

    public Collection<ContasBanco> getContasBancoCollection1() {
        return contasBancoCollection1;
    }

    public void setContasBancoCollection1(Collection<ContasBanco> contasBancoCollection1) {
        this.contasBancoCollection1 = contasBancoCollection1;
    }

    public Collection<MovStock> getMovStockCollection() {
        return movStockCollection;
    }

    public void setMovStockCollection(Collection<MovStock> movStockCollection) {
        this.movStockCollection = movStockCollection;
    }

    public Collection<Fotos> getFotosCollection() {
        return fotosCollection;
    }

    public void setFotosCollection(Collection<Fotos> fotosCollection) {
        this.fotosCollection = fotosCollection;
    }

    public CatEnt getIdCatEnt() {
        return idCatEnt;
    }

    public void setIdCatEnt(CatEnt idCatEnt) {
        this.idCatEnt = idCatEnt;
    }

    public CondPagam getCondPagam() {
        return condPagam;
    }

    public void setCondPagam(CondPagam condPagam) {
        this.condPagam = condPagam;
    }

    public Collection<Entidades> getEntidadesCollection() {
        return entidadesCollection;
    }

    public void setEntidadesCollection(Collection<Entidades> entidadesCollection) {
        this.entidadesCollection = entidadesCollection;
    }

    public Entidades getIdEntVendedor() {
        return idEntVendedor;
    }

    public void setIdEntVendedor(Entidades idEntVendedor) {
        this.idEntVendedor = idEntVendedor;
    }

    public ModosExp getIdModoExp() {
        return idModoExp;
    }

    public void setIdModoExp(ModosExp idModoExp) {
        this.idModoExp = idModoExp;
    }

    public TiposEnt getTipoEnt() {
        return tipoEnt;
    }

    public void setTipoEnt(TiposEnt tipoEnt) {
        this.tipoEnt = tipoEnt;
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

    public Zonas getZona() {
        return zona;
    }

    public void setZona(Zonas zona) {
        this.zona = zona;
    }

    public Collection<RelEntItens> getRelEntItensCollection() {
        return relEntItensCollection;
    }

    public void setRelEntItensCollection(Collection<RelEntItens> relEntItensCollection) {
        this.relEntItensCollection = relEntItensCollection;
    }

    public Collection<RelEnt> getRelEntCollection() {
        return relEntCollection;
    }

    public void setRelEntCollection(Collection<RelEnt> relEntCollection) {
        this.relEntCollection = relEntCollection;
    }

    public Collection<RelEnt> getRelEntCollection1() {
        return relEntCollection1;
    }

    public void setRelEntCollection1(Collection<RelEnt> relEntCollection1) {
        this.relEntCollection1 = relEntCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnt != null ? idEnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidades)) {
            return false;
        }
        Entidades other = (Entidades) object;
        if ((this.idEnt == null && other.idEnt != null) || (this.idEnt != null && !this.idEnt.equals(other.idEnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Entidades[idEnt=" + idEnt + "]";
    }

}
