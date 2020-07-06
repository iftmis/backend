package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionMemberDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionMemberDTO.class);
        InspectionMemberDTO inspectionMemberDTO1 = new InspectionMemberDTO();
        inspectionMemberDTO1.setId(1L);
        InspectionMemberDTO inspectionMemberDTO2 = new InspectionMemberDTO();
        assertThat(inspectionMemberDTO1).isNotEqualTo(inspectionMemberDTO2);
        inspectionMemberDTO2.setId(inspectionMemberDTO1.getId());
        assertThat(inspectionMemberDTO1).isEqualTo(inspectionMemberDTO2);
        inspectionMemberDTO2.setId(2L);
        assertThat(inspectionMemberDTO1).isNotEqualTo(inspectionMemberDTO2);
        inspectionMemberDTO1.setId(null);
        assertThat(inspectionMemberDTO1).isNotEqualTo(inspectionMemberDTO2);
    }
}
