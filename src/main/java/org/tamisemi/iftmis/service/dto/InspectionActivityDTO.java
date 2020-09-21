package org.tamisemi.iftmis.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionActivity} entity.
 */
public class InspectionActivityDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    private Integer days;

    private Long inspectionPlanId;

    private Long objectiveId;

    private String objectiveName;

    private Long auditableAreaId;

    private String auditableAreaName;

    private Long subAreaId;

    private String subAreaName;
    private Set<RiskDTO> risks = new HashSet<>();
    private Set<OrganisationUnitDTO> organisationUnits = new HashSet<>();
    private String quarter_one;
    private String quarter_two;
    private String quarter_three;
    private String quarter_four;

    public String getQuarter_two() {
        return quarter_two;
    }

    public void setQuarter_two(String quarter_two) {
        this.quarter_two = quarter_two;
    }

    public String getQuarter_three() {
        return quarter_three;
    }

    public void setQuarter_three(String quarter_three) {
        this.quarter_three = quarter_three;
    }

    public String getQuarter_four() {
        return quarter_four;
    }

    public void setQuarter_four(String quarter_four) {
        this.quarter_four = quarter_four;
    }

    public String getQuarter_one() {
        return quarter_one;
    }

    public void setQuarter_one(String quarter_one) {
        this.quarter_one = quarter_one;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Long getInspectionPlanId() {
        return inspectionPlanId;
    }

    public void setInspectionPlanId(Long inspectionPlanId) {
        this.inspectionPlanId = inspectionPlanId;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public void setObjectiveName(String objectiveName) {
        this.objectiveName = objectiveName;
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

    public Set<RiskDTO> getRisks() {
        return risks;
    }

    public void setRisks(Set<RiskDTO> risks) {
        this.risks = risks;
    }

    public Set<OrganisationUnitDTO> getOrganisationUnits() {
        return organisationUnits;
    }

    public void setOrganisationUnits(Set<OrganisationUnitDTO> organisationUnits) {
        this.organisationUnits = organisationUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionActivityDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionActivityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionActivityDTO{" +
            "id=" + getId() +
            ", days=" + getDays() +
            ", inspectionPlanId=" + getInspectionPlanId() +
            ", objectiveId=" + getObjectiveId() +
            ", objectiveName='" + getObjectiveName() + "'" +
            ", auditableAreaId=" + getAuditableAreaId() +
            ", auditableAreaName='" + getAuditableAreaName() + "'" +
            ", subAreaId=" + getSubAreaId() +
            ", subAreaName='" + getSubAreaName() + "'" +
            ", risks='" + getRisks() + "'" +
            ", organisationUnits='" + getOrganisationUnits() + "'" +
            ", quarter_one='" + getQuarter_one() + '\'' +
            ", quarter_two='" + getQuarter_two() + '\'' +
            ", quarter_three='" + getQuarter_three() + '\'' +
            ", quarter_four='" + getQuarter_four() + '\'' +
            "}";
    }
}
