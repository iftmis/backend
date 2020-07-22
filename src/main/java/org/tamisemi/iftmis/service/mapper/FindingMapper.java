package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FindingDTO;

/**
 * Mapper for the entity {@link Finding} and its DTO {@link FindingDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganisationUnitMapper.class })
public interface FindingMapper extends EntityMapper<FindingDTO, Finding> {
    @Mapping(source = "organisationUnit.id", target = "organisationUnitId")
    @Mapping(source = "organisationUnit.name", target = "organisationUnitName")
    @Mapping(source = "findingRecommendations", target = "findingRecommendations")
    FindingDTO toDto(Finding finding);

    @Mapping(source = "organisationUnitId", target = "organisationUnit")
    @Mapping(source = "findingRecommendations", target = "findingRecommendations")
    Finding toEntity(FindingDTO findingDTO);

    default Finding fromId(Long id) {
        if (id == null) {
            return null;
        }
        Finding finding = new Finding();
        finding.setId(id);
        return finding;
    }
}
