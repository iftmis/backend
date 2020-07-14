package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskRegister.class);
        RiskRegister riskRegister1 = new RiskRegister();
        riskRegister1.setId(1L);
        RiskRegister riskRegister2 = new RiskRegister();
        riskRegister2.setId(riskRegister1.getId());
        assertThat(riskRegister1).isEqualTo(riskRegister2);
        riskRegister2.setId(2L);
        assertThat(riskRegister1).isNotEqualTo(riskRegister2);
        riskRegister1.setId(null);
        assertThat(riskRegister1).isNotEqualTo(riskRegister2);
    }
}
