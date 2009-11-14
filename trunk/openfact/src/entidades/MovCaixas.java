/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "mov_caixas", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "MovCaixas.findAll", query = "SELECT m FROM MovCaixas m"), @NamedQuery(name = "MovCaixas.findByIdMovCx", query = "SELECT m FROM MovCaixas m WHERE m.idMovCx = :idMovCx"), @NamedQuery(name = "MovCaixas.findByAberta", query = "SELECT m FROM MovCaixas m WHERE m.aberta = :aberta"), @NamedQuery(name = "MovCaixas.findByValor", query = "SELECT m FROM MovCaixas m WHERE m.valor = :valor"), @NamedQuery(name = "MovCaixas.findByDataCriacao", query = "SELECT m FROM MovCaixas m WHERE m.dataCriacao = :dataCriacao"), @NamedQuery(name = "MovCaixas.findByDataAlteracao", query = "SELECT m FROM MovCaixas m WHERE m.dataAlteracao = :dataAlteracao")})
public class MovCaixas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_mov_cx", nullable = false)
    private Long idMovCx;
    @Column(name = "aberta")
    private Boolean aberta;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private BigInteger valor;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @JoinColumn(name = "id_cx", referencedColumnName = "id_cx", nullable = false)
    @ManyToOne(optional = false)
    private Caixas idCx;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;

    public MovCaixas() {
    }

    public MovCaixas(Long idMovCx) {
        this.idMovCx = idMovCx;
    }

    public MovCaixas(Long idMovCx, BigInteger valor) {
        this.idMovCx = idMovCx;
        this.valor = valor;
    }

    public Long getIdMovCx() {
        return idMovCx;
    }

    public void setIdMovCx(Long idMovCx) {
        this.idMovCx = idMovCx;
    }

    public Boolean getAberta() {
        return aberta;
    }

    public void setAberta(Boolean aberta) {
        this.aberta = aberta;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
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

    public Caixas getIdCx() {
        return idCx;
    }

    public void setIdCx(Caixas idCx) {
        this.idCx = idCx;
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
        hash += (idMovCx != null ? idMovCx.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovCaixas)) {
            return false;
        }
        MovCaixas other = (MovCaixas) object;
        if ((this.idMovCx == null && other.idMovCx != null) || (this.idMovCx != null && !this.idMovCx.equals(other.idMovCx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MovCaixas[idMovCx=" + idMovCx + "]";
    }

}
