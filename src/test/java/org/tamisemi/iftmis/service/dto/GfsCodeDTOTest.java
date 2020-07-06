package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class GfsCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GfsCodeDTO.class);
        GfsCodeDTO gfsCodeDTO1 = new GfsCodeDTO();
        gfsCodeDTO1.setId(1L);
        GfsCodeDTO gfsCodeDTO2 = new GfsCodeDTO();
        assertThat(gfsCodeDTO1).isNotEqualTo(gfsCodeDTO2);
        gfsCodeDTO2.setId(gfsCodeDTO1.getId());
        assertThat(gfsCodeDTO1).isEqualTo(gfsCodeDTO2);
        gfsCodeDTO2.setId(2L);
        assertThat(gfsCodeDTO1).isNotEqualTo(gfsCodeDTO2);
        gfsCodeDTO1.setId(null);
        assertThat(gfsCodeDTO1).isNotEqualTo(gfsCodeDTO2);
    }
}
