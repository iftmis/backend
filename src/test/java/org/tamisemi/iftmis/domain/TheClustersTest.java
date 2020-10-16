package org.tamisemi.iftmis.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class TheClustersTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TheClusters.class);
        TheClusters theClusters1 = new TheClusters();
        theClusters1.setId(1L);
        TheClusters theClusters2 = new TheClusters();
        theClusters2.setId(theClusters1.getId());
        assertThat(theClusters1).isEqualTo(theClusters2);
        theClusters2.setId(2L);
        assertThat(theClusters1).isNotEqualTo(theClusters2);
        theClusters1.setId(null);
        assertThat(theClusters1).isNotEqualTo(theClusters2);
    }
}
