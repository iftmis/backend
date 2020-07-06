package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.OrganisationUnitLevel} entity.
 */
@ApiModel(description = "The OrganisationUnitLevel(organisation_unit_levels) entity.\n@author Chris")
public class OrganisationUnitLevelDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @Size(min = 1, max = 64)
    private String code;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    @NotNull
    private Integer level;

    @NotNull
    private Boolean isInspectionLevel;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean isIsInspectionLevel() {
        return isInspectionLevel;
    }

    public void setIsInspectionLevel(Boolean isInspectionLevel) {
        this.isInspectionLevel = isInspectionLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganisationUnitLevelDTO)) {
            return false;
        }

        return id != null && id.equals(((OrganisationUnitLevelDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganisationUnitLevelDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", isInspectionLevel='" + isIsInspectionLevel() + "'" +
            "}";
    }
}
