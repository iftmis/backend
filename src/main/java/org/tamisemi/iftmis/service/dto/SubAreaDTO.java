package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.SubArea} entity.
 */
@ApiModel(description = "The InspectionProgram(inspection_programs) entity.\n@author Chris")
public class SubAreaDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    private Long areaId;

    private String areaName;

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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long auditableAreaId) {
        this.areaId = auditableAreaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String auditableAreaName) {
        this.areaName = auditableAreaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubAreaDTO)) {
            return false;
        }

        return id != null && id.equals(((SubAreaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubAreaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", areaId=" + getAreaId() +
            ", areaName='" + getAreaName() + "'" +
            "}";
    }
}
