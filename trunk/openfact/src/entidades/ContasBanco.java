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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "contas_banco", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "ContasBanco.findAll", query = "SELECT c FROM ContasBanco c"), @NamedQuery(name = "ContasBanco.findByIdConta", query = "SELECT c FROM ContasBanco c WHERE c.idConta = :idConta"), @NamedQuery(name = "ContasBanco.findByNrConta", query = "SELECT c FROM ContasBanco c WHERE c.nrConta = :nrConta"), @NamedQuery(name = "ContasBanco.findByTitular", query = "SELECT c FROM ContasBanco c WHERE c.titular = :titular"), @NamedQuery(name = "ContasBanco.findByNib", query = "SELECT c FROM ContasBanco c WHERE c.nib = :nib"), @NamedQuery(name = "ContasBanco.findBySaldoActual", query = "SELECT c FROM ContasBanco c WHERE c.saldoActual = :saldoActual"), @NamedQuery(name = "ContasBanco.findBySaldoDisponivel", query = "SELECT c FROM ContasBanco c WHERE c.saldoDisponivel = :saldoDisponivel"), @NamedQuery(name = "ContasBanco.findByObs", query = "SELECT c FROM ContasBanco c WHERE c.obs = :obs"), @NamedQuery(name = "ContasBanco.findByDataCriacao", query = "SELECT c FROM ContasBanco c WHERE c.dataCriacao = :dataCriacao"), @NamedQuery(name = "ContasBanco.findByDataAlteracao", query = "SELECT c FROM ContasBanco c WHERE c.dataAlteracao = :dataAlteracao"), @NamedQuery(name = "ContasBanco.findByStatus", query = "SELECT c FROM ContasBanco c WHERE c.status = :status")})
public class ContasBanco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_conta", nullable = false)
    private Long idConta;
    @Column(name = "nr_conta", length = 2147483647)
    private String nrConta;
    @Column(name = "titular", length = 2147483647)
    private String titular;
    @Column(name = "nib", length = 2147483647)
    private String nib;
    @Column(name = "saldo_actual")
    private BigInteger saldoActual;
    @Column(name = "saldo_disponivel")
    private BigInteger saldoDisponivel;
    @Column(name = "obs", length = 2147483647)
    private String obs;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Column(name = "status")
    private Character status;
    @JoinTable(name = "rel_contas_banco_entidades", joinColumns = {@JoinColumn(name = "id_conta", referencedColumnName = "id_conta", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_ent", referencedColumnName = "id_ent", nullable = false)})
    @ManyToMany
    private Collection<Entidades> entidadesCollection;
    @JoinColumn(name = "id_ent", referencedColumnName = "id_ent")
    @ManyToOne
    private Entidades idEnt;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;

    public ContasBanco() {
    }

    public ContasBanco(Long idConta) {
        this.idConta = idConta;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getNrConta() {
        return nrConta;
    }

    public void setNrConta(String nrConta) {
        this.nrConta = nrConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public BigInteger getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigInteger saldoActual) {
        this.saldoActual = saldoActual;
    }

    public BigInteger getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(BigInteger saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
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

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Collection<Entidades> getEntidadesCollection() {
        return entidadesCollection;
    }

    public void setEntidadesCollection(Collection<Entidades> entidadesCollection) {
        this.entidadesCollection = entidadesCollection;
    }

    public Entidades getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(Entidades idEnt) {
        this.idEnt = idEnt;
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
        hash += (idConta != null ? idConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasBanco)) {
            return false;
        }
        ContasBanco other = (ContasBanco) object;
        if ((this.idConta == null && other.idConta != null) || (this.idConta != null && !this.idConta.equals(other.idConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ContasBanco[idConta=" + idConta + "]";
    }

}
