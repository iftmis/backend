package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionArea} entity.
 */
@ApiModel(description = "The InspectionAuditableArea(inspection_auditable_areas) entity.\n@author Chris")
public class InspectionAreaWithSubAreaDTO extends InspectionAreaDTO {
    private Set<InspectionSubAreaDTO> inspectionSubAreas = new HashSet<>();

    public Set<InspectionSubAreaDTO> getInspectionSubAreas() {
        return inspectionSubAreas;
    }

    public void setInspectionSubAreas(Set<InspectionSubAreaDTO> inspectionSubAreas) {
        this.inspectionSubAreas = inspectionSubAreas;
    }
}
