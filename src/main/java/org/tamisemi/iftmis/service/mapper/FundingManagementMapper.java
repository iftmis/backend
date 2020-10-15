package org.tamisemi.iftmis.service.mapper;


import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FundingManagementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FundingManagement} and its DTO {@link FundingManagementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FundingManagementMapper extends EntityMapper<FundingManagementDTO, FundingManagement> {



    default FundingManagement fromId(Long id) {
        if (id == null) {
            return null;
        }
        FundingManagement fundingManagement = new FundingManagement();
        fundingManagement.setId(id);
        return fundingManagement;
    }
}
