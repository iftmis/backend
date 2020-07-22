package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionBudgetDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionBudgetDTO.class);
        InspectionBudgetDTO inspectionBudgetDTO1 = new InspectionBudgetDTO();
        inspectionBudgetDTO1.setId(1L);
        InspectionBudgetDTO inspectionBudgetDTO2 = new InspectionBudgetDTO();
        assertThat(inspectionBudgetDTO1).isNotEqualTo(inspectionBudgetDTO2);
        inspectionBudgetDTO2.setId(inspectionBudgetDTO1.getId());
        assertThat(inspectionBudgetDTO1).isEqualTo(inspectionBudgetDTO2);
        inspectionBudgetDTO2.setId(2L);
        assertThat(inspectionBudgetDTO1).isNotEqualTo(inspectionBudgetDTO2);
        inspectionBudgetDTO1.setId(null);
        assertThat(inspectionBudgetDTO1).isNotEqualTo(inspectionBudgetDTO2);
    }
}
