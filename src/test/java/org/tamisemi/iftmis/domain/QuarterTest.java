package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class QuarterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quarter.class);
        Quarter quarter1 = new Quarter();
        quarter1.setId(1L);
        Quarter quarter2 = new Quarter();
        quarter2.setId(quarter1.getId());
        assertThat(quarter1).isEqualTo(quarter2);
        quarter2.setId(2L);
        assertThat(quarter1).isNotEqualTo(quarter2);
        quarter1.setId(null);
        assertThat(quarter1).isNotEqualTo(quarter2);
    }
}
