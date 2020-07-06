package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionDTO.class);
        InspectionDTO inspectionDTO1 = new InspectionDTO();
        inspectionDTO1.setId(1L);
        InspectionDTO inspectionDTO2 = new InspectionDTO();
        assertThat(inspectionDTO1).isNotEqualTo(inspectionDTO2);
        inspectionDTO2.setId(inspectionDTO1.getId());
        assertThat(inspectionDTO1).isEqualTo(inspectionDTO2);
        inspectionDTO2.setId(2L);
        assertThat(inspectionDTO1).isNotEqualTo(inspectionDTO2);
        inspectionDTO1.setId(null);
        assertThat(inspectionDTO1).isNotEqualTo(inspectionDTO2);
    }
}
