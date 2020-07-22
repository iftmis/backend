package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FindingRecommendationDTO;

/**
 * Mapper for the entity {@link FindingRecommendation} and its DTO {@link FindingRecommendationDTO}.
 */
@Mapper(componentModel = "spring", uses = { FindingMapper.class })
public interface FindingRecommendationMapper extends EntityMapper<FindingRecommendationDTO, FindingRecommendation> {
    @Mapping(source = "finding.id", target = "findingId")
    @Mapping(source = "finding.code", target = "findingCode")
    @Mapping(source = "finding.description", target = "findingDescription")
    @Mapping(source = "findingResponses", target = "findingResponses")
    FindingRecommendationDTO toDto(FindingRecommendation findingRecommendation);

    @Mapping(source = "findingId", target = "finding")
    @Mapping(source = "findingResponses", target = "findingResponses")
    FindingRecommendation toEntity(FindingRecommendationDTO findingRecommendationDTO);

    default FindingRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        FindingRecommendation findingRecommendation = new FindingRecommendation();
        findingRecommendation.setId(id);
        return findingRecommendation;
    }
}
