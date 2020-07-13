package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskRegisterDTO.class);
        RiskRegisterDTO riskRegisterDTO1 = new RiskRegisterDTO();
        riskRegisterDTO1.setId(1L);
        RiskRegisterDTO riskRegisterDTO2 = new RiskRegisterDTO();
        assertThat(riskRegisterDTO1).isNotEqualTo(riskRegisterDTO2);
        riskRegisterDTO2.setId(riskRegisterDTO1.getId());
        assertThat(riskRegisterDTO1).isEqualTo(riskRegisterDTO2);
        riskRegisterDTO2.setId(2L);
        assertThat(riskRegisterDTO1).isNotEqualTo(riskRegisterDTO2);
        riskRegisterDTO1.setId(null);
        assertThat(riskRegisterDTO1).isNotEqualTo(riskRegisterDTO2);
    }
}
