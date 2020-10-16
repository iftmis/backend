package org.tamisemi.iftmis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FundingManagementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundingManagementDTO.class);
        FundingManagementDTO fundingManagementDTO1 = new FundingManagementDTO();
        fundingManagementDTO1.setId(1L);
        FundingManagementDTO fundingManagementDTO2 = new FundingManagementDTO();
        assertThat(fundingManagementDTO1).isNotEqualTo(fundingManagementDTO2);
        fundingManagementDTO2.setId(fundingManagementDTO1.getId());
        assertThat(fundingManagementDTO1).isEqualTo(fundingManagementDTO2);
        fundingManagementDTO2.setId(2L);
        assertThat(fundingManagementDTO1).isNotEqualTo(fundingManagementDTO2);
        fundingManagementDTO1.setId(null);
        assertThat(fundingManagementDTO1).isNotEqualTo(fundingManagementDTO2);
    }
}
