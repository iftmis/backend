package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.Indicator} entity.
 */
@ApiModel(description = "The Indicator(indicators) entity.\n@author Chris")
public class IndicatorDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 2, max = 1000)
    private String name;

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
        if (!(o instanceof IndicatorDTO)) {
            return false;
        }

        return id != null && id.equals(((IndicatorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IndicatorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", subAreaId=" + getSubAreaId() +
            ", subAreaName='" + getSubAreaName() + "'" +
            "}";
    }
}
