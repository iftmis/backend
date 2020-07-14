package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.Lob;
import javax.validation.constraints.*;
import org.tamisemi.iftmis.domain.enumeration.RatingSource;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.RiskRating} entity.
 */
@ApiModel(description = "The RiskRating(risk_ratings) entity.\n@author Chris")
public class RiskRatingDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    private RatingSource source;

    @NotNull
    @Min(value = 0)
    private Integer impact;

    @NotNull
    @Min(value = 0)
    private Integer likelihood;

    private String comments;

    private Long riskId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RatingSource getSource() {
        return source;
    }

    public void setSource(RatingSource source) {
        this.source = source;
    }

    public Integer getImpact() {
        return impact;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public Integer getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(Integer likelihood) {
        this.likelihood = likelihood;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskRatingDTO)) {
            return false;
        }

        return id != null && id.equals(((RiskRatingDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskRatingDTO{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", impact=" + getImpact() +
            ", likelihood=" + getLikelihood() +
            ", comments='" + getComments() + "'" +
            ", riskId=" + getRiskId() +
            "}";
    }
}
