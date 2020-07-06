package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionFindingDTO;

/**
 * Mapper for the entity {@link InspectionFinding} and its DTO {@link InspectionFindingDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionWorkDoneMapper.class, FindingCategoryMapper.class, FindingSubCategoryMapper.class })
public interface InspectionFindingMapper extends EntityMapper<InspectionFindingDTO, InspectionFinding> {
    @Mapping(source = "workDone.id", target = "workDoneId")
    @Mapping(source = "workDone.name", target = "workDoneName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "subCategory.id", target = "subCategoryId")
    @Mapping(source = "subCategory.name", target = "subCategoryName")
    InspectionFindingDTO toDto(InspectionFinding inspectionFinding);

    @Mapping(source = "workDoneId", target = "workDone")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "subCategoryId", target = "subCategory")
    InspectionFinding toEntity(InspectionFindingDTO inspectionFindingDTO);

    default InspectionFinding fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionFinding inspectionFinding = new InspectionFinding();
        inspectionFinding.setId(id);
        return inspectionFinding;
    }
}
