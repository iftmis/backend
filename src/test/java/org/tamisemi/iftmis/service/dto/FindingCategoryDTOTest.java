package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingCategoryDTO.class);
        FindingCategoryDTO findingCategoryDTO1 = new FindingCategoryDTO();
        findingCategoryDTO1.setId(1L);
        FindingCategoryDTO findingCategoryDTO2 = new FindingCategoryDTO();
        assertThat(findingCategoryDTO1).isNotEqualTo(findingCategoryDTO2);
        findingCategoryDTO2.setId(findingCategoryDTO1.getId());
        assertThat(findingCategoryDTO1).isEqualTo(findingCategoryDTO2);
        findingCategoryDTO2.setId(2L);
        assertThat(findingCategoryDTO1).isNotEqualTo(findingCategoryDTO2);
        findingCategoryDTO1.setId(null);
        assertThat(findingCategoryDTO1).isNotEqualTo(findingCategoryDTO2);
    }
}
