/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "rel_tipos_ent", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "RelTiposEnt.findAll", query = "SELECT r FROM RelTiposEnt r"), @NamedQuery(name = "RelTiposEnt.findByIdRelacao", query = "SELECT r FROM RelTiposEnt r WHERE r.idRelacao = :idRelacao"), @NamedQuery(name = "RelTiposEnt.findByDescricao", query = "SELECT r FROM RelTiposEnt r WHERE r.descricao = :descricao")})
public class RelTiposEnt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_relacao", nullable = false)
    private Long idRelacao;
    @Column(name = "descricao", length = 2147483647)
    private String descricao;
    @JoinColumn(name = "tipo_ent", referencedColumnName = "tipo_ent")
    @ManyToOne
    private TiposEnt tipoEnt;
    @JoinColumn(name = "tipo_ent_rel", referencedColumnName = "tipo_ent")
    @ManyToOne
    private TiposEnt tipoEntRel;
    @OneToMany(mappedBy = "idRelacao")
    private Collection<RelEnt> relEntCollection;

    public RelTiposEnt() {
    }

    public RelTiposEnt(Long idRelacao) {
        this.idRelacao = idRelacao;
    }

    public Long getIdRelacao() {
        return idRelacao;
    }

    public void setIdRelacao(Long idRelacao) {
        this.idRelacao = idRelacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TiposEnt getTipoEnt() {
        return tipoEnt;
    }

    public void setTipoEnt(TiposEnt tipoEnt) {
        this.tipoEnt = tipoEnt;
    }

    public TiposEnt getTipoEntRel() {
        return tipoEntRel;
    }

    public void setTipoEntRel(TiposEnt tipoEntRel) {
        this.tipoEntRel = tipoEntRel;
    }

    public Collection<RelEnt> getRelEntCollection() {
        return relEntCollection;
    }

    public void setRelEntCollection(Collection<RelEnt> relEntCollection) {
        this.relEntCollection = relEntCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacao != null ? idRelacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelTiposEnt)) {
            return false;
        }
        RelTiposEnt other = (RelTiposEnt) object;
        if ((this.idRelacao == null && other.idRelacao != null) || (this.idRelacao != null && !this.idRelacao.equals(other.idRelacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RelTiposEnt[idRelacao=" + idRelacao + "]";
    }

}
