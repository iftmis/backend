package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingSubCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingSubCategory.class);
        FindingSubCategory findingSubCategory1 = new FindingSubCategory();
        findingSubCategory1.setId(1L);
        FindingSubCategory findingSubCategory2 = new FindingSubCategory();
        findingSubCategory2.setId(findingSubCategory1.getId());
        assertThat(findingSubCategory1).isEqualTo(findingSubCategory2);
        findingSubCategory2.setId(2L);
        assertThat(findingSubCategory1).isNotEqualTo(findingSubCategory2);
        findingSubCategory1.setId(null);
        assertThat(findingSubCategory1).isNotEqualTo(findingSubCategory2);
    }
}
