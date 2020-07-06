package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class OrganisationUnitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganisationUnit.class);
        OrganisationUnit organisationUnit1 = new OrganisationUnit();
        organisationUnit1.setId(1L);
        OrganisationUnit organisationUnit2 = new OrganisationUnit();
        organisationUnit2.setId(organisationUnit1.getId());
        assertThat(organisationUnit1).isEqualTo(organisationUnit2);
        organisationUnit2.setId(2L);
        assertThat(organisationUnit1).isNotEqualTo(organisationUnit2);
        organisationUnit1.setId(null);
        assertThat(organisationUnit1).isNotEqualTo(organisationUnit2);
    }
}
