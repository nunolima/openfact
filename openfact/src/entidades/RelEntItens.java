/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "rel_ent_itens", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "RelEntItens.findAll", query = "SELECT r FROM RelEntItens r"), @NamedQuery(name = "RelEntItens.findByIdEnt", query = "SELECT r FROM RelEntItens r WHERE r.relEntItensPK.idEnt = :idEnt"), @NamedQuery(name = "RelEntItens.findByTipoEnt", query = "SELECT r FROM RelEntItens r WHERE r.relEntItensPK.tipoEnt = :tipoEnt"), @NamedQuery(name = "RelEntItens.findByIdItem", query = "SELECT r FROM RelEntItens r WHERE r.relEntItensPK.idItem = :idItem"), @NamedQuery(name = "RelEntItens.findByData", query = "SELECT r FROM RelEntItens r WHERE r.data = :data"), @NamedQuery(name = "RelEntItens.findByValor", query = "SELECT r FROM RelEntItens r WHERE r.valor = :valor"), @NamedQuery(name = "RelEntItens.findByAdquirido", query = "SELECT r FROM RelEntItens r WHERE r.adquirido = :adquirido"), @NamedQuery(name = "RelEntItens.findByDataCriacao", query = "SELECT r FROM RelEntItens r WHERE r.dataCriacao = :dataCriacao"), @NamedQuery(name = "RelEntItens.findByDataAlteracao", query = "SELECT r FROM RelEntItens r WHERE r.dataAlteracao = :dataAlteracao")})
public class RelEntItens implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelEntItensPK relEntItensPK;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "valor")
    private BigInteger valor;
    @Column(name = "adquirido")
    private Boolean adquirido;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @JoinColumn(name = "id_ent", referencedColumnName = "id_ent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidades entidades;
    @JoinColumn(name = "id_item", referencedColumnName = "id_item", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Itens itens;
    @JoinColumn(name = "tipo_ent", referencedColumnName = "tipo_ent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TiposEnt tiposEnt;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;

    public RelEntItens() {
    }

    public RelEntItens(RelEntItensPK relEntItensPK) {
        this.relEntItensPK = relEntItensPK;
    }

    public RelEntItens(long idEnt, String tipoEnt, long idItem) {
        this.relEntItensPK = new RelEntItensPK(idEnt, tipoEnt, idItem);
    }

    public RelEntItensPK getRelEntItensPK() {
        return relEntItensPK;
    }

    public void setRelEntItensPK(RelEntItensPK relEntItensPK) {
        this.relEntItensPK = relEntItensPK;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public Boolean getAdquirido() {
        return adquirido;
    }

    public void setAdquirido(Boolean adquirido) {
        this.adquirido = adquirido;
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

    public Entidades getEntidades() {
        return entidades;
    }

    public void setEntidades(Entidades entidades) {
        this.entidades = entidades;
    }

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }

    public TiposEnt getTiposEnt() {
        return tiposEnt;
    }

    public void setTiposEnt(TiposEnt tiposEnt) {
        this.tiposEnt = tiposEnt;
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
        hash += (relEntItensPK != null ? relEntItensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelEntItens)) {
            return false;
        }
        RelEntItens other = (RelEntItens) object;
        if ((this.relEntItensPK == null && other.relEntItensPK != null) || (this.relEntItensPK != null && !this.relEntItensPK.equals(other.relEntItensPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RelEntItens[relEntItensPK=" + relEntItensPK + "]";
    }

}
