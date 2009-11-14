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
@Table(name = "fotos", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Fotos.findAll", query = "SELECT f FROM Fotos f"), @NamedQuery(name = "Fotos.findByIdEnt", query = "SELECT f FROM Fotos f WHERE f.fotosPK.idEnt = :idEnt"), @NamedQuery(name = "Fotos.findByFotoId", query = "SELECT f FROM Fotos f WHERE f.fotosPK.fotoId = :fotoId"), @NamedQuery(name = "Fotos.findByTitulo", query = "SELECT f FROM Fotos f WHERE f.titulo = :titulo"), @NamedQuery(name = "Fotos.findByFoto", query = "SELECT f FROM Fotos f WHERE f.foto = :foto"), @NamedQuery(name = "Fotos.findByDataCriacao", query = "SELECT f FROM Fotos f WHERE f.dataCriacao = :dataCriacao")})
public class Fotos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FotosPK fotosPK;
    @Basic(optional = false)
    @Column(name = "titulo", nullable = false, length = 2147483647)
    private String titulo;
    @Column(name = "foto")
    private BigInteger foto;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @JoinColumn(name = "id_ent", referencedColumnName = "id_ent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidades entidades;
    @JoinColumn(name = "user_criacao", referencedColumnName = "id_utilizador", nullable = false)
    @ManyToOne(optional = false)
    private Utilizadores userCriacao;

    public Fotos() {
    }

    public Fotos(FotosPK fotosPK) {
        this.fotosPK = fotosPK;
    }

    public Fotos(FotosPK fotosPK, String titulo) {
        this.fotosPK = fotosPK;
        this.titulo = titulo;
    }

    public Fotos(long idEnt, long fotoId) {
        this.fotosPK = new FotosPK(idEnt, fotoId);
    }

    public FotosPK getFotosPK() {
        return fotosPK;
    }

    public void setFotosPK(FotosPK fotosPK) {
        this.fotosPK = fotosPK;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigInteger getFoto() {
        return foto;
    }

    public void setFoto(BigInteger foto) {
        this.foto = foto;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Entidades getEntidades() {
        return entidades;
    }

    public void setEntidades(Entidades entidades) {
        this.entidades = entidades;
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
        hash += (fotosPK != null ? fotosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotos)) {
            return false;
        }
        Fotos other = (Fotos) object;
        if ((this.fotosPK == null && other.fotosPK != null) || (this.fotosPK != null && !this.fotosPK.equals(other.fotosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Fotos[fotosPK=" + fotosPK + "]";
    }

}
