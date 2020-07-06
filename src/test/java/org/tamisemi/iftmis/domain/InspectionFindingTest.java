package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionFindingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionFinding.class);
        InspectionFinding inspectionFinding1 = new InspectionFinding();
        inspectionFinding1.setId(1L);
        InspectionFinding inspectionFinding2 = new InspectionFinding();
        inspectionFinding2.setId(inspectionFinding1.getId());
        assertThat(inspectionFinding1).isEqualTo(inspectionFinding2);
        inspectionFinding2.setId(2L);
        assertThat(inspectionFinding1).isNotEqualTo(inspectionFinding2);
        inspectionFinding1.setId(null);
        assertThat(inspectionFinding1).isNotEqualTo(inspectionFinding2);
    }
}
