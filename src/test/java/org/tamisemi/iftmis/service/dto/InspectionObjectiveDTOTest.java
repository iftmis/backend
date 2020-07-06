package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionObjectiveDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionObjectiveDTO.class);
        InspectionObjectiveDTO inspectionObjectiveDTO1 = new InspectionObjectiveDTO();
        inspectionObjectiveDTO1.setId(1L);
        InspectionObjectiveDTO inspectionObjectiveDTO2 = new InspectionObjectiveDTO();
        assertThat(inspectionObjectiveDTO1).isNotEqualTo(inspectionObjectiveDTO2);
        inspectionObjectiveDTO2.setId(inspectionObjectiveDTO1.getId());
        assertThat(inspectionObjectiveDTO1).isEqualTo(inspectionObjectiveDTO2);
        inspectionObjectiveDTO2.setId(2L);
        assertThat(inspectionObjectiveDTO1).isNotEqualTo(inspectionObjectiveDTO2);
        inspectionObjectiveDTO1.setId(null);
        assertThat(inspectionObjectiveDTO1).isNotEqualTo(inspectionObjectiveDTO2);
    }
}
