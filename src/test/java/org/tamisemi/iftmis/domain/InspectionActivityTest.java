package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionActivityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionActivity.class);
        InspectionActivity inspectionActivity1 = new InspectionActivity();
        inspectionActivity1.setId(1L);
        InspectionActivity inspectionActivity2 = new InspectionActivity();
        inspectionActivity2.setId(inspectionActivity1.getId());
        assertThat(inspectionActivity1).isEqualTo(inspectionActivity2);
        inspectionActivity2.setId(2L);
        assertThat(inspectionActivity1).isNotEqualTo(inspectionActivity2);
        inspectionActivity1.setId(null);
        assertThat(inspectionActivity1).isNotEqualTo(inspectionActivity2);
    }
}
