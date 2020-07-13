package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.RiskCategoryDTO;

/**
 * Mapper for the entity {@link RiskCategory} and its DTO {@link RiskCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RiskCategoryMapper extends EntityMapper<RiskCategoryDTO, RiskCategory> {
    default RiskCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        RiskCategory riskCategory = new RiskCategory();
        riskCategory.setId(id);
        return riskCategory;
    }
}
