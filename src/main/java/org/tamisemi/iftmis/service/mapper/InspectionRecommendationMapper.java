package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionRecommendationDTO;

/**
 * Mapper for the entity {@link InspectionRecommendation} and its DTO {@link InspectionRecommendationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InspectionRecommendationMapper extends EntityMapper<InspectionRecommendationDTO, InspectionRecommendation> {
    default InspectionRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionRecommendation inspectionRecommendation = new InspectionRecommendation();
        inspectionRecommendation.setId(id);
        return inspectionRecommendation;
    }
}
