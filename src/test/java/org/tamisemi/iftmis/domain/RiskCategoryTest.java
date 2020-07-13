package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskCategory.class);
        RiskCategory riskCategory1 = new RiskCategory();
        riskCategory1.setId(1L);
        RiskCategory riskCategory2 = new RiskCategory();
        riskCategory2.setId(riskCategory1.getId());
        assertThat(riskCategory1).isEqualTo(riskCategory2);
        riskCategory2.setId(2L);
        assertThat(riskCategory1).isNotEqualTo(riskCategory2);
        riskCategory1.setId(null);
        assertThat(riskCategory1).isNotEqualTo(riskCategory2);
    }
}
