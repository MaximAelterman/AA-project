/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author r0631
 */
@Entity
@Table(name = "RESERVATIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservaties.findAll", query = "SELECT r FROM Reservaties r")
    , @NamedQuery(name = "Reservaties.findByRnr", query = "SELECT r FROM Reservaties r WHERE r.rnr = :rnr")})
public class Reservaties implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RNR")
    private BigDecimal rnr;
    @JoinColumn(name = "GEBRUIKERSNAAM", referencedColumnName = "GEBRUIKERSNAAM")
    @ManyToOne
    private Gebruikers gebruikersnaam;
    @JoinColumn(name = "MOMID", referencedColumnName = "MOMID")
    @ManyToOne
    private Momenten momid;

    public Reservaties() {
    }

    public Reservaties(BigDecimal rnr) {
        this.rnr = rnr;
    }

    public BigDecimal getRnr() {
        return rnr;
    }

    public void setRnr(BigDecimal rnr) {
        this.rnr = rnr;
    }

    public Gebruikers getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(Gebruikers gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public Momenten getMomid() {
        return momid;
    }

    public void setMomid(Momenten momid) {
        this.momid = momid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rnr != null ? rnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservaties)) {
            return false;
        }
        Reservaties other = (Reservaties) object;
        if ((this.rnr == null && other.rnr != null) || (this.rnr != null && !this.rnr.equals(other.rnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Reservaties[ rnr=" + rnr + " ]";
    }
    
}
