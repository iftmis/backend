package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FindingSubCategoryDTO;

/**
 * Mapper for the entity {@link FindingSubCategory} and its DTO {@link FindingSubCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FindingSubCategoryMapper extends EntityMapper<FindingSubCategoryDTO, FindingSubCategory> {
    default FindingSubCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        FindingSubCategory findingSubCategory = new FindingSubCategory();
        findingSubCategory.setId(id);
        return findingSubCategory;
    }
}
