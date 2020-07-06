package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionProcedureDTO;

/**
 * Mapper for the entity {@link InspectionProcedure} and its DTO {@link InspectionProcedureDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionIndicatorMapper.class, ProcedureMapper.class })
public interface InspectionProcedureMapper extends EntityMapper<InspectionProcedureDTO, InspectionProcedure> {
    @Mapping(source = "inspectionIndicator.id", target = "inspectionIndicatorId")
    @Mapping(source = "inspectionIndicator.name", target = "inspectionIndicatorName")
    @Mapping(source = "procedure.id", target = "procedureId")
    @Mapping(source = "procedure.name", target = "procedureName")
    InspectionProcedureDTO toDto(InspectionProcedure inspectionProcedure);

    @Mapping(source = "inspectionIndicatorId", target = "inspectionIndicator")
    @Mapping(source = "procedureId", target = "procedure")
    InspectionProcedure toEntity(InspectionProcedureDTO inspectionProcedureDTO);

    default InspectionProcedure fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionProcedure inspectionProcedure = new InspectionProcedure();
        inspectionProcedure.setId(id);
        return inspectionProcedure;
    }
}
