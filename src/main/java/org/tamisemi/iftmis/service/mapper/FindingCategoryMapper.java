package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FindingCategoryDTO;

/**
 * Mapper for the entity {@link FindingCategory} and its DTO {@link FindingCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FindingCategoryMapper extends EntityMapper<FindingCategoryDTO, FindingCategory> {
    default FindingCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        FindingCategory findingCategory = new FindingCategory();
        findingCategory.setId(id);
        return findingCategory;
    }
}
