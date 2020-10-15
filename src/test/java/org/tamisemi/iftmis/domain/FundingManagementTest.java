package org.tamisemi.iftmis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FundingManagementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundingManagement.class);
        FundingManagement fundingManagement1 = new FundingManagement();
        fundingManagement1.setId(1L);
        FundingManagement fundingManagement2 = new FundingManagement();
        fundingManagement2.setId(fundingManagement1.getId());
        assertThat(fundingManagement1).isEqualTo(fundingManagement2);
        fundingManagement2.setId(2L);
        assertThat(fundingManagement1).isNotEqualTo(fundingManagement2);
        fundingManagement1.setId(null);
        assertThat(fundingManagement1).isNotEqualTo(fundingManagement2);
    }
}
