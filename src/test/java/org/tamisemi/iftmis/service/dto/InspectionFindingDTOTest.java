package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionFindingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionFindingDTO.class);
        InspectionFindingDTO inspectionFindingDTO1 = new InspectionFindingDTO();
        inspectionFindingDTO1.setId(1L);
        InspectionFindingDTO inspectionFindingDTO2 = new InspectionFindingDTO();
        assertThat(inspectionFindingDTO1).isNotEqualTo(inspectionFindingDTO2);
        inspectionFindingDTO2.setId(inspectionFindingDTO1.getId());
        assertThat(inspectionFindingDTO1).isEqualTo(inspectionFindingDTO2);
        inspectionFindingDTO2.setId(2L);
        assertThat(inspectionFindingDTO1).isNotEqualTo(inspectionFindingDTO2);
        inspectionFindingDTO1.setId(null);
        assertThat(inspectionFindingDTO1).isNotEqualTo(inspectionFindingDTO2);
    }
}
