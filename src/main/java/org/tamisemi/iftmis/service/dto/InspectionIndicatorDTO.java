package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionIndicator} entity.
 */
@ApiModel(description = "The InspectionIndicator(inspection_indicators) entity.\n@author Chris")
public class InspectionIndicatorDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 1000)
    private String name;

    private Long inspectionSubAreaId;

    private String inspectionSubAreaName;

    private Long indicatorId;

    private String indicatorName;

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

    public Long getInspectionSubAreaId() {
        return inspectionSubAreaId;
    }

    public void setInspectionSubAreaId(Long inspectionSubAreaId) {
        this.inspectionSubAreaId = inspectionSubAreaId;
    }

    public String getInspectionSubAreaName() {
        return inspectionSubAreaName;
    }

    public void setInspectionSubAreaName(String inspectionSubAreaName) {
        this.inspectionSubAreaName = inspectionSubAreaName;
    }

    public Long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionIndicatorDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionIndicatorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionIndicatorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", inspectionSubAreaId=" + getInspectionSubAreaId() +
            ", inspectionSubAreaName='" + getInspectionSubAreaName() + "'" +
            ", indicatorId=" + getIndicatorId() +
            ", indicatorName='" + getIndicatorName() + "'" +
            "}";
    }
}
