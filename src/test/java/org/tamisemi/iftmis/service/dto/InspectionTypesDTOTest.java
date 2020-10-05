package org.tamisemi.iftmis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionTypesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionTypesDTO.class);
        InspectionTypesDTO inspectionTypesDTO1 = new InspectionTypesDTO();
        inspectionTypesDTO1.setId(1L);
        InspectionTypesDTO inspectionTypesDTO2 = new InspectionTypesDTO();
        assertThat(inspectionTypesDTO1).isNotEqualTo(inspectionTypesDTO2);
        inspectionTypesDTO2.setId(inspectionTypesDTO1.getId());
        assertThat(inspectionTypesDTO1).isEqualTo(inspectionTypesDTO2);
        inspectionTypesDTO2.setId(2L);
        assertThat(inspectionTypesDTO1).isNotEqualTo(inspectionTypesDTO2);
        inspectionTypesDTO1.setId(null);
        assertThat(inspectionTypesDTO1).isNotEqualTo(inspectionTypesDTO2);
    }
}
