package org.tamisemi.iftmis.service.mapper;


import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FundingManagementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FundingManagement} and its DTO {@link FundingManagementDTO}.
 */
@Mapper(componentModel = "spring", uses = {TheClustersMapper.class, FindingSubCategoryMapper.class, FinancialYearMapper.class, OrganisationUnitLevelMapper.class})
public interface FundingManagementMapper extends EntityMapper<FundingManagementDTO, FundingManagement> {

    @Mapping(source = "theClusters.id", target = "theClustersId")
    @Mapping(source = "theClusters.name", target = "theClustersName")
    @Mapping(source = "findingSubCategory.id", target = "findingSubCategoryId")
    @Mapping(source = "findingSubCategory.name", target = "findingSubCategoryName")
    @Mapping(source = "financialYear.id", target = "financialYearId")
    @Mapping(source = "financialYear.name", target = "financialYearName")
    @Mapping(source = "organisationUnitLevel.id", target = "organisationUnitLevelId")
    @Mapping(source = "organisationUnitLevel.name", target = "organisationUnitLevelName")
    FundingManagementDTO toDto(FundingManagement fundingManagement);

    @Mapping(source = "theClustersId", target = "theClusters")
    @Mapping(source = "findingSubCategoryId", target = "findingSubCategory")
    @Mapping(source = "financialYearId", target = "financialYear")
    @Mapping(source = "organisationUnitLevelId", target = "organisationUnitLevel")
    FundingManagement toEntity(FundingManagementDTO fundingManagementDTO);

    default FundingManagement fromId(Long id) {
        if (id == null) {
            return null;
        }
        FundingManagement fundingManagement = new FundingManagement();
        fundingManagement.setId(id);
        return fundingManagement;
    }
}
