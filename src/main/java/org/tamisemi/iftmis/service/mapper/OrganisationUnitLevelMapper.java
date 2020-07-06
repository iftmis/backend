package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.OrganisationUnitLevelDTO;

/**
 * Mapper for the entity {@link OrganisationUnitLevel} and its DTO {@link OrganisationUnitLevelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganisationUnitLevelMapper extends EntityMapper<OrganisationUnitLevelDTO, OrganisationUnitLevel> {
    default OrganisationUnitLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrganisationUnitLevel organisationUnitLevel = new OrganisationUnitLevel();
        organisationUnitLevel.setId(id);
        return organisationUnitLevel;
    }
}
