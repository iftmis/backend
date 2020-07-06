package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.QuarterDTO;

/**
 * Mapper for the entity {@link Quarter} and its DTO {@link QuarterDTO}.
 */
@Mapper(componentModel = "spring", uses = { FinancialYearMapper.class })
public interface QuarterMapper extends EntityMapper<QuarterDTO, Quarter> {
    @Mapping(source = "financialYear.id", target = "financialYearId")
    @Mapping(source = "financialYear.name", target = "financialYearName")
    QuarterDTO toDto(Quarter quarter);

    @Mapping(source = "financialYearId", target = "financialYear")
    Quarter toEntity(QuarterDTO quarterDTO);

    default Quarter fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quarter quarter = new Quarter();
        quarter.setId(id);
        return quarter;
    }
}
