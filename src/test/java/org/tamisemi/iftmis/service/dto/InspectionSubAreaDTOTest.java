package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionSubAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionSubAreaDTO.class);
        InspectionSubAreaDTO inspectionSubAreaDTO1 = new InspectionSubAreaDTO();
        inspectionSubAreaDTO1.setId(1L);
        InspectionSubAreaDTO inspectionSubAreaDTO2 = new InspectionSubAreaDTO();
        assertThat(inspectionSubAreaDTO1).isNotEqualTo(inspectionSubAreaDTO2);
        inspectionSubAreaDTO2.setId(inspectionSubAreaDTO1.getId());
        assertThat(inspectionSubAreaDTO1).isEqualTo(inspectionSubAreaDTO2);
        inspectionSubAreaDTO2.setId(2L);
        assertThat(inspectionSubAreaDTO1).isNotEqualTo(inspectionSubAreaDTO2);
        inspectionSubAreaDTO1.setId(null);
        assertThat(inspectionSubAreaDTO1).isNotEqualTo(inspectionSubAreaDTO2);
    }
}
