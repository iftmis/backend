package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.SubAreaDTO;

/**
 * Mapper for the entity {@link SubArea} and its DTO {@link SubAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = { AuditableAreaMapper.class })
public interface SubAreaMapper extends EntityMapper<SubAreaDTO, SubArea> {
    @Mapping(source = "area.id", target = "areaId")
    @Mapping(source = "area.name", target = "areaName")
    SubAreaDTO toDto(SubArea subArea);

    @Mapping(source = "areaId", target = "area")
    SubArea toEntity(SubAreaDTO subAreaDTO);

    default SubArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubArea subArea = new SubArea();
        subArea.setId(id);
        return subArea;
    }
}
