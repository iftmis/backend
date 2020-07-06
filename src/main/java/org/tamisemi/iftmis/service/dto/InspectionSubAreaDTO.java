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

    @NotNull
    @Size(max = 1000)
    private String name;

    private Long inspectionObjectiveId;

    private String inspectionObjectiveName;

    private Long subAreaId;

    private String subAreaName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInspectionObjectiveId() {
        return inspectionObjectiveId;
    }

    public void setInspectionObjectiveId(Long inspectionObjectiveId) {
        this.inspectionObjectiveId = inspectionObjectiveId;
    }

    public String getInspectionObjectiveName() {
        return inspectionObjectiveName;
    }

    public void setInspectionObjectiveName(String inspectionObjectiveName) {
        this.inspectionObjectiveName = inspectionObjectiveName;
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
            ", name='" + getName() + "'" +
            ", inspectionObjectiveId=" + getInspectionObjectiveId() +
            ", inspectionObjectiveName='" + getInspectionObjectiveName() + "'" +
            ", subAreaId=" + getSubAreaId() +
            ", subAreaName='" + getSubAreaName() + "'" +
            "}";
    }
}
