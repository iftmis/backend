package org.tamisemi.iftmis.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.FundingManagement} entity.
 */
public class FundingManagementDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String title;

    private String description;

    private String type;

    private String code;

    private Integer level;

    private String conditions;

    private String causes;

    private String impact;

    private String implication;

    private String recommendation;

    private String managementResponse;

    private String implementation;

    private String auditComment;

    private String status;

    private String contacts;


    private Long theClustersId;

    private String theClustersName;

    private Long findingSubCategoryId;

    private String findingSubCategoryName;

    private Long financialYearId;

    private String financialYearName;

    private Long organisationUnitLevelId;

    private String organisationUnitLevelName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getManagementResponse() {
        return managementResponse;
    }

    public void setManagementResponse(String managementResponse) {
        this.managementResponse = managementResponse;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Long getTheClustersId() {
        return theClustersId;
    }

    public void setTheClustersId(Long theClustersId) {
        this.theClustersId = theClustersId;
    }

    public String getTheClustersName() {
        return theClustersName;
    }

    public void setTheClustersName(String theClustersName) {
        this.theClustersName = theClustersName;
    }

    public Long getFindingSubCategoryId() {
        return findingSubCategoryId;
    }

    public void setFindingSubCategoryId(Long findingSubCategoryId) {
        this.findingSubCategoryId = findingSubCategoryId;
    }

    public String getFindingSubCategoryName() {
        return findingSubCategoryName;
    }

    public void setFindingSubCategoryName(String findingSubCategoryName) {
        this.findingSubCategoryName = findingSubCategoryName;
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

    public Long getOrganisationUnitLevelId() {
        return organisationUnitLevelId;
    }

    public void setOrganisationUnitLevelId(Long organisationUnitLevelId) {
        this.organisationUnitLevelId = organisationUnitLevelId;
    }

    public String getOrganisationUnitLevelName() {
        return organisationUnitLevelName;
    }

    public void setOrganisationUnitLevelName(String organisationUnitLevelName) {
        this.organisationUnitLevelName = organisationUnitLevelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundingManagementDTO)) {
            return false;
        }

        return id != null && id.equals(((FundingManagementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundingManagementDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", code='" + getCode() + "'" +
            ", level=" + getLevel() +
            ", conditions='" + getConditions() + "'" +
            ", causes='" + getCauses() + "'" +
            ", impact='" + getImpact() + "'" +
            ", implication='" + getImplication() + "'" +
            ", recommendation='" + getRecommendation() + "'" +
            ", managementResponse='" + getManagementResponse() + "'" +
            ", implementation='" + getImplementation() + "'" +
            ", auditComment='" + getAuditComment() + "'" +
            ", status='" + getStatus() + "'" +
            ", contacts='" + getContacts() + "'" +
            ", theClustersId=" + getTheClustersId() +
            ", theClustersName='" + getTheClustersName() + "'" +
            ", findingSubCategoryId=" + getFindingSubCategoryId() +
            ", findingSubCategoryName='" + getFindingSubCategoryName() + "'" +
            ", financialYearId=" + getFinancialYearId() +
            ", financialYearName='" + getFinancialYearName() + "'" +
            ", organisationUnitLevelId=" + getOrganisationUnitLevelId() +
            ", organisationUnitLevelName='" + getOrganisationUnitLevelName() + "'" +
            "}";
    }
}
