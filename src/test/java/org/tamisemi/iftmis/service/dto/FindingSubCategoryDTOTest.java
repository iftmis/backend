package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingSubCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingSubCategoryDTO.class);
        FindingSubCategoryDTO findingSubCategoryDTO1 = new FindingSubCategoryDTO();
        findingSubCategoryDTO1.setId(1L);
        FindingSubCategoryDTO findingSubCategoryDTO2 = new FindingSubCategoryDTO();
        assertThat(findingSubCategoryDTO1).isNotEqualTo(findingSubCategoryDTO2);
        findingSubCategoryDTO2.setId(findingSubCategoryDTO1.getId());
        assertThat(findingSubCategoryDTO1).isEqualTo(findingSubCategoryDTO2);
        findingSubCategoryDTO2.setId(2L);
        assertThat(findingSubCategoryDTO1).isNotEqualTo(findingSubCategoryDTO2);
        findingSubCategoryDTO1.setId(null);
        assertThat(findingSubCategoryDTO1).isNotEqualTo(findingSubCategoryDTO2);
    }
}
