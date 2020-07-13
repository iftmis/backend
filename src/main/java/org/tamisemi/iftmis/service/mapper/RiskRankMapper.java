package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.RiskRankDTO;

/**
 * Mapper for the entity {@link RiskRank} and its DTO {@link RiskRankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RiskRankMapper extends EntityMapper<RiskRankDTO, RiskRank> {
    default RiskRank fromId(Long id) {
        if (id == null) {
            return null;
        }
        RiskRank riskRank = new RiskRank();
        riskRank.setId(id);
        return riskRank;
    }
}
