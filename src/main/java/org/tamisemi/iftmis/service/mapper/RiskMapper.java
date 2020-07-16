package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.RiskDTO;

/**
 * Mapper for the entity {@link Risk} and its DTO {@link RiskDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = { RiskRegisterMapper.class, ObjectiveMapper.class, RiskCategoryMapper.class, OrganisationUnitMapper.class }
)
public interface RiskMapper extends EntityMapper<RiskDTO, Risk> {
    @Mapping(source = "riskRegister.id", target = "riskRegisterId")
    @Mapping(source = "riskRegister.name", target = "riskRegisterName")
    @Mapping(source = "objective.id", target = "objectiveId")
    @Mapping(source = "objective.code", target = "objectiveCode")
    @Mapping(source = "objective.description", target = "objectiveDescription")
    @Mapping(source = "riskCategory.id", target = "riskCategoryId")
    @Mapping(source = "riskCategory.name", target = "riskCategoryName")
    @Mapping(source = "riskOwner.id", target = "riskOwnerId")
    @Mapping(source = "riskOwner.name", target = "riskOwnerName")
    @Mapping(source = "riskRatings", target = "riskRatings")
    RiskDTO toDto(Risk risk);

    @Mapping(source = "riskRegisterId", target = "riskRegister")
    @Mapping(source = "objectiveId", target = "objective")
    @Mapping(source = "riskCategoryId", target = "riskCategory")
    @Mapping(source = "riskOwnerId", target = "riskOwner")
    Risk toEntity(RiskDTO riskDTO);

    default Risk fromId(Long id) {
        if (id == null) {
            return null;
        }
        Risk risk = new Risk();
        risk.setId(id);
        return risk;
    }
}
