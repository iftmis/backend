package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingCategory.class);
        FindingCategory findingCategory1 = new FindingCategory();
        findingCategory1.setId(1L);
        FindingCategory findingCategory2 = new FindingCategory();
        findingCategory2.setId(findingCategory1.getId());
        assertThat(findingCategory1).isEqualTo(findingCategory2);
        findingCategory2.setId(2L);
        assertThat(findingCategory1).isNotEqualTo(findingCategory2);
        findingCategory1.setId(null);
        assertThat(findingCategory1).isNotEqualTo(findingCategory2);
    }
}
