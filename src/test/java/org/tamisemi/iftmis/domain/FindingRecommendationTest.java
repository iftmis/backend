package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingRecommendationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingRecommendation.class);
        FindingRecommendation findingRecommendation1 = new FindingRecommendation();
        findingRecommendation1.setId(1L);
        FindingRecommendation findingRecommendation2 = new FindingRecommendation();
        findingRecommendation2.setId(findingRecommendation1.getId());
        assertThat(findingRecommendation1).isEqualTo(findingRecommendation2);
        findingRecommendation2.setId(2L);
        assertThat(findingRecommendation1).isNotEqualTo(findingRecommendation2);
        findingRecommendation1.setId(null);
        assertThat(findingRecommendation1).isNotEqualTo(findingRecommendation2);
    }
}
