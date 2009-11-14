/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "paises", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Paises.findAll", query = "SELECT p FROM Paises p"), @NamedQuery(name = "Paises.findByIdPais", query = "SELECT p FROM Paises p WHERE p.idPais = :idPais"), @NamedQuery(name = "Paises.findByDescricao", query = "SELECT p FROM Paises p WHERE p.descricao = :descricao"), @NamedQuery(name = "Paises.findByMoeda", query = "SELECT p FROM Paises p WHERE p.moeda = :moeda"), @NamedQuery(name = "Paises.findByCambio", query = "SELECT p FROM Paises p WHERE p.cambio = :cambio"), @NamedQuery(name = "Paises.findByBandeira", query = "SELECT p FROM Paises p WHERE p.bandeira = :bandeira")})
public class Paises implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pais", nullable = false)
    private Integer idPais;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @Column(name = "moeda", length = 3)
    private String moeda;
    @Column(name = "cambio")
    private BigInteger cambio;
    @Column(name = "bandeira")
    private BigInteger bandeira;

    public Paises() {
    }

    public Paises(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public BigInteger getCambio() {
        return cambio;
    }

    public void setCambio(BigInteger cambio) {
        this.cambio = cambio;
    }

    public BigInteger getBandeira() {
        return bandeira;
    }

    public void setBandeira(BigInteger bandeira) {
        this.bandeira = bandeira;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Paises[idPais=" + idPais + "]";
    }

}
