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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author PedrodeSousa
 */
@Entity
@Table(name = "livros", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"}), @UniqueConstraint(columnNames = {"isbn"})})
@NamedQueries({@NamedQuery(name = "Livros.findAll", query = "SELECT l FROM Livros l"), @NamedQuery(name = "Livros.findById", query = "SELECT l FROM Livros l WHERE l.id = :id"), @NamedQuery(name = "Livros.findByTitulo", query = "SELECT l FROM Livros l WHERE l.titulo = :titulo"), @NamedQuery(name = "Livros.findByIsbn", query = "SELECT l FROM Livros l WHERE l.isbn = :isbn"), @NamedQuery(name = "Livros.findByAno", query = "SELECT l FROM Livros l WHERE l.ano = :ano"), @NamedQuery(name = "Livros.findByEdicaoNr", query = "SELECT l FROM Livros l WHERE l.edicaoNr = :edicaoNr"), @NamedQuery(name = "Livros.findByAquisicaoData", query = "SELECT l FROM Livros l WHERE l.aquisicaoData = :aquisicaoData"), @NamedQuery(name = "Livros.findByAquisicaoValor", query = "SELECT l FROM Livros l WHERE l.aquisicaoValor = :aquisicaoValor")})
public class Livros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Basic(optional = false)
    @Column(name = "isbn", nullable = false, length = 17)
    private String isbn;
    @Column(name = "ano")
    private BigInteger ano;
    @Column(name = "edicao_nr")
    private BigInteger edicaoNr;
    @Basic(optional = false)
    @Column(name = "aquisicao_data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date aquisicaoData;
    @Basic(optional = false)
    @Column(name = "aquisicao_valor", nullable = false)
    private BigInteger aquisicaoValor;
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Areas areaId;
    @JoinColumn(name = "editora_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Editoras editoraId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livroId")
    private Collection<AutoresLivros> autoresLivrosCollection;

    public Livros() {
    }

    public Livros(Long id) {
        this.id = id;
    }

    public Livros(Long id, String titulo, String isbn, Date aquisicaoData, BigInteger aquisicaoValor) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.aquisicaoData = aquisicaoData;
        this.aquisicaoValor = aquisicaoValor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigInteger getAno() {
        return ano;
    }

    public void setAno(BigInteger ano) {
        this.ano = ano;
    }

    public BigInteger getEdicaoNr() {
        return edicaoNr;
    }

    public void setEdicaoNr(BigInteger edicaoNr) {
        this.edicaoNr = edicaoNr;
    }

    public Date getAquisicaoData() {
        return aquisicaoData;
    }

    public void setAquisicaoData(Date aquisicaoData) {
        this.aquisicaoData = aquisicaoData;
    }

    public BigInteger getAquisicaoValor() {
        return aquisicaoValor;
    }

    public void setAquisicaoValor(BigInteger aquisicaoValor) {
        this.aquisicaoValor = aquisicaoValor;
    }

    public Areas getAreaId() {
        return areaId;
    }

    public void setAreaId(Areas areaId) {
        this.areaId = areaId;
    }

    public Editoras getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Editoras editoraId) {
        this.editoraId = editoraId;
    }

    public Collection<AutoresLivros> getAutoresLivrosCollection() {
        return autoresLivrosCollection;
    }

    public void setAutoresLivrosCollection(Collection<AutoresLivros> autoresLivrosCollection) {
        this.autoresLivrosCollection = autoresLivrosCollection;
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
        if (!(object instanceof Livros)) {
            return false;
        }
        Livros other = (Livros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Livros[id=" + id + "]";
    }

}
