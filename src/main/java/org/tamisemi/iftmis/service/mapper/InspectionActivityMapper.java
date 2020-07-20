package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionActivityDTO;

/**
 * Mapper for the entity {@link InspectionActivity} and its DTO {@link InspectionActivityDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        InspectionPlanMapper.class,
        ObjectiveMapper.class,
        AuditableAreaMapper.class,
        SubAreaMapper.class,
        RiskMapper.class,
        OrganisationUnitMapper.class,
    }
)
public interface InspectionActivityMapper extends EntityMapper<InspectionActivityDTO, InspectionActivity> {
    @Mapping(source = "inspectionPlan.id", target = "inspectionPlanId")
    @Mapping(source = "objective.id", target = "objectiveId")
    @Mapping(source = "objective.description", target = "objectiveName")
    @Mapping(source = "auditableArea.id", target = "auditableAreaId")
    @Mapping(source = "auditableArea.name", target = "auditableAreaName")
    @Mapping(source = "subArea.id", target = "subAreaId")
    @Mapping(source = "subArea.name", target = "subAreaName")
    @Mapping(target = "organisationUnits", source = "organisationUnits")
    @Mapping(target = "risks", source = "risks")
    InspectionActivityDTO toDto(InspectionActivity inspectionActivity);

    @Mapping(source = "inspectionPlanId", target = "inspectionPlan")
    @Mapping(source = "objectiveId", target = "objective")
    @Mapping(source = "auditableAreaId", target = "auditableArea")
    @Mapping(source = "subAreaId", target = "subArea")
    @Mapping(target = "removeRisk", ignore = true)
    @Mapping(target = "removeOrganisationUnits", ignore = true)
    @Mapping(target = "organisationUnits", source = "organisationUnits")
    @Mapping(target = "risks", source = "risks")
    InspectionActivity toEntity(InspectionActivityDTO inspectionActivityDTO);

    default InspectionActivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionActivity inspectionActivity = new InspectionActivity();
        inspectionActivity.setId(id);
        return inspectionActivity;
    }

    default String map(byte[] value) {
        return new String(value);
    }
}
