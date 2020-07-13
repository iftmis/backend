package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.ObjectiveDTO;

/**
 * Mapper for the entity {@link Objective} and its DTO {@link ObjectiveDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ObjectiveMapper extends EntityMapper<ObjectiveDTO, Objective> {
    default Objective fromId(Long id) {
        if (id == null) {
            return null;
        }
        Objective objective = new Objective();
        objective.setId(id);
        return objective;
    }
}
