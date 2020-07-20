package org.tamisemi.iftmis.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link org.tamisemi.iftmis.domain.InspectionActivity} entity. This class is used
 * in {@link org.tamisemi.iftmis.web.rest.InspectionActivityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /inspection-activities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class InspectionActivityCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter days;

    private LongFilter inspectionPlanId;

    private LongFilter objectiveId;

    private LongFilter auditableAreaId;

    private LongFilter subAreaId;

    private LongFilter riskId;

    private LongFilter organisationUnitsId;

    public InspectionActivityCriteria() {}

    public InspectionActivityCriteria(InspectionActivityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.days = other.days == null ? null : other.days.copy();
        this.inspectionPlanId = other.inspectionPlanId == null ? null : other.inspectionPlanId.copy();
        this.objectiveId = other.objectiveId == null ? null : other.objectiveId.copy();
        this.auditableAreaId = other.auditableAreaId == null ? null : other.auditableAreaId.copy();
        this.subAreaId = other.subAreaId == null ? null : other.subAreaId.copy();
        this.riskId = other.riskId == null ? null : other.riskId.copy();
        this.organisationUnitsId = other.organisationUnitsId == null ? null : other.organisationUnitsId.copy();
    }

    @Override
    public InspectionActivityCriteria copy() {
        return new InspectionActivityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getDays() {
        return days;
    }

    public void setDays(IntegerFilter days) {
        this.days = days;
    }

    public LongFilter getInspectionPlanId() {
        return inspectionPlanId;
    }

    public void setInspectionPlanId(LongFilter inspectionPlanId) {
        this.inspectionPlanId = inspectionPlanId;
    }

    public LongFilter getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(LongFilter objectiveId) {
        this.objectiveId = objectiveId;
    }

    public LongFilter getAuditableAreaId() {
        return auditableAreaId;
    }

    public void setAuditableAreaId(LongFilter auditableAreaId) {
        this.auditableAreaId = auditableAreaId;
    }

    public LongFilter getSubAreaId() {
        return subAreaId;
    }

    public void setSubAreaId(LongFilter subAreaId) {
        this.subAreaId = subAreaId;
    }

    public LongFilter getRiskId() {
        return riskId;
    }

    public void setRiskId(LongFilter riskId) {
        this.riskId = riskId;
    }

    public LongFilter getOrganisationUnitsId() {
        return organisationUnitsId;
    }

    public void setOrganisationUnitsId(LongFilter organisationUnitsId) {
        this.organisationUnitsId = organisationUnitsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final InspectionActivityCriteria that = (InspectionActivityCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(days, that.days) &&
            Objects.equals(inspectionPlanId, that.inspectionPlanId) &&
            Objects.equals(objectiveId, that.objectiveId) &&
            Objects.equals(auditableAreaId, that.auditableAreaId) &&
            Objects.equals(subAreaId, that.subAreaId) &&
            Objects.equals(riskId, that.riskId) &&
            Objects.equals(organisationUnitsId, that.organisationUnitsId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, days, inspectionPlanId, objectiveId, auditableAreaId, subAreaId, riskId, organisationUnitsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionActivityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (days != null ? "days=" + days + ", " : "") +
                (inspectionPlanId != null ? "inspectionPlanId=" + inspectionPlanId + ", " : "") +
                (objectiveId != null ? "objectiveId=" + objectiveId + ", " : "") +
                (auditableAreaId != null ? "auditableAreaId=" + auditableAreaId + ", " : "") +
                (subAreaId != null ? "subAreaId=" + subAreaId + ", " : "") +
                (riskId != null ? "riskId=" + riskId + ", " : "") +
                (organisationUnitsId != null ? "organisationUnitsId=" + organisationUnitsId + ", " : "") +
            "}";
    }
}
