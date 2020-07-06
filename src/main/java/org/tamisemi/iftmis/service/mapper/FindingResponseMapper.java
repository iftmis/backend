package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FindingResponseDTO;

/**
 * Mapper for the entity {@link FindingResponse} and its DTO {@link FindingResponseDTO}.
 */
@Mapper(componentModel = "spring", uses = { FindingRecommendationMapper.class })
public interface FindingResponseMapper extends EntityMapper<FindingResponseDTO, FindingResponse> {
    @Mapping(source = "recommendation.id", target = "recommendationId")
    @Mapping(source = "recommendation.description", target = "recommendationDescription")
    FindingResponseDTO toDto(FindingResponse findingResponse);

    @Mapping(source = "recommendationId", target = "recommendation")
    FindingResponse toEntity(FindingResponseDTO findingResponseDTO);

    default FindingResponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        FindingResponse findingResponse = new FindingResponse();
        findingResponse.setId(id);
        return findingResponse;
    }

    default String map(byte[] value) {
        return new String(value);
    }
}
