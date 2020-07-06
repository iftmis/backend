package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionProcedureTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionProcedure.class);
        InspectionProcedure inspectionProcedure1 = new InspectionProcedure();
        inspectionProcedure1.setId(1L);
        InspectionProcedure inspectionProcedure2 = new InspectionProcedure();
        inspectionProcedure2.setId(inspectionProcedure1.getId());
        assertThat(inspectionProcedure1).isEqualTo(inspectionProcedure2);
        inspectionProcedure2.setId(2L);
        assertThat(inspectionProcedure1).isNotEqualTo(inspectionProcedure2);
        inspectionProcedure1.setId(null);
        assertThat(inspectionProcedure1).isNotEqualTo(inspectionProcedure2);
    }
}
