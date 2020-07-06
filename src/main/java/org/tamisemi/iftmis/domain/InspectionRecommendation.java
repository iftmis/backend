package org.tamisemi.iftmis.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.tamisemi.iftmis.domain.enumeration.ImplementationStatus;

/**
 * The InspectionRecommendation entity.\n@author Chris
 */
@Entity
@Table(name = "inspection_recommendations")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionRecommendation extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "implementation_status", nullable = false)
    private ImplementationStatus implementationStatus;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "compliance_plan")
    private String compliancePlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public InspectionRecommendation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImplementationStatus getImplementationStatus() {
        return implementationStatus;
    }

    public InspectionRecommendation implementationStatus(ImplementationStatus implementationStatus) {
        this.implementationStatus = implementationStatus;
        return this;
    }

    public void setImplementationStatus(ImplementationStatus implementationStatus) {
        this.implementationStatus = implementationStatus;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public InspectionRecommendation completionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getCompliancePlan() {
        return compliancePlan;
    }

    public InspectionRecommendation compliancePlan(String compliancePlan) {
        this.compliancePlan = compliancePlan;
        return this;
    }

    public void setCompliancePlan(String compliancePlan) {
        this.compliancePlan = compliancePlan;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionRecommendation)) {
            return false;
        }
        return id != null && id.equals(((InspectionRecommendation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionRecommendation{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", implementationStatus='" + getImplementationStatus() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", compliancePlan='" + getCompliancePlan() + "'" +
            "}";
    }
}
