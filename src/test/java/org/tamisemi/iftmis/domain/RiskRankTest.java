package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskRankTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskRank.class);
        RiskRank riskRank1 = new RiskRank();
        riskRank1.setId(1L);
        RiskRank riskRank2 = new RiskRank();
        riskRank2.setId(riskRank1.getId());
        assertThat(riskRank1).isEqualTo(riskRank2);
        riskRank2.setId(2L);
        assertThat(riskRank1).isNotEqualTo(riskRank2);
        riskRank1.setId(null);
        assertThat(riskRank1).isNotEqualTo(riskRank2);
    }
}
