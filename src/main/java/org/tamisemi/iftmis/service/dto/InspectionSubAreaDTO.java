package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionSubArea} entity.
 */
@ApiModel(description = "The InspectionSubArea(inspection_sub_areas) entity.\n@author Chris")
public class InspectionSubAreaDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    private Long inspectionAreaId;

    private String inspectionAreaName;

    private Long subAreaId;

    private String subAreaName;

    private String generalObjective;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubAreaId() {
        return subAreaId;
    }

    public void setSubAreaId(Long subAreaId) {
        this.subAreaId = subAreaId;
    }

    public String getSubAreaName() {
        return subAreaName;
    }

    public void setSubAreaName(String subAreaName) {
        this.subAreaName = subAreaName;
    }

    public Long getInspectionAreaId() {
        return inspectionAreaId;
    }

    public void setInspectionAreaId(Long inspectionAreaId) {
        this.inspectionAreaId = inspectionAreaId;
    }

    public String getInspectionAreaName() {
        return inspectionAreaName;
    }

    public void setInspectionAreaName(String inspectionAreaName) {
        this.inspectionAreaName = inspectionAreaName;
    }

    public String getGeneralObjective() {
        return generalObjective;
    }

    public void setGeneralObjective(String generalObjective) {
        this.generalObjective = generalObjective;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionSubAreaDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionSubAreaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionSubAreaDTO{" +
            "id=" + getId() +
            ", inspectionObjectiveId=" + getInspectionAreaId() +
            ", inspectionObjectiveName='" + getInspectionAreaName() + "'" +
            ", subAreaId=" + getSubAreaId() +
            ", subAreaName='" + getSubAreaName() + "'" +
            "}";
    }
}
