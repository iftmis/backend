package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FinancialYearDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialYearDTO.class);
        FinancialYearDTO financialYearDTO1 = new FinancialYearDTO();
        financialYearDTO1.setId(1L);
        FinancialYearDTO financialYearDTO2 = new FinancialYearDTO();
        assertThat(financialYearDTO1).isNotEqualTo(financialYearDTO2);
        financialYearDTO2.setId(financialYearDTO1.getId());
        assertThat(financialYearDTO1).isEqualTo(financialYearDTO2);
        financialYearDTO2.setId(2L);
        assertThat(financialYearDTO1).isNotEqualTo(financialYearDTO2);
        financialYearDTO1.setId(null);
        assertThat(financialYearDTO1).isNotEqualTo(financialYearDTO2);
    }
}
