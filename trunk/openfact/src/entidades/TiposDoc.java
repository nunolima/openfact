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
@Table(name = "tipos_doc", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "TiposDoc.findAll", query = "SELECT t FROM TiposDoc t"), @NamedQuery(name = "TiposDoc.findByTipoDoc", query = "SELECT t FROM TiposDoc t WHERE t.tipoDoc = :tipoDoc"), @NamedQuery(name = "TiposDoc.findByDescricao", query = "SELECT t FROM TiposDoc t WHERE t.descricao = :descricao"), @NamedQuery(name = "TiposDoc.findByMovStock", query = "SELECT t FROM TiposDoc t WHERE t.movStock = :movStock"), @NamedQuery(name = "TiposDoc.findByCliente", query = "SELECT t FROM TiposDoc t WHERE t.cliente = :cliente"), @NamedQuery(name = "TiposDoc.findByDocBase", query = "SELECT t FROM TiposDoc t WHERE t.docBase = :docBase"), @NamedQuery(name = "TiposDoc.findByDocInterno", query = "SELECT t FROM TiposDoc t WHERE t.docInterno = :docInterno"), @NamedQuery(name = "TiposDoc.findByNrInicialOmissao", query = "SELECT t FROM TiposDoc t WHERE t.nrInicialOmissao = :nrInicialOmissao"), @NamedQuery(name = "TiposDoc.findByDataCriacao", query = "SELECT t FROM TiposDoc t WHERE t.dataCriacao = :dataCriacao"), @NamedQuery(name = "TiposDoc.findByDataAlteracao", query = "SELECT t FROM TiposDoc t WHERE t.dataAlteracao = :dataAlteracao")})
public class TiposDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_doc", nullable = false, length = 4)
    private String tipoDoc;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @Column(name = "mov_stock")
    private Boolean movStock;
    @Column(name = "cliente")
    private Boolean cliente;
    @Column(name = "doc_base")
    private Boolean docBase;
    @Column(name = "doc_interno")
    private Boolean docInterno;
    @Basic(optional = false)
    @Column(name = "nr_inicial_omissao", nullable = false)
    private long nrInicialOmissao;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @JoinTable(name = "rel_doc_doc", joinColumns = {@JoinColumn(name = "id_tipo_docs_dest", referencedColumnName = "tipo_doc", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_tipo_docs_orig", referencedColumnName = "tipo_doc", nullable = false)})
    @ManyToMany
    private Collection<TiposDoc> tiposDocCollection;
    @ManyToMany(mappedBy = "tiposDocCollection")
    private Collection<TiposDoc> tiposDocCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDoc")
    private Collection<Docs> docsCollection;
    @JoinColumn(name = "tipo_mov_stock", referencedColumnName = "tipo_mov_stock")
    @ManyToOne
    private TiposMovStock tipoMovStock;
    @JoinColumn(name = "user_alteracao", referencedColumnName = "id_utilizador")
    @ManyToOne
    private Utilizadores userAlteracao;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposDoc")
    private Collection<NrDocs> nrDocsCollection;

    public TiposDoc() {
    }

    public TiposDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public TiposDoc(String tipoDoc, long nrInicialOmissao) {
        this.tipoDoc = tipoDoc;
        this.nrInicialOmissao = nrInicialOmissao;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getMovStock() {
        return movStock;
    }

    public void setMovStock(Boolean movStock) {
        this.movStock = movStock;
    }

    public Boolean getCliente() {
        return cliente;
    }

    public void setCliente(Boolean cliente) {
        this.cliente = cliente;
    }

    public Boolean getDocBase() {
        return docBase;
    }

    public void setDocBase(Boolean docBase) {
        this.docBase = docBase;
    }

    public Boolean getDocInterno() {
        return docInterno;
    }

    public void setDocInterno(Boolean docInterno) {
        this.docInterno = docInterno;
    }

    public long getNrInicialOmissao() {
        return nrInicialOmissao;
    }

    public void setNrInicialOmissao(long nrInicialOmissao) {
        this.nrInicialOmissao = nrInicialOmissao;
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

    public Collection<TiposDoc> getTiposDocCollection() {
        return tiposDocCollection;
    }

    public void setTiposDocCollection(Collection<TiposDoc> tiposDocCollection) {
        this.tiposDocCollection = tiposDocCollection;
    }

    public Collection<TiposDoc> getTiposDocCollection1() {
        return tiposDocCollection1;
    }

    public void setTiposDocCollection1(Collection<TiposDoc> tiposDocCollection1) {
        this.tiposDocCollection1 = tiposDocCollection1;
    }

    public Collection<Docs> getDocsCollection() {
        return docsCollection;
    }

    public void setDocsCollection(Collection<Docs> docsCollection) {
        this.docsCollection = docsCollection;
    }

    public TiposMovStock getTipoMovStock() {
        return tipoMovStock;
    }

    public void setTipoMovStock(TiposMovStock tipoMovStock) {
        this.tipoMovStock = tipoMovStock;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDoc != null ? tipoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposDoc)) {
            return false;
        }
        TiposDoc other = (TiposDoc) object;
        if ((this.tipoDoc == null && other.tipoDoc != null) || (this.tipoDoc != null && !this.tipoDoc.equals(other.tipoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TiposDoc[tipoDoc=" + tipoDoc + "]";
    }

}
