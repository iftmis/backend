package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionObjectiveTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionObjective.class);
        InspectionObjective inspectionObjective1 = new InspectionObjective();
        inspectionObjective1.setId(1L);
        InspectionObjective inspectionObjective2 = new InspectionObjective();
        inspectionObjective2.setId(inspectionObjective1.getId());
        assertThat(inspectionObjective1).isEqualTo(inspectionObjective2);
        inspectionObjective2.setId(2L);
        assertThat(inspectionObjective1).isNotEqualTo(inspectionObjective2);
        inspectionObjective1.setId(null);
        assertThat(inspectionObjective1).isNotEqualTo(inspectionObjective2);
    }
}
