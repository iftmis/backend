package org.tamisemi.iftmis.service.mapper;


import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InspectionTypes} and its DTO {@link InspectionTypesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InspectionTypesMapper extends EntityMapper<InspectionTypesDTO, InspectionTypes> {



    default InspectionTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionTypes inspectionTypes = new InspectionTypes();
        inspectionTypes.setId(id);
        return inspectionTypes;
    }
}
