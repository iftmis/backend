package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionWorkDoneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionWorkDone.class);
        InspectionWorkDone inspectionWorkDone1 = new InspectionWorkDone();
        inspectionWorkDone1.setId(1L);
        InspectionWorkDone inspectionWorkDone2 = new InspectionWorkDone();
        inspectionWorkDone2.setId(inspectionWorkDone1.getId());
        assertThat(inspectionWorkDone1).isEqualTo(inspectionWorkDone2);
        inspectionWorkDone2.setId(2L);
        assertThat(inspectionWorkDone1).isNotEqualTo(inspectionWorkDone2);
        inspectionWorkDone1.setId(null);
        assertThat(inspectionWorkDone1).isNotEqualTo(inspectionWorkDone2);
    }
}
