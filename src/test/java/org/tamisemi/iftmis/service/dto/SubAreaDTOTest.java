package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class SubAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubAreaDTO.class);
        SubAreaDTO subAreaDTO1 = new SubAreaDTO();
        subAreaDTO1.setId(1L);
        SubAreaDTO subAreaDTO2 = new SubAreaDTO();
        assertThat(subAreaDTO1).isNotEqualTo(subAreaDTO2);
        subAreaDTO2.setId(subAreaDTO1.getId());
        assertThat(subAreaDTO1).isEqualTo(subAreaDTO2);
        subAreaDTO2.setId(2L);
        assertThat(subAreaDTO1).isNotEqualTo(subAreaDTO2);
        subAreaDTO1.setId(null);
        assertThat(subAreaDTO1).isNotEqualTo(subAreaDTO2);
    }
}
