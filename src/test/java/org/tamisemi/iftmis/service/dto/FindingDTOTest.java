package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingDTO.class);
        FindingDTO findingDTO1 = new FindingDTO();
        findingDTO1.setId(1L);
        FindingDTO findingDTO2 = new FindingDTO();
        assertThat(findingDTO1).isNotEqualTo(findingDTO2);
        findingDTO2.setId(findingDTO1.getId());
        assertThat(findingDTO1).isEqualTo(findingDTO2);
        findingDTO2.setId(2L);
        assertThat(findingDTO1).isNotEqualTo(findingDTO2);
        findingDTO1.setId(null);
        assertThat(findingDTO1).isNotEqualTo(findingDTO2);
    }
}
