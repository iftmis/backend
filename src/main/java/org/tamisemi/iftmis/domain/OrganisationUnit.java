package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * The OrganisationUnit entity.\n@author Chris
 */
@Entity
@Table(name = "organisation_units")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganisationUnit extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 1, max = 64)
    @Column(name = "code", length = 64, unique = true)
    private String code;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "background")
    private String background;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "organisationUnits", allowSetters = true)
    private OrganisationUnitLevel organisationUnitLevel;

    @ManyToOne
    @JsonIgnoreProperties(value = "organisationUnits", allowSetters = true)
    private OrganisationUnit parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public OrganisationUnit code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public OrganisationUnit name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public OrganisationUnit address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OrganisationUnit phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public OrganisationUnit email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBackground() {
        return background;
    }

    public OrganisationUnit background(String background) {
        this.background = background;
        return this;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public byte[] getLogo() {
        return logo;
    }

    public OrganisationUnit logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public OrganisationUnit logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public OrganisationUnitLevel getOrganisationUnitLevel() {
        return organisationUnitLevel;
    }

    public OrganisationUnit organisationUnitLevel(OrganisationUnitLevel organisationUnitLevel) {
        this.organisationUnitLevel = organisationUnitLevel;
        return this;
    }

    public void setOrganisationUnitLevel(OrganisationUnitLevel organisationUnitLevel) {
        this.organisationUnitLevel = organisationUnitLevel;
    }

    public OrganisationUnit getParent() {
        return parent;
    }

    public OrganisationUnit parent(OrganisationUnit organisationUnit) {
        this.parent = organisationUnit;
        return this;
    }

    public void setParent(OrganisationUnit organisationUnit) {
        this.parent = organisationUnit;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganisationUnit)) {
            return false;
        }
        return id != null && id.equals(((OrganisationUnit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganisationUnit{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", background='" + getBackground() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            "}";
    }
}
