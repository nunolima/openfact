/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "ivas", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "Ivas.findAll", query = "SELECT i FROM Ivas i"), @NamedQuery(name = "Ivas.findByZonaIva", query = "SELECT i FROM Ivas i WHERE i.ivasPK.zonaIva = :zonaIva"), @NamedQuery(name = "Ivas.findByIdTaxa", query = "SELECT i FROM Ivas i WHERE i.ivasPK.idTaxa = :idTaxa"), @NamedQuery(name = "Ivas.findByDescricao", query = "SELECT i FROM Ivas i WHERE i.descricao = :descricao"), @NamedQuery(name = "Ivas.findByTaxaIva", query = "SELECT i FROM Ivas i WHERE i.taxaIva = :taxaIva")})
public class Ivas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IvasPK ivasPK;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @Column(name = "taxa_iva")
    private Integer taxaIva;
    @OneToMany(mappedBy = "ivas")
    private Collection<DocLinhas> docLinhasCollection;
    @OneToMany(mappedBy = "ivas")
    private Collection<Parametros> parametrosCollection;

    public Ivas() {
    }

    public Ivas(IvasPK ivasPK) {
        this.ivasPK = ivasPK;
    }

    public Ivas(String zonaIva, int idTaxa) {
        this.ivasPK = new IvasPK(zonaIva, idTaxa);
    }

    public IvasPK getIvasPK() {
        return ivasPK;
    }

    public void setIvasPK(IvasPK ivasPK) {
        this.ivasPK = ivasPK;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTaxaIva() {
        return taxaIva;
    }

    public void setTaxaIva(Integer taxaIva) {
        this.taxaIva = taxaIva;
    }

    public Collection<DocLinhas> getDocLinhasCollection() {
        return docLinhasCollection;
    }

    public void setDocLinhasCollection(Collection<DocLinhas> docLinhasCollection) {
        this.docLinhasCollection = docLinhasCollection;
    }

    public Collection<Parametros> getParametrosCollection() {
        return parametrosCollection;
    }

    public void setParametrosCollection(Collection<Parametros> parametrosCollection) {
        this.parametrosCollection = parametrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ivasPK != null ? ivasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ivas)) {
            return false;
        }
        Ivas other = (Ivas) object;
        if ((this.ivasPK == null && other.ivasPK != null) || (this.ivasPK != null && !this.ivasPK.equals(other.ivasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ivas[ivasPK=" + ivasPK + "]";
    }

}
