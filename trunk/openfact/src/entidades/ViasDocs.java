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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author User
 */
@Entity
@Table(name = "vias_docs", catalog = "ecofact_testes", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"descricao"})})
@NamedQueries({@NamedQuery(name = "ViasDocs.findAll", query = "SELECT v FROM ViasDocs v"), @NamedQuery(name = "ViasDocs.findByIdVia", query = "SELECT v FROM ViasDocs v WHERE v.idVia = :idVia"), @NamedQuery(name = "ViasDocs.findByDescricao", query = "SELECT v FROM ViasDocs v WHERE v.descricao = :descricao")})
public class ViasDocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_via", nullable = false)
    private Integer idVia;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;

    public ViasDocs() {
    }

    public ViasDocs(Integer idVia) {
        this.idVia = idVia;
    }

    public ViasDocs(Integer idVia, String descricao) {
        this.idVia = idVia;
        this.descricao = descricao;
    }

    public Integer getIdVia() {
        return idVia;
    }

    public void setIdVia(Integer idVia) {
        this.idVia = idVia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVia != null ? idVia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViasDocs)) {
            return false;
        }
        ViasDocs other = (ViasDocs) object;
        if ((this.idVia == null && other.idVia != null) || (this.idVia != null && !this.idVia.equals(other.idVia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ViasDocs[idVia=" + idVia + "]";
    }

}
