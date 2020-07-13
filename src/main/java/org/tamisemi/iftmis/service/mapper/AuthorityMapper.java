package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.Mapper;
import org.tamisemi.iftmis.domain.Authority;
import org.tamisemi.iftmis.service.dto.AuthorityDTO;

/**
 * Mapper for the entity {@link Authority} and its DTO {@link AuthorityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuthorityMapper extends EntityMapper<AuthorityDTO, Authority> {
    default Authority fromId(String name) {
        if (name == null) {
            return null;
        }
        Authority authority = new Authority();
        authority.setName(name);
        return authority;
    }
}
