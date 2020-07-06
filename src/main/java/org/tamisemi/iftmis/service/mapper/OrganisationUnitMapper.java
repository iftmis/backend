package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.OrganisationUnitDTO;

/**
 * Mapper for the entity {@link OrganisationUnit} and its DTO {@link OrganisationUnitDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganisationUnitLevelMapper.class })
public interface OrganisationUnitMapper extends EntityMapper<OrganisationUnitDTO, OrganisationUnit> {
    @Mapping(source = "organisationUnitLevel.id", target = "organisationUnitLevelId")
    @Mapping(source = "organisationUnitLevel.name", target = "organisationUnitLevelName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    OrganisationUnitDTO toDto(OrganisationUnit organisationUnit);

    @Mapping(source = "organisationUnitLevelId", target = "organisationUnitLevel")
    @Mapping(source = "parentId", target = "parent")
    OrganisationUnit toEntity(OrganisationUnitDTO organisationUnitDTO);

    default OrganisationUnit fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrganisationUnit organisationUnit = new OrganisationUnit();
        organisationUnit.setId(id);
        return organisationUnit;
    }
}
