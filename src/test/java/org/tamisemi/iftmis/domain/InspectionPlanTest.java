package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionPlanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionPlan.class);
        InspectionPlan inspectionPlan1 = new InspectionPlan();
        inspectionPlan1.setId(1L);
        InspectionPlan inspectionPlan2 = new InspectionPlan();
        inspectionPlan2.setId(inspectionPlan1.getId());
        assertThat(inspectionPlan1).isEqualTo(inspectionPlan2);
        inspectionPlan2.setId(2L);
        assertThat(inspectionPlan1).isNotEqualTo(inspectionPlan2);
        inspectionPlan1.setId(null);
        assertThat(inspectionPlan1).isNotEqualTo(inspectionPlan2);
    }
}
