package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.tamisemi.iftmis.domain.enumeration.ActionPlanCategory;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionFinding} entity.
 */
@ApiModel(description = "The InspectionFinding(inspection_findings) entity.\n@author Chris")
public class InspectionFindingDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 5)
    private String code;

    private String description;

    private String condition;

    private Boolean disclosedLastInspection;

    private String causes;

    private ActionPlanCategory actionPlanCategory;

    private String implication;

    private Boolean isClosed;

    private Long workDoneId;

    private String workDoneName;

    private Long categoryId;

    private String categoryName;

    private Long subCategoryId;

    private String subCategoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean isDisclosedLastInspection() {
        return disclosedLastInspection;
    }

    public void setDisclosedLastInspection(Boolean disclosedLastInspection) {
        this.disclosedLastInspection = disclosedLastInspection;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public ActionPlanCategory getActionPlanCategory() {
        return actionPlanCategory;
    }

    public void setActionPlanCategory(ActionPlanCategory actionPlanCategory) {
        this.actionPlanCategory = actionPlanCategory;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    public Boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Long getWorkDoneId() {
        return workDoneId;
    }

    public void setWorkDoneId(Long inspectionWorkDoneId) {
        this.workDoneId = inspectionWorkDoneId;
    }

    public String getWorkDoneName() {
        return workDoneName;
    }

    public void setWorkDoneName(String inspectionWorkDoneName) {
        this.workDoneName = inspectionWorkDoneName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long findingCategoryId) {
        this.categoryId = findingCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String findingCategoryName) {
        this.categoryName = findingCategoryName;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long findingSubCategoryId) {
        this.subCategoryId = findingSubCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String findingSubCategoryName) {
        this.subCategoryName = findingSubCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionFindingDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionFindingDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionFindingDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", condition='" + getCondition() + "'" +
            ", disclosedLastInspection='" + isDisclosedLastInspection() + "'" +
            ", causes='" + getCauses() + "'" +
            ", actionPlanCategory='" + getActionPlanCategory() + "'" +
            ", implication='" + getImplication() + "'" +
            ", isClosed='" + isIsClosed() + "'" +
            ", workDoneId=" + getWorkDoneId() +
            ", workDoneName='" + getWorkDoneName() + "'" +
            ", categoryId=" + getCategoryId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", subCategoryId=" + getSubCategoryId() +
            ", subCategoryName='" + getSubCategoryName() + "'" +
            "}";
    }
}
