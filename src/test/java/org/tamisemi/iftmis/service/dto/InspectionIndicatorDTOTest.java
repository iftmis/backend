package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionIndicatorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionIndicatorDTO.class);
        InspectionIndicatorDTO inspectionIndicatorDTO1 = new InspectionIndicatorDTO();
        inspectionIndicatorDTO1.setId(1L);
        InspectionIndicatorDTO inspectionIndicatorDTO2 = new InspectionIndicatorDTO();
        assertThat(inspectionIndicatorDTO1).isNotEqualTo(inspectionIndicatorDTO2);
        inspectionIndicatorDTO2.setId(inspectionIndicatorDTO1.getId());
        assertThat(inspectionIndicatorDTO1).isEqualTo(inspectionIndicatorDTO2);
        inspectionIndicatorDTO2.setId(2L);
        assertThat(inspectionIndicatorDTO1).isNotEqualTo(inspectionIndicatorDTO2);
        inspectionIndicatorDTO1.setId(null);
        assertThat(inspectionIndicatorDTO1).isNotEqualTo(inspectionIndicatorDTO2);
    }
}
