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
 * Criteria class for the {@link org.tamisemi.iftmis.domain.Risk} entity. This class is used
 * in {@link org.tamisemi.iftmis.web.rest.RiskResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /risks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RiskCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter description;

    private LongFilter riskRegisterId;

    private LongFilter objectiveId;

    private LongFilter riskCategoryId;

    public RiskCriteria() {
    }

    public RiskCriteria(RiskCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.riskRegisterId = other.riskRegisterId == null ? null : other.riskRegisterId.copy();
        this.objectiveId = other.objectiveId == null ? null : other.objectiveId.copy();
        this.riskCategoryId = other.riskCategoryId == null ? null : other.riskCategoryId.copy();
    }

    @Override
    public RiskCriteria copy() {
        return new RiskCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public LongFilter getRiskRegisterId() {
        return riskRegisterId;
    }

    public void setRiskRegisterId(LongFilter riskRegisterId) {
        this.riskRegisterId = riskRegisterId;
    }

    public LongFilter getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(LongFilter objectiveId) {
        this.objectiveId = objectiveId;
    }

    public LongFilter getRiskCategoryId() {
        return riskCategoryId;
    }

    public void setRiskCategoryId(LongFilter riskCategoryId) {
        this.riskCategoryId = riskCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RiskCriteria that = (RiskCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(description, that.description) &&
                Objects.equals(riskRegisterId, that.riskRegisterId) &&
                Objects.equals(objectiveId, that.objectiveId) &&
                Objects.equals(riskCategoryId, that.riskCategoryId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, riskRegisterId, objectiveId, riskCategoryId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (riskRegisterId != null ? "riskRegisterId=" + riskRegisterId + ", " : "") +
            (objectiveId != null ? "objectiveId=" + objectiveId + ", " : "") +
            (riskCategoryId != null ? "riskCategoryId=" + riskCategoryId + ", " : "") +
            "}";
    }
}
