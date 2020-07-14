package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class RiskCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskCategoryDTO.class);
        RiskCategoryDTO riskCategoryDTO1 = new RiskCategoryDTO();
        riskCategoryDTO1.setId(1L);
        RiskCategoryDTO riskCategoryDTO2 = new RiskCategoryDTO();
        assertThat(riskCategoryDTO1).isNotEqualTo(riskCategoryDTO2);
        riskCategoryDTO2.setId(riskCategoryDTO1.getId());
        assertThat(riskCategoryDTO1).isEqualTo(riskCategoryDTO2);
        riskCategoryDTO2.setId(2L);
        assertThat(riskCategoryDTO1).isNotEqualTo(riskCategoryDTO2);
        riskCategoryDTO1.setId(null);
        assertThat(riskCategoryDTO1).isNotEqualTo(riskCategoryDTO2);
    }
}
