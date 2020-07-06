package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class OrganisationUnitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganisationUnitDTO.class);
        OrganisationUnitDTO organisationUnitDTO1 = new OrganisationUnitDTO();
        organisationUnitDTO1.setId(1L);
        OrganisationUnitDTO organisationUnitDTO2 = new OrganisationUnitDTO();
        assertThat(organisationUnitDTO1).isNotEqualTo(organisationUnitDTO2);
        organisationUnitDTO2.setId(organisationUnitDTO1.getId());
        assertThat(organisationUnitDTO1).isEqualTo(organisationUnitDTO2);
        organisationUnitDTO2.setId(2L);
        assertThat(organisationUnitDTO1).isNotEqualTo(organisationUnitDTO2);
        organisationUnitDTO1.setId(null);
        assertThat(organisationUnitDTO1).isNotEqualTo(organisationUnitDTO2);
    }
}
