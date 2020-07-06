package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionArea.class);
        InspectionArea inspectionArea1 = new InspectionArea();
        inspectionArea1.setId(1L);
        InspectionArea inspectionArea2 = new InspectionArea();
        inspectionArea2.setId(inspectionArea1.getId());
        assertThat(inspectionArea1).isEqualTo(inspectionArea2);
        inspectionArea2.setId(2L);
        assertThat(inspectionArea1).isNotEqualTo(inspectionArea2);
        inspectionArea1.setId(null);
        assertThat(inspectionArea1).isNotEqualTo(inspectionArea2);
    }
}
