package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.tamisemi.iftmis.domain.enumeration.ImplementationStatus;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.FindingRecommendation} entity.
 */
@ApiModel(description = "The FindingRecommendation(finding_recommendations) entity.\n@author Chris")
public class FindingRecommendationDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    private String description;

    @NotNull
    private ImplementationStatus implementationStatus;

    private Long findingId;

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

    public Long getFindingId() {
        return findingId;
    }

    public void setFindingId(Long findingId) {
        this.findingId = findingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindingRecommendationDTO)) {
            return false;
        }

        return id != null && id.equals(((FindingRecommendationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FindingRecommendationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", implementationStatus='" + getImplementationStatus() + "'" +
            ", findingId=" + getFindingId() +
            "}";
    }
}
