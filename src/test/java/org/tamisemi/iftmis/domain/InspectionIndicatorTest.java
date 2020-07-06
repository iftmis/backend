package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionIndicatorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionIndicator.class);
        InspectionIndicator inspectionIndicator1 = new InspectionIndicator();
        inspectionIndicator1.setId(1L);
        InspectionIndicator inspectionIndicator2 = new InspectionIndicator();
        inspectionIndicator2.setId(inspectionIndicator1.getId());
        assertThat(inspectionIndicator1).isEqualTo(inspectionIndicator2);
        inspectionIndicator2.setId(2L);
        assertThat(inspectionIndicator1).isNotEqualTo(inspectionIndicator2);
        inspectionIndicator1.setId(null);
        assertThat(inspectionIndicator1).isNotEqualTo(inspectionIndicator2);
    }
}
