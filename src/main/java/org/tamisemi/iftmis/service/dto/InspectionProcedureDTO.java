package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionProcedure} entity.
 */
@ApiModel(description = "The InspectionProcedure entity.\n@author Chris")
public class InspectionProcedureDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 1000)
    private String name;

    private Long inspectionIndicatorId;

    private String inspectionIndicatorName;

    private Long procedureId;

    private String procedureName;

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

    public Long getInspectionIndicatorId() {
        return inspectionIndicatorId;
    }

    public void setInspectionIndicatorId(Long inspectionIndicatorId) {
        this.inspectionIndicatorId = inspectionIndicatorId;
    }

    public String getInspectionIndicatorName() {
        return inspectionIndicatorName;
    }

    public void setInspectionIndicatorName(String inspectionIndicatorName) {
        this.inspectionIndicatorName = inspectionIndicatorName;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionProcedureDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionProcedureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionProcedureDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", inspectionIndicatorId=" + getInspectionIndicatorId() +
            ", inspectionIndicatorName='" + getInspectionIndicatorName() + "'" +
            ", procedureId=" + getProcedureId() +
            ", procedureName='" + getProcedureName() + "'" +
            "}";
    }
}
