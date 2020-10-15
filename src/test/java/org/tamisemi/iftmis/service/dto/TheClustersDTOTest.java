package org.tamisemi.iftmis.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class TheClustersDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TheClustersDTO.class);
        TheClustersDTO theClustersDTO1 = new TheClustersDTO();
        theClustersDTO1.setId(1L);
        TheClustersDTO theClustersDTO2 = new TheClustersDTO();
        assertThat(theClustersDTO1).isNotEqualTo(theClustersDTO2);
        theClustersDTO2.setId(theClustersDTO1.getId());
        assertThat(theClustersDTO1).isEqualTo(theClustersDTO2);
        theClustersDTO2.setId(2L);
        assertThat(theClustersDTO1).isNotEqualTo(theClustersDTO2);
        theClustersDTO1.setId(null);
        assertThat(theClustersDTO1).isNotEqualTo(theClustersDTO2);
    }
}
