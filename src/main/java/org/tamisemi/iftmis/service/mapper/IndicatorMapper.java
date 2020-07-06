package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.IndicatorDTO;

/**
 * Mapper for the entity {@link Indicator} and its DTO {@link IndicatorDTO}.
 */
@Mapper(componentModel = "spring", uses = { AuditableAreaMapper.class })
public interface IndicatorMapper extends EntityMapper<IndicatorDTO, Indicator> {
    @Mapping(source = "subArea.id", target = "subAreaId")
    @Mapping(source = "subArea.name", target = "subAreaName")
    IndicatorDTO toDto(Indicator indicator);

    @Mapping(source = "subAreaId", target = "subArea")
    Indicator toEntity(IndicatorDTO indicatorDTO);

    default Indicator fromId(Long id) {
        if (id == null) {
            return null;
        }
        Indicator indicator = new Indicator();
        indicator.setId(id);
        return indicator;
    }
}
