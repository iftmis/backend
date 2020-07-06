package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionWorkDoneDTO;

/**
 * Mapper for the entity {@link InspectionWorkDone} and its DTO {@link InspectionWorkDoneDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionProcedureMapper.class })
public interface InspectionWorkDoneMapper extends EntityMapper<InspectionWorkDoneDTO, InspectionWorkDone> {
    @Mapping(source = "procedure.id", target = "procedureId")
    @Mapping(source = "procedure.name", target = "procedureName")
    InspectionWorkDoneDTO toDto(InspectionWorkDone inspectionWorkDone);

    @Mapping(source = "procedureId", target = "procedure")
    InspectionWorkDone toEntity(InspectionWorkDoneDTO inspectionWorkDoneDTO);

    default InspectionWorkDone fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionWorkDone inspectionWorkDone = new InspectionWorkDone();
        inspectionWorkDone.setId(id);
        return inspectionWorkDone;
    }
}
