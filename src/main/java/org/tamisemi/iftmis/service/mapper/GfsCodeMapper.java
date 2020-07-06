package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.GfsCodeDTO;

/**
 * Mapper for the entity {@link GfsCode} and its DTO {@link GfsCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GfsCodeMapper extends EntityMapper<GfsCodeDTO, GfsCode> {
    default GfsCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        GfsCode gfsCode = new GfsCode();
        gfsCode.setId(id);
        return gfsCode;
    }
}
