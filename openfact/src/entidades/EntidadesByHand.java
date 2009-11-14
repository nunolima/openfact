/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 *
 * @author User
 */
@Entity
@NamedQuery(name="findAllEntidades", query="select e from EntidadesByHand e")
public class EntidadesByHand implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="tipoEntidadeId", nullable=false, updatable=false)
    private TipoEntidadesByHand tipoEntidade;

    @Column(nullable=false)
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEntidadesByHand getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(TipoEntidadesByHand tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
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
        if (!(object instanceof EntidadesByHand)) {
            return false;
        }
        EntidadesByHand other = (EntidadesByHand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Entidades[id=" + id + ", nome=" + nome + ", tipo=" + tipoEntidade.getDescricao() + "]";
    }

}
