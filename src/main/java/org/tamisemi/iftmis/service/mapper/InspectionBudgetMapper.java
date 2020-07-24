package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionBudgetDTO;

/**
 * Mapper for the entity {@link InspectionBudget} and its DTO {@link InspectionBudgetDTO}.
 */
@Mapper(componentModel = "spring", uses = { GfsCodeMapper.class, InspectionMapper.class })
public interface InspectionBudgetMapper extends EntityMapper<InspectionBudgetDTO, InspectionBudget> {
    @Mapping(source = "gfsCode.id", target = "gfsCodeId")
    @Mapping(source = "gfsCode.description", target = "gfsCodeDescription")
    @Mapping(source = "inspection.id", target = "inspectionId")
    InspectionBudgetDTO toDto(InspectionBudget inspectionBudget);

    @Mapping(source = "gfsCodeId", target = "gfsCode")
    @Mapping(source = "inspectionId", target = "inspection")
    InspectionBudget toEntity(InspectionBudgetDTO inspectionBudgetDTO);

    default InspectionBudget fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionBudget inspectionBudget = new InspectionBudget();
        inspectionBudget.setId(id);
        return inspectionBudget;
    }

    default String map(byte[] value) {
        return new String(value);
    }
}
