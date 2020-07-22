package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionSubAreaDTO;

/**
 * Mapper for the entity {@link InspectionSubArea} and its DTO {@link InspectionSubAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionAreaMapper.class, SubAreaMapper.class })
public interface InspectionSubAreaMapper extends EntityMapper<InspectionSubAreaDTO, InspectionSubArea> {
    @Mapping(source = "inspectionArea.id", target = "inspectionAreaId")
    @Mapping(source = "inspectionArea.name", target = "inspectionAreaName")
    @Mapping(source = "subArea.id", target = "subAreaId")
    @Mapping(source = "subArea.name", target = "subAreaName")
    InspectionSubAreaDTO toDto(InspectionSubArea inspectionSubArea);

    @Mapping(source = "inspectionAreaId", target = "inspectionArea")
    @Mapping(source = "subAreaId", target = "subArea")
    InspectionSubArea toEntity(InspectionSubAreaDTO inspectionSubAreaDTO);

    default InspectionSubArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionSubArea inspectionSubArea = new InspectionSubArea();
        inspectionSubArea.setId(id);
        return inspectionSubArea;
    }
}
