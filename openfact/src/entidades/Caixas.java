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
@Table(name = "caixas", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Caixas.findAll", query = "SELECT c FROM Caixas c"), @NamedQuery(name = "Caixas.findByIdCx", query = "SELECT c FROM Caixas c WHERE c.idCx = :idCx"), @NamedQuery(name = "Caixas.findByDescricao", query = "SELECT c FROM Caixas c WHERE c.descricao = :descricao"), @NamedQuery(name = "Caixas.findByDataCriacao", query = "SELECT c FROM Caixas c WHERE c.dataCriacao = :dataCriacao"), @NamedQuery(name = "Caixas.findByDataAlteracao", query = "SELECT c FROM Caixas c WHERE c.dataAlteracao = :dataAlteracao")})
public class Caixas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cx", nullable = false)
    private Long idCx;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCx")
    private Collection<MovCaixas> movCaixasCollection;

    public Caixas() {
    }

    public Caixas(Long idCx) {
        this.idCx = idCx;
    }

    public Caixas(Long idCx, String descricao) {
        this.idCx = idCx;
        this.descricao = descricao;
    }

    public Long getIdCx() {
        return idCx;
    }

    public void setIdCx(Long idCx) {
        this.idCx = idCx;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Collection<MovCaixas> getMovCaixasCollection() {
        return movCaixasCollection;
    }

    public void setMovCaixasCollection(Collection<MovCaixas> movCaixasCollection) {
        this.movCaixasCollection = movCaixasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCx != null ? idCx.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixas)) {
            return false;
        }
        Caixas other = (Caixas) object;
        if ((this.idCx == null && other.idCx != null) || (this.idCx != null && !this.idCx.equals(other.idCx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Caixas[idCx=" + idCx + "]";
    }

}
