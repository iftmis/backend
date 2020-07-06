package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.tamisemi.iftmis.domain.enumeration.ResponseType;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.FindingResponse} entity.
 */
@ApiModel(description = "The FindingResponse(inspection_responses) entity.\n@author Chris")
public class FindingResponseDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    private ResponseType source;

    private String description;

    private Long recommendationId;

    private String recommendationDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseType getSource() {
        return source;
    }

    public void setSource(ResponseType source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long findingRecommendationId) {
        this.recommendationId = findingRecommendationId;
    }

    public String getRecommendationDescription() {
        return recommendationDescription;
    }

    public void setRecommendationDescription(String findingRecommendationDescription) {
        this.recommendationDescription = findingRecommendationDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindingResponseDTO)) {
            return false;
        }

        return id != null && id.equals(((FindingResponseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FindingResponseDTO{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", description='" + getDescription() + "'" +
            ", recommendationId=" + getRecommendationId() +
            ", recommendationDescription='" + getRecommendationDescription() + "'" +
            "}";
    }
}
