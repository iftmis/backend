package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.RiskRegisterDTO;

/**
 * Mapper for the entity {@link RiskRegister} and its DTO {@link RiskRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrganisationUnitMapper.class, FinancialYearMapper.class })
public interface RiskRegisterMapper extends EntityMapper<RiskRegisterDTO, RiskRegister> {
    @Mapping(source = "organisationUnit.id", target = "organisationUnitId")
    @Mapping(source = "organisationUnit.name", target = "organisationUnitName")
    @Mapping(source = "financialYear.id", target = "financialYearId")
    @Mapping(source = "financialYear.name", target = "financialYearName")
    RiskRegisterDTO toDto(RiskRegister riskRegister);

    @Mapping(source = "organisationUnitId", target = "organisationUnit")
    @Mapping(source = "financialYearId", target = "financialYear")
    RiskRegister toEntity(RiskRegisterDTO riskRegisterDTO);

    default RiskRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        RiskRegister riskRegister = new RiskRegister();
        riskRegister.setId(id);
        return riskRegister;
    }
}
