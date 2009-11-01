/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "PESSOAS")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByDatanasc", query = "SELECT p FROM Pessoa p WHERE p.datanasc = :datanasc"),
    @NamedQuery(name = "Pessoa.findByMorada", query = "SELECT p FROM Pessoa p WHERE p.morada = :morada"),
    @NamedQuery(name = "Pessoa.findByLocalidade", query = "SELECT p FROM Pessoa p WHERE p.localidade = :localidade"),
    @NamedQuery(name = "Pessoa.findByTelfcasa", query = "SELECT p FROM Pessoa p WHERE p.telfcasa = :telfcasa"),
    @NamedQuery(name = "Pessoa.findByTelfempresa", query = "SELECT p FROM Pessoa p WHERE p.telfempresa = :telfempresa"),
    @NamedQuery(name = "Pessoa.findByTelfmovel1", query = "SELECT p FROM Pessoa p WHERE p.telfmovel1 = :telfmovel1"),
    @NamedQuery(name = "Pessoa.findByTelfmovel2", query = "SELECT p FROM Pessoa p WHERE p.telfmovel2 = :telfmovel2"),
    @NamedQuery(name = "Pessoa.findByTelfmovel3", query = "SELECT p FROM Pessoa p WHERE p.telfmovel3 = :telfmovel3"),
    @NamedQuery(name = "Pessoa.findByActivo", query = "SELECT p FROM Pessoa p WHERE p.activo = :activo"),
    @NamedQuery(name = "Pessoa.findByDatacriacao", query = "SELECT p FROM Pessoa p WHERE p.datacriacao = :datacriacao")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DATANASC")
    @Temporal(TemporalType.DATE)
    private Date datanasc;
    @Column(name = "MORADA")
    private String morada;
    @Column(name = "LOCALIDADE")
    private String localidade;
    @Column(name = "TELFCASA")
    private String telfcasa;
    @Column(name = "TELFEMPRESA")
    private String telfempresa;
    @Column(name = "TELFMOVEL1")
    private String telfmovel1;
    @Column(name = "TELFMOVEL2")
    private String telfmovel2;
    @Column(name = "TELFMOVEL3")
    private String telfmovel3;
    @Column(name = "ACTIVO")
    private Character activo;
    @Column(name = "DATACRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacriacao;
    @ManyToMany
    @JoinTable(name = "Contactos",
    joinColumns = @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIA"))
    private Set<Categoria> categorias;

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getTelfcasa() {
        return telfcasa;
    }

    public void setTelfcasa(String telfcasa) {
        this.telfcasa = telfcasa;
    }

    public String getTelfempresa() {
        return telfempresa;
    }

    public void setTelfempresa(String telfempresa) {
        this.telfempresa = telfempresa;
    }

    public String getTelfmovel1() {
        return telfmovel1;
    }

    public void setTelfmovel1(String telfmovel1) {
        this.telfmovel1 = telfmovel1;
    }

    public String getTelfmovel2() {
        return telfmovel2;
    }

    public void setTelfmovel2(String telfmovel2) {
        this.telfmovel2 = telfmovel2;
    }

    public String getTelfmovel3() {
        return telfmovel3;
    }

    public void setTelfmovel3(String telfmovel3) {
        this.telfmovel3 = telfmovel3;
    }

    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "Nome: " + nome +
                "Morada: " + morada +
                "Localidade: " + localidade +
                "Telef.Casa: " + telfcasa +
                "Telef.Empresa: " + telfempresa +
                "Telef.Movel: " + telfmovel1;
    }
}
