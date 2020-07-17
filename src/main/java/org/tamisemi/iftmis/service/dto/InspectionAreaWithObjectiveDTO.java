package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionArea} entity.
 */
@ApiModel(description = "The InspectionAuditableArea(inspection_auditable_areas) entity.\n@author Chris")
public class InspectionAreaWithObjectiveDTO extends InspectionAreaDTO {
    private Set<InspectionObjectiveDTO> inspectionObjectives = new HashSet<>();

    public Set<InspectionObjectiveDTO> getInspectionObjectives() {
        return inspectionObjectives;
    }

    public void setInspectionObjectives(Set<InspectionObjectiveDTO> inspectionObjectives) {
        this.inspectionObjectives = inspectionObjectives;
    }
}
