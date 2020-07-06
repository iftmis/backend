package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionAreaDTO;

/**
 * Mapper for the entity {@link InspectionArea} and its DTO {@link InspectionAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionMapper.class, AuditableAreaMapper.class })
public interface InspectionAreaMapper extends EntityMapper<InspectionAreaDTO, InspectionArea> {
    @Mapping(source = "inspection.id", target = "inspectionId")
    @Mapping(source = "auditableArea.id", target = "auditableAreaId")
    @Mapping(source = "auditableArea.name", target = "auditableAreaName")
    InspectionAreaDTO toDto(InspectionArea inspectionArea);

    @Mapping(source = "inspectionId", target = "inspection")
    @Mapping(source = "auditableAreaId", target = "auditableArea")
    InspectionArea toEntity(InspectionAreaDTO inspectionAreaDTO);

    default InspectionArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionArea inspectionArea = new InspectionArea();
        inspectionArea.setId(id);
        return inspectionArea;
    }
}
