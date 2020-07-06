package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionMemberTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionMember.class);
        InspectionMember inspectionMember1 = new InspectionMember();
        inspectionMember1.setId(1L);
        InspectionMember inspectionMember2 = new InspectionMember();
        inspectionMember2.setId(inspectionMember1.getId());
        assertThat(inspectionMember1).isEqualTo(inspectionMember2);
        inspectionMember2.setId(2L);
        assertThat(inspectionMember1).isNotEqualTo(inspectionMember2);
        inspectionMember1.setId(null);
        assertThat(inspectionMember1).isNotEqualTo(inspectionMember2);
    }
}
