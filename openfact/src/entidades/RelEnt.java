/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
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
@Table(name = "rel_ent", catalog = "ecofact_testes", schema = "public")
@NamedQueries({@NamedQuery(name = "RelEnt.findAll", query = "SELECT r FROM RelEnt r"), @NamedQuery(name = "RelEnt.findByIdEnt", query = "SELECT r FROM RelEnt r WHERE r.relEntPK.idEnt = :idEnt"), @NamedQuery(name = "RelEnt.findByIdEntRel", query = "SELECT r FROM RelEnt r WHERE r.relEntPK.idEntRel = :idEntRel")})
public class RelEnt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelEntPK relEntPK;
    @JoinColumn(name = "id_ent", referencedColumnName = "id_ent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidades entidades;
    @JoinColumn(name = "id_ent_rel", referencedColumnName = "id_ent", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidades entidades1;
    @JoinColumn(name = "id_relacao", referencedColumnName = "id_relacao")
    @ManyToOne
    private RelTiposEnt idRelacao;

    public RelEnt() {
    }

    public RelEnt(RelEntPK relEntPK) {
        this.relEntPK = relEntPK;
    }

    public RelEnt(long idEnt, long idEntRel) {
        this.relEntPK = new RelEntPK(idEnt, idEntRel);
    }

    public RelEntPK getRelEntPK() {
        return relEntPK;
    }

    public void setRelEntPK(RelEntPK relEntPK) {
        this.relEntPK = relEntPK;
    }

    public Entidades getEntidades() {
        return entidades;
    }

    public void setEntidades(Entidades entidades) {
        this.entidades = entidades;
    }

    public Entidades getEntidades1() {
        return entidades1;
    }

    public void setEntidades1(Entidades entidades1) {
        this.entidades1 = entidades1;
    }

    public RelTiposEnt getIdRelacao() {
        return idRelacao;
    }

    public void setIdRelacao(RelTiposEnt idRelacao) {
        this.idRelacao = idRelacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relEntPK != null ? relEntPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelEnt)) {
            return false;
        }
        RelEnt other = (RelEnt) object;
        if ((this.relEntPK == null && other.relEntPK != null) || (this.relEntPK != null && !this.relEntPK.equals(other.relEntPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RelEnt[relEntPK=" + relEntPK + "]";
    }

}
