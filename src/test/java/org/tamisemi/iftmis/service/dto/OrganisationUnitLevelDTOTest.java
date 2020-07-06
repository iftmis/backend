package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class OrganisationUnitLevelDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganisationUnitLevelDTO.class);
        OrganisationUnitLevelDTO organisationUnitLevelDTO1 = new OrganisationUnitLevelDTO();
        organisationUnitLevelDTO1.setId(1L);
        OrganisationUnitLevelDTO organisationUnitLevelDTO2 = new OrganisationUnitLevelDTO();
        assertThat(organisationUnitLevelDTO1).isNotEqualTo(organisationUnitLevelDTO2);
        organisationUnitLevelDTO2.setId(organisationUnitLevelDTO1.getId());
        assertThat(organisationUnitLevelDTO1).isEqualTo(organisationUnitLevelDTO2);
        organisationUnitLevelDTO2.setId(2L);
        assertThat(organisationUnitLevelDTO1).isNotEqualTo(organisationUnitLevelDTO2);
        organisationUnitLevelDTO1.setId(null);
        assertThat(organisationUnitLevelDTO1).isNotEqualTo(organisationUnitLevelDTO2);
    }
}
