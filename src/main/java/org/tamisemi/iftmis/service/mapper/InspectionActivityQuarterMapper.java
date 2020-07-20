package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionActivityQuarterDTO;

/**
 * Mapper for the entity {@link InspectionActivityQuarter} and its DTO {@link InspectionActivityQuarterDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionActivityMapper.class, QuarterMapper.class })
public interface InspectionActivityQuarterMapper extends EntityMapper<InspectionActivityQuarterDTO, InspectionActivityQuarter> {
    @Mapping(source = "activity.id", target = "activityId")
    @Mapping(source = "quarter.id", target = "quarterId")
    @Mapping(source = "quarter.name", target = "quarterName")
    InspectionActivityQuarterDTO toDto(InspectionActivityQuarter inspectionActivityQuarter);

    @Mapping(source = "activityId", target = "activity")
    @Mapping(source = "quarterId", target = "quarter")
    InspectionActivityQuarter toEntity(InspectionActivityQuarterDTO inspectionActivityQuarterDTO);

    default InspectionActivityQuarter fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionActivityQuarter inspectionActivityQuarter = new InspectionActivityQuarter();
        inspectionActivityQuarter.setId(id);
        return inspectionActivityQuarter;
    }
}
