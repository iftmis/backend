package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class GfsCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GfsCode.class);
        GfsCode gfsCode1 = new GfsCode();
        gfsCode1.setId(1L);
        GfsCode gfsCode2 = new GfsCode();
        gfsCode2.setId(gfsCode1.getId());
        assertThat(gfsCode1).isEqualTo(gfsCode2);
        gfsCode2.setId(2L);
        assertThat(gfsCode1).isNotEqualTo(gfsCode2);
        gfsCode1.setId(null);
        assertThat(gfsCode1).isNotEqualTo(gfsCode2);
    }
}
