package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class AuditableAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditableArea.class);
        AuditableArea auditableArea1 = new AuditableArea();
        auditableArea1.setId(1L);
        AuditableArea auditableArea2 = new AuditableArea();
        auditableArea2.setId(auditableArea1.getId());
        assertThat(auditableArea1).isEqualTo(auditableArea2);
        auditableArea2.setId(2L);
        assertThat(auditableArea1).isNotEqualTo(auditableArea2);
        auditableArea1.setId(null);
        assertThat(auditableArea1).isNotEqualTo(auditableArea2);
    }
}
