/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "itens_anexos", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "ItensAnexos.findAll", query = "SELECT i FROM ItensAnexos i"), @NamedQuery(name = "ItensAnexos.findByIdItem", query = "SELECT i FROM ItensAnexos i WHERE i.itensAnexosPK.idItem = :idItem"), @NamedQuery(name = "ItensAnexos.findByIdItemAnexo", query = "SELECT i FROM ItensAnexos i WHERE i.itensAnexosPK.idItemAnexo = :idItemAnexo"), @NamedQuery(name = "ItensAnexos.findByMotivo", query = "SELECT i FROM ItensAnexos i WHERE i.motivo = :motivo")})
public class ItensAnexos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItensAnexosPK itensAnexosPK;
    @Column(name = "motivo", length = 2147483647)
    private String motivo;
    @JoinColumn(name = "id_item_anexo", referencedColumnName = "id_item", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Itens itens;
    @JoinColumn(name = "id_item", referencedColumnName = "id_item", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Itens itens1;

    public ItensAnexos() {
    }

    public ItensAnexos(ItensAnexosPK itensAnexosPK) {
        this.itensAnexosPK = itensAnexosPK;
    }

    public ItensAnexos(long idItem, long idItemAnexo) {
        this.itensAnexosPK = new ItensAnexosPK(idItem, idItemAnexo);
    }

    public ItensAnexosPK getItensAnexosPK() {
        return itensAnexosPK;
    }

    public void setItensAnexosPK(ItensAnexosPK itensAnexosPK) {
        this.itensAnexosPK = itensAnexosPK;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }

    public Itens getItens1() {
        return itens1;
    }

    public void setItens1(Itens itens1) {
        this.itens1 = itens1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itensAnexosPK != null ? itensAnexosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensAnexos)) {
            return false;
        }
        ItensAnexos other = (ItensAnexos) object;
        if ((this.itensAnexosPK == null && other.itensAnexosPK != null) || (this.itensAnexosPK != null && !this.itensAnexosPK.equals(other.itensAnexosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ItensAnexos[itensAnexosPK=" + itensAnexosPK + "]";
    }

}
