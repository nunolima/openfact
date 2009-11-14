/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "anos_fiscais", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "AnosFiscais.findAll", query = "SELECT a FROM AnosFiscais a"), @NamedQuery(name = "AnosFiscais.findByAno", query = "SELECT a FROM AnosFiscais a WHERE a.ano = :ano"), @NamedQuery(name = "AnosFiscais.findByDataIni", query = "SELECT a FROM AnosFiscais a WHERE a.dataIni = :dataIni"), @NamedQuery(name = "AnosFiscais.findByDataFim", query = "SELECT a FROM AnosFiscais a WHERE a.dataFim = :dataFim"), @NamedQuery(name = "AnosFiscais.findByObs", query = "SELECT a FROM AnosFiscais a WHERE a.obs = :obs"), @NamedQuery(name = "AnosFiscais.findByDataCriacao", query = "SELECT a FROM AnosFiscais a WHERE a.dataCriacao = :dataCriacao"), @NamedQuery(name = "AnosFiscais.findByDataAlteracao", query = "SELECT a FROM AnosFiscais a WHERE a.dataAlteracao = :dataAlteracao")})
public class AnosFiscais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ano", nullable = false)
    private Integer ano;
    @Basic(optional = false)
    @Column(name = "data_ini", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataIni;
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Column(name = "obs", length = 2147483647)
    private String obs;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @OneToMany(mappedBy = "ano")
    private Collection<Docs> docsCollection;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anosFiscais")
    private Collection<NrDocs> nrDocsCollection;
    @OneToMany(mappedBy = "anoFiscal")
    private Collection<Parametros> parametrosCollection;

    public AnosFiscais() {
    }

    public AnosFiscais(Integer ano) {
        this.ano = ano;
    }

    public AnosFiscais(Integer ano, Date dataIni) {
        this.ano = ano;
        this.dataIni = dataIni;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public Collection<Docs> getDocsCollection() {
        return docsCollection;
    }

    public void setDocsCollection(Collection<Docs> docsCollection) {
        this.docsCollection = docsCollection;
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

    public Collection<NrDocs> getNrDocsCollection() {
        return nrDocsCollection;
    }

    public void setNrDocsCollection(Collection<NrDocs> nrDocsCollection) {
        this.nrDocsCollection = nrDocsCollection;
    }

    public Collection<Parametros> getParametrosCollection() {
        return parametrosCollection;
    }

    public void setParametrosCollection(Collection<Parametros> parametrosCollection) {
        this.parametrosCollection = parametrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ano != null ? ano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnosFiscais)) {
            return false;
        }
        AnosFiscais other = (AnosFiscais) object;
        if ((this.ano == null && other.ano != null) || (this.ano != null && !this.ano.equals(other.ano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AnosFiscais[ano=" + ano + "]";
    }

}
