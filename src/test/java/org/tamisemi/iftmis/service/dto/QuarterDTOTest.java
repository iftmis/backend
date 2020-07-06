package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class QuarterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuarterDTO.class);
        QuarterDTO quarterDTO1 = new QuarterDTO();
        quarterDTO1.setId(1L);
        QuarterDTO quarterDTO2 = new QuarterDTO();
        assertThat(quarterDTO1).isNotEqualTo(quarterDTO2);
        quarterDTO2.setId(quarterDTO1.getId());
        assertThat(quarterDTO1).isEqualTo(quarterDTO2);
        quarterDTO2.setId(2L);
        assertThat(quarterDTO1).isNotEqualTo(quarterDTO2);
        quarterDTO1.setId(null);
        assertThat(quarterDTO1).isNotEqualTo(quarterDTO2);
    }
}
