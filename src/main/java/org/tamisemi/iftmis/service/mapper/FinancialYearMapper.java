package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FinancialYearDTO;

/**
 * Mapper for the entity {@link FinancialYear} and its DTO {@link FinancialYearDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FinancialYearMapper extends EntityMapper<FinancialYearDTO, FinancialYear> {
    default FinancialYear fromId(Long id) {
        if (id == null) {
            return null;
        }
        FinancialYear financialYear = new FinancialYear();
        financialYear.setId(id);
        return financialYear;
    }
}
