package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.RiskRatingDTO;

/**
 * Mapper for the entity {@link RiskRating} and its DTO {@link RiskRatingDTO}.
 */
@Mapper(componentModel = "spring", uses = { RiskMapper.class })
public interface RiskRatingMapper extends EntityMapper<RiskRatingDTO, RiskRating> {
    @Mapping(source = "risk.id", target = "riskId")
    RiskRatingDTO toDto(RiskRating riskRating);

    @Mapping(source = "riskId", target = "risk")
    RiskRating toEntity(RiskRatingDTO riskRatingDTO);

    default RiskRating fromId(Long id) {
        if (id == null) {
            return null;
        }
        RiskRating riskRating = new RiskRating();
        riskRating.setId(id);
        return riskRating;
    }
}
