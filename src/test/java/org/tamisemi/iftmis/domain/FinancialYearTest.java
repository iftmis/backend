package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FinancialYearTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialYear.class);
        FinancialYear financialYear1 = new FinancialYear();
        financialYear1.setId(1L);
        FinancialYear financialYear2 = new FinancialYear();
        financialYear2.setId(financialYear1.getId());
        assertThat(financialYear1).isEqualTo(financialYear2);
        financialYear2.setId(2L);
        assertThat(financialYear1).isNotEqualTo(financialYear2);
        financialYear1.setId(null);
        assertThat(financialYear1).isNotEqualTo(financialYear2);
    }
}
