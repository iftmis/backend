package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrganisationUnitLevelMapperTest {
    private OrganisationUnitLevelMapper organisationUnitLevelMapper;

    @BeforeEach
    public void setUp() {
        organisationUnitLevelMapper = new OrganisationUnitLevelMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(organisationUnitLevelMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(organisationUnitLevelMapper.fromId(null)).isNull();
    }
}
