package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

import org.tamisemi.iftmis.domain.FindingRecommendation;
import org.tamisemi.iftmis.domain.enumeration.ActionPlanCategory;
import org.tamisemi.iftmis.domain.enumeration.FindingSource;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.Finding} entity.
 */
@ApiModel(description = "The Finding(findings) entity.\n@author Chris")
public class FindingDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    private FindingSource source;

    private String code;

    private String description;

    @NotNull
    private ActionPlanCategory actionPlanCategory;

    private Boolean isClosed;

    private Long organisationUnitId;

    private String organisationUnitName;

    private Set<FindingRecommendation> findingRecommendations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FindingSource getSource() {
        return source;
    }

    public void setSource(FindingSource source) {
        this.source = source;
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

    public ActionPlanCategory getActionPlanCategory() {
        return actionPlanCategory;
    }

    public void setActionPlanCategory(ActionPlanCategory actionPlanCategory) {
        this.actionPlanCategory = actionPlanCategory;
    }

    public Boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
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

    public Set<FindingRecommendation> getFindingRecommendations() {
        return findingRecommendations;
    }

    public void setFindingRecommendations(Set<FindingRecommendation> findingRecommendations) {
        this.findingRecommendations = findingRecommendations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindingDTO)) {
            return false;
        }

        return id != null && id.equals(((FindingDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
