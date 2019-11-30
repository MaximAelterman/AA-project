/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_pkg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Max
 */
@Entity
@Table(name = "RESERVATIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservaties.findAll", query = "SELECT r FROM Reservaties r")
    , @NamedQuery(name = "Reservaties.findByRnr", query = "SELECT r FROM Reservaties r WHERE r.rnr = :rnr")
    , @NamedQuery(name = "Reservaties.findByDatum", query = "SELECT r FROM Reservaties r WHERE r.datum = :datum")
    , @NamedQuery(name = "Reservaties.findByUur", query = "SELECT r FROM Reservaties r WHERE r.uur = :uur")
    , @NamedQuery(name = "Reservaties.findByDuur", query = "SELECT r FROM Reservaties r WHERE r.duur = :duur")})
public class Reservaties implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RNR")
    private BigDecimal rnr;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "UUR")
    private BigInteger uur;
    @Column(name = "DUUR")
    private BigInteger duur;
    @JoinColumn(name = "GEBRUIKERSNAAM", referencedColumnName = "GEBRUIKERSNAAM")
    @ManyToOne
    private Gebruikers gebruikersnaam;
    @JoinColumn(name = "MNR", referencedColumnName = "MNR")
    @ManyToOne
    private Machines mnr;

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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public BigInteger getUur() {
        return uur;
    }

    public void setUur(BigInteger uur) {
        this.uur = uur;
    }

    public BigInteger getDuur() {
        return duur;
    }

    public void setDuur(BigInteger duur) {
        this.duur = duur;
    }

    public Gebruikers getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(Gebruikers gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public Machines getMnr() {
        return mnr;
    }

    public void setMnr(Machines mnr) {
        this.mnr = mnr;
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
        return "bean_pkg.Reservaties[ rnr=" + rnr + " ]";
    }
    
}
