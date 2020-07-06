package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.tamisemi.iftmis.domain.enumeration.ActionPlanCategory;

/**
 * The InspectionFinding(inspection_findings) entity.\n@author Chris
 */
@Entity
@Table(name = "inspection_findings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionFinding extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "code", length = 5, nullable = false)
    private String code;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;

    @Column(name = "condition")
    private String condition;

    @Column(name = "disclosed_last_inspection")
    private Boolean disclosedLastInspection;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "causes")
    private String causes;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_plan_category")
    private ActionPlanCategory actionPlanCategory;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "implication")
    private String implication;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionFindings", allowSetters = true)
    private InspectionWorkDone workDone;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionFindings", allowSetters = true)
    private FindingCategory category;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionFindings", allowSetters = true)
    private FindingSubCategory subCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public InspectionFinding code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public InspectionFinding description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public InspectionFinding condition(String condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean isDisclosedLastInspection() {
        return disclosedLastInspection;
    }

    public InspectionFinding disclosedLastInspection(Boolean disclosedLastInspection) {
        this.disclosedLastInspection = disclosedLastInspection;
        return this;
    }

    public void setDisclosedLastInspection(Boolean disclosedLastInspection) {
        this.disclosedLastInspection = disclosedLastInspection;
    }

    public String getCauses() {
        return causes;
    }

    public InspectionFinding causes(String causes) {
        this.causes = causes;
        return this;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public ActionPlanCategory getActionPlanCategory() {
        return actionPlanCategory;
    }

    public InspectionFinding actionPlanCategory(ActionPlanCategory actionPlanCategory) {
        this.actionPlanCategory = actionPlanCategory;
        return this;
    }

    public void setActionPlanCategory(ActionPlanCategory actionPlanCategory) {
        this.actionPlanCategory = actionPlanCategory;
    }

    public String getImplication() {
        return implication;
    }

    public InspectionFinding implication(String implication) {
        this.implication = implication;
        return this;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    public Boolean isIsClosed() {
        return isClosed;
    }

    public InspectionFinding isClosed(Boolean isClosed) {
        this.isClosed = isClosed;
        return this;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public InspectionWorkDone getWorkDone() {
        return workDone;
    }

    public InspectionFinding workDone(InspectionWorkDone inspectionWorkDone) {
        this.workDone = inspectionWorkDone;
        return this;
    }

    public void setWorkDone(InspectionWorkDone inspectionWorkDone) {
        this.workDone = inspectionWorkDone;
    }

    public FindingCategory getCategory() {
        return category;
    }

    public InspectionFinding category(FindingCategory findingCategory) {
        this.category = findingCategory;
        return this;
    }

    public void setCategory(FindingCategory findingCategory) {
        this.category = findingCategory;
    }

    public FindingSubCategory getSubCategory() {
        return subCategory;
    }

    public InspectionFinding subCategory(FindingSubCategory findingSubCategory) {
        this.subCategory = findingSubCategory;
        return this;
    }

    public void setSubCategory(FindingSubCategory findingSubCategory) {
        this.subCategory = findingSubCategory;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionFinding)) {
            return false;
        }
        return id != null && id.equals(((InspectionFinding) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionFinding{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", condition='" + getCondition() + "'" +
            ", disclosedLastInspection='" + isDisclosedLastInspection() + "'" +
            ", causes='" + getCauses() + "'" +
            ", actionPlanCategory='" + getActionPlanCategory() + "'" +
            ", implication='" + getImplication() + "'" +
            ", isClosed='" + isIsClosed() + "'" +
            "}";
    }
}
