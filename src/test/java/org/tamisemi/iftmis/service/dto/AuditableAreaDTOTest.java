package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class AuditableAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditableAreaDTO.class);
        AuditableAreaDTO auditableAreaDTO1 = new AuditableAreaDTO();
        auditableAreaDTO1.setId(1L);
        AuditableAreaDTO auditableAreaDTO2 = new AuditableAreaDTO();
        assertThat(auditableAreaDTO1).isNotEqualTo(auditableAreaDTO2);
        auditableAreaDTO2.setId(auditableAreaDTO1.getId());
        assertThat(auditableAreaDTO1).isEqualTo(auditableAreaDTO2);
        auditableAreaDTO2.setId(2L);
        assertThat(auditableAreaDTO1).isNotEqualTo(auditableAreaDTO2);
        auditableAreaDTO1.setId(null);
        assertThat(auditableAreaDTO1).isNotEqualTo(auditableAreaDTO2);
    }
}
