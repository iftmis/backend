package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tamisemi.iftmis.domain.InspectionArea;
import org.tamisemi.iftmis.service.dto.InspectionAreaDTO;
import org.tamisemi.iftmis.service.dto.InspectionAreaWithObjectiveDTO;

/**
 * Mapper for the entity {@link InspectionArea} and its DTO {@link InspectionAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionMapper.class, AuditableAreaMapper.class, InspectionObjectiveMapper.class })
public interface InspectionAreaWithObjectiveMapper extends EntityMapper<InspectionAreaDTO, InspectionArea> {
    @Mapping(source = "inspection.id", target = "inspectionId")
    @Mapping(source = "auditableArea.id", target = "auditableAreaId")
    @Mapping(source = "auditableArea.name", target = "auditableAreaName")
    @Mapping(source = "inspectionObjectives", target = "inspectionObjectives")
    InspectionAreaWithObjectiveDTO toDto(InspectionArea inspectionArea);
}
