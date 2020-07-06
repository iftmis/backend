package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.AuditableAreaDTO;

/**
 * Mapper for the entity {@link AuditableArea} and its DTO {@link AuditableAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditableAreaMapper extends EntityMapper<AuditableAreaDTO, AuditableArea> {
    default AuditableArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditableArea auditableArea = new AuditableArea();
        auditableArea.setId(id);
        return auditableArea;
    }
}
