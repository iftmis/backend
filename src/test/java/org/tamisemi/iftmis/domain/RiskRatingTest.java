package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskRatingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskRating.class);
        RiskRating riskRating1 = new RiskRating();
        riskRating1.setId(1L);
        RiskRating riskRating2 = new RiskRating();
        riskRating2.setId(riskRating1.getId());
        assertThat(riskRating1).isEqualTo(riskRating2);
        riskRating2.setId(2L);
        assertThat(riskRating1).isNotEqualTo(riskRating2);
        riskRating1.setId(null);
        assertThat(riskRating1).isNotEqualTo(riskRating2);
    }
}
