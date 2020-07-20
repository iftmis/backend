package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionActivityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionActivityDTO.class);
        InspectionActivityDTO inspectionActivityDTO1 = new InspectionActivityDTO();
        inspectionActivityDTO1.setId(1L);
        InspectionActivityDTO inspectionActivityDTO2 = new InspectionActivityDTO();
        assertThat(inspectionActivityDTO1).isNotEqualTo(inspectionActivityDTO2);
        inspectionActivityDTO2.setId(inspectionActivityDTO1.getId());
        assertThat(inspectionActivityDTO1).isEqualTo(inspectionActivityDTO2);
        inspectionActivityDTO2.setId(2L);
        assertThat(inspectionActivityDTO1).isNotEqualTo(inspectionActivityDTO2);
        inspectionActivityDTO1.setId(null);
        assertThat(inspectionActivityDTO1).isNotEqualTo(inspectionActivityDTO2);
    }
}
