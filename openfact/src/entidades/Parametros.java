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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "parametros", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"), @NamedQuery(name = "Parametros.findByIdParam", query = "SELECT p FROM Parametros p WHERE p.idParam = :idParam"), @NamedQuery(name = "Parametros.findByDescricao", query = "SELECT p FROM Parametros p WHERE p.descricao = :descricao")})
public class Parametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_param", nullable = false)
    private Integer idParam;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 2147483647)
    private String descricao;
    @JoinColumn(name = "ano_fiscal", referencedColumnName = "ano")
    @ManyToOne
    private AnosFiscais anoFiscal;
    @JoinColumns({@JoinColumn(name = "zona_iva", referencedColumnName = "zona_iva"), @JoinColumn(name = "id_taxa", referencedColumnName = "id_taxa")})
    @ManyToOne
    private Ivas ivas;

    public Parametros() {
    }

    public Parametros(Integer idParam) {
        this.idParam = idParam;
    }

    public Parametros(Integer idParam, String descricao) {
        this.idParam = idParam;
        this.descricao = descricao;
    }

    public Integer getIdParam() {
        return idParam;
    }

    public void setIdParam(Integer idParam) {
        this.idParam = idParam;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AnosFiscais getAnoFiscal() {
        return anoFiscal;
    }

    public void setAnoFiscal(AnosFiscais anoFiscal) {
        this.anoFiscal = anoFiscal;
    }

    public Ivas getIvas() {
        return ivas;
    }

    public void setIvas(Ivas ivas) {
        this.ivas = ivas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParam != null ? idParam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.idParam == null && other.idParam != null) || (this.idParam != null && !this.idParam.equals(other.idParam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Parametros[idParam=" + idParam + "]";
    }

}
