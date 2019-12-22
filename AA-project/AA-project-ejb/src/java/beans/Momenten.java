/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author r0631
 */
@Entity
@Table(name = "MOMENTEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Momenten.findAll", query = "SELECT m FROM Momenten m")
    , @NamedQuery(name = "Momenten.findByMomid", query = "SELECT m FROM Momenten m WHERE m.momid = :momid")
    , @NamedQuery(name = "Momenten.findByStrt", query = "SELECT m FROM Momenten m WHERE m.strt = :strt")
    , @NamedQuery(name = "Momenten.findByDuur", query = "SELECT m FROM Momenten m WHERE m.duur = :duur")
    , @NamedQuery(name = "Momenten.findByDatum", query = "SELECT m FROM Momenten m WHERE m.datum = :datum")})
public class Momenten implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOMID")
    private BigDecimal momid;
    @Column(name = "STRT")
    private BigInteger strt;
    @Column(name = "DUUR")
    private BigInteger duur;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @OneToMany(mappedBy = "momid")
    private Collection<Reservaties> reservatiesCollection;
    @JoinColumn(name = "MNR", referencedColumnName = "MNR")
    @ManyToOne
    private Machines mnr;

    public Momenten() {
    }

    public Momenten(BigDecimal momid) {
        this.momid = momid;
    }

    public BigDecimal getMomid() {
        return momid;
    }

    public void setMomid(BigDecimal momid) {
        this.momid = momid;
    }

    public BigInteger getStrt() {
        return strt;
    }

    public void setStrt(BigInteger strt) {
        this.strt = strt;
    }

    public BigInteger getDuur() {
        return duur;
    }

    public void setDuur(BigInteger duur) {
        this.duur = duur;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @XmlTransient
    public Collection<Reservaties> getReservatiesCollection() {
        return reservatiesCollection;
    }

    public void setReservatiesCollection(Collection<Reservaties> reservatiesCollection) {
        this.reservatiesCollection = reservatiesCollection;
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
        hash += (momid != null ? momid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Momenten)) {
            return false;
        }
        Momenten other = (Momenten) object;
        if ((this.momid == null && other.momid != null) || (this.momid != null && !this.momid.equals(other.momid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Momenten[ momid=" + momid + " ]";
    }
    
}
