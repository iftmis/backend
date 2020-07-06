package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.ProcedureDTO;

/**
 * Mapper for the entity {@link Procedure} and its DTO {@link ProcedureDTO}.
 */
@Mapper(componentModel = "spring", uses = { IndicatorMapper.class })
public interface ProcedureMapper extends EntityMapper<ProcedureDTO, Procedure> {
    @Mapping(source = "indicator.id", target = "indicatorId")
    @Mapping(source = "indicator.name", target = "indicatorName")
    ProcedureDTO toDto(Procedure procedure);

    @Mapping(source = "indicatorId", target = "indicator")
    Procedure toEntity(ProcedureDTO procedureDTO);

    default Procedure fromId(Long id) {
        if (id == null) {
            return null;
        }
        Procedure procedure = new Procedure();
        procedure.setId(id);
        return procedure;
    }
}
