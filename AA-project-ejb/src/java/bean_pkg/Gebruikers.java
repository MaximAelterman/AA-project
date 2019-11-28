/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_pkg;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "GEBRUIKERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gebruikers.findAll", query = "SELECT g FROM Gebruikers g")
    , @NamedQuery(name = "Gebruikers.findByGebruikersnaam", query = "SELECT g FROM Gebruikers g WHERE g.gebruikersnaam = :gebruikersnaam")
    , @NamedQuery(name = "Gebruikers.findByPaswoord", query = "SELECT g FROM Gebruikers g WHERE g.paswoord = :paswoord")
    , @NamedQuery(name = "Gebruikers.findByOpleiding", query = "SELECT g FROM Gebruikers g WHERE g.opleiding = :opleiding")})
public class Gebruikers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GEBRUIKERSNAAM")
    private String gebruikersnaam;
    @Size(max = 20)
    @Column(name = "PASWOORD")
    private String paswoord;
    @Size(max = 10)
    @Column(name = "OPLEIDING")
    private String opleiding;
    @OneToMany(mappedBy = "gebruikersnaam")
    private Collection<Reservaties> reservatiesCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "gebruikers")
    private Groepen groepen;

    public Gebruikers() {
    }

    public Gebruikers(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public String getOpleiding() {
        return opleiding;
    }

    public void setOpleiding(String opleiding) {
        this.opleiding = opleiding;
    }

    @XmlTransient
    public Collection<Reservaties> getReservatiesCollection() {
        return reservatiesCollection;
    }

    public void setReservatiesCollection(Collection<Reservaties> reservatiesCollection) {
        this.reservatiesCollection = reservatiesCollection;
    }

    public Groepen getGroepen() {
        return groepen;
    }

    public void setGroepen(Groepen groepen) {
        this.groepen = groepen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gebruikersnaam != null ? gebruikersnaam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gebruikers)) {
            return false;
        }
        Gebruikers other = (Gebruikers) object;
        if ((this.gebruikersnaam == null && other.gebruikersnaam != null) || (this.gebruikersnaam != null && !this.gebruikersnaam.equals(other.gebruikersnaam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean_pkg.Gebruikers[ gebruikersnaam=" + gebruikersnaam + " ]";
    }
    
}
