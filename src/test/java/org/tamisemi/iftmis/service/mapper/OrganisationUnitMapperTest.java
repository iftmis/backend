package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrganisationUnitMapperTest {
    private OrganisationUnitMapper organisationUnitMapper;

    @BeforeEach
    public void setUp() {
        organisationUnitMapper = new OrganisationUnitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(organisationUnitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(organisationUnitMapper.fromId(null)).isNull();
    }
}
