package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import org.tamisemi.iftmis.domain.enumeration.ImplementationStatus;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionRecommendation} entity.
 */
@ApiModel(description = "The InspectionRecommendation entity.\n@author Chris")
public class InspectionRecommendationDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    private String description;

    @NotNull
    private ImplementationStatus implementationStatus;

    private LocalDate completionDate;

    private String compliancePlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImplementationStatus getImplementationStatus() {
        return implementationStatus;
    }

    public void setImplementationStatus(ImplementationStatus implementationStatus) {
        this.implementationStatus = implementationStatus;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getCompliancePlan() {
        return compliancePlan;
    }

    public void setCompliancePlan(String compliancePlan) {
        this.compliancePlan = compliancePlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionRecommendationDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionRecommendationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionRecommendationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", implementationStatus='" + getImplementationStatus() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", compliancePlan='" + getCompliancePlan() + "'" +
            "}";
    }
}
