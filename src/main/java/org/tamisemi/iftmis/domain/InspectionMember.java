package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.tamisemi.iftmis.domain.enumeration.InspectionRole;

/**
 * The InspectionMember entity.\n@author Chris
 */
@Entity
@Table(name = "inspection_members")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionMember extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private InspectionRole role;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionMembers", allowSetters = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = "inspectionMembers", allowSetters = true)
    private FileResource letterAttachment;

    @ManyToOne
    @JsonIgnoreProperties(value = "inspectionMembers", allowSetters = true)
    private FileResource declarationAttachment;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionMembers", allowSetters = true)
    private Inspection inspection;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InspectionRole getRole() {
        return role;
    }

    public InspectionMember role(InspectionRole role) {
        this.role = role;
        return this;
    }

    public void setRole(InspectionRole role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public InspectionMember user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FileResource getLetterAttachment() {
        return letterAttachment;
    }

    public InspectionMember letterAttachment(FileResource fileResource) {
        this.letterAttachment = fileResource;
        return this;
    }

    public void setLetterAttachment(FileResource fileResource) {
        this.letterAttachment = fileResource;
    }

    public FileResource getDeclarationAttachment() {
        return declarationAttachment;
    }

    public InspectionMember declarationAttachment(FileResource fileResource) {
        this.declarationAttachment = fileResource;
        return this;
    }

    public void setDeclarationAttachment(FileResource fileResource) {
        this.declarationAttachment = fileResource;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public InspectionMember inspection(Inspection inspection) {
        this.inspection = inspection;
        return this;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionMember)) {
            return false;
        }
        return id != null && id.equals(((InspectionMember) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionMember{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            "}";
    }
}
