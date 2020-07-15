package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.Risk} entity.
 */
@ApiModel(description = "The Risk(risks) entity.\n@author Chris")
public class RiskDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @Size(min = 1, max = 64)
    private String code;

    private String description;

    /**
     * Risk{riskOwner(name) required} to OrganisationUnit
     */
    @ApiModelProperty(value = "Risk{riskOwner(name) required} to OrganisationUnit")
    private Long riskRegisterId;

    private Long objectiveId;

    private String objectiveCode;

    private Long riskCategoryId;

    private String riskCategoryName;

    private Long riskOwnerId;

    private String riskOwnerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRiskRegisterId() {
        return riskRegisterId;
    }

    public void setRiskRegisterId(Long riskRegisterId) {
        this.riskRegisterId = riskRegisterId;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public String getObjectiveCode() {
        return objectiveCode;
    }

    public void setObjectiveCode(String objectiveCode) {
        this.objectiveCode = objectiveCode;
    }

    public Long getRiskCategoryId() {
        return riskCategoryId;
    }

    public void setRiskCategoryId(Long riskCategoryId) {
        this.riskCategoryId = riskCategoryId;
    }

    public String getRiskCategoryName() {
        return riskCategoryName;
    }

    public void setRiskCategoryName(String riskCategoryName) {
        this.riskCategoryName = riskCategoryName;
    }

    public Long getRiskOwnerId() {
        return riskOwnerId;
    }

    public void setRiskOwnerId(Long organsiationUnitId) {
        this.riskOwnerId = organsiationUnitId;
    }

    public String getRiskOwnerName() {
        return riskOwnerName;
    }

    public void setRiskOwnerName(String organsiationUnitName) {
        this.riskOwnerName = organsiationUnitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskDTO)) {
            return false;
        }

        return id != null && id.equals(((RiskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", riskRegisterId=" + getRiskRegisterId() +
            ", objectiveId=" + getObjectiveId() +
            ", objectiveCode='" + getObjectiveCode() + "'" +
            ", riskCategoryId=" + getRiskCategoryId() +
            ", riskCategoryName='" + getRiskCategoryName() + "'" +
            ", riskOwnerId=" + getRiskOwnerId() +
            ", riskOwnerName='" + getRiskOwnerName() + "'" +
            "}";
    }
}