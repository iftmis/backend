package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionWorkDone} entity.
 */
@ApiModel(description = "The InspectionWorkDone(inspection_work_dones) entity.\n@author Chris")
public class InspectionWorkDoneDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    private String name;

    private Boolean isOk;

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

    public Boolean isIsOk() {
        return isOk;
    }

    public void setIsOk(Boolean isOk) {
        this.isOk = isOk;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long inspectionProcedureId) {
        this.procedureId = inspectionProcedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String inspectionProcedureName) {
        this.procedureName = inspectionProcedureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionWorkDoneDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionWorkDoneDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionWorkDoneDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isOk='" + isIsOk() + "'" +
            ", procedureId=" + getProcedureId() +
            ", procedureName='" + getProcedureName() + "'" +
            "}";
    }
}
