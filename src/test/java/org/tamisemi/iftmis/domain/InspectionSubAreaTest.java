package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionSubAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionSubArea.class);
        InspectionSubArea inspectionSubArea1 = new InspectionSubArea();
        inspectionSubArea1.setId(1L);
        InspectionSubArea inspectionSubArea2 = new InspectionSubArea();
        inspectionSubArea2.setId(inspectionSubArea1.getId());
        assertThat(inspectionSubArea1).isEqualTo(inspectionSubArea2);
        inspectionSubArea2.setId(2L);
        assertThat(inspectionSubArea1).isNotEqualTo(inspectionSubArea2);
        inspectionSubArea1.setId(null);
        assertThat(inspectionSubArea1).isNotEqualTo(inspectionSubArea2);
    }
}
