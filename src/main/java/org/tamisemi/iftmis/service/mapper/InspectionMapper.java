package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionDTO;

/**
 * Mapper for the entity {@link Inspection} and its DTO {@link InspectionDTO}.
 */
@Mapper(componentModel = "spring", uses = { FinancialYearMapper.class, OrganisationUnitMapper.class })
public interface InspectionMapper extends EntityMapper<InspectionDTO, Inspection> {
    @Mapping(source = "financialYear.id", target = "financialYearId")
    @Mapping(source = "financialYear.name", target = "financialYearName")
    @Mapping(source = "organisationUnit.id", target = "organisationUnitId")
    @Mapping(source = "organisationUnit.name", target = "organisationUnitName")
    InspectionDTO toDto(Inspection inspection);

    @Mapping(source = "financialYearId", target = "financialYear")
    @Mapping(source = "organisationUnitId", target = "organisationUnit")
    Inspection toEntity(InspectionDTO inspectionDTO);

    default Inspection fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inspection inspection = new Inspection();
        inspection.setId(id);
        return inspection;
    }
}
