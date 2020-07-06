package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionProcedureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionProcedureDTO.class);
        InspectionProcedureDTO inspectionProcedureDTO1 = new InspectionProcedureDTO();
        inspectionProcedureDTO1.setId(1L);
        InspectionProcedureDTO inspectionProcedureDTO2 = new InspectionProcedureDTO();
        assertThat(inspectionProcedureDTO1).isNotEqualTo(inspectionProcedureDTO2);
        inspectionProcedureDTO2.setId(inspectionProcedureDTO1.getId());
        assertThat(inspectionProcedureDTO1).isEqualTo(inspectionProcedureDTO2);
        inspectionProcedureDTO2.setId(2L);
        assertThat(inspectionProcedureDTO1).isNotEqualTo(inspectionProcedureDTO2);
        inspectionProcedureDTO1.setId(null);
        assertThat(inspectionProcedureDTO1).isNotEqualTo(inspectionProcedureDTO2);
    }
}
