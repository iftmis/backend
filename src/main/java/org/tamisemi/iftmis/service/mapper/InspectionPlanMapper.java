package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionPlanDTO;

/**
 * Mapper for the entity {@link InspectionPlan} and its DTO {@link InspectionPlanDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganisationUnitMapper.class, FinancialYearMapper.class })
public interface InspectionPlanMapper extends EntityMapper<InspectionPlanDTO, InspectionPlan> {
    @Mapping(source = "organisationUnit.id", target = "organisationUnitId")
    @Mapping(source = "organisationUnit.name", target = "organisationUnitName")
    @Mapping(source = "financialYear.id", target = "financialYearId")
    @Mapping(source = "financialYear.name", target = "financialYearName")
    InspectionPlanDTO toDto(InspectionPlan inspectionPlan);

    @Mapping(source = "organisationUnitId", target = "organisationUnit")
    @Mapping(source = "financialYearId", target = "financialYear")
    InspectionPlan toEntity(InspectionPlanDTO inspectionPlanDTO);

    default InspectionPlan fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionPlan inspectionPlan = new InspectionPlan();
        inspectionPlan.setId(id);
        return inspectionPlan;
    }
}
