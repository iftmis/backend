package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingRecommendationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingRecommendationDTO.class);
        FindingRecommendationDTO findingRecommendationDTO1 = new FindingRecommendationDTO();
        findingRecommendationDTO1.setId(1L);
        FindingRecommendationDTO findingRecommendationDTO2 = new FindingRecommendationDTO();
        assertThat(findingRecommendationDTO1).isNotEqualTo(findingRecommendationDTO2);
        findingRecommendationDTO2.setId(findingRecommendationDTO1.getId());
        assertThat(findingRecommendationDTO1).isEqualTo(findingRecommendationDTO2);
        findingRecommendationDTO2.setId(2L);
        assertThat(findingRecommendationDTO1).isNotEqualTo(findingRecommendationDTO2);
        findingRecommendationDTO1.setId(null);
        assertThat(findingRecommendationDTO1).isNotEqualTo(findingRecommendationDTO2);
    }
}
