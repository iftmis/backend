package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskRankDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskRankDTO.class);
        RiskRankDTO riskRankDTO1 = new RiskRankDTO();
        riskRankDTO1.setId(1L);
        RiskRankDTO riskRankDTO2 = new RiskRankDTO();
        assertThat(riskRankDTO1).isNotEqualTo(riskRankDTO2);
        riskRankDTO2.setId(riskRankDTO1.getId());
        assertThat(riskRankDTO1).isEqualTo(riskRankDTO2);
        riskRankDTO2.setId(2L);
        assertThat(riskRankDTO1).isNotEqualTo(riskRankDTO2);
        riskRankDTO1.setId(null);
        assertThat(riskRankDTO1).isNotEqualTo(riskRankDTO2);
    }
}
