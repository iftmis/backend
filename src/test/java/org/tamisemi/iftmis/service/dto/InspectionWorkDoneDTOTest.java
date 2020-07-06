package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionWorkDoneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionWorkDoneDTO.class);
        InspectionWorkDoneDTO inspectionWorkDoneDTO1 = new InspectionWorkDoneDTO();
        inspectionWorkDoneDTO1.setId(1L);
        InspectionWorkDoneDTO inspectionWorkDoneDTO2 = new InspectionWorkDoneDTO();
        assertThat(inspectionWorkDoneDTO1).isNotEqualTo(inspectionWorkDoneDTO2);
        inspectionWorkDoneDTO2.setId(inspectionWorkDoneDTO1.getId());
        assertThat(inspectionWorkDoneDTO1).isEqualTo(inspectionWorkDoneDTO2);
        inspectionWorkDoneDTO2.setId(2L);
        assertThat(inspectionWorkDoneDTO1).isNotEqualTo(inspectionWorkDoneDTO2);
        inspectionWorkDoneDTO1.setId(null);
        assertThat(inspectionWorkDoneDTO1).isNotEqualTo(inspectionWorkDoneDTO2);
    }
}
