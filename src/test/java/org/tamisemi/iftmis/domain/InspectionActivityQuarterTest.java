package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionActivityQuarterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionActivityQuarter.class);
        InspectionActivityQuarter inspectionActivityQuarter1 = new InspectionActivityQuarter();
        inspectionActivityQuarter1.setId(1L);
        InspectionActivityQuarter inspectionActivityQuarter2 = new InspectionActivityQuarter();
        inspectionActivityQuarter2.setId(inspectionActivityQuarter1.getId());
        assertThat(inspectionActivityQuarter1).isEqualTo(inspectionActivityQuarter2);
        inspectionActivityQuarter2.setId(2L);
        assertThat(inspectionActivityQuarter1).isNotEqualTo(inspectionActivityQuarter2);
        inspectionActivityQuarter1.setId(null);
        assertThat(inspectionActivityQuarter1).isNotEqualTo(inspectionActivityQuarter2);
    }
}
