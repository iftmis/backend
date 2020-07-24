package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionBudgetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionBudget.class);
        InspectionBudget inspectionBudget1 = new InspectionBudget();
        inspectionBudget1.setId(1L);
        InspectionBudget inspectionBudget2 = new InspectionBudget();
        inspectionBudget2.setId(inspectionBudget1.getId());
        assertThat(inspectionBudget1).isEqualTo(inspectionBudget2);
        inspectionBudget2.setId(2L);
        assertThat(inspectionBudget1).isNotEqualTo(inspectionBudget2);
        inspectionBudget1.setId(null);
        assertThat(inspectionBudget1).isNotEqualTo(inspectionBudget2);
    }
}
