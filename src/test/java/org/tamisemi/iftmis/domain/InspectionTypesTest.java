package org.tamisemi.iftmis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionTypes.class);
        InspectionTypes inspectionTypes1 = new InspectionTypes();
        inspectionTypes1.setId(1L);
        InspectionTypes inspectionTypes2 = new InspectionTypes();
        inspectionTypes2.setId(inspectionTypes1.getId());
        assertThat(inspectionTypes1).isEqualTo(inspectionTypes2);
        inspectionTypes2.setId(2L);
        assertThat(inspectionTypes1).isNotEqualTo(inspectionTypes2);
        inspectionTypes1.setId(null);
        assertThat(inspectionTypes1).isNotEqualTo(inspectionTypes2);
    }
}
