package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class SubAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubArea.class);
        SubArea subArea1 = new SubArea();
        subArea1.setId(1L);
        SubArea subArea2 = new SubArea();
        subArea2.setId(subArea1.getId());
        assertThat(subArea1).isEqualTo(subArea2);
        subArea2.setId(2L);
        assertThat(subArea1).isNotEqualTo(subArea2);
        subArea1.setId(null);
        assertThat(subArea1).isNotEqualTo(subArea2);
    }
}
