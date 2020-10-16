package org.tamisemi.iftmis.service.mapper;


import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.TheClustersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TheClusters} and its DTO {@link TheClustersDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TheClustersMapper extends EntityMapper<TheClustersDTO, TheClusters> {



    default TheClusters fromId(Long id) {
        if (id == null) {
            return null;
        }
        TheClusters theClusters = new TheClusters();
        theClusters.setId(id);
        return theClusters;
    }
}
