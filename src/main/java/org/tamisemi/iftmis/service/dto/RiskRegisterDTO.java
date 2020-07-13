package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.RiskRegister} entity.
 */
@ApiModel(description = "The RiskRegister(risk_registers) entity.\n@author Chris")
public class RiskRegisterDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    private Boolean isApproved;

    private LocalDate approvedDate;

    @Size(max = 200)
    private String approvedBy;

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

    public Boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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
        if (!(o instanceof RiskRegisterDTO)) {
            return false;
        }

        return id != null && id.equals(((RiskRegisterDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskRegisterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isApproved='" + isIsApproved() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            ", approvedBy='" + getApprovedBy() + "'" +
            ", organisationUnitId=" + getOrganisationUnitId() +
            ", organisationUnitName='" + getOrganisationUnitName() + "'" +
            ", financialYearId=" + getFinancialYearId() +
            ", financialYearName='" + getFinancialYearName() + "'" +
            "}";
    }
}
