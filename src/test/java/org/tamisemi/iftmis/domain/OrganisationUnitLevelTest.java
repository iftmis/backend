package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class OrganisationUnitLevelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganisationUnitLevel.class);
        OrganisationUnitLevel organisationUnitLevel1 = new OrganisationUnitLevel();
        organisationUnitLevel1.setId(1L);
        OrganisationUnitLevel organisationUnitLevel2 = new OrganisationUnitLevel();
        organisationUnitLevel2.setId(organisationUnitLevel1.getId());
        assertThat(organisationUnitLevel1).isEqualTo(organisationUnitLevel2);
        organisationUnitLevel2.setId(2L);
        assertThat(organisationUnitLevel1).isNotEqualTo(organisationUnitLevel2);
        organisationUnitLevel1.setId(null);
        assertThat(organisationUnitLevel1).isNotEqualTo(organisationUnitLevel2);
    }
}
