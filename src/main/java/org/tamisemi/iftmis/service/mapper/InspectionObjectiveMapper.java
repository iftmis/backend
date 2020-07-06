package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionObjectiveDTO;

/**
 * Mapper for the entity {@link InspectionObjective} and its DTO {@link InspectionObjectiveDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionAreaMapper.class })
public interface InspectionObjectiveMapper extends EntityMapper<InspectionObjectiveDTO, InspectionObjective> {
    @Mapping(source = "inspectionArea.id", target = "inspectionAreaId")
    @Mapping(source = "inspectionArea.name", target = "inspectionAreaName")
    InspectionObjectiveDTO toDto(InspectionObjective inspectionObjective);

    @Mapping(source = "inspectionAreaId", target = "inspectionArea")
    InspectionObjective toEntity(InspectionObjectiveDTO inspectionObjectiveDTO);

    default InspectionObjective fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionObjective inspectionObjective = new InspectionObjective();
        inspectionObjective.setId(id);
        return inspectionObjective;
    }
}
