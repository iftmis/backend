package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionPlanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionPlanDTO.class);
        InspectionPlanDTO inspectionPlanDTO1 = new InspectionPlanDTO();
        inspectionPlanDTO1.setId(1L);
        InspectionPlanDTO inspectionPlanDTO2 = new InspectionPlanDTO();
        assertThat(inspectionPlanDTO1).isNotEqualTo(inspectionPlanDTO2);
        inspectionPlanDTO2.setId(inspectionPlanDTO1.getId());
        assertThat(inspectionPlanDTO1).isEqualTo(inspectionPlanDTO2);
        inspectionPlanDTO2.setId(2L);
        assertThat(inspectionPlanDTO1).isNotEqualTo(inspectionPlanDTO2);
        inspectionPlanDTO1.setId(null);
        assertThat(inspectionPlanDTO1).isNotEqualTo(inspectionPlanDTO2);
    }
}
