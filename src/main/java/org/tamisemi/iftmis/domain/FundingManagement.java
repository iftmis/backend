package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A FundingManagement.
 */
@Entity
@Table(name = "funding_management")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FundingManagement extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "code")
    private String code;

    @Column(name = "level")
    private Integer level;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "causes")
    private String causes;

    @Column(name = "impact")
    private String impact;

    @Column(name = "implication")
    private String implication;

    @Column(name = "recommendation")
    private String recommendation;

    @Column(name = "management_response")
    private String managementResponse;

    @Column(name = "implementation")
    private String implementation;

    @Column(name = "audit_comment")
    private String auditComment;

    @Column(name = "status")
    private String status;

    @Column(name = "contacts")
    private String contacts;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "fundingManagements", allowSetters = true)
    private TheClusters theClusters;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "fundingManagements", allowSetters = true)
    private FindingSubCategory findingSubCategory;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "fundingManagements", allowSetters = true)
    private FinancialYear financialYear;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "fundingManagements", allowSetters = true)
    private OrganisationUnitLevel organisationUnitLevel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public FundingManagement title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public FundingManagement description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public FundingManagement type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public FundingManagement code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public FundingManagement level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getConditions() {
        return conditions;
    }

    public FundingManagement conditions(String conditions) {
        this.conditions = conditions;
        return this;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getCauses() {
        return causes;
    }

    public FundingManagement causes(String causes) {
        this.causes = causes;
        return this;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public String getImpact() {
        return impact;
    }

    public FundingManagement impact(String impact) {
        this.impact = impact;
        return this;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getImplication() {
        return implication;
    }

    public FundingManagement implication(String implication) {
        this.implication = implication;
        return this;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public FundingManagement recommendation(String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getManagementResponse() {
        return managementResponse;
    }

    public FundingManagement managementResponse(String managementResponse) {
        this.managementResponse = managementResponse;
        return this;
    }

    public void setManagementResponse(String managementResponse) {
        this.managementResponse = managementResponse;
    }

    public String getImplementation() {
        return implementation;
    }

    public FundingManagement implementation(String implementation) {
        this.implementation = implementation;
        return this;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getAuditComment() {
        return auditComment;
    }

    public FundingManagement auditComment(String auditComment) {
        this.auditComment = auditComment;
        return this;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public String getStatus() {
        return status;
    }

    public FundingManagement status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContacts() {
        return contacts;
    }

    public FundingManagement contacts(String contacts) {
        this.contacts = contacts;
        return this;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public TheClusters getTheClusters() {
        return theClusters;
    }

    public FundingManagement theClusters(TheClusters theClusters) {
        this.theClusters = theClusters;
        return this;
    }

    public void setTheClusters(TheClusters theClusters) {
        this.theClusters = theClusters;
    }

    public FindingSubCategory getFindingSubCategory() {
        return findingSubCategory;
    }

    public FundingManagement findingSubCategory(FindingSubCategory findingSubCategory) {
        this.findingSubCategory = findingSubCategory;
        return this;
    }

    public void setFindingSubCategory(FindingSubCategory findingSubCategory) {
        this.findingSubCategory = findingSubCategory;
    }

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public FundingManagement financialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
        return this;
    }

    public void setFinancialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
    }

    public OrganisationUnitLevel getOrganisationUnitLevel() {
        return organisationUnitLevel;
    }

    public FundingManagement organisationUnitLevel(OrganisationUnitLevel organisationUnitLevel) {
        this.organisationUnitLevel = organisationUnitLevel;
        return this;
    }

    public void setOrganisationUnitLevel(OrganisationUnitLevel organisationUnitLevel) {
        this.organisationUnitLevel = organisationUnitLevel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundingManagement)) {
            return false;
        }
        return id != null && id.equals(((FundingManagement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundingManagement{" +
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
            "}";
    }
}
