package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionObjective} entity.
 */
@ApiModel(description = "The InspectionGeneralIndicator entity.\n@author Chris")
public class InspectionObjectiveDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 1000)
    private String name;

    private Long inspectionAreaId;

    private String inspectionAreaName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionObjectiveDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionObjectiveDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionObjectiveDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", inspectionAreaId=" + getInspectionAreaId() +
            ", inspectionAreaName='" + getInspectionAreaName() + "'" +
            "}";
    }
}
