package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.tamisemi.iftmis.domain.enumeration.InspectionType;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.Inspection} entity.
 */
@ApiModel(description = "The Inspection(inspections) entity.\n@author Chris")
public class InspectionDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private InspectionType inspectionType;

    private Long financialYearId;

    private String financialYearName;

    private Long organisationUnitId;

    private String organisationUnitName;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", inspectionType='" + getInspectionType() + "'" +
            ", financialYearId=" + getFinancialYearId() +
            ", financialYearName='" + getFinancialYearName() + "'" +
            ", organisationUnitId=" + getOrganisationUnitId() +
            ", organisationUnitName='" + getOrganisationUnitName() + "'" +
            "}";
    }
}
