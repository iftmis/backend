package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionAreaDTO.class);
        InspectionAreaDTO inspectionAreaDTO1 = new InspectionAreaDTO();
        inspectionAreaDTO1.setId(1L);
        InspectionAreaDTO inspectionAreaDTO2 = new InspectionAreaDTO();
        assertThat(inspectionAreaDTO1).isNotEqualTo(inspectionAreaDTO2);
        inspectionAreaDTO2.setId(inspectionAreaDTO1.getId());
        assertThat(inspectionAreaDTO1).isEqualTo(inspectionAreaDTO2);
        inspectionAreaDTO2.setId(2L);
        assertThat(inspectionAreaDTO1).isNotEqualTo(inspectionAreaDTO2);
        inspectionAreaDTO1.setId(null);
        assertThat(inspectionAreaDTO1).isNotEqualTo(inspectionAreaDTO2);
    }
}
