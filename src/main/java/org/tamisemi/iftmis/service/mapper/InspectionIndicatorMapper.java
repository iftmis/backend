package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionIndicatorDTO;

/**
 * Mapper for the entity {@link InspectionIndicator} and its DTO {@link InspectionIndicatorDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionSubAreaMapper.class, IndicatorMapper.class })
public interface InspectionIndicatorMapper extends EntityMapper<InspectionIndicatorDTO, InspectionIndicator> {
    @Mapping(source = "inspectionSubArea.id", target = "inspectionSubAreaId")
    @Mapping(source = "indicator.id", target = "indicatorId")
    @Mapping(source = "indicator.name", target = "indicatorName")
    InspectionIndicatorDTO toDto(InspectionIndicator inspectionIndicator);

    @Mapping(source = "inspectionSubAreaId", target = "inspectionSubArea")
    @Mapping(source = "indicatorId", target = "indicator")
    InspectionIndicator toEntity(InspectionIndicatorDTO inspectionIndicatorDTO);

    default InspectionIndicator fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionIndicator inspectionIndicator = new InspectionIndicator();
        inspectionIndicator.setId(id);
        return inspectionIndicator;
    }
}
