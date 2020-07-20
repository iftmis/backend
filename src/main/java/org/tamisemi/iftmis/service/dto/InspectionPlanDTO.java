package org.tamisemi.iftmis.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionPlan} entity.
 */
public class InspectionPlanDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    private String name;

    private Long organisationUnitId;

    private String organisationUnitName;

    private Long financialYearId;

    private String financialYearName;

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

    public Long getOrganisationUnitId() {
        return organisationUnitId;
    }

    public void setOrganisationUnitId(Long organisationUnitId) {
        this.organisationUnitId = organisationUnitId;
    }

    public String getOrganisationUnitName() {
        return organisationUnitName;
    }

    public void setOrganisationUnitName(String organisationUnitName) {
        this.organisationUnitName = organisationUnitName;
    }

    public Long getFinancialYearId() {
        return financialYearId;
    }

    public void setFinancialYearId(Long financialYearId) {
        this.financialYearId = financialYearId;
    }

    public String getFinancialYearName() {
        return financialYearName;
    }

    public void setFinancialYearName(String financialYearName) {
        this.financialYearName = financialYearName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionPlanDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionPlanDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionPlanDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", organisationUnitId=" + getOrganisationUnitId() +
            ", organisationUnitName='" + getOrganisationUnitName() + "'" +
            ", financialYearId=" + getFinancialYearId() +
            ", financialYearName='" + getFinancialYearName() + "'" +
            "}";
    }
}
