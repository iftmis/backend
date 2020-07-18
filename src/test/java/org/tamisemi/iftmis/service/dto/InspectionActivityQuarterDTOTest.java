package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionActivityQuarterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionActivityQuarterDTO.class);
        InspectionActivityQuarterDTO inspectionActivityQuarterDTO1 = new InspectionActivityQuarterDTO();
        inspectionActivityQuarterDTO1.setId(1L);
        InspectionActivityQuarterDTO inspectionActivityQuarterDTO2 = new InspectionActivityQuarterDTO();
        assertThat(inspectionActivityQuarterDTO1).isNotEqualTo(inspectionActivityQuarterDTO2);
        inspectionActivityQuarterDTO2.setId(inspectionActivityQuarterDTO1.getId());
        assertThat(inspectionActivityQuarterDTO1).isEqualTo(inspectionActivityQuarterDTO2);
        inspectionActivityQuarterDTO2.setId(2L);
        assertThat(inspectionActivityQuarterDTO1).isNotEqualTo(inspectionActivityQuarterDTO2);
        inspectionActivityQuarterDTO1.setId(null);
        assertThat(inspectionActivityQuarterDTO1).isNotEqualTo(inspectionActivityQuarterDTO2);
    }
}
