package org.tamisemi.iftmis.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link org.tamisemi.iftmis.domain.FundingManagement} entity. This class is used
 * in {@link org.tamisemi.iftmis.web.rest.FundingManagementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /funding-managements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FundingManagementCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter description;

    private StringFilter type;

    private StringFilter code;

    private IntegerFilter level;

    private StringFilter conditions;

    private StringFilter causes;

    private StringFilter impact;

    private StringFilter implication;

    private StringFilter recommendation;

    private StringFilter managementResponse;

    private StringFilter implementation;

    private StringFilter auditComment;

    private StringFilter status;

    private StringFilter contacts;

    private LongFilter theClustersId;

    private LongFilter findingSubCategoryId;

    private LongFilter financialYearId;

    private LongFilter organisationUnitLevelId;

    public FundingManagementCriteria() {
    }

    public FundingManagementCriteria(FundingManagementCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.conditions = other.conditions == null ? null : other.conditions.copy();
        this.causes = other.causes == null ? null : other.causes.copy();
        this.impact = other.impact == null ? null : other.impact.copy();
        this.implication = other.implication == null ? null : other.implication.copy();
        this.recommendation = other.recommendation == null ? null : other.recommendation.copy();
        this.managementResponse = other.managementResponse == null ? null : other.managementResponse.copy();
        this.implementation = other.implementation == null ? null : other.implementation.copy();
        this.auditComment = other.auditComment == null ? null : other.auditComment.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.contacts = other.contacts == null ? null : other.contacts.copy();
        this.theClustersId = other.theClustersId == null ? null : other.theClustersId.copy();
        this.findingSubCategoryId = other.findingSubCategoryId == null ? null : other.findingSubCategoryId.copy();
        this.financialYearId = other.financialYearId == null ? null : other.financialYearId.copy();
        this.organisationUnitLevelId = other.organisationUnitLevelId == null ? null : other.organisationUnitLevelId.copy();
    }

    @Override
    public FundingManagementCriteria copy() {
        return new FundingManagementCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
    }

    public StringFilter getConditions() {
        return conditions;
    }

    public void setConditions(StringFilter conditions) {
        this.conditions = conditions;
    }

    public StringFilter getCauses() {
        return causes;
    }

    public void setCauses(StringFilter causes) {
        this.causes = causes;
    }

    public StringFilter getImpact() {
        return impact;
    }

    public void setImpact(StringFilter impact) {
        this.impact = impact;
    }

    public StringFilter getImplication() {
        return implication;
    }

    public void setImplication(StringFilter implication) {
        this.implication = implication;
    }

    public StringFilter getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(StringFilter recommendation) {
        this.recommendation = recommendation;
    }

    public StringFilter getManagementResponse() {
        return managementResponse;
    }

    public void setManagementResponse(StringFilter managementResponse) {
        this.managementResponse = managementResponse;
    }

    public StringFilter getImplementation() {
        return implementation;
    }

    public void setImplementation(StringFilter implementation) {
        this.implementation = implementation;
    }

    public StringFilter getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(StringFilter auditComment) {
        this.auditComment = auditComment;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getContacts() {
        return contacts;
    }

    public void setContacts(StringFilter contacts) {
        this.contacts = contacts;
    }

    public LongFilter getTheClustersId() {
        return theClustersId;
    }

    public void setTheClustersId(LongFilter theClustersId) {
        this.theClustersId = theClustersId;
    }

    public LongFilter getFindingSubCategoryId() {
        return findingSubCategoryId;
    }

    public void setFindingSubCategoryId(LongFilter findingSubCategoryId) {
        this.findingSubCategoryId = findingSubCategoryId;
    }

    public LongFilter getFinancialYearId() {
        return financialYearId;
    }

    public void setFinancialYearId(LongFilter financialYearId) {
        this.financialYearId = financialYearId;
    }

    public LongFilter getOrganisationUnitLevelId() {
        return organisationUnitLevelId;
    }

    public void setOrganisationUnitLevelId(LongFilter organisationUnitLevelId) {
        this.organisationUnitLevelId = organisationUnitLevelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FundingManagementCriteria that = (FundingManagementCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(type, that.type) &&
            Objects.equals(code, that.code) &&
            Objects.equals(level, that.level) &&
            Objects.equals(conditions, that.conditions) &&
            Objects.equals(causes, that.causes) &&
            Objects.equals(impact, that.impact) &&
            Objects.equals(implication, that.implication) &&
            Objects.equals(recommendation, that.recommendation) &&
            Objects.equals(managementResponse, that.managementResponse) &&
            Objects.equals(implementation, that.implementation) &&
            Objects.equals(auditComment, that.auditComment) &&
            Objects.equals(status, that.status) &&
            Objects.equals(contacts, that.contacts) &&
            Objects.equals(theClustersId, that.theClustersId) &&
            Objects.equals(findingSubCategoryId, that.findingSubCategoryId) &&
            Objects.equals(financialYearId, that.financialYearId) &&
            Objects.equals(organisationUnitLevelId, that.organisationUnitLevelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        description,
        type,
        code,
        level,
        conditions,
        causes,
        impact,
        implication,
        recommendation,
        managementResponse,
        implementation,
        auditComment,
        status,
        contacts,
        theClustersId,
        findingSubCategoryId,
        financialYearId,
        organisationUnitLevelId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundingManagementCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (level != null ? "level=" + level + ", " : "") +
                (conditions != null ? "conditions=" + conditions + ", " : "") +
                (causes != null ? "causes=" + causes + ", " : "") +
                (impact != null ? "impact=" + impact + ", " : "") +
                (implication != null ? "implication=" + implication + ", " : "") +
                (recommendation != null ? "recommendation=" + recommendation + ", " : "") +
                (managementResponse != null ? "managementResponse=" + managementResponse + ", " : "") +
                (implementation != null ? "implementation=" + implementation + ", " : "") +
                (auditComment != null ? "auditComment=" + auditComment + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (contacts != null ? "contacts=" + contacts + ", " : "") +
                (theClustersId != null ? "theClustersId=" + theClustersId + ", " : "") +
                (findingSubCategoryId != null ? "findingSubCategoryId=" + findingSubCategoryId + ", " : "") +
                (financialYearId != null ? "financialYearId=" + financialYearId + ", " : "") +
                (organisationUnitLevelId != null ? "organisationUnitLevelId=" + organisationUnitLevelId + ", " : "") +
            "}";
    }

}
