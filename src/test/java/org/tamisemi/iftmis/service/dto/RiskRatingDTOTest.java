package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskRatingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskRatingDTO.class);
        RiskRatingDTO riskRatingDTO1 = new RiskRatingDTO();
        riskRatingDTO1.setId(1L);
        RiskRatingDTO riskRatingDTO2 = new RiskRatingDTO();
        assertThat(riskRatingDTO1).isNotEqualTo(riskRatingDTO2);
        riskRatingDTO2.setId(riskRatingDTO1.getId());
        assertThat(riskRatingDTO1).isEqualTo(riskRatingDTO2);
        riskRatingDTO2.setId(2L);
        assertThat(riskRatingDTO1).isNotEqualTo(riskRatingDTO2);
        riskRatingDTO1.setId(null);
        assertThat(riskRatingDTO1).isNotEqualTo(riskRatingDTO2);
    }
}
