/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author User
 */
@Entity
@Table(name = "utilizadores", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Utilizadores.findAll", query = "SELECT u FROM Utilizadores u"), @NamedQuery(name = "Utilizadores.findByIdUtilizador", query = "SELECT u FROM Utilizadores u WHERE u.idUtilizador = :idUtilizador"), @NamedQuery(name = "Utilizadores.findByNome", query = "SELECT u FROM Utilizadores u WHERE u.nome = :nome"), @NamedQuery(name = "Utilizadores.findByObs", query = "SELECT u FROM Utilizadores u WHERE u.obs = :obs"), @NamedQuery(name = "Utilizadores.findByPasswd", query = "SELECT u FROM Utilizadores u WHERE u.passwd = :passwd")})
public class Utilizadores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_utilizador", nullable = false, length = 2147483647)
    private String idUtilizador;
    @Column(name = "nome", length = 2147483647)
    private String nome;
    @Column(name = "obs", length = 2147483647)
    private String obs;
    @Column(name = "passwd", length = 2147483647)
    private String passwd;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<ContasBanco> contasBancoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<ContasBanco> contasBancoCollection1;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<Docs> docsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<Docs> docsCollection1;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<TiposDoc> tiposDocCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<TiposDoc> tiposDocCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<MovStock> movStockCollection;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<Caixas> caixasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<Caixas> caixasCollection1;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<MovCaixas> movCaixasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<MovCaixas> movCaixasCollection1;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<AnosFiscais> anosFiscaisCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<AnosFiscais> anosFiscaisCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<Fotos> fotosCollection;
    @JoinColumn(name = "id_tipo_user", referencedColumnName = "id_tipo_user")
    @ManyToOne
    private TiposUser idTipoUser;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<Entidades> entidadesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<Entidades> entidadesCollection1;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<TaxasDesc> taxasDescCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<TaxasDesc> taxasDescCollection1;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<RelEntItens> relEntItensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<RelEntItens> relEntItensCollection1;
    @OneToMany(mappedBy = "userAlteracao")
    private Collection<Itens> itensCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCriacao")
    private Collection<Itens> itensCollection1;

    public Utilizadores() {
    }

    public Utilizadores(String idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(String idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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

    public Collection<Docs> getDocsCollection() {
        return docsCollection;
    }

    public void setDocsCollection(Collection<Docs> docsCollection) {
        this.docsCollection = docsCollection;
    }

    public Collection<Docs> getDocsCollection1() {
        return docsCollection1;
    }

    public void setDocsCollection1(Collection<Docs> docsCollection1) {
        this.docsCollection1 = docsCollection1;
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

    public Collection<MovStock> getMovStockCollection() {
        return movStockCollection;
    }

    public void setMovStockCollection(Collection<MovStock> movStockCollection) {
        this.movStockCollection = movStockCollection;
    }

    public Collection<Caixas> getCaixasCollection() {
        return caixasCollection;
    }

    public void setCaixasCollection(Collection<Caixas> caixasCollection) {
        this.caixasCollection = caixasCollection;
    }

    public Collection<Caixas> getCaixasCollection1() {
        return caixasCollection1;
    }

    public void setCaixasCollection1(Collection<Caixas> caixasCollection1) {
        this.caixasCollection1 = caixasCollection1;
    }

    public Collection<MovCaixas> getMovCaixasCollection() {
        return movCaixasCollection;
    }

    public void setMovCaixasCollection(Collection<MovCaixas> movCaixasCollection) {
        this.movCaixasCollection = movCaixasCollection;
    }

    public Collection<MovCaixas> getMovCaixasCollection1() {
        return movCaixasCollection1;
    }

    public void setMovCaixasCollection1(Collection<MovCaixas> movCaixasCollection1) {
        this.movCaixasCollection1 = movCaixasCollection1;
    }

    public Collection<AnosFiscais> getAnosFiscaisCollection() {
        return anosFiscaisCollection;
    }

    public void setAnosFiscaisCollection(Collection<AnosFiscais> anosFiscaisCollection) {
        this.anosFiscaisCollection = anosFiscaisCollection;
    }

    public Collection<AnosFiscais> getAnosFiscaisCollection1() {
        return anosFiscaisCollection1;
    }

    public void setAnosFiscaisCollection1(Collection<AnosFiscais> anosFiscaisCollection1) {
        this.anosFiscaisCollection1 = anosFiscaisCollection1;
    }

    public Collection<Fotos> getFotosCollection() {
        return fotosCollection;
    }

    public void setFotosCollection(Collection<Fotos> fotosCollection) {
        this.fotosCollection = fotosCollection;
    }

    public TiposUser getIdTipoUser() {
        return idTipoUser;
    }

    public void setIdTipoUser(TiposUser idTipoUser) {
        this.idTipoUser = idTipoUser;
    }

    public Collection<Entidades> getEntidadesCollection() {
        return entidadesCollection;
    }

    public void setEntidadesCollection(Collection<Entidades> entidadesCollection) {
        this.entidadesCollection = entidadesCollection;
    }

    public Collection<Entidades> getEntidadesCollection1() {
        return entidadesCollection1;
    }

    public void setEntidadesCollection1(Collection<Entidades> entidadesCollection1) {
        this.entidadesCollection1 = entidadesCollection1;
    }

    public Collection<TaxasDesc> getTaxasDescCollection() {
        return taxasDescCollection;
    }

    public void setTaxasDescCollection(Collection<TaxasDesc> taxasDescCollection) {
        this.taxasDescCollection = taxasDescCollection;
    }

    public Collection<TaxasDesc> getTaxasDescCollection1() {
        return taxasDescCollection1;
    }

    public void setTaxasDescCollection1(Collection<TaxasDesc> taxasDescCollection1) {
        this.taxasDescCollection1 = taxasDescCollection1;
    }

    public Collection<RelEntItens> getRelEntItensCollection() {
        return relEntItensCollection;
    }

    public void setRelEntItensCollection(Collection<RelEntItens> relEntItensCollection) {
        this.relEntItensCollection = relEntItensCollection;
    }

    public Collection<RelEntItens> getRelEntItensCollection1() {
        return relEntItensCollection1;
    }

    public void setRelEntItensCollection1(Collection<RelEntItens> relEntItensCollection1) {
        this.relEntItensCollection1 = relEntItensCollection1;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilizador != null ? idUtilizador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilizadores)) {
            return false;
        }
        Utilizadores other = (Utilizadores) object;
        if ((this.idUtilizador == null && other.idUtilizador != null) || (this.idUtilizador != null && !this.idUtilizador.equals(other.idUtilizador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Utilizadores[idUtilizador=" + idUtilizador + "]";
    }

}
