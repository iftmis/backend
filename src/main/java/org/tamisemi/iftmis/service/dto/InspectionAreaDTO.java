package org.tamisemi.iftmis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.tamisemi.iftmis.domain.InspectionObjective;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionArea} entity.
 */
@ApiModel(description = "The InspectionAuditableArea(inspection_auditable_areas) entity.\n@author Chris")
public class InspectionAreaDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 2000)
    private String name;

    private Long inspectionId;

    private Long auditableAreaId;

    private String auditableAreaName;

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

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    public Long getAuditableAreaId() {
        return auditableAreaId;
    }

    public void setAuditableAreaId(Long auditableAreaId) {
        this.auditableAreaId = auditableAreaId;
    }

    public String getAuditableAreaName() {
        return auditableAreaName;
    }

    public void setAuditableAreaName(String auditableAreaName) {
        this.auditableAreaName = auditableAreaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionAreaDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionAreaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionAreaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", inspectionId=" + getInspectionId() +
            ", auditableAreaId=" + getAuditableAreaId() +
            ", auditableAreaName='" + getAuditableAreaName() + "'" +
            "}";
    }
}
