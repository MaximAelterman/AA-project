/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_pkg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Max
 */
@Entity
@Table(name = "MACHINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Machines.findAll", query = "SELECT m FROM Machines m")
    , @NamedQuery(name = "Machines.findByMnr", query = "SELECT m FROM Machines m WHERE m.mnr = :mnr")
    , @NamedQuery(name = "Machines.findByMnaam", query = "SELECT m FROM Machines m WHERE m.mnaam = :mnaam")
    , @NamedQuery(name = "Machines.findByOmschrijving", query = "SELECT m FROM Machines m WHERE m.omschrijving = :omschrijving")
    , @NamedQuery(name = "Machines.findByMloc", query = "SELECT m FROM Machines m WHERE m.mloc = :mloc")
    , @NamedQuery(name = "Machines.findByOpleiding", query = "SELECT m FROM Machines m WHERE m.opleiding = :opleiding")
    , @NamedQuery(name = "Machines.findByAankoopprijs", query = "SELECT m FROM Machines m WHERE m.aankoopprijs = :aankoopprijs")
    , @NamedQuery(name = "Machines.findByHuurprijs", query = "SELECT m FROM Machines m WHERE m.huurprijs = :huurprijs")})
public class Machines implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MNR")
    private BigDecimal mnr;
    @Size(max = 20)
    @Column(name = "MNAAM")
    private String mnaam;
    @Size(max = 120)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Size(max = 20)
    @Column(name = "MLOC")
    private String mloc;
    @Size(max = 20)
    @Column(name = "OPLEIDING")
    private String opleiding;
    @Column(name = "AANKOOPPRIJS")
    private Double aankoopprijs;
    @Column(name = "HUURPRIJS")
    private Double huurprijs;
    @OneToMany(mappedBy = "mnr")
    private Collection<Reservaties> reservatiesCollection;

    public Machines() {
    }

    public Machines(BigDecimal mnr) {
        this.mnr = mnr;
    }

    public BigDecimal getMnr() {
        return mnr;
    }

    public void setMnr(BigDecimal mnr) {
        this.mnr = mnr;
    }

    public String getMnaam() {
        return mnaam;
    }

    public void setMnaam(String mnaam) {
        this.mnaam = mnaam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getMloc() {
        return mloc;
    }

    public void setMloc(String mloc) {
        this.mloc = mloc;
    }

    public String getOpleiding() {
        return opleiding;
    }

    public void setOpleiding(String opleiding) {
        this.opleiding = opleiding;
    }

    public Double getAankoopprijs() {
        return aankoopprijs;
    }

    public void setAankoopprijs(Double aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    public Double getHuurprijs() {
        return huurprijs;
    }

    public void setHuurprijs(Double huurprijs) {
        this.huurprijs = huurprijs;
    }

    @XmlTransient
    public Collection<Reservaties> getReservatiesCollection() {
        return reservatiesCollection;
    }

    public void setReservatiesCollection(Collection<Reservaties> reservatiesCollection) {
        this.reservatiesCollection = reservatiesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mnr != null ? mnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Machines)) {
            return false;
        }
        Machines other = (Machines) object;
        if ((this.mnr == null && other.mnr != null) || (this.mnr != null && !this.mnr.equals(other.mnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean_pkg.Machines[ mnr=" + mnr + " ]";
    }
    
}
