package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

import org.tamisemi.iftmis.domain.FindingResponse;
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

    private String findingCode;

    private String findingDescription;

    private Set<FindingResponse> findingResponses = new HashSet<>();

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

    public String getFindingCode() {
        return findingCode;
    }

    public void setFindingCode(String findingCode) {
        this.findingCode = findingCode;
    }

    public String getFindingDescription() {
        return findingDescription;
    }

    public void setFindingDescription(String findingDescription) {
        this.findingDescription = findingDescription;
    }

    public Set<FindingResponse> getFindingResponses() {
        return findingResponses;
    }

    public void setFindingResponses(Set<FindingResponse> findingResponses) {
        this.findingResponses = findingResponses;
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

    @Override
    public String toString() {
        return "FindingRecommendationDTO{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", implementationStatus=" + implementationStatus +
            ", findingId=" + findingId +
            ", findingCode='" + findingCode + '\'' +
            ", findingDescription='" + findingDescription + '\'' +
            ", findingResponses=" + findingResponses +
            '}';
    }
}
