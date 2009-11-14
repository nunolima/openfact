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
@Table(name = "mov_stock", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "MovStock.findAll", query = "SELECT m FROM MovStock m"), @NamedQuery(name = "MovStock.findByIdMovStock", query = "SELECT m FROM MovStock m WHERE m.idMovStock = :idMovStock"), @NamedQuery(name = "MovStock.findByNrDocExterno", query = "SELECT m FROM MovStock m WHERE m.nrDocExterno = :nrDocExterno"), @NamedQuery(name = "MovStock.findByTipoDocExterno", query = "SELECT m FROM MovStock m WHERE m.tipoDocExterno = :tipoDocExterno"), @NamedQuery(name = "MovStock.findByDataDoc", query = "SELECT m FROM MovStock m WHERE m.dataDoc = :dataDoc"), @NamedQuery(name = "MovStock.findByQtd", query = "SELECT m FROM MovStock m WHERE m.qtd = :qtd"), @NamedQuery(name = "MovStock.findByPreco", query = "SELECT m FROM MovStock m WHERE m.preco = :preco"), @NamedQuery(name = "MovStock.findByDataCriacao", query = "SELECT m FROM MovStock m WHERE m.dataCriacao = :dataCriacao")})
public class MovStock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_mov_stock", nullable = false)
    private Long idMovStock;
    @Column(name = "nr_doc_externo", length = 2147483647)
    private String nrDocExterno;
    @Column(name = "tipo_doc_externo", length = 2147483647)
    private String tipoDocExterno;
    @Column(name = "data_doc")
    @Temporal(TemporalType.DATE)
    private Date dataDoc;
    @Basic(optional = false)
    @Column(name = "qtd", nullable = false)
    private int qtd;
    @Basic(optional = false)
    @Column(name = "preco", nullable = false)
    private BigInteger preco;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @JoinColumn(name = "id_doc", referencedColumnName = "id_doc")
    @ManyToOne
    private Docs idDoc;
    @JoinColumn(name = "id_ent", referencedColumnName = "id_ent")
    @ManyToOne
    private Entidades idEnt;
    @JoinColumn(name = "id_item", referencedColumnName = "id_item")
    @ManyToOne
    private Itens idItem;
    @JoinColumn(name = "tipo_mov_stock", referencedColumnName = "tipo_mov_stock")
    @ManyToOne
    private TiposMovStock tipoMovStock;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;

    public MovStock() {
    }

    public MovStock(Long idMovStock) {
        this.idMovStock = idMovStock;
    }

    public MovStock(Long idMovStock, int qtd, BigInteger preco) {
        this.idMovStock = idMovStock;
        this.qtd = qtd;
        this.preco = preco;
    }

    public Long getIdMovStock() {
        return idMovStock;
    }

    public void setIdMovStock(Long idMovStock) {
        this.idMovStock = idMovStock;
    }

    public String getNrDocExterno() {
        return nrDocExterno;
    }

    public void setNrDocExterno(String nrDocExterno) {
        this.nrDocExterno = nrDocExterno;
    }

    public String getTipoDocExterno() {
        return tipoDocExterno;
    }

    public void setTipoDocExterno(String tipoDocExterno) {
        this.tipoDocExterno = tipoDocExterno;
    }

    public Date getDataDoc() {
        return dataDoc;
    }

    public void setDataDoc(Date dataDoc) {
        this.dataDoc = dataDoc;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public BigInteger getPreco() {
        return preco;
    }

    public void setPreco(BigInteger preco) {
        this.preco = preco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Docs getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Docs idDoc) {
        this.idDoc = idDoc;
    }

    public Entidades getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(Entidades idEnt) {
        this.idEnt = idEnt;
    }

    public Itens getIdItem() {
        return idItem;
    }

    public void setIdItem(Itens idItem) {
        this.idItem = idItem;
    }

    public TiposMovStock getTipoMovStock() {
        return tipoMovStock;
    }

    public void setTipoMovStock(TiposMovStock tipoMovStock) {
        this.tipoMovStock = tipoMovStock;
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
        hash += (idMovStock != null ? idMovStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovStock)) {
            return false;
        }
        MovStock other = (MovStock) object;
        if ((this.idMovStock == null && other.idMovStock != null) || (this.idMovStock != null && !this.idMovStock.equals(other.idMovStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MovStock[idMovStock=" + idMovStock + "]";
    }

}
