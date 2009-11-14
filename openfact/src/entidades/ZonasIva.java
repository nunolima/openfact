/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
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
@Table(name = "zonas_iva", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "ZonasIva.findAll", query = "SELECT z FROM ZonasIva z"), @NamedQuery(name = "ZonasIva.findByZonaIva", query = "SELECT z FROM ZonasIva z WHERE z.zonaIva = :zonaIva"), @NamedQuery(name = "ZonasIva.findByDescricao", query = "SELECT z FROM ZonasIva z WHERE z.descricao = :descricao"), @NamedQuery(name = "ZonasIva.findByIdTaxaOmissao", query = "SELECT z FROM ZonasIva z WHERE z.idTaxaOmissao = :idTaxaOmissao")})
public class ZonasIva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "zona_iva", nullable = false, length = 6)
    private String zonaIva;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @Column(name = "id_taxa_omissao")
    private Integer idTaxaOmissao;

    public ZonasIva() {
    }

    public ZonasIva(String zonaIva) {
        this.zonaIva = zonaIva;
    }

    public String getZonaIva() {
        return zonaIva;
    }

    public void setZonaIva(String zonaIva) {
        this.zonaIva = zonaIva;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdTaxaOmissao() {
        return idTaxaOmissao;
    }

    public void setIdTaxaOmissao(Integer idTaxaOmissao) {
        this.idTaxaOmissao = idTaxaOmissao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zonaIva != null ? zonaIva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZonasIva)) {
            return false;
        }
        ZonasIva other = (ZonasIva) object;
        if ((this.zonaIva == null && other.zonaIva != null) || (this.zonaIva != null && !this.zonaIva.equals(other.zonaIva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ZonasIva[zonaIva=" + zonaIva + "]";
    }

}
