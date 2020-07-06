package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionRecommendationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionRecommendationDTO.class);
        InspectionRecommendationDTO inspectionRecommendationDTO1 = new InspectionRecommendationDTO();
        inspectionRecommendationDTO1.setId(1L);
        InspectionRecommendationDTO inspectionRecommendationDTO2 = new InspectionRecommendationDTO();
        assertThat(inspectionRecommendationDTO1).isNotEqualTo(inspectionRecommendationDTO2);
        inspectionRecommendationDTO2.setId(inspectionRecommendationDTO1.getId());
        assertThat(inspectionRecommendationDTO1).isEqualTo(inspectionRecommendationDTO2);
        inspectionRecommendationDTO2.setId(2L);
        assertThat(inspectionRecommendationDTO1).isNotEqualTo(inspectionRecommendationDTO2);
        inspectionRecommendationDTO1.setId(null);
        assertThat(inspectionRecommendationDTO1).isNotEqualTo(inspectionRecommendationDTO2);
    }
}
